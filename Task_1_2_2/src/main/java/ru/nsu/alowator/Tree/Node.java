package ru.nsu.alowator.Tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private final T value;

    private Node<T> parent = null;
    private final List<Node<T>> children;

    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node<T> node) {
        children.add(node);
        node.parent = this;
    }

    public void remove() {
        if (this.parent != null) {
            for (Node<T> child : children) {
                this.parent.addChild(child);
            }
        }
    }

    public T getValue() {
        return value;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}
