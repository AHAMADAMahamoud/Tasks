<?php

/**
 * PHP File todolist_principal_view
 * @package TodoList\View
 * @author MHA14633
 */
namespace TodoList\api;

use TodoList\Controller\TaskController;
use TodoList\Model\Task;

$taskController = new TaskController();


$task_data = json_decode(file_get_contents("php://input"));

$task=new Task($task_data->id,$task_data->label, $task_data->status, $task_data->date_task);
$ok=$taskController->saveTask($task);

$response_array=null;

if($ok===true){
    http_response_code(200);

    $response_array=array(
        "error" => 0,
        "info" => "Task saved succeful"
    );
}else{
    http_response_code(500);

    $response_array=array(
        "error" => 2,
        "info" => "Unsaved Task "
    );
}

echo json_encode($response_array);