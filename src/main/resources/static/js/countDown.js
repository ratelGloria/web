

var time = 5;
    var timer;


 function countDown(){
     $("#countDown").text(time)
     if(time == 0){
        $(window).attr('location',"/logIn")
     }else {

         $("#countDown").text(" ")
         time = time-1
         $("#countDown").text(time)


     }
 }
    timer = setInterval("countDown()",1000)


