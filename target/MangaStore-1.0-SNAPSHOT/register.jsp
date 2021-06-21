<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Manga Store &ndash; <fmt:message key="button.register"/></title>
</head>
<body>
<div class="container mx-auto mt-2 mb-2" style="width: 500px;">
    <div class="row" style="text-align: center;">
        <img src="img/logo.png" style="width: 200px">
        <h3 id="registerHead" class="mt-3"><fmt:message key="head.register"/></h3>
    </div>
    <form id="registerForm" method="post" action="register" class="mt-2">
        <div class="form-group">
            <label for="registerUsername"><fmt:message key="label.login"/></label>
            <input type="text" name="userLogin" class="form-control" id="registerUsername" placeholder="Username" required>
            <small id="registerUsernameHelp" class="form-text text-danger">${requestScope.loginError}</small>
        </div>
        <div class="form-group">
            <label for="registerEmail"><fmt:message key="label.email"/></label>
            <input type="email" name="userEmail" class="form-control" id="registerEmail" placeholder="example@mail.ex" required>
            <small id="registerEmailHelp" class="form-text text-danger">${requestScope.emailError}</small>
        </div>
        <div class="form-group">
            <label for="registerPassword">
                <fmt:message key="label.password"/>
            </label>
            <input type="password" name="userPassword" class="form-control" id="registerPassword" placeholder="<fmt:message key="placeholder.password"/>" required>
            <small id="registerPasswordHelp" class="form-text text-danger">
                ${requestScope.passwordError}
            </small>
        </div>
        <div class="form-group">
            <label for="registerConfirmPassword"><fmt:message key="label.password.confirm"/></label>
            <input type="password" name="confirmPassword" class="form-control" id="registerConfirmPassword" placeholder="<fmt:message key="placeholder.password"/>" required>
            <small id="registerConfirmPasswordHelp" class="form-text text-danger">
                ${requestScope.passwordError}
            </small>
        </div>
        <div class="form-group">
            <label for="registerPostalCode"><fmt:message key="label.postal"/></label>
            <input type="text" name="userPostalCode" class="form-control" id="registerPostalCode" pattern="[0-9]{6}" placeholder="040600" required>
            <small id="registerPostalCodedHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group">
            <label for="registerAddress"><fmt:message key="label.address"/></label>
            <input type="text" name="userAddress" class="form-control" id="registerAddress" placeholder="Kazakhstan,Almaty" required>
            <small id="registerAddressHelp" class="form-text text-muted"></small>
        </div>
        <div class="form-group">
            <label for="registerPhone"><fmt:message key="label.phone"/></label>
            <input type="tel"  name="userPhone" id="registerPhone" class="form-control"pattern="[0-9]{10}" placeholder="7771112233" required>
            <small id="registerPhoneHelp" class="form-text text-muted"></small>
        </div>
        <button type="submit" class="btn btn-primary btn-block text-uppercase" style="width: 475px;">
            <fmt:message key="button.register"/>
        </button>
        <a class="d-block text-center mt-2 small" href="signIn.jsp">
            <fmt:message key="button.signIn"/>
        </a>
        <a class="d-block text-center mt-2 small" href="index.jsp">
            <fmt:message key="button.main"/>
        </a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>