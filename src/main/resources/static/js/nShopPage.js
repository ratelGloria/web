$(function(){
    $("#minus").click(function(){
        $("#numShow").val(parseInt($("#numShow").val())-1)
    })
    $("#add").click(function(){
        var numS = $("#numShow").val()
        var num = parseInt(numS)
        $("#numShow").val(num+1)
    })

    $("#shoppingCart").click(function(){

        var xml3 = new XMLHttpRequest()
        var user = "user"
         var shop_id = $("#shop_id").val()
        console.log(shop_id+typeof (shop_id))
        xml3.open("get","/shoppingCartServlet?user="+user+"&shop_id="+shop_id,true)
        xml3.onreadystatechange = function(){

            if(xml3.status == 200 && xml3.readyState ==4){

                var getmessage = xml3.responseText
                 var shopNum= eval(getmessage).length
                $(".shopCar").valueOf(shopNum)

            }
        }


    })

})