<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<jsp:useBean id="Constants" class="com.epam.MangaStore.constants.Constants"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>MangaStore &ndash; <fmt:message key="title.cart"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <c:if test="${(sessionScope.user.roleID eq Constants.roleUserID) and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row">
            <div class="col-md-12">
                <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                    <table class="table manage-candidates-top mb-0">
                        <thead>
                        <tr>
                            <th><fmt:message key="th.select"/></th>
                            <th><fmt:message key="th.volume"/></th>
                            <th><fmt:message key="th.price"/> (<span><fmt:message key="span.currency.tenge"/></span>)
                            </th>
                            <th><fmt:message key="th.quantity"/></th>
                            <th><fmt:message key="th.sum"/> (<span><fmt:message key="span.currency.tenge"/></span>)</th>
                            <th><fmt:message key="th.delete"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.cartItems}" var="cartItem">
                            <tr>
                                <td>
                                    <div class="form-check">
                                        <input name="cartItemID" value="${cartItem.id}" form="checkOutSelected"
                                               class="form-check-input" type="checkbox">
                                    </div>
                                </td>
                                <td>
                                    <div>
                                        <div>
                                            <h5 class="card-title">
                                                <fmt:message key="head.vol"/>
                                                <span>${cartItem.volume.number}</span>: ${cartItem.volume.title}
                                            </h5>
                                            <div>
                                                <ul class="list-items">
                                                    <li>ISBN: <span>${cartItem.volume.isbn}</span></li>
                                                    <li><fmt:message key="li.pageCount"/>:
                                                        <span>${cartItem.volume.pageCount}</span></li>
                                                    <li><fmt:message key="li.format"/>:
                                                        <span>${cartItem.volume.format}</span></li>
                                                    <li><fmt:message key="li.size"/>:
                                                        <span>${cartItem.volume.size}</span></li>
                                                    <li><fmt:message key="li.binding"/>:
                                                        <span>${cartItem.volume.binding}</span></li>
                                                    <li><fmt:message key="li.releaseDate"/>:
                                                        <span>${cartItem.volume.releaseDate}</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <b class="text-success">${cartItem.volume.price}</b>
                                </td>
                                <td>
                                    <form action="SetQuantity" method="post" id="SetQuantityForm">
                                        <input type="hidden" name="cartItemID" value="${cartItem.id}">
                                        <input
                                                class="form-control"
                                                type="number"
                                                name="quantity"
                                                min="1"
                                                onchange="this.form.submit();"
                                                value="${cartItem.quantity}"
                                        >
                                    </form>
                                </td>
                                <td>
                                    <span class="text-danger"><b>${cartItem.volume.price * cartItem.quantity}</b><span></span></span>
                                </td>
                                <td class="candidate-list-favourite-time text-center">
                                    <form action="DeleteCartItem" method="post" class="form-check">
                                        <input type="hidden" name="cartItemID" value="${cartItem.id}">
                                        <button type="submit" class="btn btn-outline-primary">
                                            <i class="far fa-trash-alt"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${not empty requestScope.cartItems}">
                        <div class="row">
                            <form id="checkOutAll" action="CheckOutAll" method="post" class="col">
                                <button type="submit" class="btn btn-primary" style="width: 100%"><fmt:message
                                        key="button.checkout.all"/></button>
                            </form>
                            <form id="checkOutSelected" action="CheckOutSelected" method="post" class="col">
                                <button type="submit" class="btn btn-outline-primary" style="width: 100%"><fmt:message
                                        key="button.checkout.selected"/></button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${empty requestScope.cartItems}">
                        <div class="mx-auto" style="text-align: center">
                            <h5 class="card-title"><fmt:message key="head.cart.empty"/></h5>
                            <img src="${pageContext.request.contextPath}/img/sad.png" style="width: 40%">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
        <img src="img/404.png" class="mx-auto" width="100%">
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
