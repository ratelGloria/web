$(function(){
    var selMain = document.getElementById("selMain");
    var selMinor = document.getElementById("selMinor");

/*$("#selMinor").click(function(){
    console.log("7777777777777777777777")
})*/
    var xmlHttpRequest = new XMLHttpRequest;
    xmlHttpRequest.open("get","/onlyCategoryServlet?cate_parent_id=all",true);

    xmlHttpRequest.onreadystatechange = function(){

        if(xmlHttpRequest.status === 200 && xmlHttpRequest.readyState === 4)
        {
            var message = xmlHttpRequest.responseText;
            var s = eval(message);
            for(var i = 0; i<s.length;i++){
                var op = document.createElement("option");
                var text = document.createTextNode(s[i].cate_name);
                console.log("pppppppppppp"+text);
                selMain.appendChild(op);
                op.appendChild(text);
                op.value = s[i].cate_id;
            }
        }
    };
    xmlHttpRequest.send();

    $("#selMinor").click(function(){

        var num1 = selMain.selectedIndex;

        var cate_parent_id = $("#selMain option:eq("+num1+")").val();

        var xmlHttpRequest = new XMLHttpRequest;
        xmlHttpRequest.open("get","/onlyCategoryServlet?cate_parent_id="+cate_parent_id,true);

        xmlHttpRequest.onreadystatechange = function(){

            if(xmlHttpRequest.status === 200 && xmlHttpRequest.readyState === 4)
            {
                var message = xmlHttpRequest.responseText;
                var s = eval(message);
                $("#selMinor option").remove()

                for(var i = 0; i<s.length;i++){
                    var op = document.createElement("option");
                    var text = document.createTextNode(s[i].cate_name);
                    console.log("pppppppppppp"+text);
                    selMain.appendChild(op);
                    op.appendChild(text);
                    op.value = s[i].cate_id;
                    $("#selMinor").append(op)
                }
            }

        };
        xmlHttpRequest.send();

    })



    $("#likeQueryCategory").click(function(){

        var num = selMinor.selectedIndex;
        var num1 = selMain.selectedIndex;

        var cate_id = $("#selMinor option:eq("+num+")").val();
        var cate_parent_id = $("#selMain option:eq("+num1+")").val();
        console.log("num1:"+num1)
        console.log("cate_name:"+cate_id)
        console.log("cate_parent_id:"+cate_parent_id)
        var xmlHttpRequest3 = new XMLHttpRequest();
        if(typeof (cate_id) == "undefined" && typeof (cate_parent_id) == "undefined"){
            xmlHttpRequest3.open("get","/onlyCategoryServlet",true);
        }else if(typeof (cate_id) == "undefined" && typeof (cate_parent_id) != "undefined"){
            xmlHttpRequest3.open("get","/onlyCategoryServlet?&cate_parent_id="+cate_parent_id,true);
        }else if(typeof (cate_id) != "undefined" && typeof (cate_parent_id) != "undefined"){
            xmlHttpRequest3.open("get","/onlyCategoryServlet?cate_id="+cate_id+"&cate_parent_id="+cate_parent_id,true);
        }


        xmlHttpRequest3.onreadystatechange = function(){

            if(xmlHttpRequest3.readyState === 4 && xmlHttpRequest3.status === 200) {
                $("#resultContent_c tr").remove()
               /* $("#resultContent_c tr:gt(0)").remove()*/
                var message3 = xmlHttpRequest3.responseText;

                var s3 = eval(message3);
                console.log("s3"+s3)

             /*   var tr1 = document.createElement("tr");
                $("#resultContent").append(tr1)
                var text11 = document.createTextNode("类别编号");
                var text21 = document.createTextNode("类别名称");
                var text31 = document.createTextNode("所属类别");
                var name = new Array(text11,text21,text31)
                for(var c = 0;c<3;c++){
                    $("#resultContent tr:eq("+0+") td:eq("+c+")").append(name[c])
                }*/
                for (var i = 0; i < s3.length; i++) {
                    var tr = document.createElement("tr");

                    for(var j = 0;j<8;j++){
                        var td = document.createElement("td");
                        $(td).css("width","227px")
                        tr.appendChild(td);
                        $("#resultContent_c").append(tr)
                    }
                    var text1 = document.createTextNode(s3[i].cate_id);
                    var text2 = document.createTextNode(s3[i].cate_name);
                    var text3 = document.createTextNode(s3[i].cate_parent_id);
                    var a = document.createElement("a");
                    var a1 = document.createElement("a");

                    var text = new Array(text1,text2,text3)

                    console.log("11111111111111"+s3[i].cate_id)
                    console.log("222222222"+text2)
                    console.log("3333333"+text3)

                    for(var c = 0;c<4;c++){

                        if(c==3){
                            var text8 = $("<input type=\"button\" value=\"修改\">" );
                            var text9= $("<input type=\"button\" value=\"删除\">" );
                            $(a).attr("href","/deleteCategoryServlet?cate_id="+s3[i].cate_id)
                            $(a1).attr("href","/deleteCategoryServlet?cate_id="+s3[i].cate_id)
                            $(a).append(text8)
                            $(a1).append(text9)

                         /*   $("#resultContent_c tr:eq("+i+") td:eq("+c+")").append(a)*/
                            $("#resultContent_c tr:eq("+i+") td:eq("+c+")").append(a1)
                        }else{
                            $("#resultContent_c tr:eq("+i+") td:eq("+c+")").append(text[c])
                        }

                    }
                    $("#resultContent_c tr td").css("width","600px")
                    $("#resultContent_c").css("width","1800px")
                }
            }

        }
        xmlHttpRequest3.send();
    })
})


