<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/26
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/materialdesignicons.min.css">
    <link rel="stylesheet" href="css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- inject:css -->
    <link rel="stylesheet" href="css/style.css">

    <!--bootstrapt弹框  -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.css">
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>

    <script>

        window.onload = function () {

            //获取图片对象
            var CheckCode = document.getElementById("code");

            //var aa = document.getElementById("change");

            //绑定单击事件
            CheckCode.onclick = function () {

                var date = new Date().getTime();

                CheckCode.src = "${pageContext.request.contextPath}/CheckCodeServlet?"+date;


            }


        }


    </script>
</head>
<body>


    <div class="container">

        <h3 align="center">管理员登录</h3>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="form-group">
                <label for="username">用户名:</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="password">密码:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
            <div class="form-inline">
                <label for="CheckCode">验证码:</label>
                <input type="text" class="form-control" id="CheckCode" name="CheckCode" placeholder="请输入验证码">
                <img id="code" src="${pageContext.request.contextPath}/CheckCodeServlet" />
            </div>
            <div class="form-group">
                <input class="btn btn btn-info" type="submit" value="登录">
            </div>
        </form>


        <%--<div style="background: chocolate">${login_error}</div>--%>


        <%--出错显示的--%>
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span>&times;</span>
            </button>
            <strong>${login_error}</strong>
        </div>
    </div>




    <!-- plugins:js -->
    <script src="js/vendor.bundle.base.js"></script>
    <script src="js/vendor.bundle.addons.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page-->
    <!-- End plugin js for this page-->
    <!-- inject:js -->
    <script src="js/off-canvas.js"></script>
    <script src="js/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="js/dashboard.js"></script>
    <!-- End custom js for this page-->

</body>
</html>
