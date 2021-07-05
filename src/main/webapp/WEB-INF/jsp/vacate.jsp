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
    <br />
    <table>
            <tr>
                <td>Car No:${parking.carNumber}</td>
                <td>Car Type: ${parking.type}</td>
                <td>Intime: ${parking.intime}</td>
                <td>Outtime: ${parking.outtime}</td>
                <td>Charge: ${parking.cost}</td>
            </tr>
    </table>
</body>
</html>