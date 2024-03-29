<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href=""> <!-- Link to your CSS file -->
</head>
<body>
    <h1>Registration Form</h1>
    <form action="register.php" method="post">
        <p>Username: <input type="text" name="username" required autocomplete="off"></p>
        <p>Full Name: <input type="text" name="fullname" required autocomplete="off"></p> 
        <p>Password: <input type="text" name="pwd" required autocomplete="off"></p>
        <p>Email: <input type="email" name="email" required autocomplete="off"></p>
        <?php include "registerProcess.php";?>
        <p>
            <input type="submit" value="Register"></p>
            <input type="button" onclick="window.location.href='login.html';" value="Back to Login">
        </p>
    </form>
</body>
</html>
