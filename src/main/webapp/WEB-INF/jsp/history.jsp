<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<body>
         <nav class="navbar navbar-inverse">
        <div class="container">
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li> 
                </ul>
            </div>
        </div>
    </nav>
    This car is parked on:
    <br />
    <table>
        <c:forEach items="${parkingHistory}" var="Parking">
            <tr>
                <td>Car No:${Parking.carNumber}</td>
                <td>Car Type: ${Parking.type}</td>
                <td>Intime: ${Parking.intime}</td>
                <td>Outtime: ${Parking.outtime}</td>
                <td>Charge: ${Parking.cost}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>