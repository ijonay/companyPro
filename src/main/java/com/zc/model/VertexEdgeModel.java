package com.zc.model;

import java.util.List;

/**
 * Created by 张镇强 on 2016/7/5 15:35.
 */
public class VertexEdgeModel {
    private String clueWord;
    private List<VertexModel> vertexs;
    private List<EdgeModel> edges;

    public VertexEdgeModel(String clueWord, List<VertexModel> vertexs, List<EdgeModel> edges) {
        this.clueWord = clueWord;
        this.vertexs = vertexs;
        this.edges = edges;
    }

    public String getClueWord() {
        return clueWord;
    }

    public void setClueWord(String clueWord) {
        this.clueWord = clueWord;
    }

    public List<VertexModel> getVertexs() {
        return vertexs;
    }

    public void setVertexs(List<VertexModel> vertexs) {
        this.vertexs = vertexs;
    }

    public List<EdgeModel> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeModel> edges) {
        this.edges = edges;
    }
}
