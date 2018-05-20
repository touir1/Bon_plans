window.onload = function(){
    $.get("./notifications_php/getNotifications.php",function(data) {
        var fromphp = JSON.parse(data);

        var data = fromphp.data;
        var href = fromphp.href;
        var seen = fromphp.seen;
        var idNotification = fromphp.idNotification;
    });
}