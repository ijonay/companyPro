$(function () {
    var layoutPadding = 50;
    var layoutDuration = 500;
    var dataArr = [],
        baseUrl = 'http://localhost:8088/api/';

    (function () {
        getModels();
        $('#analysisBtn').click(query);
    })();

    function getModels() {
        $.get(baseUrl + 'models', function (res) {
            if (res.code == 200 && res.data.length > 0) {
                var html = '';
                for (var i = res.data.length - 1; i >= 0; i--) {
                    res.data[i]
                    html += '<option value="' +
                        res.data[i].code + '">' +
                        res.data[i].name +
                        '</option> ';
                }

                $('#model').html(html);
            } else {
                alert(res.message);
            }
        });
    }

    function query() {
        var model = $('#model').val(),
            clueWord = $('#clueWord').val(),
            relevancy = $('#relevancy').val(),
            topN = $('#topN').val(),
            length = $('#length').val();

        if (!model || !clueWord || !relevancy || !topN || !length) {
            alert('请检查输入是否正确!');
            return false;
        }

        $.get(baseUrl + 'words/' + model, {
            clueWord: clueWord,
            relevancy: relevancy,
            topN: topN,
            length: length
        }, function (res) {
            if (res.code == 200) {
                generateGraph(res.data);
            } else {
                alert(res.message);
            }
        });
    }

    function generateGraph(data) {
        if (window.cy) {
            window.cy.$('node').remove();
        }

        if (data) {
            for (var i = 0; i < data.vertexs.length; i++) {
                var vModel = data.vertexs[i];
                if (vModel.value == data.clueWord) {
                    dataArr.push({
                        data: {
                            id: encodeURI(vModel.value),
                            name: vModel.value
                        },
                        selectable: true,
                        group: "nodes",
                    });
                } else {
                    dataArr.push({
                        data: {
                            id: encodeURI(vModel.value),
                            name: vModel.value
                        },
                        group: "nodes"
                    });
                }
            }
            for (var i = 0; i < data.edges.length; i++) {
                var eModel = data.edges[i];
                dataArr.push({
                    data: {
                        id: eModel.source + "-" + eModel.target,
                        source: encodeURI(eModel.source),
                        target: encodeURI(eModel.target)
                    },
                    group: "edges"
                });
            }
        }
        var cy = window.cy = cytoscape({
            container: document.getElementById('graph-container'),

            boxSelectionEnabled: false,
            //autounselectify: true,

            layout: {
                name: 'concentric',
                concentric: function (node) {
                    return node.degree();
                },
                levelWidth: function (nodes) {
                    return 2;
                }
            },

            style: [{
                selector: 'node',
                style: {
                    'content': 'data(name)',
                    'height': 30,
                    'width': 30,
                    'background-color': '#30c9bc'
                }
            }, {
                selector: "node[name='" + data.clueWord + "']",
                style: {
                    'content': 'data(name)',
                    'height': 50,
                    'width': 50,
                    'background-color': '#000000'
                }
            }, {
                selector: 'edge',
                style: {
                    'curve-style': 'haystack',
                    'haystack-radius': 0,
                    'width': 5,
                    'opacity': 0.5,
                    'line-color': '#a8eae5'
                }
            }],

            elements: dataArr
        });

        cy.on('select', 'node', function (e) {
            node = this;
            highlight(node);
        });

        function highlight(node) {
            var nhood = node.neighborhood();
            //var nhood = node.closedNeighborhood();

            cy.batch(function () {
                //cy.elements().not(nhood).removeClass('highlighted').addClass('faded');
                // nhood.removeClass('faded').addClass('highlighted');

                var npos = node.position();
                var w = window.innerWidth;
                var h = window.innerHeight;

                cy.stop().animate({
                    fit: {
                        eles: cy.elements(),
                        padding: layoutPadding
                    }
                }, {
                    duration: layoutDuration
                }).delay(layoutDuration, function () {
                    nhood.layout({
                        name: 'concentric',
                        padding: layoutPadding,
                        animate: true,
                        animationDuration: layoutDuration,
                        boundingBox: {
                            x1: npos.x - w / 2,
                            x2: npos.x + w / 2,
                            y1: npos.y - w / 2,
                            y2: npos.y + w / 2
                        },
                        fit: true,
                        concentric: function (n) {
                            if (node.id() === n.id()) {
                                return 2;
                            } else {
                                return 1;
                            }
                        },
                        levelWidth: function () {
                            return 1;
                        }
                    });
                });

            });
        }
    }
});
