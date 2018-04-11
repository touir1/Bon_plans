<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 06/02/2018
 * Time: 19:28
 */

include_once "utils.php";
include_once "db_utils.php";

class mysql_db_manager
{
    private $server_config;
    private $pdo;

    private function init_dependencies()
    {
        $this->server_config = include_once 'server_config.php';
        $this->pdo = null;
    }

    public function __construct()
    {
        $this->init_dependencies();

        try {

            $this->pdo = new PDO(
                "mysql:host=".$this->server_config['database']['host'].";"
                ."dbname=".$this->server_config['database']['dbname'].";"
                ."charset=utf8"
                , $this->server_config['database']['username'], $this->server_config['database']['password']);

            // set the PDO error mode to exception
            $this->pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            //set charset for names
            $this->pdo->setAttribute(PDO::MYSQL_ATTR_INIT_COMMAND , "SET NAMES 'utf8'");

            //echo "Connected successfully";
        } catch (PDOException $e) {
            echo $e->getCode().'<BR>';
            echo "Connection failed: " . $e->getMessage() . "<BR>";
        }

    }

    public function __destruct()
    {
        $this->pdo = null;
    }

    private function execute($request){
        //echo "<br>running: ".$request."<br>";

        $stmt = $this->pdo->prepare($request);
        $this->pdo->beginTransaction();

        try{
            $stmt->execute();
            $this->pdo->commit();

        } catch (PDOException $e) {
            $this->pdo->rollBack();
            echo $e->getCode().'<BR>';
            echo "Connection failed: " . $e->getMessage() . "<BR>";
            throw new Exception("failed to execute request: ".$request);
        }


        return $stmt;
    }

    public function select($request)
    {
        $stmt = $this->execute($request);
        $result = $stmt->fetchall(PDO::FETCH_ASSOC);

        $stmt->closeCursor();
        return $result;
    }

    public function p_select($select_object)
    {
        $req = utils::concat_if_true("","SELECT ".utils::array_to_string($select_object->columns),true);
        $req = utils::concat_if_true($req," FROM ".utils::array_to_string($select_object->table_names),true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($select_object->conditions,"AND"),count($select_object->conditions) > 0);
        $req = utils::concat_if_true($req," GROUP BY ".utils::array_to_string($select_object->group),count($select_object->group) > 0);
        $req = utils::concat_if_true($req," HAVING ".utils::array_to_string($select_object->having),count($select_object->having) > 0);
        $req = utils::concat_if_true($req," ORDER BY ".utils::array_to_string($select_object->order_by)." ".$select_object->order,count($select_object->order_by) > 0);
        $req = utils::concat_if_true($req," LIMIT ".$select_object->limit." OFFSET ".$select_object->offset,$select_object->limit > 0);

        return $this->select($req);
    }

    public function insert($request)
    {
        $stmt = $this->execute($request);
        $stmt->closeCursor();
    }

    public function p_insert($insert_object)
    {
        $req = utils::concat_if_true("","INSERT INTO ".$insert_object->table_name,true);
        $req = utils::concat_if_true($req,"(".utils::array_to_string($insert_object->columns).")",true);
        $req = utils::concat_if_true($req," VALUES(".utils::array_to_string($insert_object->values).")",true);

        $this->insert($req);
    }

    public function update($request)
    {
        $stmt = $this->execute($request);
        $stmt->closeCursor();
    }

    public function p_update($update_object)
    {
        $req = utils::concat_if_true("","UPDATE ".$update_object->table_name,true);
        $req = utils::concat_if_true($req," SET ".utils::array_to_string($update_object->updated_data),true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($update_object->conditions,"AND"),true);

        $this->update($req);
    }

    public function delete($request)
    {
        $stmt = $this->execute($request);
        $stmt->closeCursor();
    }

    public function p_delete($delete_object)
    {
        $req = utils::concat_if_true("","DELETE FROM ".$delete_object->table_name,true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($delete_object->conditions,"AND"),true);

        $this->delete($req);
    }

}



