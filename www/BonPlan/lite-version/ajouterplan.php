<?php
session_start();
if(isset($_SESSION['connecter'])){
include ("../Entities/Config.php");
include ("../Entities/Categorie.php");

$c = new Config();

$conn = $c->getConnexion();

$ca = new Categorie();

$cats =$ca->getAll($conn);

include("tete.php");
?>


    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">gestion des plans</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Plans</a></li>
                    <li class="breadcrumb-item active">Creer un plan</li>
                </ol>
            </div>
            <div class="col-md-6 col-4 align-self-center">
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
                        <form class="form-horizontal form-material" method="post" action="creerplan.php"   enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-md-12">Titre</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Titre" name="titre" class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Categorie</label>
                                <div class="col-md-12">
                                    <select name="idCategorie" id="" class="form-control">
                                        <?php foreach($cats as $categorie){ ?>
                                            <option value="<?php echo($categorie[0]); ?>"><?php echo($categorie[1]); ?></option>
                                        <?php } ?>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Prix initial</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Prix initial" name="prixInitial" class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="example-email" class="col-md-12">Prix promotionnel</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Prix promotionnel" name="prixPromo" class="form-control form-control-line" id="example-email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Date de debut</label>
                                <div class="col-md-12">
                                    <input type="date" class="form-control form-control-line" name="dd" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">date du fin</label>
                                <div class="col-md-12">
                                    <input type="date" placeholder="ville" name="df" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Quantite</label>
                                <div class="col-md-12">
                                    <input type="number" placeholder="Quantite" name="qte" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">description</label>
                                <div class="col-md-12">
                                    <textarea rows="5" class="form-control form-control-line" name="description" required placeholder="description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Image</label>
                                <div class="col-md-12">
                                    <input type="file" name="fileToUpload" id="fileToUpload" class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button class="btn btn-success">Creer Plan</button>
                                </div>
                            </div>
                        </form>
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