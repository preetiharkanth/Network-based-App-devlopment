<%@page import="java.util.List"%>
<%@page import="JavaBeans.study"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participate</title>
        <link rel="stylesheet" href="style/login.css">
        <link rel="stylesheet" href="style/style.css" type="text/css" />
        <link rel="stylesheet" href="style/reset.css">
        <link rel="stylesheet" href="style/table_css.css">

    </head>
    <body>
        <%@include file="header_footer_l.jsp"%>
        <%@include file="sidebar.jsp"%>
        <br><br>
        <label class="two"> Studies </label><br><br>
        <p>${msg}</p><br><br>
        <table id="part_table">
            <tr><th>Study Name</th>
                <th>Image</th>
                <th>Question</th>
                <th>Action</th>
            </tr>
            <%  List<study> studies = (List<study>) request.getAttribute("studies");
                for (study s : studies) {
            %>

            <tr class="alt">
                <!--<input type="hidden"  name ="code" value="${s.SCode}">-->
                <!--<td class="seven"> <input type="text" name="code"><%out.print(s.getSCode());%></td>-->
                <td><%out.println(s.getSName());%></td>
                <td ><img src="GetImage?file=<%out.print(s.getImageURL());%>" class="edtqus" alt="Question Image"></td>
                <td><%out.println(s.getQuestion());%></td>
                <td><form action="StudyController?code=<%out.print(s.getSCode());%>" method="post"><button name="action" value="participate">Participate</button></form></td>
            </tr><%}%>


        </table>
    </body>
</html>
