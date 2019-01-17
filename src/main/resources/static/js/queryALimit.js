$(function(){

    function pageN(page){

        var shop_id = $("#shop_id").val()
        var shop_name = $("#shop_name").val()
        var shop_des =$("#shop_des").val()
        var choosePage = page
        var num = selMinor.selectedIndex;
        var num1 = selMain.selectedIndex;

        var cate_id = $("#selMinor option:eq("+num+")").val();
        var cate_parent_id = $("#selMain option:eq("+num1+")").val();

        var xmlHttpRequest3 = new XMLHttpRequest();

        if(isNaN(cate_id)){
            xmlHttpRequest3.open("get","/queryShopCondition?shop_id="+shop_id+"&shop_name="+shop_name+"&shop_des="+shop_des+"&selMain="+cate_parent_id+"&selMinor=&choosePage="+choosePage,true);

        }else{
            xmlHttpRequest3.open("get","/queryShopCondition?shop_id="+shop_id+"&shop_name="+shop_name+"&shop_des="+shop_des+"&selMain=&selMinor="+cate_id+"&choosePage="+choosePage,true);

        }
        $("#page").text()
        $("#page").html(choosePage)

        xmlHttpRequest3.onreadystatechange = function(){
            if(xmlHttpRequest3.readyState === 4 && xmlHttpRequest3.status === 200) {
                $("#resultContent tr").remove()
                $("#resultContent tr td").remove()
                $("#limpage li:gt(0)").remove()
                var message3 = xmlHttpRequest3.responseText;

                var messResult = incision(message3)

                var s3 = eval(messResult[1]);
                var s2 = eval(messResult[0]);

                for (var i = 0; i < s3.length; i++) {
                    var tr = document.createElement("tr");
                    for(var j = 0;j<9;j++){
                        var td = document.createElement("td");
                        tr.appendChild(td);
                        $("#resultContent").append(tr)
                    }
                    var img = document.createElement("img");
                    var a = document.createElement("a");
                    var a1 = document.createElement("a");

                    var text1 = document.createTextNode(s3[i].shop_id);
                    var text2 = document.createTextNode(s3[i].shop_name);
                    var text3 = document.createTextNode(s3[i].shop_des);
                    var text4 = document.createTextNode(s3[i].shop_img);
                    var text5 = document.createTextNode(s3[i].shop_price);
                    var text6 = document.createTextNode(s3[i].shop_cate_id);
                    var text7 = document.createTextNode(s3[i].shop_stock);

                    var text = new Array(text1,text2,text3,text4,text5,text6,text7)

                    for(var c = 0;c<9;c++){

                        if(c == 3){
                            $(img).attr("src","http://192.168.118.1:8080/photo/"+s3[i].shop_img)

                            $(img).css({
                                "width":"50px",
                                "height":"50px"
                            })
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(img)
                        }else if( c == 7){
                            var text8 = $("<input type=\"button\" value=\"修改\">" );
                            var text9= $("<input type=\"button\" value=\"删除\">" );
                            $(a).attr("href","/toChangeShopServlet?shop_id="+s3[i].shop_id+"&shop_cate_id="+s3[i].shop_cate_id)
                            $(a1).attr("href","/deleteShopServlet?shop_id="+s3[i].shop_id+"&shop_cate_id="+s3[i].shop_cate_id)
                            $(a).append(text8)
                            $(a1).append(text9)

                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(a)
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(a1)
                        }else{
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(text[c])
                        }
                    }
                }
                var allPage = s2[s2.length-1]

                $("#allPage").html(allPage)
                var allData = s3[s3.length-1]
                if(allData == 0 ){
                    $("#allData").html(1)
                    $("#limpage li:gt(0)").remove()
                }else {
                    $("#limpage li:gt(0)").remove()
                    for(var i = 0;i<s2.length;i++){
                        var num = i+1;
                        var jiedian = $(" <li class = \"ye\"><a href=\"#\" class=\"pagination numPage\" id = \"nomargin\">"+num+"</a></li>")
                        $("#limpage").append(jiedian)
                    }
                }
            }
            var right = $("<li><a href=\"#\" aria-label=\"Next\" id = \"downpage\">\n" + "  <span aria-hidden=\"true\">&raquo;</span>\n" + " </a></li>")
            $("#limpage").append(right)
        }

        xmlHttpRequest3.send();
    }

    function incision (message){

        var messIncision = message.split("][")

        for(var i = 0;i<messIncision.length;i++){
            if(i%2==0){
                messIncision[i] = messIncision[i]+"]"
            }else{
                messIncision[i] = "["+messIncision[i]
            }
        }
        return messIncision
    }

    $("#likeQueryShop").click(function(){

        var shop_id_j = $("#shop_id").val()
        var shop_name_j = $("#shop_name").val()
        var shop_des_j =$("#shop_des").val()
        var choosePage_j = $("#pageButton").html()
        var num = selMinor.selectedIndex;
        var num1 = selMain.selectedIndex;

        var cate_id = $("#selMinor option:eq("+num+")").val();
        var cate_parent_id = $("#selMain option:eq("+num1+")").val();

        $("#page").text(1)


        var xmlHttpRequest3 = new XMLHttpRequest();


        if(isNaN(cate_id)){
            xmlHttpRequest3.open("get","/queryShopCondition?shop_id="+shop_id_j+"&shop_name="+shop_name_j+"&shop_des="+shop_des_j+"&selMain="+cate_parent_id+"&selMinor=&choosePage=1",true);

        }else{
            xmlHttpRequest3.open("get","/queryShopCondition?shop_id="+shop_id_j+"&shop_name="+shop_name_j+"&shop_des="+shop_des_j+"&selMain=&selMinor="+cate_id+"&choosePage=1",true);

        }

        xmlHttpRequest3.onreadystatechange = function(){
            if(xmlHttpRequest3.readyState === 4 && xmlHttpRequest3.status === 200) {
                $("#resultContent tr").remove()
                $("#resultContent tr td").remove()
                $("#limpage li:gt(0)").remove()

                var message3 = xmlHttpRequest3.responseText;

                var messResult = incision(message3)

                var s3 = eval(messResult[1]);
                var s2 = eval(messResult[0]);

                for (var i = 0; i < s3.length; i++) {
                    var tr = document.createElement("tr");
                    for(var j = 0;j<9;j++){
                        var td = document.createElement("td");
                        tr.appendChild(td);
                        $("#resultContent").append(tr)
                    }
                    var img = document.createElement("img");
                    var a = document.createElement("a");
                    var a1 = document.createElement("a");
                    var text1 = document.createTextNode(s3[i].shop_id);
                    var text2 = document.createTextNode(s3[i].shop_name);
                    var text3 = document.createTextNode(s3[i].shop_des);
                    var text4 = document.createTextNode(s3[i].shop_img);
                    var text5 = document.createTextNode(s3[i].shop_price);
                    var text6 = document.createTextNode(s3[i].shop_cate_id);
                    var text7 = document.createTextNode(s3[i].shop_stock);


                    var text = new Array(text1,text2,text3,text4,text5,text6,text7)

                    for(var c = 0;c<9;c++){

                        if(c == 3){
                            $(img).attr("src","http://192.168.118.1:8080/photo/"+s3[i].shop_img)

                            $(img).css({
                                "width":"50px",
                                "height":"50px"
                            })
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(img)
                        }else if( c == 7){
                            var text8 = $("<input type=\"button\" value=\"修改\">" );
                            var text9= $("<input type=\"button\" value=\"删除\">" );
                            $(a).attr("href","/toChangeShopServlet?shop_id="+s3[i].shop_id+"&shop_cate_id="+s3[i].shop_cate_id)
                            $(a1).attr("href","/deleteShopServlet?shop_id="+s3[i].shop_id+"&shop_cate_id="+s3[i].shop_cate_id)
                            $(a).append(text8)
                            $(a1).append(text9)

                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(a)
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(a1)
                        }else{
                            $("#resultContent tr:eq("+i+") td:eq("+c+")").append(text[c])
                        }
                    }
                }

                var allData = s3[s3.length-1]
                if(allData == 0 ){
                    $("#allData").html(1)
                    $("#limpage li:gt(0)").remove()
                }else {
                    $("#allData").html(allData)
                    $("#limpage li:gt(0)").remove()
                    for(var i = 0;i<s2.length-1;i++){

                        var num = i+1;

                        var jiedian = $(" <li class = \"ye\"><a href=\"#\" class=\"pagination numPage\" id = \"nomargin\">"+num+"</a></li>")
                        $("#limpage").append(jiedian)
                    }
                }
            }
            var right = $("<li><a href=\"#\" aria-label=\"Next\" id = \"downpage\">\n" + "  <span aria-hidden=\"true\">&raquo;</span>\n" + " </a></li>")
            $("#limpage").append(right)
        }
        xmlHttpRequest3.send();
    })

    $("#limpage").on("click","#uppage",function(){
        var page = $("#page").text()

        var pageNum = page-1;

        pageN(pageNum);

    })

    $("#limpage").on("click","#downpage",function(){
        var page = $("#page").text()

        var pageNum = parseInt(page)+1;

        pageN(pageNum);
    })
    $("#limpage").on("click",".numPage",function() {

        var page = $(this).text()
        console.log("-------"+page)
        pageN(page)

    })
})