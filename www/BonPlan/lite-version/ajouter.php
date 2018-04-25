<?php
session_start();
if(isset($_SESSION['connecter'])) {
    /*
    include ("Config.php");
    include ("utilisateur.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $user = new Utilisateur();
    $user->setPrenom("prenom");
    $user->setAdresse("Tunis");
    $user->setEmail("prenom@email.com");
    $user->setIdGroup(1);
    $user->setMdp("secret");
    $user->setNom("nom");
    $user->setPhoto("test");
    $user->setVille("Tunis");

    $user->add($conn);

    */
    include("tete.php");
    ?>


    <div class="container-fluid">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">gestion des profiles</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Profiles</a></li>
                    <li class="breadcrumb-item active">Creer un profile</li>
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
                        <form class="form-horizontal form-material" method="post" action="ajouterprofile.php"   enctype="multipart/form-data">
                            <input type="hidden" name="group" value="2">

                            <div class="form-group">
                                <label class="col-md-12">Nom</label>

                                <div class="col-md-12">
                                    <input type="text" placeholder="nom" name="nom"
                                           class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Prenom</label>

                                <div class="col-md-12">
                                    <input type="text" placeholder="prenom" name="prenom"
                                           class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="example-email" class="col-md-12">Email</label>

                                <div class="col-md-12">
                                    <input type="email" placeholder="email" name="email"
                                           class="form-control form-control-line" id="example-email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Mot de passe</label>

                                <div class="col-md-12">
                                    <input type="password" class="form-control form-control-line" name="mdp" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ville</label>

                                <div class="col-md-12">
                                    <input type="text" placeholder="ville" name="ville" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Adresse</label>

                                <div class="col-md-12">
                                    <textarea rows="5" class="form-control form-control-line" name="adresse"
                                              required></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Image</label>

                                <div class="col-md-12">
                                    <input  type="file" name="fileToUpload" id="fileToUpload"  class="form-control form-control-line" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button class="btn btn-success">Creer Profile</button>
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