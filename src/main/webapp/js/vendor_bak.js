$(function() {

    var sigmaInstance;

    $('#refreshBtn').click(function() {
        refreshGraph();
    });

    $('#analysisBtn').click(function(e) {
        var color = '#666',
            graphOption = {
                nodes: [],
                edges: []
            };
        for (var i = data.vertexs.length - 1; i >= 0; i--) {
            var size = data.vertexs[i].weight,
                x = Math.random(),
                y = Math.random();
            if (data.vertexs[i].value === data.clueWord) {
                color = '#bd2c00';
                size = 3;
                x = 0;
                y = 0;
            }

            graphOption.nodes.push({
                id: data.vertexs[i].value,
                label: data.vertexs[i].value + '&' + Number(size).toFixed(2),
                x: x,
                y: y,
                size: size,
                color: color
            });
        }

        for (var i = data.edges.length - 1; i >= 0; i--) {
            graphOption.edges.push({
                id: 'e' + i,
                source: data.edges[i].source,
                target: data.edges[i].target,
                size: data.edges[i].weight,
                color: '#ccc',
                hover_color: '#000'
            });
        }

        generateGraph(graphOption);
    });

    function refreshGraph() {
        if (sigmaInstance) {
            // 这个地方莫名其妙啊
            // 要执行两次才能消除图像
            sigmaInstance.refresh();
            sigmaInstance.graph.clear();

            sigmaInstance.refresh();
            sigmaInstance.graph.clear();
        }
    }

    function generateGraph(graphOption) {
        refreshGraph();

        sigmaInstance = new sigma({
            graph: graphOption,
            renderer: {
                container: document.getElementById('graph-container'),
                type: 'canvas'
            },
            settings: {
                minEdgeSize: 0.5,
                maxEdgeSize: 4,
                enableEdgeHovering: true,
                edgeHoverColor: 'edge',
                defaultEdgeHoverColor: '#000',
                edgeHoverSizeRatio: 1,
                edgeHoverExtremities: true,
            }
        });
        sigma.plugins.dragNodes(sigmaInstance, sigmaInstance.renderers[0]);
    }
});
