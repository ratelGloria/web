$(function(){

    var selMain = document.getElementById("selMain_c");
    var selMinor = document.getElementById("selMinor_c");
    var mo = document.getElementById("mo");

    var xmlHttpRequest = new XMLHttpRequest;
    xmlHttpRequest.open("get","/queryCategoryServlet?cate_parent_id=null",true);
    console.log("????????/"+xmlHttpRequest.readyState)
    xmlHttpRequest.onreadystatechange = function(){
        console.log("ooooooooo"+xmlHttpRequest.readyState)
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
    /* var xmlHttpRequest2 = new XMLHttpRequest;
     xmlHttpRequest2.open("get","/queryCategoryServlet",true);
     console.log("????????/"+xmlHttpRequest.readyState)
     xmlHttpRequest2.onreadystatechange = function(){
         console.log("ooooooooo"+xmlHttpRequest.readyState)
         if(xmlHttpRequest2.status === 200 && xmlHttpRequest2.readyState === 4)
         {
             var message = xmlHttpRequest2.responseText;
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
     xmlHttpRequest2.send();
 */
   /*  var selMainmo = $("#mo").val()
console.log($("#mo").val())

    var xmlHttpRequest = new XMLHttpRequest;
    xmlHttpRequest.open("get","/onlyCategoryServlet",true);

    xmlHttpRequest.onreadystatechange = function(){

        if(xmlHttpRequest.status === 200 && xmlHttpRequest.readyState === 4)
        {
            var message = xmlHttpRequest.responseText;
            var s = eval(message);
            console.log("wwwwwwwwwwwww"+s.length)
            for(var i = 0; i<s.length;i++){
                var text = document.createTextNode(s[i].cate_name);

                var op = document.createElement("option");

                /!*selMain.appendChild(op);
                op.appendChild(text);*!/
                console.log("wwwwwwwwwwwww"+s[i].cate_id)
                op.value = s[i].cate_id;
            }
        }
    };
    xmlHttpRequest.send();*/

/*    var selMain = document.getElementById("selMain_c");
    var selMinor = document.getElementById("selMinor_c");
    var mo = document.getElementById("mo");*/

    $("#selMain_c").change(function(){
     /*   $("#selMain_c option").remove()*/
        var num = selMain.selectedIndex
        var cate_id = $("#selMain_c option:eq("+num+")").val();
        var xmlHttpRequest = new XMLHttpRequest;
        xmlHttpRequest.open("get","/queryTwoCateServlet?cate_id="+cate_id,true);

        xmlHttpRequest.onreadystatechange = function(){

            if(xmlHttpRequest.status === 200 && xmlHttpRequest.readyState === 4)
            {
                var message = xmlHttpRequest.responseText;
                var s = eval(message);
                for(var i = 0; i<s.length;i++){
                    var op = document.createElement("option");
                    var text = document.createTextNode(s[i].cate_name);
                    console.log("pppppppppppp"+text);
                    selMinor.appendChild(op);
                    op.appendChild(text);
                    op.value = s[i].cate_id;
                }
            }

        };
        xmlHttpRequest.send();
    })


})