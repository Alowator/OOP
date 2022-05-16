package ru.nsu.alowator.core;

import java.util.concurrent.TimeUnit;

public class Game implements Runnable {
    private final int DEFAULT_RAW_COUNT = 20;
    private final int DEFAULT_COL_COUNT = 30;
    private final int DEFAULT_FOOD_COUNT = 30;
    private final int DEFAULT_WALLS_COUNT = 5;
    private final int WIN_SIZE = 100;

    private final GameWatcher watcher;
    private final Grid grid;
    private final Snake snake;

    private final Thread gameThread;

    public Game(GameWatcher watcher) {
        this.grid = new Grid(DEFAULT_RAW_COUNT, DEFAULT_COL_COUNT, DEFAULT_FOOD_COUNT, DEFAULT_WALLS_COUNT);
        this.snake = new Snake(new Cell(2, 2));
        this.watcher = watcher;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastFrameUpdateTime = 0;
        long lastSnakeMoveTime = 0;
        while (true) {
            if (System.currentTimeMillis() - lastFrameUpdateTime >= 20) {
                lastFrameUpdateTime = System.currentTimeMillis();
                watcher.updateFrame(grid, snake);
            }
            if (System.currentTimeMillis() - lastSnakeMoveTime >= 100) {
                lastSnakeMoveTime = System.currentTimeMillis();
                snake.move(grid.getRowCount(), grid.getColCount());
                checkFoodCollision();
                checkSnakeCollision();
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
        watcher.updateFrame(grid, snake);
        watcher.gameOver();
        gameThread.interrupt();
    }

    private void gameWin() {
        watcher.updateFrame(grid, snake);
        watcher.gameWin();
        gameThread.interrupt();
    }

    private void checkFoodCollision() {
        Cell currentCell = grid.getCell(snake.head().getRow(), snake.head().getCol());
        if (currentCell.getType() == Cell.Type.FOOD) {
            snake.grow();
            grid.exchangeCellType(currentCell.getRow(), currentCell.getCol(), Cell.Type.EMPTY);
            grid.addFood(1);
        }
    }

    private void checkSnakeCollision() {
        Cell headCell = snake.head();
        snake.stream().forEach(bodyCell -> {
            if (headCell.isIntersect(bodyCell) && bodyCell != snake.head()) {
                gameOver();
            }
        });
    }

    private void checkSnakeSize() {
        if (snake.size() >= WIN_SIZE) {
            gameWin();
        }
    }
}
