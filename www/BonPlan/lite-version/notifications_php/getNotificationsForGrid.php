<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 20/05/2018
 * Time: 02:11
 */

session_start();
$conn = null;

$notificationData = null;

if(isset($_SESSION['connecter'])) {
    include_once("../../Entities/Config.php");
    include_once("../../Entities/notification.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $notification = new Notification();

    if($_SESSION['connecter'][1] > 1){
        $notificationData = $notification->getGlobalNotifications($conn);

    }
    else if($_SESSION['connecter'][1] == 1){
        $notificationData = $notification->getNotificationsByUser($conn,$_SESSION['connecter'][0]);
    }
}

$data = array();

$data_array = array();
$href_array = array();
$seen_array = array();
$id_notification_array = array();
$delete_array = array();

if($_SESSION['connecter'][1] == 1) {


    foreach ($notificationData as $notif){
        $msg = utf8_decode($notif['message']);
        $msg = str_replace('?','é',$msg);
        array_push($data_array,$msg);
        if($notif['idPlan'] != 0){
            array_push($href_array, 'single.php?id='.$notif['idPlan']);
            array_push($id_notification_array, $notif['idNotification']);
        }
        else{
            array_push($href_array, 'javascript:void(0)');
            array_push($id_notification_array, $notif['idNotification']);
        }
        if($notif['seen'] == 0){
            array_push($seen_array, 0);
        }
        else{
            array_push($seen_array, 1);
        }
        array_push($delete_array, 'javascript:void(0)');
    }
    $data = array(
        'data' => $data_array,
        'href' => $href_array,
        'seen' => $seen_array,
        'idNotification' => $id_notification_array,
        'delete' => $delete_array
    );
}
else if($_SESSION['connecter'][1] > 1){

    foreach ($notificationData as $notif){
        $msg = utf8_decode($notif['message']);
        $msg = str_replace('?','é',$msg);
        array_push($data_array,$msg);

        array_push($href_array, 'javascript:void(0)');
        array_push($id_notification_array, $notif['idNotification']);

        if($notif['seen'] == 0){
            array_push($seen_array, 0);
        }
        else{
            array_push($seen_array, 1);
        }

        array_push($delete_array, 'delete_notification.php?id='.$notif['idNotification']);

    }
    $data = array(
        'data' => $data_array,
        'href' => $href_array,
        'seen' => $seen_array,
        'idNotification' => $id_notification_array,
        'delete' => $delete_array
    );

}
else{
    //echo json_encode($data);
}
echo json_encode($data);