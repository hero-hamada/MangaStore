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
    <title>Manga Store &ndash; <fmt:message key="title.my.orders"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <c:if test="${sessionScope.user.roleID eq Constants.roleUserID and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row">
            <div class="col-md-12">
                <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                    <table class="table manage-candidates-top mb-0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="th.order.products"/></th>
                            <th><fmt:message key="th.total"/></th>
                            <th><fmt:message key="th.status"/></th>
                            <th><fmt:message key="th.date"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty requestScope.userOrders}">
                            <div class="mx-auto" style="text-align: center">
                                <h5 class="card-title"><fmt:message key="head.order.empty"/></h5>
                                <img src="${pageContext.request.contextPath}/img/sad.png" style="width: 40%">
                            </div>
                        </c:if>
                        <c:if test="${not empty requestScope.userOrders}">
                            <c:forEach items="${requestScope.userOrders}" var="order">
                                <tr class="candidates-list">
                                    <td class="title">
                                            ${order.id}
                                    </td>
                                    <td class="title">
                                        <c:forEach items="${order.orderItems}" var="orderItem">
                                            <ul class="list-group mb-3">
                                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                    <div>
                                                        <h5 class="card-title">
                                                            <fmt:message key="head.vol"/>
                                                            <span>${orderItem.volume.number}</span>: ${orderItem.volume.title}
                                                        </h5>
                                                        <div>
                                                            <ul>
                                                                <li>ISBN: <span>${orderItem.volume.isbn}</span></li>
                                                                <li><fmt:message key="li.pageCount"/>
                                                                    <span>${orderItem.volume.pageCount}</span>
                                                                </li>
                                                                <li><fmt:message key="li.format"/>:
                                                                    <span>${orderItem.volume.format}</span></li>
                                                                <li><fmt:message key="li.size"/>:
                                                                    <span>${orderItem.volume.size}</span></li>
                                                                <li><fmt:message key="li.binding"/>:
                                                                    <span>${orderItem.volume.binding}</span></li>
                                                                <li><fmt:message key="li.releaseDate"/>:
                                                                    <span>${orderItem.volume.releaseDate}</span>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <b class="text-success">${orderItem.quantity} <fmt:message
                                                                key="b.pieces.short"/></b> x
                                                        <b class="text-danger"> ${orderItem.fixedPrice} ₸</b>
                                                    </div>
                                                </li>
                                            </ul>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <span><b>${order.totalPrice}</b> <fmt:message key="span.currency.tenge"/></span>
                                    </td>
                                    <td>
                                        <span class="text-primary"><b>${order.status.name}</b></span>
                                    </td>
                                    <td>
                                        <span><i>${order.createdDate}</i></span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
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
