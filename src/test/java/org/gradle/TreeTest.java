package org.gradle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import com.foreks.feed.MyTreeSet;
import com.foreks.feed.MyTreeSetImpl;

public class TreeTest {
    MyTreeSet<Integer> tree;

    @Before
    public void init() {
        final Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(final Integer o1, final Integer o2) {
                return o1.hashCode() - o2.hashCode();
            }
        };
        this.tree = new MyTreeSetImpl<Integer>("inorder", comparator);

    }

    @Test
    public void canUpdateValue() {
        addSampleElements();
        this.tree.updateValue(60, 30);
        assertFalse("aðaçta hala 60 var", this.tree.contains(60));
    }

    @Test
    public void canAdd() {
        addSampleElements();
        checkIfSampleIsAdded();
        System.out.println(this.tree.toString());
    }

    @Test
    public void canRemove() {
        addSampleElements();
        assertTrue("aðaç 5 içermiyor", this.tree.contains(60));
        assertTrue("5 silinemedi", this.tree.remove(60));
        assertFalse("aðaçta hala 5 var", this.tree.contains(60));
    }

    public void addStringElements() {
        this.tree.add(10);
        this.tree.add(15);
        this.tree.add(25);
        this.tree.add(30);
        this.tree.add(29);
        this.tree.add(3);
    }

    @Test
    public void print() {
        addStringElements();
        System.out.println(this.tree.toString());
    }

    private void checkIfSampleIsAdded() {
        assertTrue(this.tree.contains(5));
        assertTrue(this.tree.contains(60));
        assertTrue(this.tree.contains(3));
        assertTrue(this.tree.contains(61));
    }

    private void addSampleElements() {
        this.tree.add(50);
        this.tree.add(5);
        this.tree.add(60);
        this.tree.add(3);
        this.tree.add(61);
        this.tree.add(6);
    }

    @Test
    public void canReturnSize() {
        addSampleElements();
        final int sizeTree = this.tree.size();
        System.out.println(sizeTree);
    }

}
