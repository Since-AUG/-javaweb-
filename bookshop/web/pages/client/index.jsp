<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $(".addToCart").click(function () {
                var bookId = $(this).attr("bookId");
                $.getJSON("${pageScope.basePath}cartservlet","action=ajaxAddItem&id="+bookId,
                function (data) {
                    $("#lastItemName").text(data.lastItemName);
                    $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                })
            });
        });

    </script>
</head>
<body>

<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.gif" >--%>
    <span class="wel_word">欢迎光临网上书店</span>
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userservlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookservlet" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">

            <div>
                <c:if test="${not empty sessionScope.cart.items}">
                    <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span><br/>
                    <div>
                      <span>您刚刚将</span><span style="color: red" id="lastItemName">${sessionScope.lastItemName}</span><span>加入到了购物车中</span>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.cart.items}">
                    <span id="cartTotalCount"> </span>
                    <div>
                        <span style="color: red" id="lastItemName">当前购物车为空</span>
                    </div>
                </c:if>
            </div>
        </div>

        <c:forEach var="book" items="${requestScope.page.items}">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="${book.imgPath}" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${book.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${book.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${book.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${book.stock}</span>
                </div>
                <div class="book_add">
                    <button bookId="${book.id}" class="addToCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>

    </div>

    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<div id="bottom">
		<span>

		</span>
</div>
</body>
</html>