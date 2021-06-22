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
    <title>MangaStore &ndash; <fmt:message key="title.edit.manga"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" style="background-color: #f5f5f5; margin-top: 80px;">
    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
    <div class="row">
        <div class="col-md-3 mt-5">
            <img src="data:image/*;base64,${requestScope.manga.cover}" alt="cover" class="img-fluid"/>
            <br>
            <form action="DisplayAllVolumes" method="get">
                <input type="hidden" name="mangaID" value="${requestScope.manga.id}">
                <button type="submit" class="btn-link"><i class="fas fa-arrow-left fa-3x"></i></button>
            </form>
        </div>
        <form action="EditManga" method="post" enctype="multipart/form-data" class="col-md-9 mt-5">
            <c:if test="${not empty requestScope.emptyFieldError}">
                <small class="form-text text-danger"><fmt:message key="small.error.empty.field"/></small>
            </c:if>
            <div class="row">
                <div class="form-group col-md-8">
                    <label><fmt:message key="label.title"/></label>
                    <input name="title" type="text" class="form-control" value="${requestScope.manga.title}" required>
                </div>
                <div class="form-group col-md-4">
                    <select name="languageID" class="form-control">
                        <option value="${Constants.localeRussianID}"
                                <c:if test="${requestScope.manga.languageID eq Constants.localeRussianID}">selected</c:if>>
                            <fmt:message key="select.option.ru"/>
                        </option>
                        <option value="${Constants.localeEnglishID}"
                                <c:if test="${requestScope.manga.languageID eq Constants.localeEnglishID}">selected</c:if>>
                            <fmt:message key="select.option.en"/>
                        </option>
                    </select>
                    <c:if test="${not empty requestScope.languageNotExistsError}">
                        <small class="form-text text-danger"><fmt:message
                                key="small.error.not.exists.language"/></small>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label><fmt:message key="label.description"/></label>
                    <textarea name="description" class="form-control" rows="3"
                              required>${requestScope.manga.description}</textarea>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="form-group col-md-6">
                    <label><fmt:message key="li.releaseDate"/></label>
                    <input name="releaseDate" type="date" value="${requestScope.manga.releaseDate}"
                           class="form-control" required>
                </div>
                <div class="form-group col-md-6">
                    <label><fmt:message key="li.publisher"/></label>
                    <input name="publisherName" type="text" value="${requestScope.manga.publisher.name}"
                           class="form-control" required>
                </div>
                <c:if test="${not empty requestScope.publisherNotExistsError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.not.exists.publisher"/></small>
                </c:if>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label><fmt:message key="label.releasingStatus"/></label>
                    <br>
                    <c:forEach items="${requestScope.releasingStatuses}" var="status">
                        <div class="form-check form-check-inline">
                            <input name="releasingStatusID" type="radio" value="${status.id}" class="form-check-input"
                                   <c:if test="${requestScope.manga.releaseStatusID eq status.id}">checked</c:if>
                                   required>
                            <label class="form-check-label">${status.name}</label>
                        </div>
                    </c:forEach>
                    <c:if test="${not empty requestScope.releasingStatusNotExistsError}">
                        <small class="form-text text-danger">
                            <fmt:message key="small.error.not.exists.releasingStatus"/>
                        </small>
                    </c:if>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <select name="accessStatusID" class="form-control">
                    <option value="${Constants.accessStatusActiveID}"
                            <c:if test="${requestScope.manga.accessStatusID eq Constants.accessStatusActiveID}">
                                selected
                            </c:if>
                    >
                        <fmt:message key="select.user.status.active"/>
                    </option>
                    <option value="${Constants.accessStatusDeletedID}"
                            <c:if test="${requestScope.manga.accessStatusID eq Constants.accessStatusDeletedID}">
                                selected
                            </c:if>
                    >
                        <fmt:message key="select.user.status.deactive"/>
                    </option>
                </select>
            </div>
            <div class="form-group">
                <label><fmt:message key="label.cover.image"/></label>
                <input name="cover" type="file" accept="image/*" class="form-control-file">
            </div>
            <c:if test="${not empty requestScope.coverError}">
                <small class="form-text text-danger"><fmt:message key="small.error.invalid.cover"/></small>
            </c:if>
            <input type="hidden" name="mangaID" value="${requestScope.manga.id}"/>
            <input type="hidden" name="coverID" value="${requestScope.manga.coverID}"/>
            <button type="submit" class="btn btn-primary btn-lg" style="float: right;"><fmt:message key="button.save"/></button>
        </form>
        </c:if>
        <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
            <img src="img/404.png" class="mx-auto" width="100%">
        </c:if>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
