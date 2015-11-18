package com.foreks.feed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class MyTreeSetImpl<T> extends AbstractTreeSet<T> {
    /**
     *
     */
    // Encapsulation
    private T                value;
    private MyTreeSetImpl<T> leftChild  = null;
    private MyTreeSetImpl<T> rightChild = null;
    private boolean          root       = false;
    Comparator<T>            comparator;

    public MyTreeSetImpl(final String strategy, final T value, final Comparator<T> comparator) {
        super(strategy);
        this.value = value;
        this.root = true;
        this.comparator = comparator;
    }

    public MyTreeSetImpl(final String strategy, final Comparator<T> comparator) {
        super(strategy);
        this.comparator = comparator;
    }

    private MyTreeSetImpl(final TreeTraversals<T> strategy, final T value, final Comparator<T> comparator) {
        super(strategy);
        this.value = value;
        this.root = true;
        this.comparator = comparator;
    }

    @Override
    public void add(final T value) {
        if (false == this.root) {
            this.value = value;
            this.root = true;
        } else if (this.comparator.compare(value, this.getValue()) <= 0) {
            if (this.getLeftChild() == null) {
                this.leftChild = new MyTreeSetImpl<T>(this.strategy, value, this.comparator);
            } else {
                this.getLeftChild().add(value);
            }
        } else {
            if (this.rightChild == null) {
                this.rightChild = new MyTreeSetImpl<T>(this.strategy, value, this.comparator);
            } else {
                this.rightChild.add(value);
            }
        }

    }

    public void setRoot(final boolean root) {
        this.root = root;
    }

    @Override
    public boolean contains(final T value) {
        MyTreeSetImpl<T> t = this;
        if (this.root == true) {
            while (t != null) {
                if (t.comparator.compare(value, t.getValue()) == 0) {
                    return true;
                } else if (this.comparator.compare(value, t.getValue()) < 0) {
                    t = (MyTreeSetImpl<T>) t.getLeftChild();
                } else {
                    t = (MyTreeSetImpl<T>) t.getRightChild();
                }
            }
        }
        return false;
    }

    @Override
    public void traverse(final Consumer<T> traversal) {
        this.strategy.traverse(this, traversal);
    }

    @Override
    public boolean remove(final T value) {
        final DeleteNode<T> del = new DeleteNode<T>();
        return del.deleteNode(this, value);
    }

    @Override
    public int size() {
        final ArrayList<T> list = new ArrayList<T>();
        this.traverse((i) -> {
            list.add(i);
        });
        return list.size();
    }

    @Override
    public ArrayList<T> toArray() {
        final ArrayList<T> list = new ArrayList<T>();
        this.traverse((i) -> {
            list.add(i);
        });
        return list;
    }

    @Override
    public String toString() {
        final StringBuilder sB = new StringBuilder();
        this.traverse(i -> sB.append(i.toString()));
        return sB.toString();
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean getRoot() {
        return this.root;
    }

    @Override
    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public MyTreeSet<T> getRightChild() {
        return this.rightChild;
    }

    @Override
    public void setRightChild(final MyTreeSet<T> rightChild) {
        this.rightChild = (MyTreeSetImpl<T>) rightChild;

    }

    @Override
    public MyTreeSet<T> getLeftChild() {
        return this.leftChild;
    }

    @Override
    public void setLeftChild(final MyTreeSet<T> leftChild) {
        this.leftChild = (MyTreeSetImpl<T>) leftChild;
    }

    @Override
    public void updateValue(final T value, final T value2) {
        remove(value);
        add(value2);
    }

}
