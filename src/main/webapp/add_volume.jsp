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
    <title>MangaStore &ndash; <fmt:message key="button.add.volume"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" style="background-color: #f5f5f5; margin-top: 80px;">
    <c:if test="${(sessionScope.user.roleID eq Constants.roleAdminID) and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
    <div class="row">
        <div class="col-md-3 mt-5">
            <img src="img/defaultcover.jpg" alt="cover" class="img-fluid"/>
        </div>

        <form action="AddVolume" method="post" enctype="multipart/form-data" class="col-md-9 mt-5">
            <c:if test="${not empty requestScope.emptyFieldError}">
                <small class="form-text text-danger"><fmt:message key="small.error.empty.field"/></small>
            </c:if>
            <div class="row">
                <div class="form-group col-md-6">
                    <label><fmt:message key="label.title"/></label>
                    <input name="title" type="text" class="form-control" required>
                </div>
                <div class="form-group col-md-6">
                    <label><fmt:message key="th.price"/> (<fmt:message key="span.currency.tenge"/>)</label>
                    <input name="price" type="number" min="0" pattern="^[1-9][0-9]*|0$" class="form-control" required>
                </div>
            </div>
            <c:if test="${not empty requestScope.negativeValueError}">
                <small class="form-text text-danger"><fmt:message key="small.error.negative.value"/></small>
            </c:if>
            <hr>
            <div class="row">
                <div class="form-group col-md-5">
                    <label>ISBN-13</label>
                    <input name="isbn" type="text" class="form-control" placeholder="978-1974705061"
                           pattern="(?=[0-9]{13}$)|97[89][-.]?[0-9]{10}" required>
                </div>
                <c:if test="${not empty requestScope.isbnError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.invalid.isbn"/></small>
                </c:if>
                <div class="form-group col-md-4">
                    <label><fmt:message key="label.number"/></label>
                    <input name="number" type="number" min="1" pattern="^[1-9][0-9]*$"
                           class="form-control" required>
                </div>
                <c:if test="${not empty requestScope.numberError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.invalid.number"/></small>
                </c:if>
                <div class="form-group col-md-3">
                    <label><fmt:message key="li.pageCount"/></label>
                    <input name="pageCount" type="number" min="1"
                           pattern="^[1-9][0-9]*$" class="form-control" required>
                </div>
                <c:if test="${not empty requestScope.negativeValueError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.negative.value"/></small>
                </c:if>
            </div>
            <div class="row">
                <div class="form-group col-md-5">
                    <label><fmt:message key="li.binding"/></label>
                    <input name="binding" type="text" class="form-control" required>
                </div>
                <div class="form-group col-md-4">
                    <label><fmt:message key="li.size"/></label>
                    <input name="size" type="text" placeholder="245 x 170 x 1"
                           pattern="[0-9.]+[ ]?x[ ]?[0-9.]+[ ]?x[ ]?[0-9.]+" class="form-control" required>
                </div>
                <c:if test="${not empty requestScope.sizeError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.size.format"/></small>
                </c:if>
                <div class="form-group col-md-3">
                    <label><fmt:message key="li.size"/></label>
                    <input name="format" type="text" placeholder="70x100/16" pattern="[0-9.]+x[0-9.]+\/[0-9.]+"
                           class="form-control" required>
                </div>
                <c:if test="${not empty requestScope.formatError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.format"/></small>
                </c:if>
            </div>
            <hr>
            <br>
            <div class="form-group col-md-4">
                <label><fmt:message key="li.releaseDate"/></label>
                <input name="releaseDate" type="date" placeholder="${requestScope.volume.releaseDate}"
                       class="form-control" required>
            </div>
            <hr>
            <input type="hidden" name="mangaID" value="${requestScope.mangaID}"/>
            <input type="hidden" name="volumeID" value="${requestScope.volumeID}"/>
            <div class="form-group">
                <label><fmt:message key="label.cover.image"/></label>
                <input name="cover" type="file" accept="image/*" class="form-control-file">
            </div>
            <c:if test="${not empty requestScope.coverError}">
                <small class="form-text text-danger"><fmt:message key="small.error.invalid.cover"/></small>
            </c:if>
            <button type="submit" class="btn btn-primary btn-lg" style="float: right;">Save</button>
        </form>
        </c:if>
        <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
        <img src="img/404.png" class="mx-auto" width="100%">
        </c:if>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
