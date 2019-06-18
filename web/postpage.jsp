<%--
  Created by IntelliJ IDEA.
  User: zzart02
  Date: 2019/6/17
  Time: 下午 05:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表單上傳</title>
</head>
<body>
    //action=postbody or Upload
    <form action="postbody" method="post" enctype="multipart/form-data">
        Username: <input type="text" name="user"><br>
        Password: <input type="password" name="passwd"><br>
        Select File :<input type="file" name="filename" value="" /><br>
        <input type="submit" value="上傳" name="upload"/>
    </form>
</body>
</html>
