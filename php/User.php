<?php
class User {
    private $user_id;
    private $username;
    private $fullname;
    private $pwd;
    private $email;

    public function __construct($user_id, $username, $fullname, $pwd, $email) {
        $this->user_id = $user_id;
        $this->username = $username;
        $this->fullname = $fullname;
        $this->pwd = $pwd;
        $this->email = $email;
    }

    public function getUserId() {
        return $this->user_id;
    }

    public function setUserId($user_id) {
        $this->user_id = $user_id;
    }

    public function getUsername() {
        return $this->username;
    }

    public function setUsername($username) {
        $this->username = $username;
    }

    public function getFullName() {
        return $this->fullname;
    }

    public function setFullName($fullname) {
        $this->username = $fullname;
    }
    
    public function getPass() {
        return $this->pwd;
    }

    public function setPass($pwd) {
        $this->pwd = $pwd;
    }

    public function getEmail() {
        return $this->email;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

}
?>