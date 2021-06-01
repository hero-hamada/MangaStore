<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

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
<div class="container" style="margin-top: 80px;">
    <div class="row" style="background-color: #f5f5f5;">
        <div class="col-4 mt-3 mb-3">
            <img src="data:image/jpeg;base64,${manga.cover}" alt="cover" class="img-thumbnail">
        </div>
        <div class="col-8 mt-3">

            <h1>${requestScope.manga.title}</h1>
            <p>${requestScope.manga.description}</p>

        </div>
    </div>
    <div class="row" style="background-color: #ffffff;">
        <div class="col-4 mt-3">
            <ul class="list-items">
                <li>
                    Release date: <span>${requestScope.manga.releaseDate}</span>
                </li>
                <li>
                    Status: <span class="text-success">${requestScope.manga.status.name}</span>
                </li>
                <li>
                    Publisher: <a href="#">${requestScope.manga.publisher.name}</a>
                </li>
                <li>
                    Genre:
                    <c:forEach items="${requestScope.manga.genres}" var="genre">
                        <a href="#">${genre.name}</a>
                    </c:forEach>
                </li>
                <li>
                    Author:
                    <c:forEach items="${requestScope.manga.authors}" var="author">
                        <a href="#">${author.firstName} ${author.middleName} ${author.lastName}</a>
                        </br>
                    </c:forEach>
                </li>
            </ul>
        </div>
        <div class="col-8  mt-3">
            <div class="card text-center">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Newest</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Oldest</a>
                        </li>
                    </ul>
                </div>
                <c:forEach items="${requestScope.manga.volumes}" var="volume">
                    <div class="card-body">
                        <div class="row g-2 mt-3">
                            <div class="col-md-3">
                                <img src="data:image/jpeg;base64,${volume.cover}" alt="cover" class="img-fluid"/>
                            </div>
                            <div class="col-md-9">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        Vol. <span>${volume.number}</span>: ${volume.title}
                                    </h5>
                                    <ul class="list-items">
                                        <li>ISBN: <span>${volume.isbn}</span></li>
                                        <li>Pages count: <span>${volume.pageCount}</span></li>
                                        <li>Format: <span>${volume.format}</span></li>
                                        <li>Size: <span>${volume.size}</span></li>
                                        <li>Binding: <span>${volume.binding}</span></li>
                                        <li>Price: <span>${volume.price}</span> tenge</li>
                                        <li>Release date: <span>${volume.releaseDate}</span></li>
                                    </ul>
                                    <div class="row card-footer">
                                        <div class="col">
                                            <form action="AddToCart" method="get">
                                                <input type="hidden" name="volumeID" value="${volume.id}">
                                                <button style="width: 100%" type="submit"
                                                        class="btn btn-outline-primary">
                                                    Add to Cart
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr/>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
