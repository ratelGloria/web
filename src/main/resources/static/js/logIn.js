$(function(){


    var servletResponse  = $("#servletResponse").val()

    console.log(servletResponse)
    console.log(typeof (servletResponse))

     var arr = servletResponse.split(",")
     var message = arr[1].split("'")

    $("#msg").html(message[1]+"!")
    $("#msg").css("color","red")
})