<?php
session_start();
if(isset($_SESSION['connecter'])) {

    include("tete.php");
    include("../Entities/Config.php");
    include("../Entities/Plan.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $plan = new Plan();

    if ($_SESSION['connecter'][1] == 1) {
        $items = $plan->getFor($conn, $_SESSION['connecter'][0]);
    } else {
        $items = $plan->getAll($conn);
    }
    ?>

    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des plans</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Plans</a></li>
                    <li class="breadcrumb-item active">Liste des plans</li>
                </ol>
            </div>
            <?php if ($_SESSION['connecter'][1] == 1) { ?>
                <div class="col-md-6 col-4 align-self-center">
                    <a href="ajouterplan.php" class="btn pull-right hidden-sm-down btn-success"> Ajouter un nouveau
                        plan</a>
                </div>
            <?php } ?>

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
                                    <th>ID</th>
                                    <th>TITRE</th>
                                    <th>NB places total</th>
                                    <th>NB places restants</th>
                                    <th>STATUT</th>
                                    <th>ACTIONS</th>
                                </tr>
                                </thead>
                                <tbody>
                                <?php foreach ($items as $item) { ?>
                                    <tr>
                                        <td><?php echo($item[0]); ?></td>
                                        <td><?php echo($item[1]); ?></td>
                                        <td><?php echo($item[6]); ?></td>
                                        <td><?php echo($item[9]); ?></td>
                                        <td><?php echo($item[10]); ?></td>
                                        <td>
                                            <?php if ($item[10] == 0) { ?>
                                                <a href="single.php?id=<?php echo($item[0]); ?>"
                                                   class="btn btn-primary">Inspecter</a>
                                                <a href="approver.php?id=<?php echo($item[0]); ?>"
                                                   class="btn btn-success">Approver</a>
                                                <a href="refuser.php?id=<?php echo($item[0]); ?>"
                                                   class="btn btn-danger">Annuler</a>
                                            <?php } else if ($item[10] == 2) {
                                                echo("Refuser");
                                            } else {
                                                echo("En cours ...");
                                            } ?>
                                        </td>
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