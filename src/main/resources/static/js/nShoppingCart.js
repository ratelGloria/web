$(function(){


        var xml2 = new XMLHttpRequest()
        xml2.open("get","/queryShoppingCart?userId=111",true)
        xml2.onreadystatechange = function(){

            if(xml2.status == 200 && xml2.readyState == 4){

                var message = xml2.responseText
                console.log("-------"+message)
                var s1 = eval(message)
                for(var i = 0;i<s1.length;i++){
                    var tr = document.createElement("tr")
                    var td = document.createElement("td")
                    var shoppingCart_shops_id = document.createTextNode(s1[i].shoppingCart_shops_id)
                    td.append(shoppingCart_shops_id)
                    tr.append(td)
                    $("#resultContent").append(tr)
                }
            }
        }
        xml2.send()


})