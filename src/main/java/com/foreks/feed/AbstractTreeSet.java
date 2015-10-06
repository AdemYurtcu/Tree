package com.foreks.feed;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractTreeSet<T> implements MyTreeSet<T> {

    /**
     *
     */

    protected TreeTraversals<T>                  strategy;
    private final TreeTraversals<T>              inOrder   = (final MyTreeSet<T> t, final Consumer<T> traverser) -> {
                                                               if (t.getRoot() == true) {
                                                                   Objects.requireNonNull(t);
                                                                   if (null != t.getLeftChild()) {
                                                                       this.inOrder.traverse(t.getLeftChild(), traverser);
                                                                   }
                                                                   traverser.accept(t.getValue());
                                                                   if (null != t.getRightChild()) {
                                                                       this.inOrder.traverse(t.getRightChild(), traverser);
                                                                   }
                                                               }
                                                           };

    private final TreeTraversals<T>              preOrder  = (final MyTreeSet<T> t, final Consumer<T> traverser) -> {
                                                               if (t.getRoot() == true) {
                                                                   Objects.requireNonNull(t);
                                                                   traverser.accept(t.getValue());
                                                                   if (null != t.getLeftChild()) {
                                                                       this.preOrder.traverse(t.getLeftChild(), traverser);
                                                                   }
                                                                   if (null != t.getRightChild()) {
                                                                       this.preOrder.traverse(t.getRightChild(), traverser);
                                                                   }
                                                               }
                                                           };
    private final TreeTraversals<T>              postOrder = (final MyTreeSet<T> t, final Consumer<T> traverser) -> {
                                                               if (t.getRoot() == true) {
                                                                   Objects.requireNonNull(t);

                                                                   if (null != t.getLeftChild()) {
                                                                       this.postOrder.traverse(t.getLeftChild(), traverser);
                                                                   }
                                                                   if (null != t.getRightChild()) {
                                                                       this.postOrder.traverse(t.getRightChild(), traverser);
                                                                   }
                                                                   traverser.accept(t.getValue());
                                                               }
                                                           };

    private final Map<String, TreeTraversals<T>> methodMap = new HashMap<String, TreeTraversals<T>>() {
                                                               private static final long serialVersionUID = -5803157130551074241L;

                                                               {
                                                                   put("inorder", AbstractTreeSet.this.inOrder);
                                                                   put("preorder", AbstractTreeSet.this.preOrder);
                                                                   put("postorder", AbstractTreeSet.this.postOrder);

                                                               }
                                                           };

    protected AbstractTreeSet(final TreeTraversals<T> strategy) {
        super();
        this.strategy = strategy;
    }

    protected AbstractTreeSet(final String strategy) {
        super();
        setStrategy(strategy);
    }

    @Override
    public void setStrategy(final String strategy) {
        this.strategy = this.methodMap.get(strategy);
    }

}
