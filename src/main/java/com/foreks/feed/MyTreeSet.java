package com.foreks.feed;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface MyTreeSet<T> {
    /*Eleman ekle*/

    /*Eleman var mi diye kontrol et*/
    public boolean contains(T el);

    /*Eleman sil başarılıysa true dön eleman bulunamadıysa false dön*/
    public boolean remove(T el);

    public void traverse(final Consumer<T> traversal);

    public MyTreeSet<T> getRightChild();

    public void setRightChild(final MyTreeSet<T> rightChild);

    public MyTreeSet<T> getLeftChild();

    public void setLeftChild(final MyTreeSet<T> rightChild);

    public void updateValue(T el, T el2);

    /*Boyutunu dön*/
    public int size();

    /*Elemanları ArrayList'e topla dön*/
    public ArrayList<T> toArray();

    public boolean getRoot();

    public T getValue();

    public void setValue(T value);

    /*Virgül seperatörü kullanarak her bir elemanı yaz örn: 1,2,357,2123,6,7*/
    @Override
    public String toString();

    void add(T value);

    void setStrategy(String strategy);

}
