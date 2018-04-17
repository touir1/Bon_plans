<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 17/04/2018
 * Time: 21:15
 */

define('ROOT_PATH', dirname(__DIR__) . '/');

include_once ROOT_PATH."../utils/db_utils.php";
include_once ROOT_PATH."../utils/mysql_db_manager.php";
include_once ROOT_PATH."../Entity/Reservation.php";
include_once ROOT_PATH."interfaces/IReservation.php";

class ReservationImpl implements IReservation
{
    private static $dbm;
    private static $tableName = "reservation";
    private static $columns_without_id = ["date","urlBonRes","idClient","idPlan"];
    private static $columns_with_id = ["idReservation","date","urlBonRes","idClient","idPlan"];
    private static $id = "idReservation";

    public function __construct()
    {
        if(is_null(self::$dbm)){
            self::$dbm = new mysql_db_manager();
        }
    }

    public function save($entity)
    {
        $insert_obj = new insert_object();
        $insert_obj->table_name = [self::$tableName];
        $insert_obj->columns = self::$columns_without_id;
        $insert_obj->values = [
            new db_date($entity->getDate()),
            new db_string($entity->getUrlBonRes()),
            new db_int($entity->getIdClient()),
            new db_int($entity->getIdPlan())
        ];

        self::$dbm->p_insert($insert_obj);
    }

    public function update($entity)
    {

        $update_obj = new update_object();
        $update_obj->table_name = [self::$tableName];
        $update_obj->updated_data = [
            "date = ".new db_date($entity->getDate()),
            "urlBonRes = ".new db_string($entity->getUrlBonRes()),
            "idClient = ".new db_int($entity->getIdClient()),
            "idPlan = ".new db_int($entity->getIdPlan())
        ];
        $update_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($entity.getIdReservation())
        ];

        self::$dbm->p_update($update_obj);
    }

    public function selectAll()
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $reservation = new Reservation();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $reservation->$method($value);
            }
            array_push($result,$reservation);
        }

        return $result;
    }

    public function getByID($id)
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];
        $select_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($id)
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = new Reservation();

        if(!empty($result_db)){
            foreach ($result_db[0] as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $result->$method($value);
            }
        }
        return $result;
    }

    public function remove($id)
    {
        $delete_obj = new delete_object();
        $delete_obj->table_name = [self::$tableName];
        $delete_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($id)
        ];
    }

    public function selectAllOrderedAsc($sortField)
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];
        $select_obj->order_by = [$sortField];
        $select_obj->order = db_order::ASC;

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $reservation = new Reservation();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $reservation->$method($value);
            }
            array_push($result,$reservation);
        }

        return $result;
    }

    public function selectAllOrderedDesc($sortField)
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];
        $select_obj->order_by = [$sortField];
        $select_obj->order = db_order::DESC;

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $reservation = new Reservation();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $reservation->$method($value);
            }
            array_push($result,$reservation);
        }

        return $result;
    }

    public function findOne($paramName, $paramValue)
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];
        $select_obj->conditions = [
            self::$tableName.".".$paramName." = ".$paramValue
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $reservation = new Reservation();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $reservation->$method($value);
            }
            array_push($result,$reservation);
        }

        return $result;
    }

    public function findCountBy($paramName, $paramValue)
    {
        $select_obj = new select_object();
        $select_obj->table_names = [self::$tableName];
        $select_obj->columns = [
            "count(*) count"
        ];
        $select_obj->conditions = [
            self::$tableName.".".$paramName." = ".new db_string($paramValue)
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = 0;

        if(!empty($result_db)){
            $result = $result_db[0]['count'];
        }

        return $result;
    }
}