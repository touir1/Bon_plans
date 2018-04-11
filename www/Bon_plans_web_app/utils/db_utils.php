<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 16/02/2018
 * Time: 23:36
 */

class select_object{
    public $table_names = ["dual"];
    public $columns = ["*"];
    public $conditions = [];
    public $group = [];
    public $having = [];
    public $order_by = [];
    public $order = db_order::ASC;
    public $limit = 0;
    public $offset = 0;
}

class delete_object{
    public $table_name = "";
    public $conditions = [];
}

class update_object{
    public $table_name = "";
    public $conditions = [];
    public $updated_data = [];
}

class insert_object{
    public $table_name = "";
    public $columns = [];
    public $values = [];
}

class db_order {
    //constants
    const ASC = "ASC";
    const DESC = "DESC";
}

class db_type{
    protected $value;

    public function __construct($value)
    {
        $this->value = $value;
    }

    public function __toString()
    {
        return "".$this->value;
    }


}

class db_float extends db_type {

}

class db_int extends db_type {

}

class db_date extends db_type {
    public function __toString()
    {
        return "'".$this->value."'";
    }
}

class db_string extends db_type {
    public function __toString()
    {
        return "'".$this->value."'";
    }
}