<?php
    class category{
        private $listId;
        private $listName;
        private $listDesc;

        public function __construct($listId, $listName, $listDesc){
            $this->listId = $listId;
            $this->listName = $listName;
            $this->listDesc = $listDesc;
    
        }

        public function getListId(){
            return $this->listId;
        }

        public function setListId($listId){
            $this->listId = $listId;
        }

        public function getListName(){
            return $this->listName;
        }

        public function setListName($listName){
            $this->listName =  $listName;

        }  

        public function getListDesc(){
            return $this->listDesc;
        }

        public function setListDesc($listDesc){
            $this->listDesc = $listDesc;
        }
      }
      
?>
