package com.zc.model;

/**
 * Created by 张镇强 on 2016/7/5 16:33.
 */
public class VertexModel {
    private String value;
    /**
     * 该点到线索点的权重和
     */
    private double weight;

    public VertexModel(String value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
