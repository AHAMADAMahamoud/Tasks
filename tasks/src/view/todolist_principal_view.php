<?php
/**
 * PHP File todolist_principal_view
 * @package TodoList\View
 * @author MHA14633
 */
namespace TodoList\view;
use TodoList\Controller\TaskController;
use TodoList\Model\Task;
$taskController = new TaskController();
?>

<form action="<?php $_SERVER['PHP_SELF']?>" method="post">
    <div class="form-group">
        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Entrer votre tâche" name="label" >
    </div>

    <div class="list-group">
       <?php echo  $taskController->getAllTasks(); ?>
    </div>

</form>
<?php
if (isset($_POST['label'])&&(!empty($_POST['label']))) {
    $task=new Task(null, $_POST['label'], 'todo', date("Y-m-d H:i:s"));
    if ($taskController->saveTask($task)) {
        header("Location: " . $_SERVER['PHP_SELF']);
    } else {
        echo "<div class='alert alert-danger' role='alert'>Unsaved Task!</div>";
    }
} elseif (!empty($_POST['check_done'])) {
    foreach ($_POST['check_done'] as $selectedTask) {
        if ($taskController->doneTask($selectedTask, 'done')) {
            header("Location: " . $_SERVER['PHP_SELF']);
        } else {
            echo "<div class='alert alert-danger' role='alert'>Unsaved Task!</div>";
        }
    }
}
?>
