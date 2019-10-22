<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title>SSO Client</title>
</head>
<body>

    <div style="text-align: center;margin-top: 100px;">
        <h1>【${xxlUser.username}】login success.</h1>

        <a href="${request.contextPath}/logout">Logout</a>

        <a href="http://xxlssoclient2.com:8082/xxl-sso-token-sample-springboot/">sso-token</a>

    </div>

</body>
</html>