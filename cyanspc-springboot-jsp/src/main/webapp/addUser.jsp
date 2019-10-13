<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<title>添加用户</title>
</head>
<body>
<h1>添加用户</h1>
<form action="<%=basePath %>user/addUser" name="userForm" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="添加">
</form>

<script  type="text/javascript">
    function addUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>users/addUsers";
        form.method = "post";
        form.submit();
    }
</script>
</body>
</html>
