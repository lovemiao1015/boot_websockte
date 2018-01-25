<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>WebSocket Logger</title>
    <script src="/show/static/jquery.min.js  "></script>
    <script src="/show/static/sockjs.min.js"></script>
    <script src="/show/static/stomp.min.js"></script>
</head>
<body>
<button onclick="openSocket()">开启日志</button><button onclick="closeSocket()">关闭日志</button>
<div id="log-container" style="height: 450px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;color: yellow">
    <div id="ss"></div>
</div>
</body>
<script>
    var stompClient = null;
    $(document).ready(function() {openSocket();});
    function openSocket() {
        if(stompClient==null){
            var socket = new SockJS('http://106.14.248.249:8080/websocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function() {
                stompClient.subscribe('/topic/pullLogger', function(event) {
                    console.log("1111111111111111111");
                    $("#ss").innerHTML(event);
//                    var content=JSON.parse(event.body);
//                    $("#log-container div").append(content.timestamp +" "+ content.level+" --- ["+ content.threadName+"] "+ content.className+"   :"+content.body).append("<br/>");
//                    $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
                });
            });
        }
    }
    function closeSocket() {
        if (stompClient != null) {
            stompClient.disconnect();
            stompClient=null;
        }
    }
</script>
</body>
</html>