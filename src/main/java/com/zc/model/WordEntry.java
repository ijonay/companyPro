package com.zc.model;

public class WordEntry implements Comparable<WordEntry> {
    public String name;
    public float score;
    //    public int length;
    public float weight;

    public WordEntry(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public WordEntry(String name, float score, float weight) {
        this(name, score);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.name + "\t" + this.score + "\t" + this.weight;
    }

    public int compareTo(WordEntry o) {
        if (this.score > o.score) {
            return -1;
        } else {
            return 1;
        }
    }

}
