package com.foreks.feed;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {
    MyTreeSet<Integer> tree;

    @Before
    public void init() {

        this.tree = new MyTreeSetImpl<Integer>("inorder", Comparator.naturalOrder());

    }

    @Test
    public void canRemove() {
        addSampleElements();
        assertTrue("agac 10 icermiyor", this.tree.contains(10));
        assertTrue("10 silinmedi", this.tree.remove(10));
        assertFalse("agacta hala 10 var", this.tree.contains(10));

    }

    public void addStringElements() {
        this.tree.add(10);
        this.tree.add(20);
        this.tree.add(5);
        this.tree.add(18);
    }

    private void addSampleElements() {
        this.tree.add(10);

    }

}
