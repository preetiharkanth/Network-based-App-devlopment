<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Study</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        
    </head>
    <body>
            <%@include file="header_footer_l.jsp"%>
            <label class="two">Editing a Study </label><br><br>
        <a href="main.jsp" class="four">&lt;&lt;&lt; Back to Main Page</a>
        
        <div class="log-form">
  
  <form action="StudyController" enctype="multipart/form-data" method="POST">
      <input type="hidden" name="code" value="${code}">
      <label>Study Name* </label><input type="text" name="name" value="${name}"  />
      <label>Question Text* </label><input type="text" name="question" value="${question}" />
      <label>Image*</label><img src="GetImage?file=${url}" class="edtqus" alt="Question Image"><input type="file" name="file" id="file" />
      <label> # Participants* </label><input type="text" name="noPart"  value="${noPart}" />
      
      <label>Description* </label><input type="text" name="description" value="${description}"  class="five"/>
      
      <button type="submit" class="btn" name="action" value="update">Update</button>
   </form>
</div>
    </body>
</html>
