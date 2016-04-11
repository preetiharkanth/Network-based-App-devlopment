<!DOCTYPE html>
<html>
    <head>

        <title>New Study</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>

    </head>
    <body>
        <%@include file="header_footer_l.jsp"%>
        <label class="two"> Adding a Study </label><br><br>
        <a href="main.jsp" class="four">&lt;&lt;&lt; Back to Main Page</a>
        <div class="log-form">

            <form action="StudyController" enctype="multipart/form-data" method="POST">
                ${msg}<br>
                <label>Study Name* </label><input type="text" name="name" required="name" />
                <label>Question Text* </label><input required="question" type="text" name="question"  />
                <label>Image*</label>
                <input type="file" name="file" id="file" required="file name"/>
                <label> # Participants* </label><input type="number" min="0" step="1" name="noPart" required="No. of Participants"/>

                <label>Description* </label><input type="text" name="description"  class="five" required="Description"/>

                <button type="submit" class="btn" name="action" value="add">Submit</button>
            </form>
        </div>
    </body>
</html>
