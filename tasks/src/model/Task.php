<?php
namespace TodoList\Model {

    class Task
    {
        private $id;
        private $label;
        private $status;
        private $date_task;

        public function __construct($id, $label, $status, $date_task)
        {
            $this->id=$id;
            $this->label=$label;
            $this->status=$status;
            $this->date_task=$date_task;
        }

        public function getId()
        {
            return $this->id;
        }


        public function setId($id)
        {
            $this->id = $id;
        }


        public function getLabel()
        {
            return $this->label;
        }


        public function setLabel($label)
        {
            $this->label = $label;
        }


        public function getStatus()
        {
            return $this->status;
        }


        public function setStatus($status)
        {
            $this->status = $status;
        }


        public function getDateTask()
        {
            return $this->date_task;
        }


        public function setDateTask($date_task)
        {
            $this->date_task = $date_task;
        }
    }
}
