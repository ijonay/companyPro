package com.zc.model.path;

/**
 * Created by 张镇强 on 2016/9/12 15:01.
 */
public class Edge {
    private String start;
    private String end;
    private float similarity;

    public Edge(String start, String end, float similarity) {
        this.start = start;
        this.end = end;
        this.similarity = similarity;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
}
