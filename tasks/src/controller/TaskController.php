<?php
/**
 * Description ... [TaskController.php]
 *
 * @project tasks
 * @author  MHA14633 <MHA14633@open-groupe.com>
 * @license Open SAS Private License
 * @since   07/05/2019
 */
namespace TodoList\Controller;

use TodoList\Model\Task;
use TodoList\Repository\TaskRepository;
/**
 * Class TaskController
 *
 * @package TodoList\Controller
 * @author  MHA14633 <MHA14633@open-groupe.com>
 * @license Open SAS Private License
 * @since   07/05/2019
 */
class TaskController
{
    /**
     * Task repository
     *
     * @var TaskRepository $taskRrepository
     */
    private $taskRrepository;


    /**
     * TaskController constructor.
     */
    public function __construct()
    {
        $this->taskRrepository = new TaskRepository();

    }//end __construct()


    /**
     * Get alltasks
     *
     * @return string
     */
    public function getAllTasks()
    {
        $taskData = $this->taskRrepository->findAll();
        $taskRow  = null;
        if ($taskData !== null) {
            foreach ($taskData as $task) {
                $taskRowButton   = "<button type='button' class='list-group-item d-flex justify-content-between align-items-center' > ";
                $taskRowCheckBox = "<input type='checkbox' name='check_done[]' value='".$task['id']."'>";

                if ($task['status'] === 'done') {
                    $taskRowLabel = '<del>'.$task['label'].'</del>';
                } else {
                    $taskRowLabel = $task['label'];
                }

                if ($task['status'] === 'done') {
                    $taskRowStatus = 'success';
                } else {
                    $taskRowStatus = 'primary';
                }

                $taskRowSpan = " <span class='badge badge-".$taskRowStatus."'badge-pill'>".$task['date_task']."</span>";

                $taskRow .= $taskRowButton.$taskRowCheckBox.$taskRowLabel.$taskRowSpan."</button>";
            }
        }//end if

        return $taskRow;

    }//end getAllTasks()


    /**
     * Get One task
     *
     * @param int $id task
     *
     * @return Task
     */
    public function getTask(int $id)
    {
        return $this->taskRrepository->findOne($id);

    }//end getTask()


    /**
     * Change Task status as done
     *
     * @param int    $id     task
     * @param string $status task status
     *
     * @return bool
     */
    public function doneTask(int $id, string $status) : bool
    {
        if ($id !== null) {
            return  $this->taskRrepository->updateStatus($id, $status, date('Y-m-d H:i:s'));
        }

    }//end doneTask()


    /**
     * Save task
     *
     * @param Task $task the task
     *
     * @return bool
     */
    public function saveTask(Task $task) : bool
    {
        if ($this->isNew($task) === true) {
            return  $this->taskRrepository->persist($task);
        } else {
            return $this->taskRrepository->save($task);
        }

    }//end saveTask()


    /**
     * Verify if task is new
     *
     * @param Task $task the task
     *
     * @return bool
     */
    private function isNew(Task $task):bool
    {
        return ($task->getId() == null);

    }//end isNew()

    public function getWsTask()
    {
        $taskData = $this->taskRrepository->findAll();
        $taskRow  = null;
        $task_array=array();

        if ($taskData !== null) {

            foreach ($taskData as $task) {
                $task_object=array(
                    "id" => $task['id'],
                    "label" => $task['label'],
                    "status" => $task['status'],
                    "date_task" => $task['date_task']
                );

                array_push($task_array, $task_object);

            }

             return $task_array;
        }

    }//end saveTask()

    public function deleteTask(int $id){
        return $this->taskRrepository->delete($id);
    }
}//end class
