package ru.nsu.alowator.Tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Tree<T> implements Collection<T> {

    private Node root = null;
    private final Map<Object, Node> objectToNodeReflection;

    Tree() {
        objectToNodeReflection = new HashMap<>();
    }

    @Override
    public int size() {
        return objectToNodeReflection.size();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        return objectToNodeReflection.containsKey(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    @Override
    public boolean add(Object value) {
        if (contains(value))
            return false;

        if (root == null) {
            root = new Node(value);
            objectToNodeReflection.put(value, root);
            return true;
        }
        addAfter(root, value);
        return true;
    }

    public boolean add(Object parent, Object value) {
        if (contains(value) || !contains(parent))
            return false;

        Node parentNote = objectToNodeReflection.get(parent);
        addAfter(parentNote, value);
        return true;
    }

    private void addAfter(@NotNull Node node, Object value) {
        Node newNode = new Node(value);
        objectToNodeReflection.put(value, newNode);
        node.addChild(newNode);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isModified = false;
        for (Object o : c) {
            isModified |= add(o);
        }
        return isModified;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;

        Node nodeToRemove = objectToNodeReflection.get(o);
        if (nodeToRemove == root) {
            if (!nodeToRemove.getChildren().isEmpty()) {
                Node newRoot = nodeToRemove.getChildren().get(0);
                for (Node node : nodeToRemove.getChildren()) {
                    if (node == newRoot)
                        continue;
                    newRoot.addChild(node);
                }
                root = newRoot;
            } else {
                root = null;
            }
        }

        nodeToRemove.remove();
        objectToNodeReflection.remove(o);
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection c) {
        boolean isModified = false;
        for (Object o : c) {
            isModified |= remove(o);
        }
        return isModified;
    }

    @Override
    public void clear() {
        root = null;
        objectToNodeReflection.clear();
    }

    @Override
    public boolean retainAll(@NotNull Collection c) {
        boolean isModified = false;
        for (Object next : this) {
            if (!c.contains(next)) {
                isModified |= remove(next);
            }
        }
        return isModified;
    }

    @Override
    public Object[] toArray() {
        List<T> list = new ArrayList<>();
        for (T x : this) {
            list.add(x);
        }
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int size = size();
        T1[] array = (T1[]) toArray();
        if (a.length < size)
            // Return a new array of a's runtime type, but my contents:
            return array;
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public Iterator<T> iterator() {
        return new ObjectBfsIterator();
    }

    private class ObjectBfsIterator implements Iterator<T> {
        NodeBfsIterator it;

        ObjectBfsIterator() {
            it = new NodeBfsIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return (T) it.next().getObject();
        }
    }

    private class NodeBfsIterator implements Iterator<Node> {
        Deque<Node> deque;

        NodeBfsIterator() {
            deque = new ArrayDeque<>();
            deque.addLast(root);
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node next() {
            Node node = deque.removeFirst();
            for (Node child : node.getChildren()) {
                deque.addLast(child);
            }
            return node;
        }
    }

}
