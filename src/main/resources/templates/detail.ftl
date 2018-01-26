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
    <div id="div" style="width: 100%;height: 100%"></div>
</body>
<script>
    var id = ${id};
    if(id == "8"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/314000969996821152.jpg'/>";
    }
    if(id == "88"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/306988309118476567.jpg'/>";
    }
    if(id == "888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/410848741924613343.jpg'/>";
    }
    if(id == "8888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/321177401576167262.jpg'/>";
    }
    if(id == "88888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/565868527742192577.jpg'/>";
    }
    if(id == "888888"){
        document.getElementById("div").innerHTML = "<img style='width: 100%;max-height: 100%' src='${springMacroRequestContext.contextPath}/static/images/518280257336566421.jpg'/>";
    }

</script>
</html>