<html>
   <body>
      
      <form action = "/parkinglot/getHistory" method = "POST">
         Vehicle Reg No: <input type = "text" name = "carNumber">
         <br />
         <input type = "submit" value = "Submit" />
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
      
   </body>
</html>