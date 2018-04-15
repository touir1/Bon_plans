<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 15/04/2018
 * Time: 15:51
 */

interface IGenericService{

    public function save($entity);

    public function update($entity);

    public function selectAll();

    public function getByID($id);

    public function remove($id);

    public function selectAllOrderedAsc($sortField);

    public function selectAllOrderedDesc($sortField);

    public function findOne($paramName, $paramValue);

    public function findCountBy($paramName, $paramValue);
}