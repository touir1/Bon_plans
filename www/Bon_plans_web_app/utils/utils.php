<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 16/02/2018
 * Time: 21:20
 */

class utils
{
    public static function array_to_string($array = [], $delim = ",")
    {
        $count = count($array);
        $result = "";
        if($count==1){
            $result = "".$array[0];
        }
        else if($count > 1){
            $result = "".$array[0];
            for($i=1;$i<$count;$i++){
                $result .= " ".$delim." ".$array[$i];
            }
        }

        return $result;
    }

    public static function concat_if_true($string1 ="", $string2 ="", $condition=false)
    {
        if($condition){
            return $string1.$string2;
        }
        else{
            return $string1;
        }
    }
}

class http_response{
    public $data;
    public $status;

    /**
     * http_response constructor.
     * @param $data
     * @param $status
     */
    public function __construct($data, $status)
    {
        $this->data = $data;
        $this->status = $status;
    }

}