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
    <title>MangaStore &ndash; <fmt:message key="head.order.settings"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row">
            <div class="col-md-12">
                <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                    <table class="table manage-candidates-top mb-0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="th.user"/></th>
                            <th><fmt:message key="th.order.products"/></th>
                            <th><fmt:message key="th.total"/></th>
                            <th><fmt:message key="th.status"/></th>
                            <th><fmt:message key="th.date"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <tr class="candidates-list">
                                <td class="title">
                                        ${order.id}
                                </td>
                                <td class="title">
                                    <div class="candidate-list-details">
                                        <div class="candidate-list-info">
                                            <div class="candidate-list-title">
                                                <h5 class="mb-0">${order.user.login}</h5>
                                            </div>
                                            <div class="candidate-list-option">
                                                <ul class="list-unstyled">
                                                    <li><i class="fas fa-envelope"></i>
                                                            ${order.user.email}</li>
                                                    <li><i class="fas fa-map-marker-alt pr-1"></i>
                                                            ${order.user.address}, ${order.user.postalCode}
                                                    </li>
                                                    <li><i class="fas fa-phone"></i>
                                                            ${order.user.phone}
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td class="title">
                                    <c:forEach items="${order.orderItems}" var="orderItem">
                                        <ul class="list-group mb-3">
                                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                <div>
                                                    <form action="DisplayAllVolumes" method="get">
                                                        <input type="hidden" name="mangaID" value="${orderItem.volume.mangaID}">
                                                        <input
                                                                type="submit"
                                                                class="btn-link"
                                                                value="Vol.${orderItem.volume.number}: ${orderItem.volume.title}"
                                                        >
                                                    </form>
                                                </div>
                                                <div>
                                                    <b class="text-success">${orderItem.quantity} <fmt:message
                                                            key="b.pieces.short"/></b> x <b
                                                        class="text-danger"> ${orderItem.fixedPrice} <fmt:message
                                                        key="span.currency.tenge"/></b>
                                                </div>
                                            </li>
                                        </ul>
                                    </c:forEach>
                                </td>
                                <td>
                                    <span><b>${order.totalPrice}</b>  <fmt:message key="span.currency.tenge"/></span>
                                </td>
                                <td>
                                    <form action="EditOrderStatus" method="post">
                                        <input type="hidden" name="orderID" value="${order.id}">
                                        <select name="orderStatusID" class="form-control"
                                                onchange="this.form.submit();">
                                            <c:forEach items="${requestScope.orderStatuses}" var="status">
                                                <option class="text-primary" value="${status.id}"
                                                        <c:if test="${order.statusID eq status.id}">
                                                            selected
                                                        </c:if>
                                                >
                                                        ${status.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </td>
                                <td>
                                    <span><i>${order.createdDate}</i></span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
        <img src="img/404.png" class="mx-auto" width="100%">
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
