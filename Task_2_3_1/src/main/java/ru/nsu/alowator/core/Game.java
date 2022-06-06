package ru.nsu.alowator.core;

import ru.nsu.alowator.core.snake.Direction;
import ru.nsu.alowator.core.snake.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable {

    private final GameWatcher watcher;
    private final Grid grid;
    private final Snake snake;
    private final Enemies enemies;
    private final int winSize;

    private final Thread gameThread;

    public Game(GameWatcher watcher, int rowCount, int colCount, int foodCount, int wallsCount, int winSize, int enemiesCount) {
        this.grid = new Grid(rowCount, colCount, foodCount, wallsCount);
        this.snake = new Snake(grid);
        this.enemies = new Enemies(grid, enemiesCount);

        this.winSize = winSize;
        this.watcher = watcher;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastSnakeMoveTime = 0;
        while (true) {
            if (System.currentTimeMillis() - lastSnakeMoveTime >= 100) {
                lastSnakeMoveTime = System.currentTimeMillis();
                snake.move();
                enemies.updateStrategy();
                enemies.stream().forEach(Snake::move);
                watcher.updateFrame(grid, snake, enemies.stream().toList());
                checkFoodCollision();
                checkSnakeCollision();
                checkWallCollision();
                checkSnakeSize();
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void end() {
        gameThread.interrupt();
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void turn(Direction direction) {
        snake.turn(direction);
    }

    private void gameOver() {
        watcher.gameOver();
        gameThread.interrupt();
    }

    private void gameWin() {
        watcher.gameWin();
        gameThread.interrupt();
    }

    private void checkFoodCollision() {
        Cell headCell = snake.head();
        if (headCell.getType() == Cell.Type.FOOD) {
            snake.grow();
            grid.exchangeCellType(headCell.getRow(), headCell.getCol(), Cell.Type.EMPTY);
            grid.addFood(1);
        }
    }

    private void checkSnakeCollision() {
        Cell headCell = snake.head();
        snake.stream().forEach(bodyCell -> {
            System.out.println(bodyCell);
            if (bodyCell.isIntersect(headCell) && bodyCell != headCell) {
                gameOver();
            }
        });
    }

    private void checkWallCollision() {
        Cell headCell = snake.head();
        if (headCell.getType() == Cell.Type.WALL) {
            gameOver();
        }
    }

    private void checkSnakeSize() {
        if (snake.size() >= winSize) {
            gameWin();
        }
    }
}
