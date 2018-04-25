<?php
session_start();
if(isset($_SESSION['connecter'])) {
    include("../Entities/Config.php");
    include("../Entities/utilisateur.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $user = new Utilisateur();
    $items = $user->getAll($conn);


    include("tete.php");
    ?>

    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des utilisateurs</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Utilisateurs</a></li>
                    <li class="breadcrumb-item active">Listes des utlisateurs</li>
                </ol>
            </div>
            <div class="col-md-6 col-4 align-self-center">
                <a href="ajouter.php" class="btn pull-right hidden-sm-down btn-success"> Ajouter un nouveau
                    administrateur</a>
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
                                    <th>NOM</th>
                                    <th>PRENOM</th>
                                    <th>TYPE</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <?php foreach ($items as $item) { ?>
                                    <tr>
                                        <td><?php echo($item[0]); ?></td>
                                        <td><?php echo($item[4]); ?></td>
                                        <td><?php echo($item[5]); ?></td>
                                        <td>
                                            <?php if ($item[1] == 1) {
                                                echo("Client");
                                            } else {
                                                echo("Administrateur");
                                            }
                                            ?>
                                        </td>
                                        <td>
                                            <?php if ($item[1] == 1) { ?>
                                                <?php if ($item[12] == 0) { ?>
                                                    <a href="bloquer.php?id=<?php echo($item[0]); ?>&etat=1"
                                                       class="btn btn-danger">Bloquer</a>
                                                <?php } else { ?>
                                                    <a href="bloquer.php?id=<?php echo($item[0]); ?>&etat=0"
                                                       class="btn btn-success">Debloquer</a>
                                                <?php } ?>
                                            <?php } else { ?>
                                                <?php if ($item[12] == 0) { ?>
                                                    <a href="bloquer.php?id=<?php echo($item[0]); ?>&etat=1"
                                                       class="btn btn-danger">Bloquer</a>
                                                <?php } else { ?>
                                                    <a href="bloquer.php?id=<?php echo($item[0]); ?>&etat=0"
                                                       class="btn btn-success">Debloquer</a>
                                                <?php } ?>
                                            <?php if($_SESSION['connecter'][1] == 3){ ?>
                                                <a href="#" class="btn btn-primary">Affecter</a>
                                            <?php } ?>
                                            <?php } ?>
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