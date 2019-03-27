package com.travnich.account.list;

import java.util.ArrayList;

public class MyArrayList<T> extends ArrayList<T> {

    public MyArrayList subListWithRef(int startIndex, int endIndex) {
        MyArrayList<T> result = new MyArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            result.add(this.get(i));
        }
        return result;
    }

}
