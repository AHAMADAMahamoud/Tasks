<?php
require 'vendor/autoload.php';

header("Content-Type: application/json; charset=UTF-8");
header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");


require 'src/api/post_delete_task.php';
