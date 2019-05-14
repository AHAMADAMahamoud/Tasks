<?php
/**
 * Description ... [TaskRepository.php]
 *
 * @project tasks
 * @author  MHA14633 <MHA14633@open-groupe.com>
 * @license Open SAS Private License
 * @since   07/05/2019
 */

namespace TodoList\Repository;

use TodoList\Model\Config\_db_connect;
use PDO;
use PDOException;
use TodoList\Model\Task;


/**
 * Class TaskRepository
 *
 * @package TodoList\Repository
 * @author  MHA14633 <MHA14633@open-groupe.com>
 * @license Open SAS Private License
 * @since   07/05/2019
 */
class TaskRepository extends _db_connect
{
    /**
     * PDO database object
     *
     * @var PDO
     */
    private $dataBase;


    /**
     * TaskRepository constructor.
     */
    public function __construct()
    {
        parent::__construct();
        $this->dataBase = parent::connect();

    }//end __construct()


    /**
     * Persist a task
     *
     * @param Task $task l'objet task
     *
     * @return bool
     */
    public function persist(Task $task) :bool
    {
        try {
            $stmt = $this->dataBase->prepare("INSERT INTO task(label, status, date_task) VALUES(:label, :status, :date_task)");
            $stmt->bindparam(":label", $task->getLabel());
            $stmt->bindparam(":status", $task->getStatus());
            $stmt->bindparam(":date_task", $task->getDateTask());

            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo $e->getMessage();
            return false;
        }

    }//end persist()


    /**
     * Get one object
     *
     * @param int $id
     *
     * @return Task
     */
    public function findOne(int $id):Task
    {
        $stmt = $this->dataBase->prepare("SELECT * FROM task WHERE id=:id");
        $stmt->bindParam(":id", $id);
        $stmt->execute();
        $stmt->setFetchMode(PDO::FETCH_CLASS, Task::class);
        return $stmt->fetch(PDO::FETCH_ASSOC);

    }//end findOne()


    /**
     * Save object
     *
     * @param Task $task object
     *
     * @return bool
     */
    public function save(Task $task) :bool
    {
        try {
            $stmt = $this->dataBase->prepare("UPDATE task SET  label=:label, status=:status, date_task=:date_task WHERE id=:id");
            $stmt->bindparam(":label", $task->getLabel());
            $stmt->bindparam(":status", $task->getStatus());
            $stmt->bindparam(":date_task", $task->getDateTask());
            $stmt->bindValue(":id", $task->getId());

            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo $e->getMessage();
            return false;
        }

    }//end save()


    /**
     * Update status
     *
     * @param int    $id       identifer
     * @param string $status   status
     * @param string   $dateTask date task
     *
     * @return bool
     */
    public function updateStatus(int $id, string $status, string $dateTask):bool
    {
        try {
            $stmt = $this->dataBase->prepare("UPDATE task SET status=:status, date_task=:date_task WHERE id=:id");
            $stmt->bindparam(":status", $status);
            $stmt->bindparam(":date_task", $dateTask);
            $stmt->bindparam(":id", $id);

            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo $e->getMessage();
            return false;
        }

    }//end updateStatus()


    /**
     * Get all task as array of set
     *
     * @return array
     */
    public function findAll()
    {
        $stmt = $this->dataBase->prepare("select * from task where status!='deleted' order by date_task");
        $stmt->execute();
        $stmt->setFetchMode(PDO::FETCH_ASSOC);

        return $stmt->fetchAll();

    }//end findAll()

    public function delete($id){
        try {
            $stmt =  $this->dataBase->prepare("delete from task  where id=:id");
            $stmt->bindParam(":id", $id);

            $stmt->execute();
            return true;
        } catch (PDOException $e) {
            echo $e->getMessage();
            return false;
        }
    }

}//end class
