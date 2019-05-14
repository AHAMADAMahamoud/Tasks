<?php
/**
 * PHP File todolist_principal_view
 * @package TodoList\View
 * @author MHA14633
 */
namespace TodoList\api;

use TodoList\Controller\TaskController;

$taskController = new TaskController();

http_response_code(200);
echo json_encode($taskController->getWsTask());