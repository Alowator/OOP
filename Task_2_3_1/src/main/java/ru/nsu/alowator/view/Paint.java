package ru.nsu.alowator.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import ru.nsu.alowator.core.GameWatcher;
import ru.nsu.alowator.core.Grid;
import ru.nsu.alowator.core.snake.Snake;

import java.util.List;

public class Paint implements GameWatcher {
    private final Canvas canvas;
    private final GraphicsContext context;
    private final int cellSize = 18;

    private final Color backgroundColor = Color.BLANCHEDALMOND;
    private final Color snakeBodyColor = Color.GREENYELLOW;
    private final Color snakeHeadColor = Color.GREEN;
    private final Color enemyBodyColor = Color.INDIANRED;
    private final Color enemyHeadColor = Color.RED;
    private final Color wallColor = Color.BLACK;
    private final Color foodColor = Color.ORANGE;

    public Paint(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    @Override
    public void updateFrame(Grid grid, Snake snake, List<Snake> enemies) {
        synchronized (canvas) {
            canvas.setWidth(cellSize * grid.getColCount());
            canvas.setHeight(cellSize * grid.getRowCount());

            grid.stream().forEach(cell -> {
                switch (cell.getType()) {
                    case EMPTY -> drawCell(cell.getCol(), cell.getRow(), backgroundColor);
                    case FOOD -> drawCell(cell.getCol(), cell.getRow(), foodColor);
                    case WALL -> drawCell(cell.getCol(), cell.getRow(), wallColor);
                }
            });

            snake.stream().forEach(cell -> drawCell(cell.getCol(), cell.getRow(), snakeBodyColor));
            drawCell(snake.head().getCol(), snake.head().getRow(), snakeHeadColor);

            enemies.forEach(enemy -> {
                enemy.stream().forEach(cell -> drawCell(cell.getCol(), cell.getRow(), enemyBodyColor));
                drawCell(enemy.head().getCol(), enemy.head().getRow(), enemyHeadColor);
            });
        }
    }

    @Override
    public void gameOver() {
        context.setFont(new Font(48));
        context.setFill(Color.RED);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("Game Over!", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    @Override
    public void gameWin() {
        context.setFont(new Font(48));
        context.setFill(Color.GREEN);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("You WIN!", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private void drawCell(int col, int row, Color color) {
        context.setFill(color);
        context.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
    }

    public int getCellSize() {
        return cellSize;
    }
}
