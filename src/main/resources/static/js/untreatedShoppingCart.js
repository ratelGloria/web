$(function(){

     var xml1 = new XMLHttpRequest();
console.log("1111"+222)
     xml1.open("get","/untreatedShoppingCart",true)

    xml1.onreadystatechange = function(){
        console.log("1111"+333)
         if(xml1.readyState == 4 && xml1.status == 200){
             $("#resultContent tr").remove()
             $("#resultContent tr td").remove()
             var message = xml1.responseText;
             var s1 = eval(message);
             console.log("s1"+s1)

             for (var i = 0; i < s1.length-1; i++) {
                 var tr = document.createElement("tr");
                 for(var j = 0;j<5;j++){
                     var td = document.createElement("td");
                     tr.appendChild(td);
                     $("#resultContent").append(tr)
                 }
                 var img = document.createElement("img");
                 var a = document.createElement("a");

                 var text1 = document.createTextNode(s1[i].shoppingCart_id);
                 var text2 = document.createTextNode(s1[i].shoppingCart_user);
                 var text3 = document.createTextNode(s1[i].shoppingCart_shops_id);
                 var text4 = document.createTextNode(s1[i].shoppingCart_createTime);
                 var text5 = document.createTextNode(s1[i].shoppingCart_treated);

                 var text = new Array(text1,text2,text3,text4,text5)

                 for(var c = 0;c<5;c++){

                     $("#resultContent tr:eq("+i+") td:eq("+c+")").append(text[c])
                 }
             }
             console.log("s3[s3.length-1]"+s1[s1.length-1])
             var allData = s1[s1.length-1]
             if(allData == 0 ){
                 $("#allData").html(1)
                 $("#pageBox span").remove()
             }else {
                 $("#allData").html(allData)
                 if(allData%10 == 0){
                     $("#pageBox span").remove()
                     for(var i = 1;i<allData/10;i++){
                         var but = document.createElement("span")
                         var text = document.createTextNode(i);
                         but.append(text)
                         $("#pageBox").append(but)
                     }
                 }else{
                     $("#pageBox span").remove()
                     for(var i = 1;i<allData/10+1;i++){

                         var but = document.createElement("span")
                         var text = document.createTextNode(i);
                         but.append(text)
                         $("#pageBox").append(but)
                     }
                 }
             }
         }
    }
    xml1.send();




})