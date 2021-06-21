<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<jsp:useBean id="Constants" class="com.epam.MangaStore.constants.Constants"/>

<nav class="navbar navbar-expand-sm navbar-light bg-light fixed-top">
    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>

    <a class="navbar-brand" href="index.jsp">
        <img src="img/logo.png" alt="logo" style="width: 150px; margin-left: 50px;">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse navbar-fixed-top row" id="navbarSupportedContent">
        <ul class="nav navbar-nav col-4">
            <li class="nav-item">
                <form action="FilterManga" method="get">
                    <input type="hidden" name="direction" value="mangas.jsp">
                    <input type="submit" class="nav-link" value="<fmt:message key="head.manga"/>">
                </form>
            </li>
        </ul>
        <ul class="nav navbar-nav col-8">
            <li class="nav-item">
                <form action="ChangeLocale" method="get">
                    <select name="locale" class="btn rounded-pill" onchange="this.form.submit();">
                        <option value="ru" <c:if test="${sessionScope.localeID eq Constants.localeRussianID}">selected</c:if>>
                            <fmt:message key="select.option.ru"/>
                        </option>
                        <option value="en" <c:if test="${sessionScope.localeID eq Constants.localeEnglishID}">selected</c:if>>
                            <fmt:message key="select.option.en"/>
                        </option>
                    </select>
                </form>

            </li>
            <li class="nav-item">
                <form action="Search" method="get">
                <div class="input-group">
                    <div class="form-outline">
                        <input type="search" name="title" class="form-control rounded-pill"
                                placeholder="<fmt:message key="placeholder.search"/>">
                    </div>
                    <button type="submit" class="btn rounded-pill">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
                </form>
            </li>
            <c:if test="${not empty sessionScope.user}">
                <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
                    <form action="DisplayCart" method="get">
                        <input type="hidden" name="direction" value="cart.jsp">
                        <button type="submit" class="btn btn-outline-primary">
                            <i class="fas fa-shopping-cart"></i>
                        </button>
                    </form>
                </c:if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUser" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user"></i>
                            ${sessionScope.user.login}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownUser">
                        <a class="dropdown-item" href="edit_profile.jsp"><fmt:message key="head.edit.profile"/></a>
                        <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
                            <form action="DisplayMyOrders" method="get">
                                <input type="hidden" name="direction" value="my_orders.jsp">
                                <input type="submit" class="form-control dropdown-item" value="<fmt:message key="nav.user.myOrders"/>">
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID}">
                            <a class="dropdown-item" href="admin_panel.jsp"><fmt:message key="head.admin.panel"/></a>
                        </c:if>
                        <form action="SignOut" method="post">
                            <input type="submit" class="form-control dropdown-item"
                                   value="<fmt:message key="button.signOut"/>">
                        </form>
                    </div>
                </li>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="signIn.jsp">
                        <i class="fas fa-user"></i>
                        <fmt:message key="button.signIn"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
