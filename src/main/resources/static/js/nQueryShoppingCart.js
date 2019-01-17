$(function(){

        var xml2 = new XMLHttpRequest()
        xml2.open("get","/queryShoppingCart?userId=111",true)
        xml2.onreadystatechange = function(){

            if(xml2.status == 200 && xml2.readyState == 4){

                var message = xml2.responseText
                var s1 = eval(message)
                console.log("-------length"+s1.length)
                for(var i = 0;i<s1.length;i++){
                    var tr = document.createElement("tr");
                    for(var j = 0;j<9;j++){
                        var td = document.createElement("td");
                        tr.appendChild(td);
                        $("#resultContent").append(tr)
                    }
                    var img = document.createElement("img");
                    var a = document.createElement("a");
                    var a1 = document.createElement("a");

                    var text1 = document.createTextNode(s1[i].shop_id);
                    var text2 = document.createTextNode(s1[i].shop_name);
                    var text3 = document.createTextNode(s1[i].shop_des);
                    var text4 = document.createTextNode(s1[i].shop_img);
                    var text5 = document.createTextNode(s1[i].shop_price);
                    var text6 = document.createTextNode(s1[i].shop_cate_id);

                    var text = new Array(text1,text2,text3,text4,text5,text6)

                    for(var c = 0;c<7;c++){

                        if(c == 3){
                            $(img).attr("src","http://192.168.118.1:8080/photo/"+s1[i].shop_img)

                            $(img).css({
                                "width":"50px",
                                "height":"50px"
                            })
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(img)
                        }else if( c == 6){
                            var text8 = $("<input type=\"button\" value=\"修改\">" );
                            var text9= $("<input type=\"button\" value=\"删除\">" );
                            $(a).attr("href","/toChangeShopServlet?shop_id="+s1[i].shop_id+"&shop_cate_id="+s1[i].shop_cate_id)
                            $(a1).attr("href","/deleteShopServlet?shop_id="+s1[i].shop_id+"&shop_cate_id="+s1[i].shop_cate_id)
                            $(a).append(text8)
                            $(a1).append(text9);

                            var divAll = document.createElement("div");
                            $(divAll).attr("display","flex")
                            $(divAll).attr("class","numShop")
                            $(divAll).html(" <div class=\"numChoose\" id = \"minus\">-</div>\n" +
                                " <input type=\"text\" class = \"numShow\" id = \"numShow\" oninput=\"value=value.replace(/[^\\d]/g,'')\" value=\"1\">\n" +
                                "<div class=\"numChoose\" id = \"add\">+</div>")

                            $(".numShow").css({
                                "width": "30px",
                                "height": "30px",
                                "text-align": "center",
                                 "display": "inline-block",
                            })
                            $(".numChoose").css({
                                "display": "inline-block",
                            "border": "1px solid",
                            "height": "28px",
                            "width": "20px",
                            "text-align": "center",
                            "line-height": "28px",
                            "cursor": "pointer"
                            })

                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(divAll)

                        }else{
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(text[c])
                        }
                    }
                }
            }
        }
        xml2.send()


    $("#resultContent").on("click","#minus",function(){
        console.log("--------------")
        $("#numShow").val(parseInt($("#numShow").val())-1)
    })
    $("#resultContent").on("click","#add",function(){
         var parentAdd = $(this).parent
        console.log("+++++++++++++parentAdd"+parentAdd)
        $(parentAdd).children
        children( $("#numShow").val(parseInt($("#numShow").val())+1))
    })

    $("#add").click(function(){
        console.log("!!!!!!!!!!!!!!!")
        var parentAdd = $(this).parent
        console.log("+++++++++++++parentAdd"+parentAdd)
        parentAdd.children( $("#numShow").val(parseInt($("#numShow").val())+1))
    })


})