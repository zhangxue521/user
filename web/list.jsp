<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/26
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户信息列表</title>
    <!-- plugins:css -->
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

        /**
         * 删除某条记录
         **/
        function delUser(id) {

            //提示信息
            if(confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/DelUserServlet?id="+id;
            }
        }

        window.onload = function () {
            /**
             * 给删除选中（批量删除）添加单击事件
             */
            document.getElementById("Dels").onclick = function () {
                if(confirm("您确定要删除吗？")){
                    var flag = false;
                    //判断是否有记录选中
                    var cb = document.getElementsByName("uid");
                    for (var i = 0;i < cb.length;i++) {
                        //有一条记录选中了
                        if(cb[i].checked){
                            flag = true;
                            break;
                        }
                    }
                    //有记录选中
                    if(flag){
                        //表单提交
                        document.getElementById("delsForm").submit();
                    }

                }


            }
            /**
             * 实现全选 全不选
             */
               //1、获取第一个Cb
            document.getElementById("firstCb").onclick = function () {
                //2、获取下列表中所有Cb
                var cb = document.getElementsByName("uid");
                //3、遍历
                for (var i = 0;i < cb.length;i++) {
                    //设置这些Cb的checked状态等于firstCb的checked状态
                    cb[i].checked = this.checked;

                }

            }
        }

    </script>
    <!-- endinject -->
</head>
<body>

    <h1 align="center">用户信息</h1>

    <div style="float: left;margin-left: 60px;margin-bottom: 10px" >
        <form class="form-inline" action="${pageContext.request.contextPath}/FindUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName"></label>姓名
                <input type="text" name="name"  value="${condition.name[0]}" class="form-control" id="exampleInputName" >
            </div>
            <div class="form-group" style="margin-right: 10px">
                <label for="exampleInputAddress"></label>籍贯
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputAddress" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail"></label>邮箱
                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail" >
            </div>
            <button type="submit" class="btn btn-info">查询</button>
        </form>
    </div>

    <div style="float: right;margin-right: 50px">
        <a type="button" class="btn btn-info" href="add.jsp">添加联系人</a>
        <a  class="btn btn-danger" href="javascript:void(0)" id="Dels">删除选中</a>
    </div>

    <br /><br />
    <div class="col-md-12 grid-margin stretch-card">
        <div class="card">
            <form id="delsForm" name="delsForm" action="${pageContext.request.contextPath}/DelsUserServlet" method="post">
                <table border="1" class="table table-bordered table-hover" style="text-align: center">
                    <tr class="success">
                        <td><input type="checkbox" id="firstCb"></td>
                        <td>编号</td>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>年龄</td>
                        <td>籍贯</td>
                        <td>邮箱</td>
                        <td>操作</td>
                    </tr>

                    <c:forEach items="${pb.list}" var="user" varStatus="s">

                        <tr>
                            <td><input type="checkbox" name="uid" value="${user.id}"></td>
                            <td>${s.count}</td>
                            <td>${user.name}</td>
                            <td>${user.gender}</td>
                            <td>${user.age}</td>
                            <td>${user.address}</td>
                            <td>${user.email}</td>
                            <td>
                                <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/FindServlet?id=${user.id}">修改</a>
                                <a type="button" class="btn btn-danger" href="javascript:delUser(${user.id});">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </form>


                <!--分页 -->
                <div>
                    <nav aria-label="Page navigation">
                        <ul class="pagination pagination-lg">
                            
                            <c:if test="${pb.currentPage == 1}">
                                <li class="disabled">
                                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                                        <span aria-hidden="true">上一页</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${pb.currentPage != 1}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                                        <span aria-hidden="true">上一页</span>
                                    </a>
                                </li>
                            </c:if>
                            

                            <c:forEach begin="1" end="${pb.totalPage}" var="i">

                                <c:if test="${pb.currentPage == i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                                </c:if>

                                <c:if test="${pb.currentPage != i}">
                                    <li><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                                </c:if>

                            </c:forEach>
                            
                            <c:if test="${pb.currentPage == pb.totalPage}">
                                <li class="disabled">
                                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage }&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                                        <span aria-hidden="true">下一页</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${pb.currentPage != pb.totalPage}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                                        <span aria-hidden="true">下一页</span>
                                    </a>
                                </li>
                            </c:if>


                            <span style="font-size: 25px;margin-left: 5px;font-family: 黑体;">
                                共${pb.totalCount}条记录，共${pb.totalPage}页
                            </span>
                        </ul>
                    </nav>
                </div>

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

