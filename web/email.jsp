<%-- 
    Document   : email
    Created on : 23 Nov, 2015, 3:59:30 PM
    Author     : Preeti Harkanth
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Email</title>
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

      <label>Email* </label><input type="text" name="email" required="Email" />
    <button type="submit" class="btn" name="action" value="reset">Submit</button>
    
   
  </form>
</div>
<!--end log form -->
    </body>
</html>
