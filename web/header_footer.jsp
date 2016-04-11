<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="style/style.css" type="text/css" />
  <link rel="stylesheet" href="style/reset.css">

    </head>
    <body>-->
<div id="page">
<% String hostName = request.getServerName();
    int port = request.getServerPort();
    Cookie cookie = new Cookie("server", hostName+ " " + port);
    response.addCookie(cookie);
    %>
    <header>
        <div><h2>
                Researchers Exchange Participation</h2>
        </div>
        <nav>
            <a href="UserController?action=about">About Us</a>
            <a href="UserController?action=how">How it works</a>
            <a href="UserController?action=main">Login</a>

        </nav>
    </header>
    <div id="footer">
     <p class="six">   <%out.print(cookie.getValue()); %>
         &copy; Researchers Exchange Participation</p>
    </div>
</div>
<!--    </body>
</html>-->
