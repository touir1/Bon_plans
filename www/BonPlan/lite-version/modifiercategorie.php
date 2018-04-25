<?php
session_start();
if(isset($_SESSION['connecter'])) {
    /**
     * Created by PhpStorm.
     * User: Mohamed Ali
     * Date: 22/04/2018
     * Time: 00:53
     */
    include("../Entities/Config.php");
    include("../Entities/Categorie.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $cat = new Categorie();

    $items = $cat->get($conn, $_GET['id']);

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
                    <li class="breadcrumb-item active">Creer une nouvelle categorie</li>
                </ol>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Start Page Content -->
        <!-- ============================================================== -->
        <!-- Row -->
        <div class="row">
            <!-- Column -->
            <div class="col-lg-8 col-xlg-9 col-md-7">
                <div class="card">
                    <div class="card-block">
                        <?php foreach ($items as $item) { ?>
                            <form class="form-horizontal form-material" method="post" action="modifiercat.php">
                                <input type="hidden" name="id" value="<?php echo($item[0]); ?>">

                                <div class="form-group">
                                    <label class="col-md-12">Titre</label>

                                    <div class="col-md-12">
                                        <input type="text" placeholder="<?php echo($item[1]); ?>" name="titre"
                                               class="form-control form-control-line" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Image</label>

                                    <div class="col-md-12">
                                        <input type="file" name="image" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success">Modifier categorie</button>
                                    </div>
                                </div>
                            </form>
                        <?php } ?>
                    </div>
                </div>
            </div>
            <!-- Column -->
        </div>
        <!-- Row -->
        <!-- ============================================================== -->
        <!-- End PAge Content -->
        <!-- ============================================================== -->
    </div>


    <?php
}
include("pied.html");
?>
