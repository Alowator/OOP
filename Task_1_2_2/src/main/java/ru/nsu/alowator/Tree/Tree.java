package ru.nsu.alowator.Tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Tree<T> implements Collection<T> {

    private Node<T> root = null;
    private final Map<T, Node<T>> valueToNodeReflection;

    /**
     * Constructs an empty tree.
     */
    Tree() {
        valueToNodeReflection = new HashMap<>();
    }

    /**
     * Returns the number of elements in this tree.
     *
     * @return the number of elements in this tree
     */
    @Override
    public int size() {
        return valueToNodeReflection.size();
    }

    /**
     * Returns {@code true} if this tree contains no elements.
     *
     * @return {@code true} if this tree contains no elements
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns {@code true} if this tree contains the specified element.
     *
     * @param o element whose presence in this tree is to be tested
     * @return {@code true} if this tree contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        return valueToNodeReflection.containsKey(o);
    }

    /**
     * Returns {@code true} if this tree contains all elements of {@code Collection}.
     *
     * @param c {@code Collection} which elements presence in this tree are to be tested
     * @return {@code true} if this tree contains all specified elements
     */
    @Override
    public boolean containsAll(@NotNull Collection c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    /**
     * Returns subtree of this tree.
     *
     * @param baseElement root of the new tree
     * @return {@code null} if this tree contains no {@code baseElement}
     */
    public Tree<T> getSubtree(T baseElement) {
        if (!contains(baseElement)) {
            return null;
        }

        Tree<T> newTree = new Tree<>();

        Node<T> node = valueToNodeReflection.get(baseElement);
        NodeBfsIterator it = new NodeBfsIterator(node);
        while (it.hasNext()) {
            node = it.next();
            if (newTree.root == null) {
                newTree.add(node.getValue());
            }
            for (Node<T> child : node.getChildren()) {
                newTree.add(node.getValue(), child.getValue());
            }
        }

        return newTree;
    }

    /**
     * Appends the specified element to this tree.
     * If this tree {@code isEmpty()}, element will be root.
     * Else specified element will be son of the root.
     *
     * @param value element to be added to this tree
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(T value) {
        if (contains(value))
            return false;

        if (root == null) {
            root = new Node<>(value);
            valueToNodeReflection.put(value, root);
            return true;
        }
        addAfter(root, value);
        return true;
    }

    /**
     * Appends the specified element {@code value} to this tree after {@code parent} element.
     *
     * @param parent parent of element to be added to this tree
     * @param value element to be added to this tree
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public boolean add(T parent, T value) {
        if (contains(value) || !contains(parent))
            return false;

        Node<T> parentNote = valueToNodeReflection.get(parent);
        addAfter(parentNote, value);
        return true;
    }

    private void addAfter(@NotNull Node<T> node, T value) {
        Node<T> newNode = new Node<>(value);
        valueToNodeReflection.put(value, newNode);
        node.addChild(newNode);
    }

    /**
     * Appends all of the elements in the specified collection to
     * this tree in {@link Tree#add} order.
     *
     * @param c collection containing elements to be added to this tree
     * @return {@code true} if this tree changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isModified = false;
        for (T o : c) {
            isModified |= add(o);
        }
        return isModified;
    }

    /**
     * Removes the element.
     *
     * @param o element to be removed from this tree
     * @return {@code true} if this tree changed as a result of the call
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;

        Node<T> nodeToRemove = valueToNodeReflection.get(o);
        if (nodeToRemove == root) {
            if (!nodeToRemove.getChildren().isEmpty()) {
                Node<T> newRoot = nodeToRemove.getChildren().get(0);
                for (Node<T> node : nodeToRemove.getChildren()) {
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
        valueToNodeReflection.remove(o);
        return true;
    }

    /**
     * Removes all elements from the {@code Collection c}.
     *
     * @param c collection containing elements to be removed from this tree
     * @return {@code true} if this tree changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean removeAll(@NotNull Collection c) {
        boolean isModified = false;
        for (Object o : c) {
            isModified |= remove(o);
        }
        return isModified;
    }

    /**
     * Removes all of the elements from this tree. The tree will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        root = null;
        valueToNodeReflection.clear();
    }

    /**
     * Retains only the elements in this tree that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this tree
     * @return {@code true} if this tree changed as a result of the call
     */
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

    /**
     * Returns an array containing all of the elements in this tree in bfs order.
     *
     * @return an array containing the elements of the tree
     */
    @Override
    public Object[] toArray() {
        List<T> list = new ArrayList<>();
        for (T x : this) {
            list.add(x);
        }
        return list.toArray();
    }

    /**
     * Returns an array containing all of the elements in this tree in bfs order.
     * If the tre fits in the specified array,
     * it is returned therein.  Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of
     * this tree.
     *
     * @param a the array into which the elements of the tree are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the tree
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this tree
     * @throws NullPointerException if the specified array is null
     */
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

    /**
     * Returns an iterator over the elements in this tree (bfs order).
     *
     * @return an iterator over the elements in this tree (bfs order)
     */
    @Override
    public Iterator<T> iterator() {
        return new ValueBfsIterator();
    }

    private class ValueBfsIterator implements Iterator<T> {
        NodeBfsIterator it;

        ValueBfsIterator() {
            it = new NodeBfsIterator(root);
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next().getValue();
        }
    }

    private class NodeBfsIterator implements Iterator<Node<T>> {
        Deque<Node<T>> deque;

        NodeBfsIterator(Node<T> baseNode) {
            deque = new ArrayDeque<>();
            if (baseNode != null) {
                deque.addLast(baseNode);
            }
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node<T> next() {
            Node<T> node = deque.removeFirst();
            for (Node<T> child : node.getChildren()) {
                deque.addLast(child);
            }
            return node;
        }
    }

}
