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
    <title>MangaStore &ndash; <fmt:message key="head.publisher.settings"/></title>

    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5" style="background-color: #f5f5f5; margin-top: 100px;">
    <c:if test="${(sessionScope.user.roleID eq Constants.roleAdminID) and
                    not sessionScope.user.banned and
                    sessionScope.user.statusID ne Constants.accessStatusDeletedID}">
        <div class="row">
            <div class="col-md-12">
                <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                    <c:if test="${not empty requestScope.emptyFieldError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.empty.field"/></small>
                    </c:if>
                    <c:if test="${not empty requestScope.notUniquePublisherError}">
                        <small class="form-text text-danger"><fmt:message key="small.error.publisher.exists"/></small>
                    </c:if>
                    <table class="table manage-candidates-top mb-0">
                        <thead>
                        <tr>
                            <th><fmt:message key="th.publisherName"/>*</th>
                            <th><fmt:message key="th.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <form action="AddNewPublisher" method="post">
                                <td class="title">
                                    <input type="text" name="publisherName" class="form-control" required>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-success"><fmt:message
                                            key="button.add"/></button>
                                </td>
                            </form>
                        </tr>
                        <c:forEach items="${requestScope.publishers}" var="publisher">
                            <tr>
                                <form action="EditPublisher" method="post">
                                    <td class="title">
                                        <input type="text" name="publisherName" value="${publisher.name}"
                                               class="form-control" required>
                                    </td>
                                    <td>
                                        <input type="hidden" name="publisherID" value="${publisher.id}" required>
                                        <button type="submit" class="btn btn-danger">
                                            <fmt:message key="button.save"/>
                                        </button>
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
