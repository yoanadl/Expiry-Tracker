<?php
$host = "localhost";
$dbname = "ExpiryTracker";
$username = "root";

// Create connection
$conn = new mysqli($host, $username, "", $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>