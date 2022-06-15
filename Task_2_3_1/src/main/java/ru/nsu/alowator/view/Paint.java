package ru.nsu.alowator.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import ru.nsu.alowator.core.View;
import ru.nsu.alowator.core.frame.Frame;
import ru.nsu.alowator.core.entities.Snake;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Paint implements View {
    private final Canvas canvas;
    private final GraphicsContext context;
    private final int cellSize = 18;

    private final Color backgroundColor = Color.BLANCHEDALMOND;
    private final Color snakeBodyColor = Color.GREENYELLOW;
    private final Color snakeHeadColor = Color.GREEN;
    private final Color wallColor = Color.BLACK;
    private final Color foodColor = Color.ORANGE;
    private final int initialRandomSeed;

    public Paint(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.initialRandomSeed = ThreadLocalRandom.current().nextInt();
    }

    @Override
    public void updateFrame(Frame gameFrame) {
        canvas.setWidth(cellSize * gameFrame.grid().getColCount());
        canvas.setHeight(cellSize * gameFrame.grid().getRowCount());
        gameFrame.grid().stream().forEach(cell -> {
            switch (cell.getType()) {
                case EMPTY -> drawCell(cell.getCol(), cell.getRow(), backgroundColor);
                case FOOD -> drawCell(cell.getCol(), cell.getRow(), foodColor);
                case WALL -> drawCell(cell.getCol(), cell.getRow(), wallColor);
            }
        });

        gameFrame.snake().stream().forEach(cell -> drawCell(cell.getCol(), cell.getRow(), snakeBodyColor));
        drawCell(gameFrame.snake().head().getCol(), gameFrame.snake().head().getRow(), snakeHeadColor);

        gameFrame.enemies().forEach(enemy -> {
            Color enemySnakeColor = getColor(enemy);
            Color enemySnakeBodyColor = enemySnakeColor.brighter();
            enemy.stream().forEach(cell -> drawCell(cell.getCol(), cell.getRow(), enemySnakeBodyColor));
            drawCell(enemy.head().getCol(), enemy.head().getRow(), enemySnakeColor);
        });

        switch (gameFrame.status()) {
            case WIN -> gameWin();
            case LOSE -> gameOver();
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    private Color getColor(Snake snake) {
        Random random = new Random(snake.hashCode() + initialRandomSeed);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }

    private void gameOver() {
        context.setFont(new Font(48));
        context.setFill(Color.RED);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("Game Over!", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private void gameWin() {
        context.setFont(new Font(48));
        context.setFill(Color.GREEN);
        context.setTextAlign(TextAlignment.CENTER);
        context.fillText("You WIN!", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    private void drawCell(int col, int row, Color color) {
        context.setFill(color);
        context.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
    }
}
