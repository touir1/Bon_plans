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
    private static $server_config;
    private static $pdo;

    private static function init_dependencies()
    {
        self::$server_config = include_once 'server_config.php';
        self::$pdo = null;
    }

    private static function init_class()
    {
        if(is_null(self::$pdo)) {
            self::init_dependencies();

            try {

                self::$pdo = new PDO(
                    "mysql:host=" . self::$server_config['database']['host'] . ";"
                    . "dbname=" . self::$server_config['database']['dbname'] . ";"
                    . "charset=utf8"
                    , self::$server_config['database']['username'], self::$server_config['database']['password']);

                // set the PDO error mode to exception
                self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                //set charset for names
                self::$pdo->setAttribute(PDO::MYSQL_ATTR_INIT_COMMAND, "SET NAMES 'utf8'");

                //echo "Connected successfully";
            } catch (PDOException $e) {
                echo $e->getCode() . '<BR>';
                echo "Connection failed: " . $e->getMessage() . "<BR>";
            }
        }
    }

    public function __construct()
    {
        if(is_null(self::$pdo)){
            self::init_class();
        }
    }

    public function __destruct()
    {
        self::$pdo = null;
    }

    private static function execute($request){
        //echo "<br>running: ".$request."<br>";

        $stmt = self::$pdo->prepare($request);
        self::$pdo->beginTransaction();

        try{
            $stmt->execute();
            self::$pdo->commit();

        } catch (PDOException $e) {
            self::$pdo->rollBack();
            echo $e->getCode().'<BR>';
            echo "Connection failed: " . $e->getMessage() . "<BR>";
            throw new Exception("failed to execute request: ".$request);
        }


        return $stmt;
    }

    public static function select($request)
    {
        $stmt = self::execute($request);
        $result = $stmt->fetchall(PDO::FETCH_ASSOC);

        $stmt->closeCursor();
        return $result;
    }

    public static function p_select($select_object)
    {
        $req = utils::concat_if_true("","SELECT ".utils::array_to_string($select_object->columns),true);
        $req = utils::concat_if_true($req," FROM ".utils::array_to_string($select_object->table_names),true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($select_object->conditions,"AND"),count($select_object->conditions) > 0);
        $req = utils::concat_if_true($req," GROUP BY ".utils::array_to_string($select_object->group),count($select_object->group) > 0);
        $req = utils::concat_if_true($req," HAVING ".utils::array_to_string($select_object->having),count($select_object->having) > 0);
        $req = utils::concat_if_true($req," ORDER BY ".utils::array_to_string($select_object->order_by)." ".$select_object->order,count($select_object->order_by) > 0);
        $req = utils::concat_if_true($req," LIMIT ".$select_object->limit." OFFSET ".$select_object->offset,$select_object->limit > 0);

        return self::select($req);
    }

    public static function insert($request)
    {
        $stmt =self::execute($request);
        $stmt->closeCursor();
    }

    public static function p_insert($insert_object)
    {
        $req = utils::concat_if_true("","INSERT INTO ".$insert_object->table_name,true);
        $req = utils::concat_if_true($req,"(".utils::array_to_string($insert_object->columns).")",true);
        $req = utils::concat_if_true($req," VALUES(".utils::array_to_string($insert_object->values).")",true);

        self::insert($req);
    }

    public static function update($request)
    {
        $stmt = self::execute($request);
        $stmt->closeCursor();
    }

    public static function p_update($update_object)
    {
        $req = utils::concat_if_true("","UPDATE ".$update_object->table_name,true);
        $req = utils::concat_if_true($req," SET ".utils::array_to_string($update_object->updated_data),true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($update_object->conditions,"AND"),true);

        self::update($req);
    }

    public static function delete($request)
    {
        $stmt = self::execute($request);
        $stmt->closeCursor();
    }

    public static function p_delete($delete_object)
    {
        $req = utils::concat_if_true("","DELETE FROM ".$delete_object->table_name,true);
        $req = utils::concat_if_true($req," WHERE ".utils::array_to_string($delete_object->conditions,"AND"),true);

        self::delete($req);
    }

}



