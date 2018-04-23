<?php
include("tete.php");
include("../Entities/Plan.php");
$plan = new Plan();
$c2 = new Categorie();

$categories = $c2->getAll($conn);

$maCat = $c2->get($conn, $_GET['id']);

$plans = $plan->getForCategorie($conn, $_GET['id']);
?>
<section class="bg-title-page p-t-50 p-b-40 flex-col-c-m" style="background-image: url(images/heading-pages-02.jpg);">
    <h2 class="l-text2 t-center">
        <?php echo($maCat[1]); ?>
    </h2>
</section>


<section class="bgwhite p-t-55 p-b-65">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-lg-3 p-b-50">
                <div class="leftbar p-r-20 p-r-0-sm">
                    <!--  -->
                    <h4 class="m-text14 p-b-7">
                        Categories
                    </h4>

                    <ul class="p-b-54">
                        <?php foreach($categories as $cat){ ?>
                        <li class="p-t-4">
                            <a href="#" class="s-text13 active1">
                                <?php echo($cat[1]); ?>
                            </a>
                        </li>
                    <?php } ?>
                    </ul>
                </div>
            </div>

            <div class="col-sm-6 col-md-8 col-lg-9 p-b-50">

                <!-- Product -->
                <div class="row">
                    <?php foreach($plans as $pl){ ?>
                    <div class="col-sm-12 col-md-6 col-lg-4 p-b-50">
                        <!-- Block2 -->
                        <div class="block2">
                            <div class="block2-img wrap-pic-w of-hidden pos-relative block2-labelnew">
                                <img src="images/item-02.jpg" alt="IMG-PRODUCT">

                                <div class="block2-overlay trans-0-4">
                                    <a href="#" class="block2-btn-addwishlist hov-pointer trans-0-4">
                                    </a>

                                    <div class="block2-btn-addcart w-size1 trans-0-4">
                                        <!-- Button -->
                                        <?php
                                        if(isset($_SESSION['connecter'])){
                                        if($_SESSION['connecter'][1] == 1){ ?>
                                            <a href="single.php?id=<?php echo($pl[0]); ?>" class="flex-c-m size1 bg4 bo-rad-23 hov1 s-text1 trans-0-4">Reserver</a>

                                        <?php }} ?>
                                    </div>
                                </div>
                            </div>

                            <div class="block2-txt p-t-20">
                                <a href="single.php?id=<?php echo($pl[0]); ?>" class="block2-name dis-block s-text3 p-b-5">
                                    <?php echo($pl[1]); ?>
                                </a>

									<span class="block2-price m-text6 p-r-5">
										<?php echo($pl[4]); ?> DNT
									</span>
                            </div>
                        </div>
                    </div>
                    <?php } ?>
                </div>

            </div>
        </div>
    </div>
</section>

<?php include('pied.html'); ?>
