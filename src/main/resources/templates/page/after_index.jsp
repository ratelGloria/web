<%--
  Created by IntelliJ IDEA.
  User: qiaojing
  Date: 2019/1/3
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../static/css/style.css">
    <link rel="stylesheet" href="../../static/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../../static/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="../../static/css/demo.css">
    <!-- GOOGLE FONTS -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    <link rel="stylesheet" href="../../static/css/webFront.css">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="after_index.jsp"><img src="assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
         首页放echars
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="index.jsp" class="active"><i class="lnr lnr-home"></i> <span>显示</span></a></li>
                    <li>
                        <a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>商品管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages" class="collapse ">
                            <ul class="nav">
                                <li><a href="queryShop.jsp" class="">查看商品</a></li>
                                <li><a href="addShop.jsp" class="">添加商品</a></li>
                                <li><a href="page-lockscreen.jsp" class=""></a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>类别管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div  class="collapse ">
                            <ul class="nav">
                                <li><a href="page-profile.jsp" class="">Profile</a></li>
                                <li><a href="page-login.jsp" class="">Login</a></li>
                                <li><a href="page-lockscreen.jsp" class="">Lockscreen</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>订单管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div  class="collapse ">
                            <ul class="nav">
                                <li><a href="page-profile.jsp" class="">Profile</a></li>
                                <li><a href="page-login.jsp" class="">Login</a></li>
                                <li><a href="page-lockscreen.jsp" class="">Lockscreen</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="elements.jsp" class=""><i class="lnr lnr-code"></i> <span>Elements</span></a></li>
                    <li><a href="charts.jsp" class=""><i class="lnr lnr-chart-bars"></i> <span>Charts</span></a></li>
                    <li><a href="panels.jsp" class=""><i class="lnr lnr-cog"></i> <span>Panels</span></a></li>
                    <li><a href="notifications.jsp" class=""><i class="lnr lnr-alarm"></i> <span>Notifications</span></a></li>
                    <li>
                        <a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Pages</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div  class="collapse ">
                            <ul class="nav">
                                <li><a href="page-profile.jsp" class="">Profile</a></li>
                                <li><a href="page-login.jsp" class="">Login</a></li>
                                <li><a href="page-lockscreen.jsp" class="">Lockscreen</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="tables.jsp" class=""><i class="lnr lnr-dice"></i> <span>Tables</span></a></li>
                    <li><a href="typography.jsp" class=""><i class="lnr lnr-text-format"></i> <span>Typography</span></a></li>
                    <li><a href="icons.jsp" class=""><i class="lnr lnr-linearicons"></i> <span>Icons</span></a></li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <iframe src="queryShop.jsp" frameborder="0" id="frameSize">
                </iframe>

            </div>
        </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
    <div class="clearfix"></div>
    <footer>
        <div class="container-fluid">
            <p class="copyright">Copyright &copy; 2017.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>

        </div>
    </footer>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="../Template_js/jquery.min.js"></script>
<script src="../Template_js/bootstrap.min.js"></script>
<script src="../Template_js/jquery.slimscroll.min.js"></script>
<script src="../Template_js/jquery.easypiechart.min.js"></script>
<script src="../Template_js/chartist.min.js"></script>
<script src="../Template_js/klorofil-common.js"></script>

</body>
</html>
