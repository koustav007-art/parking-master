<html>
   <body>
      
      <form action = "/parkinglot/getVacancy" method = "POST">
  <input type="radio" id="Car" name="type" value="Car">
  <label for="Car">Car</label><br>
  <input type="radio" id="Bike" name="type" value="Bike">
  <label for="Bike">Bike</label><br>
  <input type="radio" id="Handicapped" name="type" value="Handicapped">
  <label for="Handicapped">Handicapped</label>
         <br />
         <input type = "submit" value = "Submit" />
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
      
   </body>
</html>