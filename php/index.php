<?php

date_default_timezone_set('Asia/Singapore');

session_start();

include __DIR__ . '/../php/create-db.php';
include __DIR__ . '/../php/index-content.php';

if (isset($_SESSION["user_id"])) {
    
    $mysqli = require __DIR__ . "/../php/connect.php";
    
    $sql = "SELECT * FROM users
            WHERE user_id = {$_SESSION["user_id"]}";
            
    $result = $mysqli->query($sql);
    
    $user = $result->fetch_assoc();
}


?>
    