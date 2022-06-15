package ru.nsu.alowator.core;

import ru.nsu.alowator.core.entities.*;
import ru.nsu.alowator.core.frame.Frame;
import ru.nsu.alowator.core.frame.Status;

import java.util.Collections;

public class Game {
    private final View view;
    private final Grid grid;
    private final Snake player;
    private final Enemies enemies;
    private final int winSize;
    private Status status;

    public Game(View view, int rowCount, int colCount, int foodCount, int wallsCount, int winSize, int enemiesCount) {
        this.grid = new Grid(rowCount, colCount, foodCount, wallsCount);
        this.player = new Snake(grid);
        this.enemies = new Enemies(grid, enemiesCount);

        this.winSize = winSize;
        this.view = view;
        this.status = Status.PLAYING;
    }

    public void nextFrame() {
        if (status != Status.PLAYING)
            return;

        // Player's snake logic
        player.move();
        checkFoodCollision(player);
        checkPlayerWallCollision(player);
        checkPlayerSelfCollision(player);
        checkKill(player, enemies);
        checkCut(player, enemies);
        checkPlayerSize(player);

        // Enemies snakes logic
        enemies.updateStrategy(player);
        enemies.stream().forEach(Snake::move);
        checkKill(player, enemies);
        checkCut(player, enemies);
        enemies.stream().forEach(this::checkFoodCollision);
        enemies.stream().forEach(this::checkEnemyWallCollision);
        enemies.stream().forEach(this::checkEnemySelfCollision);
        enemies.stream().forEach(this::checkPlayerCollision);
        enemies.stream().forEach(enemy -> checkKill(enemy, enemies));
        enemies.stream().forEach(enemy -> checkCut(enemy, enemies));

        Frame frame = new Frame(grid, player, enemies.stream().toList(), status);
        view.updateFrame(frame);
    }

    public void turn(Direction direction) {
        player.turn(direction);
    }

    private void gameOver() {
        status = Status.LOSE;
    }

    private void gameWin() {
        status = Status.WIN;
    }

    private void checkFoodCollision(Snake snake) {
        Cell head = snake.head();
        if (head.getType() == Cell.Type.FOOD) {
            snake.grow();
            grid.exchangeCellType(head.getRow(), head.getCol(), Cell.Type.EMPTY);
            grid.addFood(1);
        }
    }

    private void checkPlayerWallCollision(Snake player) {
        if (player.head().getType() == Cell.Type.WALL)
            gameOver();
    }

    private void checkEnemyWallCollision(Snake enemy) {
        if (enemy.head().getType() == Cell.Type.WALL)
            enemies.remove(enemy);
    }

    private void checkPlayerSelfCollision(Snake player) {
        int headOccurrences = Collections.frequency(player.stream().toList(), player.head());
        if (headOccurrences > 1)
            gameOver();
    }

    private void checkEnemySelfCollision(Snake enemy) {
        long headOccurrences = enemy.stream().filter(cell -> cell.isIntersect(enemy.head())).count();
        if (headOccurrences > 1)
            enemies.remove(enemy);
    }

    private void checkKill(Snake snake, Enemies enemies) {
        enemies.stream().filter(enemy -> enemy.head().isIntersect(snake.head()) && enemy != snake).forEach(enemies::remove);
    }

    private void checkCut(Snake snake, Enemies enemies) {
        Cell head = snake.head();
        enemies.stream().filter(enemy -> enemy.stream().anyMatch(head::isIntersect) && enemy.head() != head).forEach(enemy -> enemy.cut(head));
    }

    private void checkPlayerSize(Snake player) {
        if (player.size() >= winSize)
            gameWin();
    }

    private void checkPlayerCollision(Snake enemy) {
        Cell head = enemy.head();
        boolean isIntersect = player.stream().anyMatch(cell -> head.isIntersect(cell) && !player.head().isIntersect(cell));
        if (isIntersect)
            player.cut(head);
    }
}
