$(function(){

    $("#ii").click(function (){


        var xml1 = new  XMLHttpRequest()

        xml1.open("get","/textServlet",true)

        xml1.onreadystatechange = function(){

            if(xml1.readyState == 4 && xml1.status == 200){

                var message1 = xml1.responseText
                console.log("message的类型"+typeof(message1))
                var arr = message1.split("][")
                console.log("arr[0]1111111111111"+arr[0])
                console.log("message11111111111111"+message1)
                var a1 = arr[0]+"]"
                var s1 = eval(a1)
                console.log("s1111111111111"+s1)
                $("body").text(s1)

            }
        }
        xml1.send()
    })


})