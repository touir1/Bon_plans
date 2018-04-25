<?php
session_start();
if(isset($_SESSION['connecter'])) {
    /**
     * Created by PhpStorm.
     * User: Mohamed Ali
     * Date: 23/04/2018
     * Time: 20:21
     */
    include("../Entities/Config.php");
    include("../Entities/Plan.php");
    include("../Entities/Commentaire.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $plan = new Plan();

    $monPlan = $plan->getOne($conn, $_GET['id']);

    $comm = new Commentaire();

    $commentaires = $comm->getFor($conn, $_GET['id']);

    include('tete.php');
    ?>

    <div class="container">

        <div class="row page-titles">
            <div class="col-md-6 col-8 align-self-center">
                <h3 class="text-themecolor m-b-0 m-t-0">Gestion des plans</h3>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="javascript:void(0)">Plan</a></li>
                    <li class="breadcrumb-item active"><?php echo($monPlan[1]); ?></li>
                </ol>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <img src="<?php echo($monPlan[3]); ?>" alt="" style="max-height: 400px;width: 100%">
                </div>
            </div>
            <div class="row">
                <h1><?php echo($monPlan[1]); ?> <h2><?php echo($monPlan[4]); ?> DNT</h2></h1>
            </div>
            <div class="row">

                <p><?php echo($monPlan[2]); ?></p>
            </div>

            <div class="row">
                <div class="card-block">
                    <h4 class="card-title">Commentaires</h4>

                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>NOM</th>
                                <th>COMMENTAIRE</th>
                                <th>ACTIONS</th>
                            </tr>
                            </thead>
                            <tbody>
                            <?php foreach ($commentaires as $commentaire) { ?>
                                <tr>
                                    <td><?php echo($commentaire[0]); ?></td>
                                    <td><?php echo($commentaire[1]); ?></td>
                                    <td><a href="suppcomm.php?id=<?php echo($commentaire[2]); ?>"
                                           class="btn btn-danger">Supprimer</a></td>
                                </tr>
                            <?php } ?>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <?php
}
include('pied.html');

?>
