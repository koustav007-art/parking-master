<html>
   <body>
      
      <form action = "/parkinglot/register" method = "POST">
         Car Reg No: <input type = "text" name = "carNumber">
         <br />
         
  <input type="radio" id="Car" name="type" value="Car">
  <label for="Car">Car</label><br>
  <input type="radio" id="Bike" name="type" value="Bike">
  <label for="Bike">Bike</label><br>
  <input type="radio" id="Handicapped" name="type" value="Handicapped">
  <label for="Handicapped">Handicapped</label>
         
         <br />
         <br />

         Password: <input type = "password" name = "password" />
         Confirm Password: <input type = "text" name = "conf" />
         <input type = "submit" value = "Submit" />
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
      
   </body>
</html>