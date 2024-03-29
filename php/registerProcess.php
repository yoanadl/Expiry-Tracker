<?php
// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Include database connection
    include "connect.php";

    // Get form data

    $username = $_POST["username"];
    $fullname = $_POST["fullname"];
    $pwd = $_POST["pwd"];
    $email = $_POST["email"];

    // Check if the username already exists
    $check_username_sql = "SELECT COUNT(*) FROM user WHERE username = ?";
    $check_username_stmt = $conn->prepare($check_username_sql);
    $check_username_stmt->bind_param("s", $username);
    $check_username_stmt->execute();
    $check_username_stmt->bind_result($username_count);
    $check_username_stmt->fetch();
    $check_username_stmt->close();

    if ($username_count > 0) {
        echo "Username already exists. Please choose a different username.";
    } else {
        // Prepare and execute SQL statement to insert data into the database
        $insert_sql = "INSERT INTO user (username, fullname, pwd, email) VALUES (?, ?, ?, ?)";
        $insert_stmt = $conn->prepare($insert_sql);
        $insert_stmt->bind_param("ssss", $username, $fullname, $pwd, $email);
        
        if ($insert_stmt->execute()) {
            $user_id = $insert_stmt->insert_id;
            echo "Registration successful!<br>";
        } else {
            echo "Error: " . $insert_stmt->error;
        }
        
        $insert_stmt->close();
    }

    $conn->close();

    // // Prepare and execute SQL statement to insert data into the database
    // $sql = "INSERT INTO user (user_id, username, fullname, pwd, email) VALUES (?, ?, ?, ?, ?)";
    // $stmt = $conn->prepare($sql);
    // $stmt->bind_param("sssss", $user_id, $username, $fullname, $pwd, $email);
    // if ($stmt->execute()) {
    //     $u_id = $stmt->insert_id;
    //     echo "Registration successful!</br>";
    //     echo "Your ID is: $u_id";
    // } else {
    //     echo "Error: " . $stmt->error;
    // }
}
?>