<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>路径生成</title>
</head>

<body>
<div id="pathList">
    路径正在生成中...
</div>
<script src="node_modules/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
    var QueryString = function () {
        // This function is anonymous, is executed immediately and
        // the return value is assigned to QueryString!
        var query_string = {};
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            // If first entry with this name
            if (typeof query_string[pair[0]] === "undefined") {
                query_string[pair[0]] = decodeURIComponent(pair[1]);
                // If second entry with this name
            } else if (typeof query_string[pair[0]] === "string") {
                var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
                query_string[pair[0]] = arr;
                // If third or later entry with this name
            } else {
                query_string[pair[0]].push(decodeURIComponent(pair[1]));
            }
        }
        return query_string;
    }();

    $(function () {
        loadData();
    });

    function loadData() {
        var query = QueryString['query'];
        var url = 'api/paths/' + QueryString['topicId'];
        $.get(url, {
            query: query
        }, function (res) {
            console.log(res);
            if (res && res.statusCode == 0) {
                var data = res.data;
                console.log(data);
                var li = '';
                for (var i = 0; i < data.length; i++) {
                    var content = '';
                    for (var j = 0; j < data[i].length; j++) {
                        var t = data[i][j];
                        console.log(t);
                        if (j != data[i].length - 1) {
                            content += t.similarity + '-' + t.name + '->';
                        } else {
                            content += t.similarity + '-' + t.name;
                        }
                    }
                    li += '<li>' + content + '</li>';
                }
                console.log(li);
                $('#pathList').html('').append(li);
            } else if (res.statusCode == 2004) {
                $('#pathList').html('没有数据');
            }
        });
    }

    function getChildren(line) {
        for (var i = 0; i < line.length; i++) {
            if (line.length > 1) {
                return {
                    'name': line.shift(),
                    children: [getChildren(line)]
                };
            } else if (line.length == 1) {
                return {
                    'name': line.shift()
                };
            }
        }
    }


</script>
</body>

</html>
