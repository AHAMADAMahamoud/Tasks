<?php
namespace TodoList\Model\Config;

use PDO;
use PDOException;

/**
 * Class _db_connect
 *
 * @package TodoList\Model\Config
 */
class _db_connect
{
    private $con;

    private $hostName;
    private $databaseName;
    private $userName;
    private $password;

    /**
     * _db_connect constructor.
     */
    public function __construct()
    {
        $db_config = parse_ini_file('_db_conf.ini');
        $this->hostName = $db_config['DB_SERVER'];
        $this->databaseName = $db_config['DB_DATABASE'];
        $this->userName = $db_config['DB_USER'];
        $this->password = $db_config['DB_PASSWORD'];
    }

    /**
     * @return PDO
     */
    public function connect()
    {
        try {
            $con = new PDO('mysql:host='.$this->hostName.';dbname='.$this->databaseName, $this->userName, $this->password, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
            $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
            print "Erreur !: " . $e->getMessage() ;
            die();
        }
        return $con;
    }

    /**
     *
     */
    public function close()
    {
        $this->con = null;
    }
}
