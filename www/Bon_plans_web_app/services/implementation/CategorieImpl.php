<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 15/04/2018
 * Time: 15:50
 */

include_once "../../utils/db_utils.php";
include_once "../../utils/mysql_db_manager.php";
include_once "../../Entity/Categorie.php";
include_once "../interfaces/ICategorie.php";

class CategorieImpl implements ICategorie
{
    private static $dbm;
    private static $tableName = "categorie";
    private static $columns_without_id = ["titre","urlphoto"];
    private static $columns_with_id = ["idCategorie","titre","urlphoto"];
    private static $id = "idCategorie";

    public function __construct()
    {
        if(is_null(self::$dbm)){
            self::$dbm = new mysql_db_manager();
        }
    }



    public function save($entity)
    {
        $insert_obj = new insert_object();
        $insert_obj->table_name = self::$tableName;
        $insert_obj->columns = self::$columns_without_id;
        $insert_obj->values = [
            new db_string($entity->getTitre()),
            new db_string($entity->getUrlphoto())
        ];

        self::$dbm->p_insert($insert_obj);
    }

    public function update($entity)
    {
        $update_obj = new update_object();
        $update_obj->table_name = self::$tableName;
        $update_obj->updated_data = [
            "titre = ".new db_string($entity->getTitre()),
            "urlphoto = ".new db_string($entity->getUrlphoto())
        ];
        $update_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($entity.getIdCategorie())
        ];

        self::$dbm->p_update($update_obj);
    }

    public function selectAll()
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $categorie = new Categorie();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $categorie->$method($value);
            }
            array_push($result,$categorie);
        }

        return $result;
    }

    public function getByID($id)
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;
        $select_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($id)
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = new Categorie();

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
        $delete_obj->table_name = self::$tableName;
        $delete_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($id)
        ];
    }

    public function selectAllOrderedAsc($sortField)
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;
        $select_obj->order_by = [$sortField];
        $select_obj->order = db_order::ASC;

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $categorie = new Categorie();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $categorie->$method($value);
            }
            array_push($result,$categorie);
        }

        return $result;
    }

    public function selectAllOrderedDesc($sortField)
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;
        $select_obj->order_by = [$sortField];
        $select_obj->order = db_order::DESC;

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $categorie = new Categorie();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $categorie->$method($value);
            }
            array_push($result,$categorie);
        }

        return $result;
    }

    public function findOne($paramName, $paramValue)
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;
        $select_obj->conditions = [
            self::$tableName.".".$paramName." = ".$paramValue
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = [];

        foreach ($result_db as $val){
            $categorie = new Categorie();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $categorie->$method($value);
            }
            array_push($result,$categorie);
        }

        return $result;
    }

    public function findCountBy($paramName, $paramValue)
    {
        $select_obj = new select_object();
        $select_obj->table_names = self::$tableName;
        $select_obj->columns = [
            "count(*) count"
        ];
        $select_obj->conditions = [
            self::$tableName.".".$paramName." = ".$paramValue
        ];

        $result_db = self::$dbm->p_select($select_obj);
        $result = 0;

        if(!empty($result_db)){
            $result = $result_db[0][0];
        }

        return $result;
    }
}
