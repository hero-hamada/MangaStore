<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/3ceb327b1f.js" crossorigin="anonymous"></script>
    <script
            type="text/javascript"
            src="https://use.fontawesome.com/releases/v5.15.3/js/conflict-detection.js">
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <title>Manga Store</title>
    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container mt-5" style="background-color: #f5f5f5;">
    <main class="mb-4 mt-4">
        <h2 class="featurette-heading" style="margin-top: 100px;"><fmt:message key="head.main"/></h2>
        <div class="container marketing">
            <hr class="featurette-divider">
            <div class="row featurette">
                <div class="col-md-7">
                    <h3 class="featurette-heading"><fmt:message key="head.main.question1"/></h3>
                    <p class="lead"><fmt:message key="p.main.answer1"/></p>
                </div>
                <div class="col-md-5">
                    <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" alt="500x500" style="width: 500px; height: 500px;" src="img/drawing_process.png" data-holder-rendered="true">
                </div>
            </div>
            <hr class="featurette-divider">
            <div class="row featurette">
                <div class="col-md-7 order-md-2">
                    <h3 class="featurette-heading"><fmt:message key="head.main.question2"/></h3>
                    <p class="lead"><fmt:message key="p.main.answer2"/></p>
                </div>
                <div class="col-md-5 order-md-1">
                    <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" alt="500x500" src="img/drawing_process1.jpg" data-holder-rendered="true" style="width: 500px; height: 500px;">
                </div>
            </div>

            <hr class="featurette-divider">
            <div class="row featurette mb-4">
                <div class="col-md-7">
                    <h3 class="featurette-heading"><fmt:message key="head.main.question3"/></h3>
                    <p class="lead"><fmt:message key="p.main.answer3"/></p>
                </div>
                <div class="col-md-5">
                    <img class="featurette-image img-fluid mx-auto" data-src="holder.js/500x500/auto" src="img/mangabook.jpg" data-holder-rendered="true" style="width: 500px; height: 500px;">
                </div>
            </div>
        </div>
    </main>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>