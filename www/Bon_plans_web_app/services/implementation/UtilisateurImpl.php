<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 17/04/2018
 * Time: 21:24
 */

define('ROOT_PATH', dirname(__DIR__) . '/');

include_once ROOT_PATH."../utils/db_utils.php";
include_once ROOT_PATH."../utils/mysql_db_manager.php";
include_once ROOT_PATH."../Entity/Utilisateur.php";
include_once ROOT_PATH."interfaces/IUtilisateur.php";

class UtilisateurImpl implements IUtilisateur
{
    private static $dbm;
    private static $tableName = "utilisateur";
    private static $columns_without_id = ["idGroupe","mdp","email",
        "nom","prenom","urlphoto","ville","adresse","dateCreation",
        "dateAcces","tempsAcces"];

    private static $columns_with_id = ["idUtilisateur","idGroupe",
        "mdp","email","nom","prenom","urlphoto","ville","adresse",
        "dateCreation","dateAcces","tempsAcces"];

    private static $id = "idUtilisateur";

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
            new db_int($entity->getIdGroupe()),
            new db_string($entity->getmdp()),
            new db_string($entity->getEmail()),
            new db_string($entity->getNom()),
            new db_string($entity->getPrenom()),
            new db_string($entity->getUrlphoto()),
            new db_string($entity->getVille()),
            new db_string($entity->getAdresse()),
            new db_date($entity->getDateCreation()),
            new db_date($entity->getDateAcces()),
            new db_int($entity->getTempsAcces())
        ];

        self::$dbm->p_insert($insert_obj);
    }

    public function update($entity)
    {

        $update_obj = new update_object();
        $update_obj->table_name = [self::$tableName];
        $update_obj->updated_data = [
            "idGroupe = ".new db_int($entity->getIdGroupe()),
            "mdp = ".new db_string($entity->getmdp()),
            "email = ".new db_string($entity->getEmail()),
            "nom = ".new db_string($entity->getNom()),
            "prenom = ".new db_string($entity->getPrenom()),
            "urlphoto = ".new db_string($entity->getUrlphoto()),
            "ville = ".new db_string($entity->getVille()),
            "adresse = ".new db_string($entity->getAdresse()),
            "dateCreation = ".new db_date($entity->getDateCreation()),
            "dateAcces = ".new db_date($entity->getDateAcces()),
            "tempsAcces = ".new db_int($entity->getTempsAcces())
        ];
        $update_obj->conditions = [
            self::$tableName.".".self::$id." = ".new db_int($entity.getIdUtilisateur())
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
            $utilisateur = new Utilisateur();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $utilisateur->$method($value);
            }
            array_push($result,$utilisateur);
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
        $result = new Utilisateur();

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
            $utilisateur = new Utilisateur();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $utilisateur->$method($value);
            }
            array_push($result,$utilisateur);
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
            $utilisateur = new Utilisateur();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $utilisateur->$method($value);
            }
            array_push($result,$utilisateur);
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
            $utilisateur = new Utilisateur();
            foreach ($val as $key => $value) {
                $keyBeginningWithMajus = $key;
                $keyBeginningWithMajus[0] = strtoupper($keyBeginningWithMajus[0]);
                $method = "set" . $keyBeginningWithMajus;
                $utilisateur->$method($value);
            }
            array_push($result,$utilisateur);
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