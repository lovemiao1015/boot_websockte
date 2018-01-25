<!DOCTYPE html>
<head>
<#assign basePath="http://localhost:8004">
    <meta charset="utf-8">
    <title>WebSocket Logger</title>
    <#--<script src="/show/static/jquery.min.js  "></script>-->
    <#--<script src="/show/static/sockjs.min.js"></script>-->
    <#--<script src="/show/static/stomp.min.js"></script>-->
    <script src="${springMacroRequestContext.contextPath}/static/jquery.min.js  "></script>
    <script src="../static/sockjs.min.js"></script>
    <script src="${basePath}/static/stomp.min.js"></script>
</head>
<body style="background-color: #333333">
<button onclick="openSocket()">开启推送</button><button onclick="closeSocket()">关闭推送</button><input><button id="hh">发送消息</button>
<div id="ss" style="padding: 10px;color: yellow">
    <div id="ss"></div>
</div>
</body>
<script>


    var stompClient = null;
    $(document).ready(function() {openSocket();});
    function openSocket() {

        if(stompClient=== null){
            var socket = new SockJS('http://localhost:8004/websocket');

            /**
             * 建立成功的回调函数
             */
            socket.onopen = function() {
                console.log('open');
            };

            /**
             * 服务器有消息返回的回调函数
             */
            socket.onmessage = function(e) {
                console.log('message', e.data);
            };

            /**
             * websocket链接关闭的回调函数
             */
            socket.onclose = function() {
                console.log('close');
            };

            stompClient = Stomp.over(socket);
            stompClient.connect({}, function() {
                stompClient.subscribe('/topic/one', function(data) {
                    $("#ss").append(data.body).append("<br/>")
                });
            });
        }
    }

//    document.getElementById("hh").onclick = function() {
    //        var  name = $("input").val();
    //        stompClient.send("/msg", {}, JSON.stringify({
    //            name : name
    //        }));
    //    };

    setInterval(function(){
        $("#hh").val("随机数---"+(Math.random()*10).toFixed(0));
        stompClient.send("/app/msg", {},$("#hh").val());
    },1000);

    function closeSocket() {
        if (stompClient != null) {
            stompClient.disconnect();
            stompClient=null;
        }
    }
</script>
</body>
</html>