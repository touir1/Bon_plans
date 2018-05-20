window.onload =( function(oldLoad){
    return function() {
        oldLoad && oldLoad();

        $.get("./notifications_php/getNotificationsForGrid.php", function (data) {
            var fromphp = JSON.parse(data);

            var data = fromphp.data;
            var href = fromphp.href;
            var seen = fromphp.seen;
            var idNotification = fromphp.idNotification;
            var idPlan = fromphp.idPlan;
            var isAdmin = fromphp.isAdmin;

            console.log(fromphp);
            var tbody = document.getElementById('notification_body');
            for (var i = 0; i < data.length; i++) {

                var row = document.createElement("tr");
                if (seen[i] == 0) {
                    row.setAttribute('class', 'not-seen-class');
                }

                var cell1 = document.createElement("td");
                var cellText1 = document.createTextNode(data[i]);
                cell1.appendChild(cellText1);
                row.appendChild(cell1);

                var cell2 = document.createElement("td");
                if (idPlan[i] > 0) {
                    var cellText2 = document.createTextNode('Non');
                    cell2.appendChild(cellText2);
                }
                else {
                    var cellText2 = document.createTextNode('Oui');
                    cell2.appendChild(cellText2);
                }
                row.appendChild(cell2);

                var cell3 = document.createElement("td");
                if (seen[i] > 0) {
                    var cellText3 = document.createTextNode('Oui');
                    cell3.appendChild(cellText3);
                }
                else {
                    var cellText3 = document.createTextNode('Non');
                    cell3.appendChild(cellText3);
                }
                row.appendChild(cell3);


                var btnCell = document.createElement("td");

                if (isAdmin[i] == 0 && idPlan[i] != "0") {
                    var newlink = document.createElement('a');
                    newlink.innerHTML = "Consulter";
                    newlink.setAttribute('class', 'btn btn-success');

                    if (idNotification[i] > 0) {
                        newlink.setAttribute('onclick', 'seenNotification(' + idNotification[i] + ',"' + href[i] + '");');
                    }
                    else {
                        newlink.setAttribute('href', href[i]);
                    }
                    btnCell.appendChild(newlink);
                }

                if (isAdmin[i] > 0) {
                    var newlink1 = document.createElement('a');
                    newlink1.innerHTML = "Supprimer";
                    newlink1.setAttribute('class', 'btn btn-danger');
                    newlink1.setAttribute('onclick', 'supprimerNotification(' + idNotification[i] + ',"notification_admin.php");');
                    btnCell.appendChild(newlink1);
                }

                row.appendChild(btnCell);

                //row added to end of table body
                tbody.appendChild(row);
            }
        });


    }
})(window.onload)

var seenNotification = function (id, href) {
    $.get('./notifications_php/seenNotification.php?id=' + id, function (data) {
        //done
        window.location.href = href;

    });
}

var supprimerNotification = function (id, href) {
    $.get('./notifications_php/delete_notification.php?id=' + id, function (data) {
        //done
        window.location.href = href;

    });
}

var ajouterGlobalNotification = function (message, href) {
    $.post('./notifications_php/ajouter_global_notification.php', {'message': message}, function (data) {
        //done
        window.location.href = href;
    });
}

var ajouterNotification = function () {
    var message = $('#msg_to_add').val();
    ajouterGlobalNotification(message, 'notification_admin.php');
}