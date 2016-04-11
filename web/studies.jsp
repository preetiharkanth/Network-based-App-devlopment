<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.study"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Studies</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        <link rel="stylesheet" href="style/table_css.css">
    <div id="fb-root"></div>
    <script>(function (d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id))
                return;
            js = d.createElement(s);
            js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5&appId=1574428886169713";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>
    <meta property="og:url"           content="http://assignment1-pharkant.rhcloud.com/NBADProject/" />
    <meta property="og:type"          content="website" />
    <meta property="og:title"         content="Your Website Title" />
    <meta property="og:description"   content="Your description" />
    <meta property="og:image"         content="http://www.your-domain.com/path/image.jpg" />
</head>
<body>
    <%@include file="header_footer_l.jsp"%>
    <label class="two"> My Studies </label><br><br>
    <a href="newstudy.jsp" class="four">Add New Study</a><br><br>
    <a href="main.jsp" class="four">&lt;&lt;&lt; Back to Main Page</a>
    <table id="part_table">
        <tr><th>Study Name</th>
            <th>Requested Participants</th>
            <th>Participants</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <% List<study> study1 = (List<study>) request.getAttribute("studies");
            if (study1 != null) {
                for (study s : study1) {
        %>

        <tr class="alt">
            <td class="seven"><input type="hidden"  name ="code"><%out.println(s.getSCode());%></td>
            <td><%out.println(s.getSName());%></td>
            <td><%out.println(s.getReqParticipants());%></td>
            <td><%out.println(s.getActParticipants());%></td>
            <td><form action="StudyController?code=<%out.print(s.getSCode());%>&amp;action=<%out.println(s.getSStatus());%>" method="post"><button><%out.println(s.getSStatus());%></button></form></td>
            <td><form action="StudyController?code=<%out.print(s.getSCode());%>" method="post"><button name="action" value="edit">Edit</button></form></td>
            <td><div class="fb-share-button" data-href="http://assignment1-pharkant.rhcloud.com/NBADProject/" data-layout="button_count"></div></td>
        </tr>
        <%}
                }%>


    </table>
</body>
</html>
