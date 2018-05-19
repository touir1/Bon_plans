<?php
$conn = null;

$countModified = 0;
$countUpdated = 0;
$notificationData = null;

if(isset($_SESSION['connecter'])) {
    include_once("../Entities/Config.php");
    include_once("../Entities/notification.php");

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
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/images/favicon.png">
    <title>Bon Plan | Administration</title>
    <!-- Bootstrap Core CSS -->
    <link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/custom-style.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


    <![endif]-->
</head>

<body class="fix-header card-no-border">
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <header class="topbar">
        <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
            <!-- ============================================================== -->
            <!-- Logo -->
            <!-- ============================================================== -->
            <div class="navbar-header">
                <a class="navbar-brand" href="index.html">
                    <!-- Logo icon -->
                    <b>
                        <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                        <!-- Dark Logo icon -->
                        <img src="../assets/images/logo-icon.png" alt="homepage" class="dark-logo" />

                    </b>
                    <!--End Logo icon -->
                    <!-- Logo text -->
                        <span>
                            <!-- dark Logo text -->
                            <img src="../assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                        </span>
                </a>
            </div>
            <!-- ============================================================== -->
            <!-- End Logo -->
            <!-- ============================================================== -->
            <div class="navbar-collapse">
                <!-- ============================================================== -->
                <!-- toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav mr-auto mt-md-0 ">
                    <!-- This is  -->
                    <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark" href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                    <li class="nav-item hidden-sm-down">
                        <form class="app-search p-l-20">
                            <input type="text" class="form-control" placeholder="Search for..."> <a class="srh-btn"><i class="ti-search"></i></a>
                        </form>
                    </li>
                </ul>
                <!-- ============================================================== -->
                <!-- User profile and search -->
                <!-- ============================================================== -->
                <ul class="navbar-nav my-lg-0">
                    <li class="nav-item dropdown">
                        <a tabindex="0"
                           class="nav-link dropdown-toggle text-muted waves-effect waves-dark"
                           aria-haspopup="true"
                           aria-expanded="false"
                           role="button"
                           data-html="true"
                           data-toggle="popover"
                           data-trigger="focus"
                           data-placement="bottom"
                           title="<b>Notifications</b>"><i class="fa fa-bell"></i></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="<?php echo($_SESSION['connecter'][6]) ?>" alt="user" class="profile-pic m-r-5" /><?php if(isset($_SESSION['connecter'])){echo($_SESSION['connecter'][4]);} ?></a>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li>
                        <a href="plans.php" class="waves-effect"><i class="fa fa-clock-o m-r-10" aria-hidden="true"></i>Gestion des Plans</a>
                    </li>
                    <li>
                        <a href="reservations.php" class="waves-effect"><i class="fa fa-table m-r-10" aria-hidden="true"></i>Gestion des réservation</a>
                    </li>
                    <?php if($_SESSION['connecter'][1] > 1){ ?>
                    <li>
                        <a href="profiles.php" class="waves-effect"><i class="fa fa-user m-r-10" aria-hidden="true"></i>Gestion des Profiles</a>
                    </li>
                    <li>
                        <a href="categories.php" class="waves-effect"><i class="fa fa-font m-r-10" aria-hidden="true"></i>Gestion des Categories</a>
                    </li>
                    <li>
                        <a href="notification_admin.php" class="waves-effect"><i class="fa fa-bell m-r-10" aria-hidden="true"></i>Gestion des Notifications</a>
                    </li>
                    <?php } ?>
                </ul>

                <div class="text-center m-t-30">
                    <a href="../logout.php" class="btn btn-danger">Se deconnecter</a>
                </div>

            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->

        <div id="popover-content" hidden>

        <script>
            tableCreate();

        	function tableCreate() {

                var fromphp =
                    <?php
                    if($_SESSION['connecter'][1] == 1) {
                        $data_array = array();
                        $href_array = array();
                        $seen_array = array();
                        foreach ($notificationData as $notif){
                            array_push($data_array,$notif[2]);
                            if($notif[3] != 0){
                                array_push($href_array, 'single.php?id='.$notif[3]);
                            }
                            else{
                                array_push($href_array, 'javascript:void(0)');
                            }
                            if($notif[1] == 0){
                                array_push($seen_array, 0);
                            }
                            else{
                                array_push($seen_array, 1);
                            }
                        }
                        $data = array(
                                'data' => $data_array,
                                'href' => $href_array,
                                'seen' => $seen_array
                        );
                        echo json_encode($data);
                    }
                    else if($_SESSION['connecter'][1] > 1){
                        $data = array(
                            'data' => array(
                                'vous avez '.$countUpdated.' nouveaux plans à valider',
                                'vous avez '.$countModified.' plans modifiés à valider'
                            ),
                            'href' => array(
                                'single.php',
                                'single.php'
                            ),
                            'seen' => array(
                                0,
                                0
                            )
                        );
                        echo json_encode($data);
                    }

                    ?>;
                var data = fromphp.data;
                var href = fromphp.href;
                var seen = fromphp.seen;

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
                    }
                    var cell = document.createElement("td");
                    var cellText = document.createTextNode(data[j]);
                    cell.appendChild(cellText);
                    row.appendChild(cell);
                    var btnCell = document.createElement("td");
                    var newlink = document.createElement('a');
                    newlink.innerHTML = "Consulter";
                    newlink.setAttribute('class', 'btn btn-success');
                    newlink.setAttribute('href', href[j]);

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
            }

        </script>

        </div>