<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <h3 align="center">修改用户信息</h3>
        <form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post">
            <!--隐藏域-->
            <input type="hidden" name="id" id="id" value="${user.id}">
            <div class="form-group">
                <label for="name">姓名:</label>
                <input type="text" class="form-control" value="${user.name}" id="name" name="name" placeholder="请输入姓名" readonly>
            </div>
            <div class="form-group">
                <label>性别:</label>
                <c:if test="${user.gender=='男'}">
                    <label class="radio-inline">
                        <input type="radio"  name="gender"  value="男" checked>男
                    </label>
                    <label class="radio-inline">
                        <input type="radio"  name="gender"  value="女"> 女
                    </label>
                </c:if>
                <c:if test="${user.gender=='女'}">
                    <label class="radio-inline">
                        <input type="radio"  name="gender"  value="男" >男
                    </label>
                    <label class="radio-inline">
                        <input type="radio"  name="gender"  value="女" checked> 女
                    </label>
                </c:if>

            </div>
            <div class="form-group">
                <label for="age">年龄:</label>
                <input type="text" class="form-control" value="${user.age}" id="age" name="age" placeholder="请输入年龄">
            </div>
            <div class="form-group">
                <label for="address">籍贯:</label>
                <select class="form-control" name="address" id="address">
                    <c:if test="${user.address=='北京'}">
                        <option value="北京" selected>北京</option>
                        <option value="天津">天津</option>
                        <option value="河北">河北</option>
                    </c:if>
                    <c:if test="${user.address=='天津'}">
                        <option value="北京">北京</option>
                        <option value="天津" selected>天津</option>
                        <option value="河北">河北</option>
                    </c:if>
                    <c:if test="${user.address=='河北'}">
                        <option value="北京">北京</option>
                        <option value="天津">天津</option>
                        <option value="河北" selected>河北</option>
                    </c:if>

                </select>
            </div>
            <div class="form-group">
                <label for="email">邮箱:</label>
                <input type="text" class="form-control" value="${user.email}" id="email" name="email" placeholder="请输入邮箱">
            </div>
            <div class="form-group" align="center">
                <input class="btn btn btn-info" type="submit" value="提交">
            </div>
        </form>

    </div>

</body>
</html>
