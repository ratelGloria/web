<%--
  Created by IntelliJ IDEA.
  User: qiaojing
  Date: 2018/11/19
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>$Title$</title>
    <%-- <link rel = "stylesheet" type = "text/css" !href = "/css/query.css"/>--%>
    <%--<link rel = "stylesheet"  href = "../css/query.css"/>--%>
    <link rel = "stylesheet"  href = "../css/query_top.css"/>
    <link rel = "stylesheet"  href = "../css/query_main.css"/>
    <link  rel="stylesheet" href="../css/query.css">
    <script type = "text/javascript" src = "../js/jquery-3.2.1.min.js"></script>
    <script type = "text/javascript" src="../js/query.js"></script>

</head>
<body>

<div class = "top" ></div>
<div class = "main">
    <div class = "main_content al_ju_cc">
        <div class = "log_up al_ju_cc">
          <%--  <form action="/helloServ" method="post">--%>
                <table cellspacing="0" cellpadding="0">
                    <form action="/logUp" method="post">
                            <h1>log up</h1>
                            <tr>
                                <td>用户名：</td>
                                <td><input type = "text" class = "logUp_text" id = "logUp_userName"  name = "logUp_userName"></td>
                                <td><span class = "hint_logUp" id = "hint_logUp_userName"></span></td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td><input type = "password" class = "logUp_text" id = "logUp_pwd" name = "logUp_pwd"></td>
                                <td><span class = "hint_logUp" id = "hint_logUp_pwd"></span></td>
                            </tr>
                        <tr>
                            <td>确认密码：</td>
                            <td><input type = "password" class = "logUp_text" id = "queren_1"></td>
                            <td><span class = "hint_logUp" id = "queren"></span></td>
                        </tr>
                            <tr>
                                <td>手机号：</td>
                                <td><input type = "text" class = "logUp_text" id = "logUp_phone"  name = "logUp_phone"></td>
                                <td><span class = "hint_logUp" id = "hint_logUp_phone"></span></td>
                            </tr>
                             <tr>
                                <td>邮箱：</td>
                                <td><input type = "text" class = "logUp_text" id = "logUp_email"  name = "logUp_email"></td>
                                <td><span class = "hint_logUp" id = "hint_logUp_emil"></span></td>
                             </tr>
                            <tr>
                                <td></td>
                                <td><input type = "submit" id = "logUp_sub" value="注  册"/></td>
                            </tr>
                    </form>

                </table>
           <%-- </form>--%>
        </div>

    </div>
</div>
<div class = "bottom"></div>
</body>
</html>


<%--<html>
<head>
    <title>Title</title>
    <script type = "text/javascript" ></script>
</head>
<body>
    <div>
        <form action="/logUp" method="post">
            <table>
                <h1>log up</h1>
                <tr>
                    <td>用户名：</td>
                    <td><input type = "text" name = "logUp_userName"></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input type = "text" name = "logUp_passWord"></td>
                </tr>
                <tr>
                    <td>手机号：</td>
                    <td><input type = "text" name = "logUp_phone"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type = "submit"/></td>
                </tr>

            </table>
        </form>
    </div>
</body>
</html>--%>
