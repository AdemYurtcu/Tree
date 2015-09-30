package org.gradle;

import java.util.function.Consumer;

@FunctionalInterface
public interface TreeTraversals<T> {
    public void traverse(final MyTreeSet<T> t, final Consumer<T> traverser);
}
