<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<jsp:useBean id="Constants" class="com.epam.MangaStore.constants.Constants"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Manga Store &ndash; <fmt:message key="head.edit.profile"/></title>
</head>
<body>
<c:if test="${not sessionScope.user.banned and sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
<div class="container mx-auto mt-2 mb-2" style="width: 500px;">
    <div class="row" style="text-align: center;">
        <img src="img/logo.png" style="width: 200px">
        <h3 class="mt-3"><fmt:message key="head.edit.profile"/></h3>
    </div>
    <form method="post" action="EditProfile" class="mt-2">
        <div class="form-group">
            <label><fmt:message key="label.email"/></label>
            <input type="email" name="userEmail" class="form-control" value="${sessionScope.user.email}" required>
            <c:if test="${not empty requestScope.emailFormatError}">
                <small class="form-text text-danger"><fmt:message key="small.error.email.format"/></small>
            </c:if>
            <c:if test="${not empty requestScope.emailExistsError}">
                <small class="form-text text-danger"><fmt:message key="small.error.email.format"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.password.old"/></label>
            <input type="password" name="oldPassword" class="form-control" placeholder="<fmt:message key="placeholder.password"/>" required>
            <c:if test="${not empty requestScope.passwordFormatError}">
                <small class="form-text text-danger"><fmt:message key="small.error.not.oldPassword"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label>
                <fmt:message key="label.password.new"/>
            </label>
            <input type="password" name="userPassword" class="form-control" placeholder="<fmt:message key="placeholder.password"/>" required>
            <c:if test="${not empty requestScope.notOldPasswordError}">
                <small class="form-text text-danger"><fmt:message key="small.error.password.format"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.postal"/></label>
            <input type="text" name="userPostalCode" class="form-control" value="${sessionScope.user.postalCode}" required>
            <c:if test="${not empty requestScope.postalCodeError}">
                <small class="form-text text-danger"><fmt:message key="small.error.postalCode.format"/></small>
            </c:if>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.address"/></label>
            <input type="text" name="userAddress" class="form-control" value="${sessionScope.user.address}" required>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.phone"/></label>
            <input type="tel"  name="userPhone" value="${sessionScope.user.phone}" class="form-control" required>
            <small id="registerPhoneHelp" class="form-text text-muted"></small>
            <c:if test="${not empty requestScope.phoneError}">
                <small class="form-text text-danger"><fmt:message key="small.error.phone.format"/></small>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary btn-block text-uppercase" style="width: 475px;">
            <fmt:message key="button.save"/>
        </button>
        <a class="d-block text-center mt-2 small" href="index.jsp">
            <fmt:message key="button.main"/>
        </a>
    </form>
    </c:if>
    <c:if test="${sessionScope.user.banned and sessionScope.user.statusID eq Constants.accessStatusDeletedID}">
        <img src="img/404.png" class="mx-auto" width="100%">
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>