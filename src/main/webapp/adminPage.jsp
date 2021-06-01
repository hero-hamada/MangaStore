<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <title>Manga Store</title>
    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 50px;">
    <div class="mt-4">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="card col card-manga">
                <div class="card-body">
                    <h6 class="card-title"><fmt:message key="head.user.settings"/></h6>
                    <form action="DisplayAllUsers" method="get">
                        <input type="hidden" name="direction" value="users.jsp">
                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="button.edit"/>">
                    </form>
                </div>
            </div>
<%--            <div class="card col card-manga">--%>
<%--                <div class="card-body">--%>
<%--                    <h6 class="card-title"><fmt:message key="head.manga.settings"/></h6>--%>
<%--                    <form action="DisplayAllMangas" method="get">--%>
<%--                        <input type="hidden" name="direction" value="mangas.jsp">--%>
<%--                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="button.edit"/>">--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="card col card-manga">--%>
<%--                <div class="card-body">--%>
<%--                    <h6 class="card-title"><fmt:message key="head.volume.settings"/></h6>--%>
<%--                    <form action="DisplayAllVolumes" method="get">--%>
<%--                        <input type="hidden" name="direction" value="volumes.jsp">--%>
<%--                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="button.edit"/>">--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="card col card-manga">--%>
<%--                <div class="card-body">--%>
<%--                    <h6 class="card-title"><fmt:message key="head.author.settings"/></h6>--%>
<%--                    <form action="DisplayAllAuthors" method="get">--%>
<%--                        <input type="hidden" name="direction" value="authors.jsp">--%>
<%--                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="button.edit"/>">--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="card col card-manga">
                <div class="card-body">
                    <h6 class="card-title"><fmt:message key="head.order.settings"/></h6>
                    <form action="DisplayAllOrders" method="get">
                        <input type="hidden" name="direction" value="orders.jsp">
                        <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="button.edit"/>">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>