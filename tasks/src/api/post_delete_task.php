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
$ok=$taskController->deleteNumberTask($task_data->id);
$response_array=null;

if($ok===true){
    http_response_code(200);

    $response_array=array(
        "error" => 0,
        "info" => "Task deleted succeful"
    );
}else{
    http_response_code(500);

    $response_array=array(
        "error" => 2,
        "info" => "delete failed "
    );
}

echo json_encode($response_array);