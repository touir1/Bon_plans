<?php
/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 22/04/2018
 * Time: 00:53
 */
session_start();

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
                    <form class="form-horizontal form-material" method="post" action="ajoutercat.php"  enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-md-12">Titre</label>
                            <div class="col-md-12">
                                <input type="text" placeholder="titre ..." name="titre" class="form-control form-control-line" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Image</label>
                            <div class="col-md-12">
                                <input  type="file" name="fileToUpload" id="fileToUpload" class="form-control form-control-line" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button class="btn btn-success">Ajouter categorie</button>
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
include("pied.html");
?>
