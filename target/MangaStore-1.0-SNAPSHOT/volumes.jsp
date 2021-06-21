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
    <title>MangaStore &ndash; <fmt:message key="title.volumes"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" style="margin-top: 100px;">
    <div class="row" style="background-color: #f5f5f5;">
        <div class="col-4 mt-3 mb-3">
            <img src="data:image/jpeg;base64,${requestScope.manga.cover}" alt="cover" class="img-thumbnail">
        </div>
        <div class="col-8 mt-3">
            <h2>
                <div class="row">
                    <div class="col-10">${requestScope.manga.title}</div>
                    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                        <form action="PrepareEditMangaPage" method="get" class="col-2 float-right">
                            <div class="form-group">
                                <input type="hidden" name="mangaID" value="${requestScope.manga.id}"
                                       class="form-control" required>
                                <button type="submit" class="btn-link">
                                    <i class="fas fa-edit"></i>
                                </button>
                            </div>
                        </form>
                    </c:if>
                </div>
            </h2>
            <p>${requestScope.manga.description}</p>
        </div>
    </div>
    <div class="row" style="background-color: #ffffff;">
        <div class="col-4 mt-3">
            <ul class="list-items">
                <li><fmt:message key="li.releaseDate"/>: <span>${requestScope.manga.releaseDate}</span></li>
                <li><fmt:message key="th.status"/>: <span>
                        class="text-success">${requestScope.manga.releaseStatus.name}</span>
                </li>
                <li><fmt:message key="th.publisherName"/>: <a href="#">${requestScope.manga.publisher.name}</a></li>
                <li><fmt:message key="li.language"/>:
                    <span class="text-uppercase text-danger border border-danger rounded">
                <c:choose>
                    <c:when test="${requestScope.manga.languageID eq Constants.localeEnglishID}">
                        <fmt:message key="select.option.en"/>
                    </c:when>
                    <c:when test="${requestScope.manga.languageID eq Constants.localeRussianID}">
                        <fmt:message key="select.option.ru"/>
                    </c:when>
                </c:choose>
                </span>
                </li>
                <li><fmt:message key="li.genres"/>:
                    <ul>
                        <c:forEach items="${requestScope.manga.genres}" var="genre">
                            <li><a href="#">${genre.name}</a>
                                <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                                    <form action="DeleteGenreFromManga" method="post" style="display: inline-block">
                                        <input type="hidden" name="genreID" value="${genre.id}" required>
                                        <input type="hidden" name="mangaID" value="${requestScope.manga.id}" required>
                                        <button type="submit" class="btn-link">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </form>
                                </c:if>
                            </li>
                        </c:forEach>
                        <c:if test="${not empty requestScope.hiddenInputError}">
                            <small class="form-text text-danger">
                                <fmt:message key="small.error.invalid.hidden"/>
                            </small>
                        </c:if>
                    </ul>
                </li>
                <li><fmt:message key="li.authors"/>:
                    <ul>
                        <c:forEach items="${requestScope.manga.authors}" var="author">
                            <li>
                                <a href="#">${author.firstName} ${author.middleName} ${author.lastName}</a>
                                <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                                    <form action="DeleteAuthorFromManga" method="post" style="display: inline-block">
                                        <input type="hidden" name="authorID" value="${author.id}" required>
                                        <input type="hidden" name="mangaID" value="${requestScope.manga.id}" required>
                                        <button type="submit" class="btn-link">
                                            <i class="fas fa-times"></i>
                                        </button>
                                    </form>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                    <hr>
                    <li>
                        <form action="AddAuthorToManga" method="post">
                            <input type="hidden" name="mangaID" value="${requestScope.manga.id}" required>
                            <input type="text" name="firstName" class="form-control" placeholder="first name" required>
                            <input type="text" name="middleName" class="form-control" placeholder="middle name">
                            <input type="text" name="lastName" class="form-control" placeholder="last name">
                            <br>
                            <c:if test="${not empty requestScope.notUniqueMangaAuthorError}">
                                <small class="form-text text-danger"><fmt:message
                                        key="small.error.author.exists"/></small>
                            </c:if>
                            <c:if test="${not empty requestScope.authorNameError}">
                                <small class="form-text text-danger"><fmt:message
                                        key="small.error.author.name"/></small>
                            </c:if>
                            <br>
                            <button style="width: 100%" type="submit" class="btn btn-outline-primary">
                                <i class="fas fa-plus"></i><fmt:message key="button.add.author"/>
                            </button>
                        </form>
                    </li>
                    <hr>
                    <li>
                        <fmt:message key="li.genres"/>:
                        <form action="AddGenreToManga" method="post">
                            <input type="hidden" name="mangaID" value="${requestScope.manga.id}" required>
                            <c:forEach items="${requestScope.genres}" var="genre">
                                <div class="form-check">
                                    <input name="genreID" value="${genre.id}" class="form-check-input" type="checkbox">
                                    <label class="form-check-label">
                                        <a href="#">${genre.name}</a>
                                    </label>
                                </div>
                            </c:forEach>
                            <button type="submit" style="width: 100%" class="btn btn-outline-primary">
                                <i class="fas fa-plus"></i><fmt:message key="button.add.genres"/>
                            </button>
                        </form>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="col-8  mt-3">
            <div class="card text-center">
                <div class="card-header">
                    <ul class="nav nav-tabs card-header-tabs">
                        <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                            <li class="nav-item">
                                <form action="PrepareAddVolumePage" method="get">
                                    <input type="hidden" name="mangaID" value="${requestScope.manga.id}" required>
                                    <button style="width: 100%" type="submit" class="btn-link">
                                        <i class="fas fa-plus"></i><fmt:message key="button.add.volume"/>
                                    </button>
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
                            <li class="nav-item btn-link">
                                <fmt:message key="title.volumes"/>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <div class="card-body">
                    <c:forEach items="${requestScope.manga.volumes}" var="volume">
                        <div class="row g-2 mt-3">
                            <div class="col-md-3">
                                <img src="data:image/jpeg;base64,${volume.cover}" alt="cover" class="img-fluid"/>
                            </div>
                            <div class="col-md-9">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <fmt:message key="head.vol"/> <span>${volume.number}</span>: ${volume.title}
                                    </h5>
                                    <ul class="list-items">
                                        <li>ISBN: <span>${volume.isbn}</span></li>
                                        <li><fmt:message key="li.pageCount"/>: <span>${volume.pageCount}</span></li>
                                        <li><fmt:message key="li.format"/>: <span>${volume.format}</span></li>
                                        <li><fmt:message key="li.size"/>: <span>${volume.size}</span></li>
                                        <li><fmt:message key="li.binding"/>: <span>${volume.binding}</span></li>
                                        <li class="text-success fw-bold"><fmt:message key="th.price"/>:
                                            <span>${volume.price}</span> <fmt:message key="span.currency.tenge"/>
                                        </li>
                                        <li><fmt:message key="li.releaseDate"/>: <span>${volume.releaseDate}</span></li>
                                    </ul>
                                    <div class="row card-footer">
                                        <div class="col">
                                            <c:if test="${sessionScope.user.roleID ne Constants.roleAdminID}">
                                                <form action="AddToCart" method="post">
                                                    <input type="hidden" name="volumeID" value="${volume.id}">
                                                    <button style="width: 100%" type="submit"
                                                            class="btn btn-outline-primary">
                                                        <fmt:message key="button.add.toCart"/>
                                                    </button>
                                                </form>
                                            </c:if>
                                            <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                                                <form action="PrepareEditVolumePage" method="get">
                                                    <input type="hidden" name="volumeID" value="${volume.id}" required>
                                                    <button style="width: 100%" type="submit"
                                                            class="btn btn-outline-primary">
                                                        <fmt:message key="button.edit"/>
                                                    </button>
                                                </form>
                                            </c:if>
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
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>