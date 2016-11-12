package com.zc.utility;

import java.util.ArrayList;

/**
 * 计数器
 * Created by xyzhuzhou on 2016/11/12 0012 12:44:32.
 */
public class Couter {

    public ArrayList<Long> temp = new ArrayList<>();

    public Couter(int i) {
        this.i = i;
    }

    public Couter() {

    }

    int i = 0;

    public void add() {
        i++;
    }

    public void subtract() {
        i--;
    }

    public int get() {
        return i;
    }

}
