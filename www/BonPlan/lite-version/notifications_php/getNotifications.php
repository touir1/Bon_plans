<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 19/05/2018
 * Time: 18:48
 */

session_start();
$conn = null;

$countModified = 0;
$countUpdated = 0;
$notificationData = null;

if(isset($_SESSION['connecter'])) {
    include_once("../../Entities/Config.php");
    include_once("../../Entities/notification.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $notification = new Notification();

    if($_SESSION['connecter'][1] > 1){
        $countModified = $notification->getCountNonValidatedModifiedPlans($conn);
        $countUpdated = $notification->getCountNonValidatedNewPlans($conn);

    }
    else if($_SESSION['connecter'][1] == 1){
        $notificationData = $notification->getNotificationsByUser($conn,$_SESSION['connecter'][0]);
    }
}

$data = array();
if($_SESSION['connecter'][1] == 1) {
    $data_array = array();
    $href_array = array();
    $seen_array = array();
    $id_notification_array = array();
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
    }
    $data = array(
        'data' => $data_array,
        'href' => $href_array,
        'seen' => $seen_array,
        'idNotification' => $id_notification_array
    );
}
else if($_SESSION['connecter'][1] > 1){
    $data = array(
        'data' => array(
            'vous avez '.$countUpdated.' nouveaux plans à valider',
            'vous avez '.$countModified.' plans modifiés à valider'
        ),
        'href' => array(
            'plans.php',
            'plans.php'
        ),
        'seen' => array(
            0,
            0
        ),
        'idNotification' => array(
            0,
            0
        )
    );

}
else{
    //echo json_encode($data);
}
echo json_encode($data);