<html>
   <body>
      
      <form action = "/userdetails/recharge" method = "POST">
         Car Reg No: <input type = "text" name = "carNumber">
         Recharge Amount: <input type = "text" name = "rechargeAmt">
         <br />
         <input type = "submit" value = "Submit" />
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
      
   </body>
</html>