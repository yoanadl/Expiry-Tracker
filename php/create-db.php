<?php

date_default_timezone_set('Asia/Singapore');

$host = "localhost";
$username = "root";
$password = ""; // Add your database password if needed

$mysqli = new mysqli($host, $username, $password);

if ($mysqli->connect_errno) {
    die("Connection error: " . $mysqli->connect_error);
}

$sql_drop = "DROP DATABASE IF EXISTS ExpiryTracker;";

// Create a new database if it doesn't exist
$sql = "CREATE DATABASE IF NOT EXISTS ExpiryTracker;";

if ($mysqli->query($sql)) {
    // echo "Database created or already exists.\n";

    // Use the newly created or existing database
    $mysqli->select_db("ExpiryTracker");

    // Create a table for user information
    $createUserTableSQL = "CREATE TABLE IF NOT EXISTS user (
        user_id int NOT NULL AUTO_INCREMENT,
        username varchar(128) NOT NULL,
        fullname varchar(512) NOT NULL,
        pwd varchar(1024) NOT NULL,
        email varchar (1024) NOT NULL,
        PRIMARY KEY (user_id)
    );";

    if ($mysqli->query($createUserTableSQL)) {
        // echo "Table 'users' created or already exists.";
    } else {
        echo "Error creating user table: " . $mysqli->error;
    }

    // Create the list table
    $createListTableSQL = "CREATE TABLE IF NOT EXISTS list (
        list_id int NOT NULL AUTO_INCREMENT,
        list_name varchar(128) NOT NULL,
        list_desc varchar(1024) NOT NULL,
        PRIMARY KEY (list_id)
    );";

    if ($mysqli->query($createListTableSQL)) {
        // echo "Table 'services' created or already exists.\n";
    } else {
        echo "Error creating list table: " . $mysqli->error . "\n";
    }

    // Create the item table
    $createItemTableSQL = "CREATE TABLE IF NOT EXISTS item (
        item_id int NOT NULL AUTO_INCREMENT,
        item_name varchar(128) NOT NULL,
        item_desc varchar(1024) NOT NULL,
        default_exp_in_days int NOT NULL,
        list_id int,
        PRIMARY KEY (item_id),
        FOREIGN KEY (list_id) REFERENCES `list` (list_id)
    );";

    if ($mysqli->query($createItemTableSQL)) {
        // echo "Table 'service_bookings' created or already exists.\n";
    } else {
        echo "Error creating item table: " . $mysqli->error . "\n";
    }

    // Create the user + list table
    $createUserListTableSQL = "CREATE TABLE IF NOT EXISTS user_list (
        user_id INT,
        list_id INT,
        created_at DATETIME,
        PRIMARY KEY (user_id, list_id),
        FOREIGN KEY (user_id) REFERENCES user(user_id),
        FOREIGN KEY (list_id) REFERENCES list(list_id)
    );";

    if ($mysqli->query($createUserListTableSQL)) {
        // echo "Table 'service_bookings' created or already exists.\n";
    } else {
        echo "Error creating user list table: " . $mysqli->error . "\n";
    }

    // Create the user + item table
    $createUserItemTableSQL = "CREATE TABLE IF NOT EXISTS user_item (
        user_id INT,
        item_id INT,
        quantity INT,
        created_at DATETIME,
        PRIMARY KEY (user_id, item_id)
        
    );";

    if ($mysqli->query($createUserItemTableSQL)) {
        // echo "Table 'user_item' created or already exists.\n";

        // Add foreign key constraint for item_id referencing item(item_id)
        $addForeignKeySQL = "ALTER TABLE user_item ADD FOREIGN KEY (item_id) REFERENCES item(item_id)";
        if ($mysqli->query($addForeignKeySQL)) {
            // echo "Foreign key constraint added for 'item_id'.\n";
        } else {
            echo "Error adding foreign key constraint for 'item_id': " . $mysqli->error . "\n";
        }    
    
    } else {
        echo "Error creating user item table: " . $mysqli->error . "\n";
    }



    
} else {
    echo "Error creating database: " . $mysqli->error;
}

$mysqli->close();
?>
