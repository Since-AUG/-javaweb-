<%--
  Created by IntelliJ IDEA.
  User: howell
  Date: 2021/3/29
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临！</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userservlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
</body>
</html>
