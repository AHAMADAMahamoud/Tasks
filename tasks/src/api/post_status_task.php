<?php

/**
 * PHP File todolist_principal_view
 * @package TodoList\View
 * @author MHA14633
 */
namespace TodoList\api;

use TodoList\Controller\TaskController;

$taskController = new TaskController();


$task_data = json_decode(file_get_contents("php://input"));
$ok=$taskController->doneTask($task_data->id,$task_data->status);
$response_array=null;

if($ok===true){
    http_response_code(200);

    $response_array=array(
        "error" => 0,
        "info" => "Task updated succeful"
    );
}else{
    http_response_code(500);

    $response_array=array(
        "error" => 2,
        "info" => "Update failed "
    );
}

echo json_encode($response_array);