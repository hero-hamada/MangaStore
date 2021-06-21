<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Manga Store &ndash; <fmt:message key="head.manga"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <div class="row">
        <div class="col-3">
            <nav class="navbar navbar-expand-lg navbar-light bg-white card sidebar mt-4">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidebar"
                        aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                    <form action="PrepareAddMangaPage" method="post">
                        <button style="width: 100%" type="submit"
                                class="btn btn-outline-primary">
                            <fmt:message key="button.add.manga"/>
                        </button>
                    </form>
                </c:if>
                <div class="collapse navbar-collapse card-body" id="sidebar">
                    <form action="FilterManga" method="get">
                        <h3 class="card-title"><fmt:message key="head.select.category"/></h3>
                        <hr/>
                        <label class="card-title"></label>
                        <div class="form-check form-check-inline">
                            <input name="genreID" type="radio" class="form-check-input" value="${Constants.genreIDAll}"
                                   <c:if test="${empty requestScope.genreID}">checked</c:if>>
                            <label class="form-check-label"><fmt:message key="label.all"/></label>
                        </div>
                        <c:forEach items="${requestScope.genres}" var="genre">
                            <div class="form-check form-check-inline">
                                <input name="genreID" type="radio" value="${genre.id}" class="form-check-input"
                                <c:if test="${genre.id eq requestScope.genreID}">checked</c:if>>
                                <label class="form-check-label">${genre.name}</label>
                            </div>
                        </c:forEach>
                        <hr/>
                        <button class="btn btn-primary justify-content-center" type="submit" style="width: 100%">
                            <fmt:message key="button.search"/>
                        </button>
                    </form>
                </div>
                <hr/>
            </nav>
        </div>
        <div class="col-9">
            <div class="mt-4">
                <div class="row row-cols-1 row-cols-md-4 g-4">
                    <c:forEach items="${requestScope.mangas}" var="manga">
                        <div class="card col card-manga">
                            <img class="card-img-top" src="data:image/jpeg;base64,${manga.cover}"/>
                            <div class="card-body">
                                <h6 class="card-title">${manga.title}</h6>
                                <p class="card-text text-primary">${manga.releasingStatus.name}</p>
                                <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                                    <form action="DisplayAllVolumes" method="get">
                                        <input type="hidden" name="mangaID" value="${manga.id}">
                                        <input type="submit" class="btn btn-outline-primary"
                                               value="<fmt:message key="button.viewMore"/>">
                                    </form>
                                </c:if>
                                <c:if test="${sessionScope.user.roleID ne Constants.roleAdminID}">
                                    <form action="DisplayActiveVolumes" method="get">
                                        <input type="hidden" name="mangaID" value="${manga.id}">
                                        <input type="submit" class="btn btn-outline-primary"
                                               value="<fmt:message key="button.viewMore"/> ">
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>

</html>
