<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recommend</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        
    </head>
    <body>
        <%@include file="header_footer_l.jsp"%>
        <label class="two"> Recommend to a friend </label><br><br>
        <a href="main.jsp" class="four">&lt;&lt;&lt; Back to Main Page</a>
         <div class="log-form">
  
  <form action="UserController" method="Get">
      ${msg}
      <label>Name* </label><input type="text" title="name" name="user" />
      <label>Email* </label><input type="text" title="email" name="useremail" />
      <label> Friend's Email* </label><input type="text" title="femail" name="recipient"  />
      
      <label>Message* </label><input type="text" title="password" class="content"  class="five" name="content"/>
      
      <button type="submit" class="btn" name="action" value="recommend">Recommend</button>
   </form>
</div>
    </body>
</html>
