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
    <title>MangaStore &ndash; <fmt:message key="title.users"/></title>

    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row" style="background-color: #f5f5f5; margin-top: 80px;">
        <div class="col-md-6 order-md-2 mb-4 mt-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart</span>
                <!-- <b class="badge badge-primary badge-pill">3</b> -->
            </h4>
            <ul class="list-group mb-3">
                <li class="d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Total price</h6>
                    </div>
                    <b class="text-danger">${requestScope.cartTotalPrice} tg</b>
                </li>
            </ul>
            <c:forEach var="cartItem" items="${requestScope.cartItems}">
            <ul class="list-group mb-3">
                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <form action="DisplayAllVolumes" method="get">
                            <input type="hidden" name="direction" value="volumes.jsp">
                            <input type="hidden" name="mangaID" value="${cartItem.volume.mangaID}">
                            <input
                                    type="submit"
                                    class="btn-link"
                                    value="Vol.${cartItem.volume.number}: ${cartItem.volume.title}"
                            >
                        </form>
                    </div>
                    <div>
                        <b class="text-success">${cartItem.quantity} pc</b> x <b class="text-danger"> ${cartItem.volume.price} tg</b>
                    </div>
                    <input type="hidden" name="volumePrice" value="${cartItem.volume.price}" required>
                    <input type="hidden" name="olumeId" value="${cartItem.volume.id}" required>
                </li>
            </ul>
            </c:forEach>
        </div>
        <div class="col-md-6 order-md-1 mt-4">
            <h4 class="mb-3">Shipping address</h4>
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
                        <form class="card p-2">
                            <button type="submit" class="btn btn-secondary">Edit</button>
                        </form>
                    </div>
                </div>
                <hr class="mb-4">

            <form action="ConfirmOrder" method="post">
                <button class="btn btn-primary btn-lg btn-block mb-4" type="submit">Confirm order</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
