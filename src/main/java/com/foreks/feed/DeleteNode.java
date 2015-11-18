package com.foreks.feed;

public class DeleteNode<T> {
    MyTreeSetImpl<T> parent            = null;
    MyTreeSetImpl<T> changeNode        = null;
    MyTreeSetImpl<T> replecementParent = null;
    boolean          right             = false, left = false;

    public boolean deleteNode(MyTreeSetImpl<T> node, final T value) {
        if (node == null) {
            return false;
        }
        if (node.comparator.compare(node.getValue(), value) == 0) {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                if (this.parent != null) {
                    if (this.right == true) {
                        this.parent.setRightChild(this.changeNode);
                    } else {
                        this.parent.setLeftChild(this.changeNode);
                    }

                } else {
                    node = this.changeNode;
                }
                return true;
            }
            if (null != node.getLeftChild() && null != node.getRightChild()) {
                node.setValue(minNodeValue(node));
                return true;
            }
            if (node.getLeftChild() != null) {
                this.parent.setLeftChild(node.getLeftChild());
                node = this.changeNode;
                return true;

            }
            if (node.getRightChild() != null) {
                this.parent.setRightChild(node.getRightChild());
                node = this.changeNode;
                return true;
            }
        }
        this.parent = node;
        if (node.comparator.compare(node.getValue(), value) > 0) {
            this.left = true;
            this.right = false;
            return deleteNode((MyTreeSetImpl<T>) node.getLeftChild(), value);
        }
        if (node.comparator.compare(node.getValue(), value) < 0) {
            this.right = true;
            this.left = false;
            return deleteNode((MyTreeSetImpl<T>) node.getRightChild(), value);
        }
        return false;
    }

    private T minNodeValue(MyTreeSet<T> node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            final T value = node.getValue();
            if (this.replecementParent != null) {
                this.replecementParent.setLeftChild(this.changeNode);
            } else {
                node = this.changeNode;
            }
            return value;
        }
        if (node.getLeftChild() == null && node.getRightChild() != null) {
            final T value = node.getValue();
            if (this.replecementParent != null) {
                this.replecementParent.setLeftChild(node.getRightChild());
            } else {
                node = node.getRightChild();
            }
            return value;
        }
        this.replecementParent = (MyTreeSetImpl<T>) node;
        return minNodeValue(node.getLeftChild());
    }
}
