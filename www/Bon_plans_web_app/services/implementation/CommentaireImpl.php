<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 15/04/2018
 * Time: 17:22
 */

define('ROOT_PATH', dirname(__DIR__) . '/');

include_once ROOT_PATH."../utils/db_utils.php";
include_once ROOT_PATH."../utils/mysql_db_manager.php";
include_once ROOT_PATH."../Entity/Commentaire.php";
include_once ROOT_PATH."interfaces/ICommentaire.php";

class CommentaireImpl
{

    private static $dbm;
    private static $tableName = "commentaire";
    private static $columns_without_id = ["texte","date","nbJaime","nbJaimePas","idClient","idPlan"];
    private static $columns_with_id = ["idCommentaire","texte","date","nbJaime","nbJaimePas","idClient","idPlan"];
    private static $id = "idCommentaire";

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
            new db_string($entity->getTexte()),
            new db_date($entity->getDate()),
            new db_int($entity->getNbJaime()),
            new db_int($entity->getNbJaimePas()),
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
            "texte = ".new db_string($entity->getTexte()),
            "date = ".new db_date($entity->getDate()),
            "nbJaime = ".new db_int($entity->getNbJaime()),
            "nbJaimePas = ".new db_int($entity->getNbJaimePas()),
            "idClient = ".new db_int($entity->getIdClient()),
            "idPlan = ".new db_int($entity->getIdPlan())
        ];
        $update_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($entity.getIdCategorie())
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
            $comentaire = new Commentaire();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $comentaire->$method($value);
            }
            array_push($result,$comentaire);
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
        $result = new Commentaire();

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
            $comentaire = new Commentaire();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $comentaire->$method($value);
            }
            array_push($result,$comentaire);
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
            $comentaire = new Commentaire();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $comentaire->$method($value);
            }
            array_push($result,$comentaire);
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
            $comentaire = new Commentaire();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $comentaire->$method($value);
            }
            array_push($result,$comentaire);
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