$(function(){
	var changeB = false;
	var findB = true;
	var addB = false;
	var type_queryB = false;
    var username_b;



	$(".add_id").val($(".Shop_id").val());

	for(var i = 0;i<12;i++){
		$(".add_table input:eq(i)").val($("#getMessage li:eq(i+1)").val());

	}

	function notOk(text){
        $(text).html("格式不符");
        $(text).css({"color":"red","display":"block"});
    }
    function Ok(text){
        $(text).html("格式符合");
        $(text).css({"color":"green","display":"block"});
    }

    /*$("#hint_logUp_userName").html("长度为8-16字节")*/
    $(".logUp_text").css({"color":"black","display":"block","font-size":"80%"})

    $("#logUp_pwd").blur(function(){
        regExp = /[\w]{6,18}/;
        var result = regExp.test($("#logUp_pwd").val());
        if(result == true){
            Ok("#hint_logUp_pwd");
        }else{
            notOk("#hint_logUp_pwd");
        }
    })
    $("#checkpwd").blur(function(){
        var pwd = $("#logUp_pwd").val()
        var result = regExp.test($("#checkpwd").val());
        if(result == pwd){
            Ok("#queren");
        }else{
            notOk("#queren");
        }
    })
    $("#logUp_phone").blur(function(){
        regExp = /[1][0-9]{10}/;
        var result = regExp.test($("#logUp_phone").val());
        if(result == true){
            Ok("#hint_logUp_phone");
        }else{
            notOk("#hint_logUp_phone");
        }
    })
    $("#logUp_emil").blur(function(){
        regExp = /[a-z0-9]{5,11}@(qq|163)(\.com|\.cn)/;
        var result = regExp.test($("#logUp_emil").val());
        if(result == true){
            Ok("#hint_logUp_emil");
        }else{
            notOk("#hint_logUp_emil");
        }
    })


    $("#logUp_userName").change(function(){
        var username = $("#logUp_userName").val()
        regExp = /[\w]{6,18}/;
        var result = regExp.test($("#logUp_userName").val());
        console.log($(".logUp_text").val())
        console.log(result)
        var userNameHint1 = $("#userNameHint1").text
        /*  if(userNameHint1 == "")*/
        if(result == true){
            $("#userNameHint").text(" ")
            $("#regexpName").text(" ")
            console.log(username)
            var xml2 = new XMLHttpRequest()

            xml2.open("get","/checkUserName/"+username,true)

            xml2.onreadystatechange = function(){

                if(xml2.readyState == 4 && xml2.status == 200){

                    var nameServerResponse = xml2.responseText
                    console.log("===="+nameServerResponse)

                    /*     var nameRes = eval(nameServerResponse)*/

                    var arr = nameServerResponse.split(",")

                    var s1 = arr[1].split(":")
                    var s2 = arr[0].split(":")
                    console.log(s1)

                    var nameResult = s1[1].split("\"")
                    console.log(nameResult)
                    if(s2[1] == 0){

                        $("#userNameHint").html(nameResult[1])
                        $("#userNameHint").css({
                            "color":"green",
                            "font-size": "10px"
                        })
                    }else if(s2[1] == 1) {
                        $("#userNameHint").html(nameResult[1]+ "！")
                        $("#userNameHint").css({
                            "color": "red",
                            "font-size": "10px"
                        })
                    }
                }
            }
            xml2.send()
        }else{
            notOk("#regexpName");
        }


   /* $.ajax({
        type:"get",
        url:"/checkUserName/"+username,
        dataType:"JSON",
        async:true,
        success:function(result){
            console.log("000"+result)
            console.log("result"+result[1])
             var arr = result.split(",")
            console.log("22222"+arr[1])
        }
    })*/

    })




    $(".submit_buttomC").click(function(){
        $(".submit_buttomC").css("transform","translate(1px,1px)");
    });


  $("li").on({
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
        	$(this).css({
        		 "background-color": "#d7d5d5"
        	})
        }
     })


     var xmlhttprequest = new XMLHttpRequest();

    $(".type_query").click(function(){
        xmlhttprequest.open("post","file/9隐.txt")

        console.log(xmlhttprequest.responseText)

        xmlhttprequest.send()

    })






   	$(".replace").click(function(){
   		$(this).parent().parent()
   		window.location.href = "change_page.jsp"
   	})
   	

	
   $(".change").click(function(){
   	changeB = true;
   	findB = false;
   	addB = false;
       type_queryB = false;
   	window.location.href = "change_page.jsp";
   })
    $(".find").click(function(){
    changeB = false;
   	findB = true;
   	addB = false;
        type_queryB = false;
   	window.location.href = "find_page_a.jsp";
   })
    $(".add").click(function(){
    changeB = false;
   	findB = false;
   	addB = true;
        type_queryB = false;
   	window.location.href = "add_page.jsp";
   })
    $(".type_query").click(function(){
        changeB = false;
        findB = false;
        addB = false;
        type_queryB = true;
        window.location.href = "add_page.jsp";
    })


    $(".submit_buttom").click(function(){
    	add_message();
    	window.location.href = "find_page.jsp";
    })
    function add_message(){
    	$("#find_table").append("<tr><td></td></tr>")
    	console.log("添加成功");
    }
    function add_tr(){
    	$("tr").css({
    		"width":"500px",
    		"height":"60px",
    		"border":"1px solid black",
    		"background-color":"black"
    	})
    }



   $("#add_name").focus(function(){
   	 $add_name = $("#add_name").val()
   	
   })
	var onePage =5;
	var pageNum;
	var $tr_find = $("#find_table").find("tr")
	if(($tr_find.length-1)%5!=0&&$tr_find.length>onePage){
        pageNum = Math.round(($tr_find.length-1)/onePage)+1;
	}else{
        pageNum = ($tr_find.length-1)/onePage;
	}

    $("#find_table").find("tr").hide()
    for(var i = 0;i<onePage;i++){
    	$("#find_table").find("tr").eq(0).show()
    	$("#find_table").find("tr").eq(1+i).show()
    }
    var pageList = "";
    for(var i = 0;i<pageNum;i++){
    	 pageList += "<a href='javascript:;'>"+(i+1)+"</a>";
    }

    $(".r_b_bottom span:eq(1)").html(pageList)
    $(".r_b_bottom span:eq(1)").find("a:eq(0)").css("color","green")

    var num =0;

    $(".r_b_bottom span:eq(1)").find("a").click(function(){

        $(".r_b_bottom span:eq(1)").find("a").css("color","blue")
		$(this).css("color","green")
    	$("#find_table").find("tr").hide()
    	 num = $(this).index()
        console.log(num)
    	for(var i =0; i<onePage;i++){
    		$("tr").eq(0).show()
    		$("tr").eq(num*onePage+i+1).show()
    	}
    })

    $(".r_b_bottom span:eq(0)").find("a").click(function(){
        $("#find_table").find("tr").hide()
        $(".r_b_bottom span:eq(1)").find("a").css("color","blue")
        var t = num-1;
    	if(num==0){
            $(".r_b_bottom span:eq(1)").find("a:eq(0)").css("color","green")
            for(var i = 0;i<onePage;i++){
                $("tr").eq(0).show()
                $("tr").eq(i+1).show()
                console.log(onePage)
            }
            return false;
		}else{
            $(".r_b_bottom span:eq(1)").find("a:eq("+t+")").css("color","green")
			for(var i = 0;i<onePage;i++){
				 $("tr").eq(0).show()
				 $("tr").eq((num-1)*onePage+i+1).show()
			 }
        }
    })
    $(".r_b_bottom span:eq(2)").find("a").click(function(){
        $("#find_table").find("tr").hide()
        var t = num+1;
        $(".r_b_bottom span:eq(1)").find("a").css("color","blue")

		if(num==pageNum){
            $(".r_b_bottom span:eq(1)").find("a:eq(pageNum)").css("color","green")
            for(var i = 0;i<onePage;i++){
                $("tr").eq(0).show()
                $("tr").eq((pageNum-1)*onePage+i+1).show()
            }
		}else{
            $(".r_b_bottom span:eq(1)").find("a:eq("+t+")").css("color","green")
        console.log("当前页"+num)
        for(var i = 0;i<onePage;i++){
            $("tr").eq(0).show()
            $("tr").eq((num+1)*onePage+i+1).show()
        }
        }
    })
})