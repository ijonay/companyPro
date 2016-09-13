package com.zc.model.path;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by 张镇强 on 2016/9/12 15:58.
 */
public class PathModel {
    private LinkedList<Node> nodes;
    private Set<Edge> edges;

    public PathModel(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public PathModel(LinkedList<Node> nodes, Set<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}
