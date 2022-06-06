package ru.nsu.alowator.core;

import ru.nsu.alowator.core.snake.Snake;

import java.util.*;
import java.util.stream.Stream;

public class Enemies {
    private Grid grid;
    private List<Snake> snakes;

    public Enemies(Grid grid, int snakesCount) {
        this.grid = grid;
        this.snakes = new ArrayList<>();
        for (int i = 0; i < snakesCount; i++) {
            this.snakes.add(new Snake(grid));
        }
    }

    public void updateStrategy() {
        if (snakes.isEmpty())
            return;

        Cell sourceCell = new Cell(-1, -1);
        Cell drainCell = new Cell(-1, -1);
    }

    private void maxFlow(Cell source, Cell drain) {
        Map<Cell, Cell> F = new HashMap<>();
        Queue<Cell> que = new ArrayDeque<>();

        while (true) {
            // part 1: BFS
            Map<Cell, Boolean> vis = new HashMap<>();
            vis.put(source, true);
            que.add(source);
            while (!que.isEmpty()) {
                Cell u = que.remove();
                Stream.concat(grid.stream(), Stream.of(source, drain)).forEach(v -> {
                    if ( capacity(u, v) - flow(u, v) > 0 ) {
                        if ( !vis.containsKey(v) ) {
                            vis.put(v, true);
                            que.add(v);
                            F.put(v, u);
                        }
                    }
                });
            }

            if ( !vis.containsKey(drain) )
                break;

            // part 2: how much?
            int CP = Integer.MAX_VALUE;
            Cell x = drain;
            while (x != source) {
                Cell y = F.get(x);
                CP = Math.min(CP, capacity(y, x) - flow(y, x));
                x = y;
            }

            // part 3: push flow
            x = drain;
            while ( x != source ) { // восстанавливаем путь P второй раз
                Cell y = F.get(x);
                changeFlow(y, x, CP);
                changeFlow(x, y, -CP);
                x = y;
            }
        }
    }

    private void changeFlow(Cell y, Cell x, int change) {
    }

    private int flow(Cell u, Cell v) {
        return 0;
    }

    private int capacity(Cell u, Cell v) {
        return 0;
    }

    public Stream<Snake> stream() {
        return snakes.stream();
    }
}
