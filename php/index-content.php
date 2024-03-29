<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css"> -->
</head>
<body>
    

    <div class="landing-container" style="text-align: center; margin-top: 100px;">
        <h1>Welcome</h1>

        <?php if (isset($user)): ?>
        
            <p>Hello <?= htmlspecialchars($user["user_name"]) ?></p>
            
            <p><a href="logout.php">Log out</a></p>
            
        <?php else: ?>
        
            <div class="button-container" style="margin-top: 20px;">
            <a href="register.html" style="text-decoration: none;">
                <button style="padding: 10px 20px; font-size: 18px;">Register</button>
            </a>
            </div>
            <div class="button-container" style="margin-top: 20px;">
                <a href="login.html" style="text-decoration: none;">
                    <button style="padding: 10px 20px; font-size: 18px;">Login</button>
                </a>
            </div>

        <?php endif; ?>
        
    </div>
    
</body>
</html>