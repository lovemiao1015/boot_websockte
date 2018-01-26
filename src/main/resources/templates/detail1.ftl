<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        html,body{
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<body>
    <div id="div" style='width: 100%;max-height: 100%'></div>
</body>
<script>
    var id = ${id};
    if(id == "8"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/1.png'/>";
    }
    if(id == "88"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/2.png'/>";
    }
    if(id == "888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/3.png'/>";
    }
    if(id == "8888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/4.png'/>";
    }
    if(id == "88888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/5.png'/>";
    }
    if(id == "888888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/6.png'/>";
    }

</script>
</html>