package ru.nsu.alowator.core.entities;

import java.util.*;
import java.util.stream.Stream;

public class Enemies {
    private final Grid grid;
    private final List<Snake> snakes;

    private int[][] capacity;
    private int[][] flow;
    private int[] path;

    public Enemies(Grid grid, int snakesCount) {
        this.grid = grid;
        this.snakes = new ArrayList<>();
        for (int i = 0; i < snakesCount; i++) {
            this.snakes.add(new Snake(grid));
        }
    }

    public void updateStrategy(Snake playerSnake) {
        if (snakes.isEmpty())
            return;

        int gridCellsCount = grid.getColCount() * grid.getRowCount();
        int totalCellsCount = gridCellsCount + 2;
        int sourceCellIndex = gridCellsCount;
        int drainCellIndex = gridCellsCount + 1;

        capacity = new int[totalCellsCount][totalCellsCount];
        flow = new int[totalCellsCount][totalCellsCount];
        path = new int[totalCellsCount];

        calculateCapacity(sourceCellIndex, drainCellIndex, playerSnake);
        maxFlow(sourceCellIndex, drainCellIndex, totalCellsCount);
        snakes.forEach(this::calculateTurn);
    }

    public void remove(Snake snake) {
        snakes.remove(snake);
    }

    public Stream<Snake> stream() {
        // Usage of toList is necessary because after snake remove shit happens
        return snakes.stream().toList().stream();
    }

    private void calculateCapacity(int sourceCellIndex, int drainCellIndex, Snake playerSnake) {
        snakes.forEach(snake -> {
            int headCellIndex = cellIndex(snake.head());
            capacity[sourceCellIndex][headCellIndex] = 1;
        });

        grid.stream().forEach(cell -> {
            switch (cell.getType()) {
                case EMPTY -> {
                    int emptyCellIndex = cellIndex(cell);
                    grid.adjacentCells(cell).forEach(adjacentCell -> {
                        if (adjacentCell.getType() != Cell.Type.WALL && !isSnakeCell(adjacentCell) && playerSnake.head() != adjacentCell) {
                            int adjacentCellIndex = cellIndex(adjacentCell);
                            capacity[emptyCellIndex][adjacentCellIndex] = 1;
                        }
                    });
                }
                case FOOD -> {
                    int foodCellIndex = cellIndex(cell);
                    capacity[foodCellIndex][drainCellIndex] = 1;
                }
            }
        });
    }

    private Direction direct(Cell from, Cell to) {
        if ((grid.getRowCount() + from.getRow() - 1) % grid.getRowCount() == to.getRow())
            return Direction.UP;
        else if ((from.getCol() + 1) % grid.getColCount() == to.getCol())
            return Direction.RIGHT;
        else if ((from.getRow() + 1) % grid.getRowCount() == to.getRow())
            return Direction.DOWN;
        else if ((grid.getColCount() + from.getCol() - 1) % grid.getColCount() == to.getCol())
            return Direction.LEFT;
        else
            throw new IllegalArgumentException("Unable to determine direction");
    }

    private boolean isSnakeCell(Cell cell) {
        return snakes.stream().anyMatch(snake -> snake.stream().anyMatch(snakeCell -> snakeCell.isIntersect(cell)));
    }

    private int cellIndex(Cell cell) {
        return cell.getRow() * grid.getColCount() + cell.getCol();
    }

    private void maxFlow(int s, int t, int n) {
        Queue<Integer> que = new ArrayDeque<>();

        while (true) {
            // part 1: BFS
            boolean[] vis = new boolean[n];
            vis[s] = true;

            que.add(s);
            while (!que.isEmpty()) {
                int u = que.remove();
                for (int v = 0; v < n; v++) {
                    if ( capacity[u][v] - flow[u][v] > 0 ) {
                        if ( !vis[v] ) {
                            vis[v] = true;
                            que.add(v);
                            path[v] = u;
                        }
                    }
                }
            }

            if ( !vis[t] )
                break;

            int CP = Integer.MAX_VALUE;
            int x = t;
            while ( x != s ) {
                int y = path[x];
                CP = Math.min(CP, capacity[y][x] - flow[y][x]);
                x = y;
            }

            x = t;
            while ( x != s ) {
                int y = path[x];
                flow[y][x] += CP;
                flow[x][y] -= CP;
                x = y;
            }
        }
    }

    private void calculateTurn(Snake snake) {
        int headCellIndex = cellIndex(snake.head());
        grid.adjacentCells(snake.head()).forEach(cell -> {
            int cellIndex = cellIndex(cell);
            if (flow[headCellIndex][cellIndex] > 0)
                snake.turn(direct(snake.head(), cell));
        });
    }
}
