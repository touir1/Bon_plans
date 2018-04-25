<?php
    include('tete.php');
include('../Entities/Plan.php');
include('../Entities/Commentaire.php');

    $p = new Plan();
    $item = $p->getOne($conn, $_GET['id']);
    $categorie = $c1->get($conn, $item[12]);

    $com = new Commentaire();
    $commentaires = $com->getFor($conn, $_GET['id']);
    $count = $com->count($conn, $_GET['id']);
?>

    <div class="container bgwhite p-t-35 p-b-80">
        <div class="flex-w flex-sb">
            <div class="w-size13 p-t-30 respon5">
                <div class="wrap-slick3 flex-sb flex-w">

                    <div class="slick3 slick-initialized slick-slider slick-dotted">
                        <div class="slick-list draggable"><div class="slick-track" style="opacity: 1; width: 981px;"><div class="item-slick3 slick-slide slick-current slick-active" data-thumb="images/thumb-item-01.jpg" data-slick-index="0" aria-hidden="false" tabindex="0" role="tabpanel" id="slick-slide10" aria-describedby="slick-slide-control10" style="width: 327px; position: relative; left: 0px; top: 0px; z-index: 999; opacity: 1;">
                                    <div class="wrap-pic-w">
                                        <img src="../lite-version/<?php echo($item[3]); ?>" alt="IMG-PRODUCT">
                                    </div>
                                </div>
                                <div class="item-slick3 slick-slide" data-thumb="images/thumb-item-02.jpg" data-slick-index="1" aria-hidden="true" tabindex="-1" role="tabpanel" id="slick-slide11" aria-describedby="slick-slide-control11" style="width: 327px; position: relative; left: -327px; top: 0px; z-index: 998; opacity: 0;">
                                    <div class="wrap-pic-w">
                                        <img src="images/product-detail-02.jpg" alt="IMG-PRODUCT">
                                    </div>
                                </div>
                                <div class="item-slick3 slick-slide" data-thumb="images/thumb-item-03.jpg" data-slick-index="2" aria-hidden="true" tabindex="-1" role="tabpanel" id="slick-slide12" aria-describedby="slick-slide-control12" style="width: 327px; position: relative; left: -654px; top: 0px; z-index: 998; opacity: 0;">
                                    <div class="wrap-pic-w">
                                        <img src="images/product-detail-03.jpg" alt="IMG-PRODUCT">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="w-size14 p-t-30 respon5">
                <h4 class="product-detail-name m-text16 p-b-13">
                   <?php echo($item[1]); ?>
                </h4>

				<span class="m-text17">
					<?php echo($item[4]); ?> DNT
				</span>

                <p class="s-text8 p-t-10">
                    <?php echo($item[2]); ?>
                </p>

                <!--  -->
                <div class="p-t-33 p-b-60">

                    <label>Quantite disponible : <?php echo($item[9]); ?></label>
                    <?php if(isset($_SESSION['connecter']) && ($item[10] != 2)){ ?>
                    <form action="reserver.php" method="post">
                        <input type="hidden" name="plan" value="<?php echo($item[0]); ?>">
                        <input type="hidden" name="client" value="<?php if(isset($_SESSION['connecter'])){echo($_SESSION['connecter'][0]);} ?>">

                        <div class="flex-r-m flex-w p-t-10">
                        <div class="w-size16 flex-m flex-w">
                            <div class="flex-w bo5 of-hidden m-r-22 m-t-10 m-b-10">
                                <button class="btn-num-product-down color1 flex-c-m size7 bg8 eff2">
                                    <i class="fs-12 fa fa-minus" aria-hidden="true"></i>
                                </button>

                                <input class="size8 m-text18 t-center num-product" type="number" name="qte" value="1" min="1" max="<?php echo($item[9]); ?>">

                                <button class="btn-num-product-up color1 flex-c-m size7 bg8 eff2">
                                    <i class="fs-12 fa fa-plus" aria-hidden="true"></i>
                                </button>
                            </div>
                            <div class="btn-addcart-product-detail size9 trans-0-4 m-t-10 m-b-10">
                                <!-- Button -->
                                <button class="flex-c-m sizefull bg1 bo-rad-23 hov1 s-text1 trans-0-4" type="submit">
                                    Reserver
                                </button>
                            </div>
                        </div>
                    </div>
                    </form>
                    <?php } ?>
                </div>
                <?php if(isset($_SESSION['connecter'])){ ?>
                <div class="p-b-45">
                    <a href="avis.php?id=<?php echo($item[0]); ?>&avis=1" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> | <?php echo($p->countlike($conn, $item[0]));?></a>
                    <a href="avis.php?id=<?php echo($item[0]); ?>&avis=2" class="btn btn-danger"><span class="glyphicon glyphicon-thumbs-down"></span> | <?php echo($p->countDisLike($conn, $item[0]));?></a>
                </div>
                <?php }else{ ?>
                    <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span> | <?php echo($p->countlike($conn, $item[0]));?></a>
                    <a href="#" class="btn btn-danger"><span class="glyphicon glyphicon-thumbs-down"></span> | <?php echo($p->countDisLike($conn, $item[0]));?></a>
                <?php } ?>
                <div class="p-b-45" style="margin-top:1em">
                    <span class="s-text8 m-r-35">Categorie : <?php echo($categorie[1]);?></span>
                </div>

                <!--  -->
                <div class="wrap-dropdown-content bo6 p-t-15 p-b-14 active-dropdown-content">
                    <h5 class="js-toggle-dropdown-content flex-sb-m cs-pointer m-text19 color0-hov trans-0-4 show-dropdown-content">
                        Description
                        <i class="down-mark fs-12 color1 fa fa-minus dis-none" aria-hidden="true"></i>
                        <i class="up-mark fs-12 color1 fa fa-plus" aria-hidden="true"></i>
                    </h5>

                    <div class="dropdown-content dis-none p-t-15 p-b-23" style="display: block;">
                        <p class="s-text8">
                            <?php echo($item[2]); ?>
                        </p>
                    </div>
                </div>

                <div class="wrap-dropdown-content bo7 p-t-15 p-b-14">
                    <h5 class="js-toggle-dropdown-content flex-sb-m cs-pointer m-text19 color0-hov trans-0-4">
                        Commentaires (<?php echo($count[0]); ?>)
                        <i class="down-mark fs-12 color1 fa fa-minus dis-none" aria-hidden="true"></i>
                        <i class="up-mark fs-12 color1 fa fa-plus" aria-hidden="true"></i>
                    </h5>

                    <div class="dropdown-content dis-none p-t-15 p-b-23">
                        <?php foreach($commentaires as $commentaire){ ?>

                            <p class="s-text8">
                                <?php echo($commentaire[1]); ?>
                                <?php if(isset($_SESSION['connecter'])){ ?>
                                <a href="signaler.php?id=<?php echo($commentaire[2]); ?>" class="pull-right">Signaler</a>
                                <?php } ?>
                            </p>
                            <label>@<?php echo($commentaire[0]); ?></label>
                        <?php } ?>
                        <?php if(isset($_SESSION['connecter'])){ ?>
                        <hr>
                        <form action="ajoutercom.php" method="post">
                            <input type="hidden" name="user" value="<?php echo($_SESSION['connecter'][0]); ?>">
                            <input type="hidden" name="plan" value="<?php echo($item[0]); ?>">
                            <div class="form-group">
                                <textarea name="commentaire" id="" class="form-control" rows="10" placeholder="Ajouter un commentaire"></textarea>
                            </div>
                            <button class="flex-c-m sizefull bg1 bo-rad-23 hov1 s-text1 trans-0-4 col-md-6 pull-right" type="submit">
                                Commenter
                            </button>
                            <div class="form-gourp">
                            </div>
                        </form>
                        <?php } ?>

        </div>
                </div>
            </div>
        </div>
    </div>

<?php
include('pied.html');
?>