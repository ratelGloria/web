<%--
  Created by IntelliJ IDEA.
  User: qiaojing
  Date: 2018/12/19
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" href="../css/bootstrap.min.css">
    <link type="text/css" href="../css/nBody.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/nPage.js"></script>
</head>
<body>
<form action="">
    <table class="terms">
        <tr>
            <td>商品编号：</td><td><input type="text" name = "shop_id" id = "shop_id" ></td>
            <td>商品名称：</td><td><input type="text" name = "shop_name" id = "shop_name"></td>
            <td>商品描述：</td><td><input type="text" name = "shop_des" id = "shop_des"></td>
            <td>商品类别：</td>
            <td><div id = "categoryBox" >
                <select id = "selMain"></select>
                <select id = "selMinor"></select></div>
            </td>
            <td> <input type="button" value="查 询" id = "likeQueryShop"></td>
        </tr>

    </table>
    <a href="nShoppingCart.jsp" id="checkShoppingCart">我的购物车</a>

    <div>
        <div style="width: 144px" class = "hh"><span>商品编号</span></div>
        <div style="width: 144px" class = "hh"><span >商品名称</span></div>
        <div style="width: 144px" class = "hh"><span >商品描述</span></div>
        <div style="width: 144px" class = "hh"><span >图片</span></div>
        <div style="width: 144px" class = "hh"><span >价格</span></div>
        <div style="width: 144px" class = "hh"><span>所属子类</span></div>
        <div style="width: 144px" class = "hh"><span >库存</span></div>
        <div style="width: 144px" class = "hh"> <span></span></div>
    </div>

    <table id = "resultContent" class="table table-striped">


    </table>
    <span id = "allData"></span>
    <span id = "pageButton">1</span>

    <nav aria-label="Page navigation">

        <ul class="pagination" id = "limpage">
            <li id = "uppage">
                <a href="#" aria-label="Previous" >
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <li class = "ye"><a href="#" class="pagination" id = "nomargin">1</a></li>

            <li id = "downpage">
                <a href="#" aria-label="Next" >
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
        当前页数：<span id ="page"></span>
    </nav>
</form>
</body>
</html>
