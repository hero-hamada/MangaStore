<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>MangaStore <fmt:message key="title.cart"/></title>

    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <div class="row">
        <div class="col-md-12">
            <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                <table class="table manage-candidates-top mb-0">
                    <thead>
                    <tr>
                        <th>Volume</th>
                        <th>Price x Quantity</th>
                        <th>Quantity</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.cartItems}" var="cartItem">
                        <tr class="candidates-list">
                            <td class="title">
                                <div class="candidate-list-details">
                                    <div class="candidate-list-info">
                                        <h5 class="card-title">
                                            Vol. <span>${cartItem.volume.number}</span>: ${cartItem.volume.title}
                                        </h5>
                                        <div class="candidate-list-option">
                                            <ul class="list-items">
                                                <li>ISBN: <span>${cartItem.volume.isbn}</span></li>
                                                <li>Pages count: <span>${cartItem.volume.pageCount}</span></li>
                                                <li>Format: <span>${cartItem.volume.format}</span></li>
                                                <li>Size: <span>${cartItem.volume.size}</span></li>
                                                <li>Price: <b>${cartItem.volume.price}</b> тг</li>
                                                <li>Binding: <span>${cartItem.volume.binding}</span></li>
                                                <li>Release date: <span>${cartItem.volume.releaseDate}</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <span><b>${cartItem.volume.price * cartItem.quantity}</b> тг</span>
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
                    <form action="CheckOutByCart" method="post" id="CheckOutForm">
                        <button type="submit" class="btn btn-danger"style="width: 100%">Checkout</button>
                    </form>
                </c:if>
                <c:if test="${empty requestScope.cartItems}">
                    <div class="mx-auto" style="text-align: center">
                        <h5 class="card-title">Your cart is empty</h5>
                        <img src="${pageContext.request.contextPath}/img/sad.png" style="width: 40%">
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
