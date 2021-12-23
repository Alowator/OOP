package ru.nsu.alowator.Tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final Object object;

    private Node parent = null;
    private final List<Node> children;

    public Node(Object object) {
        this.object = object;
        this.children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
        node.parent = this;
    }

    public void remove() {
        if (this.parent != null) {
            for (Node child : children) {
                this.parent.addChild(child);
            }
        }
    }

    public Object getObject() {
        return object;
    }

    public List<Node> getChildren() {
        return children;
    }
}
