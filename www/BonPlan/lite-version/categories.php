<?php
session_start();

include("../Entities/Config.php");
include("../Entities/Categorie.php");

$c = new Config();

$conn = $c->getConnexion();
$cat = new Categorie();
$items = $cat->getAll($conn);


include("tete.php");
?>

    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des categories</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Categories</a></li>
                    <li class="breadcrumb-item active">Listes des categories</li>
                </ol>
            </div>
            <div class="col-md-6 col-4 align-self-center">
                <a href="ajoutercategorie.php" class="btn pull-right hidden-sm-down btn-success"> Ajouter une nouvelle categorie</a>
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
                                    <th>TITRE</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <?php foreach($items as $item){ ?>
                                    <tr>
                                        <td><?php echo($item[0]); ?></td>
                                        <td><?php echo($item[1]); ?></td>
                                        <td><a href="modifiercategorie.php?id=<?php echo($item[0]); ?>" class="btn btn-primary">Modifier</a></td>
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
include("pied.html");
?>