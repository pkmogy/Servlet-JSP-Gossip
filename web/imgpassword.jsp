<%--
  Created by IntelliJ IDEA.
  User: zzart02
  Date: 2019/6/19
  Time: 下午 04:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>測試驗證碼機制</title>
    <meta charset="UTF-8">
</head>
<body>
    <form action="pet" method="post">
        姓　名：<input type="text" name="user"><br>
        密　碼：<input type="password" name="passwd"><br>
        驗證碼：<input type="text" name="check"> <img src="password"/><br>
        <input type="submit" value="送出"/>
    </form>
</body>
</html>
