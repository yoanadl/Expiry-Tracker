<?php
session_start();
include("connect.php");
require_once "USER.php";

$is_invalid = false;

if($_SERVER = ["REQUEST_METHOD"] === "POST"){
    if(isset ($_POST["username"]) && isset($_POST["pwd"])){
        $username = $_POST["username"];
        $pwd = $_POST["pwd"];

        //Check if the Username and Password is filled 
        $username = isset($_POST["username"]) ? intval($_POST["username"]) : 0;
        $pwd = isset($_POST["pwd"]) ? trim($_POST["pwd"]) : '';

        if ($username <= 0 || empty($pwd)) {
            $is_invalid = true;
        }else{
            $sql = $conn->prepare("SELECT * FROM user WHERE username = ? AND pwd = ?");
            $sql->bind_param("ss", $username, $pwd);
            $sql->execute();

            $result = $sql->get_result();
            echo $result->num_rows;

            if($result->num_rows == 1){
                $user = $result->fetch_assoc();
                // Create a new User instance
                $userObj = new User($user["user_id"], 
                $user["username"],
                $user["fullname"], 
                $user["pwd"], 
                $user["email"]);

                $_SESSION["username"] = $user["username"];
                //$_SESSION["login_user"] = $userObj;
            }

        }
    }
}

?>
