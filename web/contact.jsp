<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        
        </head>
    <body> 
        <%@include file="header_footer_l.jsp"%>
    <label class="two"> Contact </label><br><br>
        <a href="main.jsp" class="four">&lt;&lt;&lt; Back to Main Page</a>
         <div class="log-form">
  
  <form action="EmailSendingServlet" method="POST">
      <label>Name* </label><input type="text" title="name" name ="user" />
      <label>Email* </label><input type="text" title="email" name="recipient" />
      <label>Message* </label><input type="text" title="content" name="content"  style="height:120px"/>
      <input type="hidden" name="useremail" value="">
      
    <button type="submit" class="btn">Submit</button>
   </form>
             <h3>When your friend logs in and participates in one user studies, you'll get 2 coins bonus.</h3>
</div>
</body>
</html>
