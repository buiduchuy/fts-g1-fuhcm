<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Quản lý hoá đơn</title>
<jsp:include page="header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="namePage" value="${sessionScope.namePage}" />
<c:set var="typeGoods" value="${sessionScope.typeGoods }" />
<div class="container">
	<div class="large-12 columns">
		<div class="large-3 columns">
			<div class="form-content"
				style="border: 1px solid #ccc; box-shadow: 1px 1px 2px 2px #CCC; margin-bottom: 50px; width: 100%;">
				<jsp:include page="vertical-menu-manage-good.jsp" />
				<div class="row"></div>
			</div>
		</div>
		<div class="large-9 columns">
			<div class="form-content"
				style="border: 1px solid #ccc; box-shadow: 1px 1px 2px 2px #CCC; margin-bottom: 50px; width: 100%;">
				<div class="form-content">
					<form action="#" method="post" accept-charset="utf-8"
						enctype="multipart/form-data" data-abide=""
						novalidate="novalidate">
						<div class="row">
							<div class="large-12 columns">
								<h2 class="page-title">
									<font color="orange">Quản lý hoá đơn</font>
								</h2>
								<c:set var="messageSuccess"
									value="${sessionScope.messageSuccess }" />
								<c:set var="messageError" value="${sessionScope.messageError }" />
								<c:if test="${not empty messageSuccess}">
									<div class="row">
										<div data-alert class="alert-box success radius inline">
											${messageSuccess} <a href="#" class="close">&times;</a>
										</div>
									</div>
									<%
										request.getSession().removeAttribute("messageSuccess");
									%>
								</c:if>
								<c:if test="${not empty messageError}">
									<div class="row">
										<div data-alert class="alert-box alert radius inline">
											${messageError} <a href="#" class="close">&times;</a>
										</div>

									</div>
									<%
										request.getSession().removeAttribute("messageError");
									%>
								</c:if>
								<c:set var="messageSuccess"
									value="${sessionScope.messageSuccess }" />
								<c:set var="messageError" value="${sessionScope.messageError }" />
								<c:if test="${not empty messageSuccess}">
									<div class="row">
										<div data-alert class="alert-box success radius inline">
											${messageSuccess} <a href="#" class="close">&times;</a>
										</div>
									</div>
									<%
										request.getSession().removeAttribute("messageSuccess");
									%>
								</c:if>
								<c:if test="${not empty messageError}">
									<div class="row">
										<div data-alert class="alert-box alert radius inline">
											${messageError} <a href="#" class="close">&times;</a>
										</div>

									</div>
									<%
										request.getSession().removeAttribute("messageError");
									%>
								</c:if>
							</div>
							<div class="large-12 columns">
								<div class="filter-bar">
									<form action="ProcessServlet" accept-charset="utf-8"
										id="frm-list-lading" method="GET">
										<div class="row">
											<div class="large-3 columns">
												<select required
													data-errormessage-value-missing="Vui lòng chọn loại hàng !"
													name="ddlgoodsCategoryID">
													<option value="0" selected="selected">Tất cả</option>
													<c:forEach var="row" items="${typeGoods}">
														<c:choose>
															<c:when test="${row.goodsCategoryId==1 }">
																<option value="${row.goodsCategoryId}">${row.name }</option>
															</c:when>
															<c:otherwise>
																<option value="${row.goodsCategoryId}">${row.name }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>

											<div class="large-3 columns">
												<div class="date-picker-wrap">
													<input type="text" value="Từ ngày" name="from_date"
														placeholder="Từ ngày" class="date-picker" /> <i
														class="icon-calendar"></i>
												</div>
											</div>

											<div class="large-3 columns date-picker-wrap">
												<div class="date-picker-wrap">
													<input type="text" value="Đến ngày" name="to_date"
														placeholder="Đến ngày" class="date-picker" /> <i
														class="icon-calendar"></i>
												</div>
											</div>

											<div class="large-3 columns">
												<button class="js_search_lading">
													<i class="icon-filter"></i> Lọc
												</button>
											</div>
										</div>
									</form>
								</div>
							</div>

							<div class="large-12 columns">

								<table id="example" class="display" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th><font color="orange">#</font></th>
											<th><font color="orange">MÃ HÀNG</font></th>
											<th><font color="orange">LOẠI HÀNG</font></th>
											<th><font color="orange">THỜI GIAN</font></th>
											<th><h4>
													<font color="orange"></font>
												</h4></th>
										</tr>
									</thead>
									<tbody>
										<c:set var="count" value="0" />
										<c:set var="listOrder" value="${sessionScope.listOrder }" />
										<c:if test="${not empty listOrder }">
											<c:forEach var="order" items="${listOrder}">
												<c:set var="count" value="${count+1 }" />
												<tr>
													<td>${count }</td>
													<td>${fn:substringBefore(fn:replace(order.createTime, '-', ''),' ')}${order.deal.goodsID }</td>
													<c:forEach var="row" items="${typeGoods }">
														<c:if
															test="${order.deal.goods.goodsCategoryID==row.goodsCategoryId }">
															<td>${row.name }</td>
														</c:if>
													</c:forEach>
													<td>Ngày giao: ${order.deal.goods.pickupTime}</br> </br>Ngày
														nhận: ${order.deal.goods.deliveryTime}
													</td>
													<td><a class="button"
														href="ProcessServlet?btnAction=viewDetailOrder&orderID=${order.orderID }">Xem
															chi tiết</a></td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								</br>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp" />