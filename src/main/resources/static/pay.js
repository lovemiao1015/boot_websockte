var pay = function (id) {

    var postData = new Object();
    // var checkValue = getCheckValue("iCheck");
    var checkValue = "WX";

    postData.charSet = "UTF-8";
    postData.version = "VER000000002";
    postData.transType = "PURC";
    postData.orderNum = curentTime();
    var orderNum = curentTime();
    postData.orderAmount = 0.01;
    postData.goodsInfo = "thanks";
    postData.merReserve = 'test';
    postData.orderCurrency = "USD";
    postData.merID = '000000000000015';
    postData.acqID = "99020344";
    postData.paymentSchema = checkValue;
    postData.transTime = getTransTime();
    var transTime = getTransTime();
    //postData.code = "NATIVE";
    postData.signType = "sha256";
    //postData.merName="allpay";
    //postData.signType= "MD5";

    //postData.frontURL="http://115.28.142.180:8080/EStore/e-righten.html";
    //postData.backURL="http://115.28.142.180:8080/EStore/e-righten.html";

    postData.frontURL = "http://106.14.248.249:8080/show/show/"+id;
    postData.backURL = "http://106.14.248.249:8080/show/show/"+id;

    // postData.frontURL = "http://127.0.0.1/EStore/e-righten.html";
    // postData.backURL = "http://127.0.0.1/EStore/e-righten.html";

    //postData.merName = responseJson.merName;
    //postData.signature = sign(postData,"2f2c77e3718c47cfb47a89a6fbc9d361");

    var signature = sign(postData,"2f2c77e3718c47cfb47a89a6fbc9d361");
    alert(signature);
    // postData.signature="1d2c201ee5d3ecd77a11dbc9d1431304";

    var url = "https://testapi.allpayx.com/pay";
    // var url = "http://testapi.allpayx.com:8000/epayment/payment";
    // https://mchapi.allpayx.com/testjspay?acqID=99020344&bacURL=http://172.30.1.105:8080/jspayresult&charSet=UTF-8&frontURL=https://www.baidu.com&goodsInfo=test&merID=000000000000015&merName=allpay&merReserve=test&orderAmount=0.01&orderCurrency=CNY&orderNum=20180119171321teVZC&paymentSchema=WX&signType=MD5&signature=bbcf7aafb1412e099fc69a321751ea97&transTime=20180119171322&transType=PURC&version=VER000000002




    if (checkValue === "WX") {

        window.location.href="https://mchapi.allpayx.com/testjspay?acqID=99020344&backURL=http://106.14.248.249:8080/show/show/"+id+"&charSet=UTF-8&frontURL=http://106.14.248.249:8080/show/show/"+id+"&goodsInfo=thanks&merID=000000000000015&merReserve=test&orderAmount=0.01&orderCurrency=USD&signature="+signature+"&orderNum="+orderNum+"&paymentSchema=WX&signType=sha256&transTime="+transTime+"&transType=PURC&version=VER000000002";

        // window.open("https://www.allpayx.com/EStore/wx.html?postData=" + base64encode(JSON.stringify(postData)));


        //window.open ('weix.html','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no') ;
        //window.open("https://www.allpayx.com/EStore/wx.html?postData=" + base64encode(JSON.stringify(postData)));
    } else {
        // $.post(url, postData,function(data){
        //     console.info(data);
        //   });
        var turnForm = document.createElement("form");
        //一定要加入到body中！！
        document.body.appendChild(turnForm);
        turnForm.method = 'post';
        turnForm.action = url;
        turnForm.target = "_blank";

        //创建隐藏表单
        Object.keys(postData).sort().forEach(function (v) {
            if (postData[v] !== undefined) {
                var newElement = document.createElement("input");
                newElement.setAttribute("name", v);
                newElement.setAttribute("type", "hidden");
                newElement.setAttribute("value", postData[v]);
                turnForm.appendChild(newElement);

            } else {
                var newElement = document.createElement("input");
                newElement.setAttribute("name", v);
                newElement.setAttribute("type", "hidden");
                newElement.setAttribute("value", "");
                turnForm.appendChild(newElement);
            }
        });

        turnForm.submit();
    }

}

//签名
var sign = function (data, key) {
    var tempArr = [];
    Object.keys(data).sort().forEach(function (v) {
        if (data[v] === undefined) {
            tempArr.push(v + '=');
        } else {
            tempArr.push(v + '=' + data[v]);
        }
    });

    var plainText = tempArr.join('&') + key;
    alert(plainText);
    return hex_sha256(plainText);
};

function getTransTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒
    var ms = now.getMilliseconds();
    var clock = year + "";

    if (month < 10)
        clock += "0";

    clock += month + "";

    if (day < 10)
        clock += "0";

    clock += day + "";

    if (hh < 10)
        clock += "0";

    clock += hh + "";
    if (mm < 10) clock += '0';
    clock += mm + "";

    if (ss < 10) clock += '0';
    clock += ss;
    return (clock);
};

function curentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒
    var ms = now.getMilliseconds();
    var clock = year + "";

    if (month < 10)
        clock += "0";

    clock += month + "";

    if (day < 10)
        clock += "0";

    clock += day + "";

    if (hh < 10)
        clock += "0";

    clock += hh + "";
    if (mm < 10) clock += '0';
    clock += mm + "";

    if (ss < 10) clock += '0';
    clock += ss;
    clock += ms;
    return (clock);
};
