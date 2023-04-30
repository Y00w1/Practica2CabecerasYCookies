<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
</head>
<body>

<h1>Login</h1>
<form action="/test/login" method="post">
    <div>
        <label for="username">Username</label>
        <div>
        <input type="text" name="username" id="username">
        </div>
    </div>
    <div>
        <label for="password">Password</label>
        <div>
            <input type="password" name="password" id="password">
        </div>
        <input type="submit" value="Login">
    </div>
</form>

</body>
</html>
