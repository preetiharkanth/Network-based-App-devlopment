<%-- 
    Document   : resetpwd
    Created on : 23 Nov, 2015, 3:59:57 PM
    Author     : Preeti Harkanth
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
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
      
      <input type="hidden" name="email" required="Email" value="<%=request.getParameter("email")%>"/>
      <label>Password* </label><input type="password" name="password" required="Password" />
      <label>Confirm Password* </label><input type="password" name="confirmpassword" required="Confirm Password" /><br>
    <button type="submit" class="btn" name="action" value="newpassword">Reset Password</button>
    
   
  </form>
</div>
<!--end log form -->
    </body>
</html>
