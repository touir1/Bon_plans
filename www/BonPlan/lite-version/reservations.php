<?php
session_start();
if(isset($_SESSION['connecter'])) {
    include("../Entities/Config.php");
    include("../Entities/Reservation.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $res = new Reservation();

    if ($_SESSION['connecter'][1] == 1) {
        $items = $res->getFor($conn, $_SESSION['connecter'][0]);
    } else {
        $items = $res->getAll($conn);
    }


    include("tete.php");
    ?>

    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des reservations</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Reservations</a></li>
                    <li class="breadcrumb-item active">Listes des reservations</li>
                </ol>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Start Page Content -->
        <!-- ============================================================== -->
        <div class="row">
            <!-- column -->
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-block">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>PLAN</th>
                                    <th>NOM</th>
                                    <th>DATE</th>
                                    <th>QUANTITE</th>
                                </tr>
                                </thead>
                                <tbody>
                                <?php foreach ($items as $item) { ?>
                                    <tr>
                                        <td><?php echo($item[0]); ?></td>
                                        <td><?php echo($item[1]); ?></td>
                                        <td><?php echo($item[2]); ?></td>
                                        <td><?php echo($item[3]); ?></td>
                                        <td><?php echo($item[4]); ?></td>
                                    </tr>
                                <?php } ?>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End PAge Content -->
        <!-- ============================================================== -->
    </div>
    <?php
}
include("pied.html");
?>