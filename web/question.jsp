<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        
    </head>
    <body>
        <%@include file="header_footer_l.jsp"%>
        <%@include file="sidebar.jsp"%>
        <br><br><label class="two"> Question </label><br><br>
        <div class="log-form">
        <form action="StudyController">
            <img src="GetImage?file=${url}" alt="Question Image" class="edtqus1"><br>
            <input class="seven" type="hidden" name="code" value="${code}">
            ${question} (1 Strongly Agree - 7 Strongly Disagree)
        <select name="rating">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
        </select>
        <button type="submit" class="btn" name="action" value="answer">Submit</button></form></div>
        
            
        </body>
</html>
