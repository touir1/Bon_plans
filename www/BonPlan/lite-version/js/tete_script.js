window.onload = function(){
    tableCreate();

    function tableCreate() {

        $.get("./notifications_php/getNotifications.php",function(data){
            var fromphp = JSON.parse(data);

            var data = fromphp.data;
            var href = fromphp.href;
            var seen = fromphp.seen;
            var idNotification = fromphp.idNotification;

            var notificationNotSeenCount = 0;

            //body reference
            var body = document.getElementById("popover-content");
            body.innerHTML = '';

            // create elements <table> and a <tbody>
            var tbl = document.createElement("table");
            tbl.className = "table table-hover";
            var tblBody = document.createElement("tbody");

            // cells creation
            for (var j = 0; j < data.length; j++) {
                // table row creation
                var row = document.createElement("tr");
                if(seen[j] == 0){
                    row.setAttribute('class', 'not-seen-class');
                    notificationNotSeenCount = notificationNotSeenCount + 1;
                    $('body').notify(
                        data[j],
                        { position:"right" }
                    );
                }
                var cell = document.createElement("td");
                var cellText = document.createTextNode(data[j]);
                cell.appendChild(cellText);
                row.appendChild(cell);
                var btnCell = document.createElement("td");
                var newlink = document.createElement('a');
                newlink.innerHTML = "Consulter";
                newlink.setAttribute('class', 'btn btn-success');

                if(idNotification[j]>0) {
                    newlink.setAttribute('onclick','seenNotification('+idNotification[j]+',"'+href[j]+'");');
                }
                else{
                    newlink.setAttribute('href', href[j]);
                }

                btnCell.appendChild(newlink);
                row.appendChild(btnCell);

                //row added to end of table body
                tblBody.appendChild(row);
            }

            // append the <tbody> inside the <table>
            tbl.appendChild(tblBody);
            // put <table> in the <body>
            body.appendChild(tbl);
            // tbl border attribute to
            //tbl.setAttribute("border", "2");
            document.getElementById('notificationCount').innerHTML = ' '+notificationNotSeenCount;
            console.log(notificationNotSeenCount);
        });


    }

    var seenNotification = function(id,href){
        $.get('./notifications_php/seenNotification.php?id='+id,function(data){
            //done
            window.location.href = href;

        });
    }
}