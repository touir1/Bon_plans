<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 17/04/2018
 * Time: 20:23
 */

define('ROOT_PATH', dirname(__DIR__) . '/');

include_once ROOT_PATH."../utils/db_utils.php";
include_once ROOT_PATH."../utils/mysql_db_manager.php";
include_once ROOT_PATH."../Entity/Plan.php";
include_once ROOT_PATH."interfaces/IPlan.php";

class PlanImpl implements IPlan
{
    private static $dbm;
    private static $tableName = "plan";
    private static $columns_without_id = ["description","urlPhoto","prixInitial",
        "nbPlaceTotal","dateDebut","dateFin","nbPlaceDispo","statut","nbJaime",
        "nbJaimePas","note","idClient","idAnnonceur","idCategorie"];

    private static $columns_with_id = ["idPlan","description","urlPhoto","prixInitial",
        "nbPlaceTotal","dateDebut","dateFin","nbPlaceDispo","statut","nbJaime",
        "nbJaimePas","note","idClient","idAnnonceur","idCategorie"];
    private static $id = "idPlan";

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
            new db_string($entity->getDescription()),
            new db_string($entity->getUrlPhoto()),
            new db_float($entity->getPrixInitial()),
            new db_int($entity->getNbPlaceTotal()),
            new db_date($entity->getDateDebut()),
            new db_date($entity->getDateFin()),
            new db_int($entity->getNbPlaceDispo()),
            new db_int($entity->getStatut()),
            new db_int($entity->getNbJaime()),
            new db_int($entity->getNbJaimePas()),
            new db_int($entity->getNote()),
            new db_int($entity->getIdClient()),
            new db_int($entity->getIdAnnonceur()),
            new db_int($entity->getIdCategorie())
        ];

        self::$dbm->p_insert($insert_obj);
    }

    public function update($entity)
    {

        $update_obj = new update_object();
        $update_obj->table_name = [self::$tableName];
        $update_obj->updated_data = [
            "description = ".new db_string($entity->getDescription()),
            "urlPhoto = ".new db_string($entity->getUrlPhoto()),
            "prixInitial = ".new db_float($entity->getPrixInitial()),
            "nbPlaceTotal = ".new db_int($entity->getNbPlaceTotal()),
            "dateDebut = ".new db_date($entity->getDateDebut()),
            "dateFin = ".new db_date($entity->getDateFin()),
            "nbPlaceDispo = ".new db_int($entity->getNbPlaceDispo()),
            "statut = ".new db_int($entity->getStatut()),
            "nbJaime = ".new db_int($entity->getNbJaime()),
            "nbJaimePas = ".new db_int($entity->getNbJaimePas()),
            "note = ".new db_int($entity->getNote()),
            "idClient = ".new db_int($entity->getIdClient()),
            "idAnnonceur = ".new db_int($entity->getIdAnnonceur()),
            "idCategorie = ".new db_int($entity->getIdCategorie())
        ];
        $update_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($entity.getIdPlan())
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
            $plan = new Plan();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $plan->$method($value);
            }
            array_push($result,$plan);
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
        $result = new Plan();

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
            $plan = new Plan();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $plan->$method($value);
            }
            array_push($result,$plan);
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
            $plan = new Plan();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $plan->$method($value);
            }
            array_push($result,$plan);
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
            $plan = new Plan();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $plan->$method($value);
            }
            array_push($result,$plan);
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