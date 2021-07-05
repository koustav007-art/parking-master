<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!-- 
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
     -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="starter-template">
                <h2>WELCOME ${message}</h2>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/userdetails/generateToken">Park Vehicle</a></li>
                    <li><a href="/userdetails/vacateParking">Vacate</a></li>
                    <li><a href="/userdetails/getHistory">Check History</a></li>
                    <li><a href="/userdetails/vacancyform">Check Vacancy</a></li>
                    <li><a href="/userdetails/rechargeform">Recharge Account</a></li>
                    <li><a href="/userdetails/rechargecheckform">Check Balance</a></li>
                    <a href="<c:url value='/userdetails/logout'/>">Click here to logout</a>  
                </ul>
            </div>
        </div>
    </nav>
    
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>