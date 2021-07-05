<html lang="en">

<body>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <br />
         <nav class="navbar navbar-inverse">
        <div class="container">
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li> 
                </ul>
            </div>
        </div>
    </nav>
    <table>
            <tr>
                <td>${parking.carNumber}</td>
                <td>${parking.type}</td>
                <td>${parking.intime}</td>
            </tr>
    </table>
</body>
</html>