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
                if (this.parent != null) {
                    if (this.left == true) {
                        this.parent.setLeftChild(node.getLeftChild());
                    } else {
                        this.parent.setRightChild(node.getLeftChild());
                    }
                    return true;
                } else {
                    node.setValue(node.getLeftChild().getValue());
                    node.setLeftChild(this.changeNode);
                    return true;
                }

            }
            if (node.getRightChild() != null) {
                if (this.parent != null) {
                    if (this.right == true) {
                        this.parent.setRightChild(node.getRightChild());
                    } else {
                        this.parent.setLeftChild(node.getRightChild());
                    }
                    return true;
                } else {
                    node.setValue(node.getRightChild().getValue());
                    node.setRightChild(this.changeNode);
                    return true;
                }

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

    private T minNodeValue(final MyTreeSet<T> node) {
        if (node.getLeftChild() == null) {
            final T value = node.getValue();
            if (node.getRightChild() != null) {
                if (this.left == false) {
                    this.replecementParent.setRightChild(node.getRightChild());
                    return value;
                } else {
                    this.replecementParent.setLeftChild(node.getRightChild());
                    return value;
                }
            } else {
                if (this.left == false) {
                    this.replecementParent.setRightChild(this.changeNode);
                    return value;
                } else {
                    this.replecementParent.setLeftChild(this.changeNode);
                    return value;
                }
            }
        }
        this.replecementParent = (MyTreeSetImpl<T>) node;
        if (this.right == false) {
            this.right = true;
            return minNodeValue(node.getRightChild());
        } else {
            this.left = true;
            return minNodeValue(node.getLeftChild());
        }
    }
}
