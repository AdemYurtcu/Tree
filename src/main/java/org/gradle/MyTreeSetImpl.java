package org.gradle;

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
        boolean check = false;
        MyTreeSetImpl<T> parent = null;
        MyTreeSetImpl<T> t = this;
        MyTreeSetImpl<T> replacement;
        MyTreeSetImpl<T> replacementParent = null;
        if (this.root == true) {
            while (check != true && null != t) {
                if (t.comparator.compare(value, t.getValue()) < 0) {
                    parent = t;
                    t = (MyTreeSetImpl<T>) t.getLeftChild();
                } else if (t.comparator.compare(value, t.getValue()) > 0) {
                    parent = t;
                    t = (MyTreeSetImpl<T>) t.getRightChild();
                } else {
                    check = true;
                }
            }
            if (check == true) {
                if (null == (MyTreeSetImpl<T>) t.getLeftChild() && null == (MyTreeSetImpl<T>) t.getRightChild()) {
                    if (null != parent) {
                        if (t.comparator.compare(t.getValue(), parent.getValue()) < 0) {
                            parent.setLeftChild(null);
                        } else {
                            parent.setRightChild(null);
                        }
                    } else {
                        this.root = false;
                    }

                    return true;
                }
                if (null != t.getLeftChild() && null != t.getRightChild()) {
                    replacement = (MyTreeSetImpl<T>) t.getLeftChild();

                    while (null != replacement.getRightChild()) {
                        replacementParent = replacement;
                        replacement = (MyTreeSetImpl<T>) replacement.getRightChild();
                    }
                    if (null != replacementParent) {
                        replacementParent.setRightChild(replacement.getLeftChild());
                        replacement.setRightChild(t.getRightChild());
                        replacement.setLeftChild(t.getLeftChild());

                    } else {

                        replacement.setRightChild(t.getRightChild());

                    }

                    if (null != parent) {
                        parent.setLeftChild(replacement);
                    } else {
                        this.setValue(replacement.getValue());
                        this.setLeftChild(replacement.getLeftChild());
                        this.setRightChild(replacement.getRightChild());
                    }
                    return true;
                } else {
                    if (null != t.getLeftChild()) {
                        if (null != parent) {
                            parent.setLeftChild(t.getLeftChild());
                        } else {
                            this.setValue(this.getLeftChild().getValue());
                            this.setLeftChild(this.getLeftChild().getLeftChild());
                        }
                        return true;
                    }
                    if (null != t.getRightChild()) {
                        if (null != parent) {
                            parent.setRightChild(t.getRightChild());
                        } else {
                            this.setValue(this.getRightChild().getValue());
                            this.setRightChild(this.getRightChild().getRightChild());
                        }
                        return true;
                    }
                }

            }
        }
        return false;
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
