package com.zc.model;

/**
 * Created by 张镇强 on 2016/7/5 15:36.
 */
public class EdgeModel {
    private String source;
    private String target;
    private double weight;

    public EdgeModel(String source, String target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
