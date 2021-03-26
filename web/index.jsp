<%@ page import="cn.itcast.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/26
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>



  <div align="center"><h2>${user.name},欢迎您</h2></div>
  <div align="center">

    <a href="${pageContext.request.contextPath}/FindUserByPageServlet"><h2>查询所有用户信息</h2></a>

  </div>

  </body>
</html>
