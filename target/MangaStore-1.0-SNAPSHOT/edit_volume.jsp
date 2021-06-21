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
    <title>MangaStore &ndash; <fmt:message key="title.edit.volume"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" style="background-color: #f5f5f5; margin-top: 80px;">
    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row">
            <div class="col-md-3 mt-5">
                <img src="data:image/*;base64,${requestScope.volume.cover}" alt="cover" class="img-fluid"/>
            </div>
            <form action="EditVolume" method="post" enctype="multipart/form-data" class="col-md-9 mt-5">
                <c:if test="${not empty requestScope.emptyFieldError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.empty.field"/></small>
                </c:if>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label><fmt:message key="label.title"/></label>
                        <input name="title" type="text" class="form-control" value="${requestScope.volume.title}"
                               required>
                    </div>
                    <div class="form-group col-md-6">
                        <label><fmt:message key="th.price"/> (<fmt:message key="span.currency.tenge"/>)</label>
                        <input name="price" type="number" min="0" value="${requestScope.volume.price}"
                               pattern="^[1-9][0-9]*|0$" class="form-control" required>
                    </div>
                    <c:if test="${not empty requestScope.negativeValueError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.negative.value"/></small>
                    </c:if>
                </div>
                <hr>
                <div class="row">
                    <div class="form-group col-md-5">
                        <label>ISBN-13</label>
                        <input name="isbn" type="text" class="form-control" value="${requestScope.volume.isbn}"
                               pattern="(?=[0-9]{13}$)|97[89][-.]?[0-9]{10}" required>
                    </div>
                    <c:if test="${not empty requestScope.isbnError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.invalid.isbn"/></small>
                    </c:if>
                    <div class="form-group col-md-4">
                        <label><fmt:message key="label.number"/></label>
                        <input name="number" type="number" min="1" value="${requestScope.volume.number}"
                               pattern="^[1-9][0-9]*$"
                               class="form-control" required>
                    </div>
                    <c:if test="${not empty requestScope.numberError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.invalid.number"/></small>
                    </c:if>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="li.pageCount"/></label>
                        <input name="pageCount" type="number" min="1" value="${requestScope.volume.pageCount}"
                               pattern="^[1-9][0-9]*$" class="form-control" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-5">
                        <label><fmt:message key="li.binding"/></label>
                        <input name="binding" type="text" value="${requestScope.volume.binding}"
                               pattern="[a-zA-Z0-9\s]+"
                               class="form-control" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label><fmt:message key="li.size"/></label>
                        <input name="size" type="text" value="${requestScope.volume.size}"
                               pattern="[0-9.]+[ ]?x[ ]?[0-9.]+[ ]?x[ ]?[0-9.]+" class="form-control" required>
                    </div>
                    <c:if test="${not empty requestScope.sizeError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.size.format"/></small>
                    </c:if>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="li.format"/></label>
                        <input name="format" type="text" value="${requestScope.volume.format}"
                               pattern="[0-9.]+x[0-9.]+\/[0-9.]+"
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
                    <input name="releaseDate" type="date" value="${requestScope.volume.releaseDate}"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <select name="accessStatusID" class="form-control">
                        <option value="${Constants.accessStatusActiveID}"
                                <c:if test="${requestScope.volume.accessStatusID eq Constants.accessStatusActiveID}">
                                    selected
                                </c:if>
                        >
                            <fmt:message key="select.user.status.active"/>
                        </option>
                        <option value="${Constants.userStatusDeletedID}"
                                <c:if test="${requestScope.volume.accessStatusID eq Constants.accessStatusDeletedID}">
                                    selected
                                </c:if>
                        >
                            <fmt:message key="select.user.status.deactive"/>
                        </option>
                    </select>
                </div>
                <c:if test="${not empty requestScope.accessStatusError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.invalid.accessStatus"/></small>
                </c:if>
                <hr>
                <div class="form-group">
                    <label><fmt:message key="label.cover.image"/></label>
                    <input name="cover" type="file" accept="image/*" class="form-control-file">
                </div>
                <c:if test="${not empty requestScope.coverError}">
                    <small class="form-text text-danger"><fmt:message key="small.error.invalid.cover"/></small>
                </c:if>
                <input type="hidden" name="volumeID" value="${requestScope.volume.id}"/>
                <input type="hidden" name="mangaID" value="${requestScope.volume.mangaID}"/>
                <input type="hidden" name="coverID" value="${requestScope.volume.coverID}"/>
                <button type="submit" class="btn btn-primary btn-lg" style="float: right;"><fmt:message key="button.save"/></button>
            </form>
        </div>
    </c:if>
    <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
        <img src="img/404.png" class="mx-auto" width="100%">
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
