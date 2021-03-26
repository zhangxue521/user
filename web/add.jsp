<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 22:20
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
</head>
<body>

<div  class="container" style="width: 40%;margin-top: 40px">

    <h3 align="center">添加用户信息</h3>
    <form action="${pageContext.request.contextPath}/AddUserServlet" method="post">
        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>
        <div class="form-group">
            <label for="female">性别:</label>
            <label class="radio-inline">
                <input type="radio"  name="gender" id="male" value="男">男
            </label>
            <label class="radio-inline">
                <input type="radio"  name="gender" id="female" value="女"> 女
            </label>
        </div>
        <div class="form-group">
            <label for="age">年龄:</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>
        <div class="form-group">
            <label for="address">籍贯:</label>
            <select class="form-control" name="address" id="address">
                <option>北京</option>
                <option>天津</option>
                <option>河北</option>
            </select>
        </div>
        <div class="form-group">
            <label for="email">邮箱:</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱">
        </div>
        <div class="form-group" align="center">
            <input class="btn btn btn-info" type="submit" value="提交">
        </div>
    </form>

</div>


</body>
</html>
