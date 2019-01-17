$(function(){

    $(".inner_ul").css("display","none")

    $(".out_li").click(function(){
        console.log("11111111111")
        $(this).next(".inner_ul").toggle()
        console.log("22222222")
    })
    $(".out_li").click(function(){

        $(this).find(".inner_ul").toggle()
    })
    $("#queryShop").click(function(){
        $("iframe").attr("src","page/queryShop.jsp")
    })
    $("#addShop").click(function(){
        $("iframe").attr("src","page/addShop.jsp")
    })
    $("#queryCategory").click(function(){
        $("iframe").attr("src","page/queryCategory.jsp")
    })
    $("#addCategory").click(function(){
        $("iframe").attr("src","page/addCategory.jsp")
    })
    $("#ShoppingCart").click(function(){
        $("iframe").attr("src","frontPage/nSalePage.jsp")
    })
    $("#untreatedShoppingCart").click(function(){
        $("iframe").attr("src","page/untreatedShoppingCart.jsp")
    })

    $(".out_li").on({
        mouseover:function(){
            $(this).css({
                "cursor": "pointer",
                "box-shadow":"2px 2px 5px #06034c"
            })
        },
        mouseout:function(){
            $(this).css({
                "box-shadow":""
            })
        }, click:function(){
            $(".out_li").children("a").css("background-color", "#f2f2f2")
            $(this).children("a").css({
                "background-color": "#d7d5d5"
            })

            $(this).next().toggle()
            $(".inner_ul").css("background-color","white")
        }
    })

    $("#shop_stock").blur(function(){
        if($("#shop_stock").val()<0){
            $("#s_p_hint").html("非法数据")
            $("#s_p_hint").css("color","red")
            /*$("#shop_stock").attr("placeholder","非法数据")*/
        }else if($("#shop_stock").val()>0){
            $("#s_p_hint").html("")
        }
    })

    $(".inner_ul li").on({
        mouseover:function(){
            $(this).css({
                "cursor": "pointer",
                "box-shadow":"2px 2px 5px #06034c"
            })
        },
        mouseout:function(){
            $(this).css({
                "box-shadow":""
            })
        },
        click:function(){
            $(".inner_ul li").children("a").css("background-color", "#f2f2f2")
            $(this).children("a").css({
                "background-color": "#d7d5d5"
            })
            window.event.cancelBubble = true;
           /* $(".out_li").css("box-shadow","")*/
        }
    })
console.log("$(\"#hint\").html()"+typeof ($("#hint").text())+"0000"+$("#hint").text())


    $("td").on("mouseover","#addBut",function(){
        console.log("$(\"#hint\").text() == \"格式错误\" || $(\"#hint\").text()==\"\")"+ $("#hint").text()=="")
        console.log("$(\"#s_p_hint\").html() == \"非法数据\""+$("#hint").text() == "格式错误" )
        console.log($("#s_p_hint").html() == "非法数据")
console.log("2222"+$("#hint").text())
        console.log("2222"+$("#hint").html())
       /* if(($("#hint").text() == "格式错误" || $("#hint").text()=="")==true||$(("#s_p_hint").html() == "非法数据")==true){*/
        if($("#hint").text() == "格式错误" ){
            console.log("格式错误jinyongggg")
            $("#addBut").attr("disabled","true")
            $("#addBut").css("color","gray")
        }/*else if( $("#hint").text()==""){
            console.log("#hintjinyongggg")
            $("#addBut").attr("disabled","true")
            $("#addBut").css("color","gray")
        }*/else if($("#s_p_hint").html() == "非法数据"){
            console.log("#s_p_hintjinyongggg")
            $("#addBut").attr("disabled","true")
            $("#addBut").css("color","gray")
        }else{
            $("#addBut").css("color","black")
            $("#addBut").removeAttr("disabled")
            console.log("j11111111111")
        }
    })

    $("#shop_img").change(function(){
        var r= new FileReader();
        f=document.getElementById('shop_img').files[0];
        fname = document.getElementById("shop_img").value
      /*  console.log("path"+fname)
        console.log("ffff"+f)*/
        r.readAsDataURL(f);
        r.onload=function (e) {
            document.getElementById('show').src=this.result;
            console.log("路径"+this.result);
        };
    })



    $("#shop_img").focus(function(){

        console.log("wwwwwwwww")
        $("#addBut").css("color","black")

        $("#addBut").removeAttr("disabled")
    })
    $("#shop_stock").focus(function(){
        console.log("wwwwwwwww")
        $("#addBut").css("color","black")

        $("#addBut").removeAttr("disabled")
    })
$("#queren").blur(function(){

    var ee = "密码正确"
    $("#queren_1").append(ee)
})

    $(".tc").click(function(){
        console.log("xxxxxxxxxxxxxxxxxxxxxxxxxx")
        var xmlHttpRequest3 = new XMLHttpRequest();
        console.log("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm")
        xmlHttpRequest3.open("get","/tcServlet",true);

        xmlHttpRequest3.onreadystatechange = function(){
            if(xmlHttpRequest3.readyState === 4 && xmlHttpRequest3.status === 200) {


                /* var title = $("<tr><td>商品编号</td><td>商品名称</td><td>商品描述</td><td>图片</td><td>价格</td><td>所属子类</td><td>库存</td><td></td></tr>")
                 $("#resultContent").append(title)*/
            }
        }
        xmlHttpRequest3.send();
    })



})