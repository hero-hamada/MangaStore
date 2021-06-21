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
    <title>MangaStore &ndash; <fmt:message key="button.confirm"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <c:if test="${(sessionScope.user.roleID eq Constants.roleUserID) and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row" style="background-color: #f5f5f5; margin-top: 80px;">
            <div class="col-md-6 order-md-2 mb-4 mt-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted"><fmt:message key="title.cart"/></span>
                </h4>
                <ul class="list-group mb-3">
                    <li class="d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><fmt:message key="th.total"/></h6>
                        </div>
                        <b class="text-danger">${requestScope.cartTotalPrice} <fmt:message
                                key="span.currency.tenge"/></b>
                    </li>
                </ul>
                <c:forEach var="cartItem" items="${requestScope.cartItems}">
                    <ul class="list-group mb-3">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <a><fmt:message key="head.vol"/> ${cartItem.volume.number}: ${cartItem.volume.title}</a>
                            </div>
                            <div>
                                <b class="text-success">${cartItem.quantity} pc</b> x <b
                                    class="text-danger"> ${cartItem.volume.price} <fmt:message
                                    key="span.currency.tenge"/> </b>
                            </div>
                            <input type="hidden" name="cartItemID" value="${cartItem.id}" form="confirmOrder" required>
                        </li>
                    </ul>
                </c:forEach>
            </div>
            <div class="col-md-6 order-md-1 mt-4">
                <h4 class="mb-3"><fmt:message key="head.address.shipping"/></h4>
                <hr class="mb-4">
                <div class="candidate-list-details">
                    <div class="candidate-list-info">
                        <div class="candidate-list-title">
                            <h5 class="mb-0">${sessionScope.user.login}</h5>
                        </div>
                        <div class="candidate-list-option">
                            <ul class="list-unstyled">
                                <li>
                                    <i class="fas fa-envelope"></i>
                                        ${sessionScope.user.email}
                                </li>
                                <li>
                                    <i class="fas fa-map-marker-alt pr-1"></i>
                                        ${sessionScope.user.address}, ${sessionScope.user.postalCode}
                                </li>
                                <li>
                                    <i class="fas fa-phone"></i>
                                        ${sessionScope.user.phone}
                                </li>
                            </ul>
                        </div>
                        <button type="submit" class="btn btn-secondary">
                            <a href="edit_profile.jsp"><fmt:message key="button.edit"/></a>
                        </button>
                    </div>
                </div>
                <hr class="mb-4">

                <form id="confirmOrder" action="ConfirmOrder" method="post">
                    <button class="btn btn-primary btn-lg btn-block mb-4" type="submit">
                        <fmt:message key="button.confirm"/>
                    </button>
                </form>
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
