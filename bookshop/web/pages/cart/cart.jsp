<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text() +"】吗?");
			})
			$("a.clear").click(function () {
				return confirm("你确定要清空购物车吗？");
			})
			$("input.updateCount").change(function () {
				var count = this.value;
				var id = $(this).attr("bookId");
				if(confirm("你确定要将【"+ $(this).parent().parent().find("td:first").text() +"】商品数量修改为 "+ count+" 吗?")){
					location.href="${pageScope.basePath}cartservlet?action=updateCount&count="+count+"&id="+id;
				}else{
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
				<a href="pages/order/order.jsp">我的订单</a>
				<a href="index.jsp">注销</a>&nbsp;&nbsp;
				<a href="index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td><input bookId="${entry.value.id}" class="updateCount" style="width: 80px" type="text" value="${entry.value.count}"/></td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItem" href="cartservlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快和小伙伴们一起去浏览商品吧！</a></td>
				</tr>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">4</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">90.00</span>元</span>
				<span class="cart_span"><a class="clear" href="cartservlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderservlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>