<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        <!--<link rel="" href="header_footer.jsp">-->
    </head>
    <body>
        <%@include file="header_footer.jsp"%>
      <div class="log-form">
  
  <form action='UserController'>
      <div>${msg}<br><br></div>
      <label>Name* </label><input type="text" name="name" required="name" />
      <label>Email* </label><input type="text" name="email" required="Email" />
      <label>Password* </label><input type="password" name="password" required="Password" />
      <label>Confirm Password* </label><input type="password" name="confirmpassword" required="Confirm Password" /><br>
    <button type="submit" class="btn" name="action" value="signup">Create Account</button>
    
   
  </form>
</div>
<!--end log form -->
    </body>
</html>
