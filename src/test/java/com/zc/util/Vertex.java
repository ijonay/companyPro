package com.zc.util;

import java.util.ArrayList;

public class Vertex {

    boolean wasVisited;
    public char label;
    ArrayList<Integer> allVisitedList;

    public void setAllVisitedList(ArrayList<Integer> allVisitedList) {
        this.allVisitedList = allVisitedList;
    }

    public ArrayList<Integer> getAllVisitedList() {
        return allVisitedList;
    }

    public boolean WasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public Vertex(char lab) // constructor
    {
        label = lab;
        wasVisited = false;
    }

    public void setVisited(int j) {
        allVisitedList.set(j, 1);

    }

}
