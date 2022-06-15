package ru.nsu.alowator.core.frame;

import ru.nsu.alowator.core.entities.Grid;
import ru.nsu.alowator.core.entities.Snake;

import java.util.List;

public record Frame(Grid grid, Snake snake, List<Snake> enemies, Status status) {}
