<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        
    </head>
    <body>
         <%@include file="header_footer.jsp"%>
      <div class="log-form">
  
  <form action='UserController'>
      <div>${msg}<br><br></div>
      <label>Email Address* </label><input type="text" name="username" required="Email Id" />
      <label>Password* </label><input type="password" name="password" required="Password" /><br>
    <button type="submit" class="btn" name="action" value="login">Login</button>
    <a class="forgot" href="email.jsp">Forgot Password?</a>
    <br><br>
        <a class="signup" href="Signup.jsp">Sign up for a new account </a>
  </form>
</div>
<!--end log form -->
    </body>
</html>
