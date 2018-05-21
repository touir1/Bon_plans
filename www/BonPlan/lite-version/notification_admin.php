<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 17/05/2018
 * Time: 17:21
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
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des notifications</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Notifications</a></li>
                    <li class="breadcrumb-item active">Listes des notifications</li>
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
                                    <th>Message</th>
                                    <th>Global</th>
                                    <th>Seen</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody id="notification_body">

                                </tbody>
                            </table>
                        </div>
                        <?php if($_SESSION['connecter'][1] > 1){ ?>
                        <div class="form-group">
                            <label for="msg_to_add"><b>Message à ajouter:</b></label>
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td><input type="text" class="form-control" id="msg_to_add"></td>
                                    <td><a class="btn btn-success" onclick="ajouterNotification();">Ajouter Notification</a></td>
                                </tr>
                                </tbody>

                            </table>

                        </div>
                        <?php } ?>
                        <div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End PAge Content -->
    <!-- ============================================================== -->
    </div>
    <script src="js/notifications_admin-custom.js"></script>
    <?php

    include("pied.html");
    ?>

