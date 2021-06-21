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
        <title>MangaStore &ndash; <fmt:message key="head.user.settings"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <c:if test="${sessionScope.user.roleID eq Constants.roleAdminID and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
    <div class="row">
        <div class="col-md-12">
            <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                <table class="table manage-candidates-top mb-0">
                    <thead>
                    <tr>
                        <th><fmt:message key="th.user.login"/></th>
                        <th><fmt:message key="th.user.role"/></th>
                        <th><fmt:message key="th.status"/></th>
                        <th><fmt:message key="th.access"/></th>
                    </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${requestScope.users}" var="user">
                        <tr class="candidates-list">
                            <td class="title">
                                <div class="candidate-list-details">
                                    <div class="candidate-list-info">
                                        <div class="candidate-list-title">
                                            <h5 class="mb-0">${user.login}</h5>
                                        </div>
                                        <div class="candidate-list-option">
                                            <ul class="list-unstyled">
                                                <li><i class="fas fa-envelope"></i>
                                                        ${user.email}</li>
                                                <li><i class="fas fa-map-marker-alt pr-1"></i>
                                                        ${user.address}, ${user.postalCode}
                                                </li>
                                                <li><i class="fas fa-phone"></i>
                                                        ${user.phone}
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <span>
                                    <c:choose>
                                        <c:when test="${user.roleID eq Constants.roleUserID}">
                                            <fmt:message key="span.role.user"/>
                                        </c:when>
                                        <c:when test="${user.roleID eq Constants.roleAdminID}">
                                            <fmt:message key="span.role.admin"/>
                                        </c:when>
                                    </c:choose>
                                </span>
                            </td>
                                <form action="EditUserAccess" method="post">
                                <input type="hidden" name="userID" value="${user.id}" required>
                                <td class="candidate-list-favourite-time text-center">
                                    <select name="statusID" class="form-control">
                                        <option value="${Constants.accessStatusDeletedID}"
                                                <c:if test="${user.statusID eq Constants.accessStatusDeletedID}">
                                                    selected
                                                </c:if>
                                        >
                                            <fmt:message key="select.user.status.deactive"/>
                                        </option>
                                        <option value="${Constants.accessStatusActiveID}"
                                                <c:if test="${user.statusID eq Constants.accessStatusActiveID}">
                                                    selected
                                                </c:if>
                                        >
                                            <fmt:message key="select.user.status.active"/>
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <div class="form-check">
                                        <input class="form-check-input"
                                               type="checkbox"
                                               name="isUserBanned"
                                               value="true"
                                               <c:if test="${user.banned}">checked</c:if>
                                        >
                                        <label class="form-check-label">
                                            <fmt:message key="label.ban"/>
                                        </label>
                                    </div>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-danger"><fmt:message key="button.save"/></button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${sessionScope.user.roleID eq Constants.roleUserID}">
        <img src="img/404.png" class="mx-auto" width="100%">
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
