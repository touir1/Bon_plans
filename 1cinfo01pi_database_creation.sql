-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 21 mai 2018 à 02:49
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bonsplans`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
  `idAvis` int(11) NOT NULL AUTO_INCREMENT,
  `idPlan` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `avis` int(11) NOT NULL DEFAULT '0',
  `note` int(11) DEFAULT '0',
  PRIMARY KEY (`idAvis`),
  KEY `fk_avis_plan` (`idPlan`),
  KEY `fk_avis_utilisateur` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) COLLATE utf8_bin NOT NULL,
  `urlPhoto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idCategorie`, `titre`, `urlPhoto`) VALUES
(13, 'Divertissement', 'http://localhost/bon_plans_api/uploads/Categorie_13.jpg'),
(14, 'Drink and Food', 'http://localhost/bon_plans_api/uploads/Categorie_14.jpg'),
(15, 'Beauté et Spa', 'http://localhost/bon_plans_api/uploads/Categorie_15.jpg'),
(16, 'Lifestyle & Accessoires', 'http://localhost/bon_plans_api/uploads/Categorie_16.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `texte` varchar(255) COLLATE utf8_bin NOT NULL,
  `date` date NOT NULL,
  `nbJaime` int(11) NOT NULL DEFAULT '0',
  `nbJaimePas` int(11) NOT NULL DEFAULT '0',
  `idClient` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL,
  PRIMARY KEY (`idCommentaire`),
  KEY `fk_commentaire_client` (`idClient`),
  KEY `fk_commentaire_plan` (`idPlan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `idGroupe` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`idGroupe`, `description`) VALUES
(1, 'Client'),
(2, 'Administrateur'),
(3, 'Super Administrateur');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `idNotification` int(11) NOT NULL AUTO_INCREMENT,
  `seen` tinyint(1) NOT NULL DEFAULT '0',
  `message` varchar(255) COLLATE utf8_bin NOT NULL,
  `idPlan` int(11) NOT NULL,
  PRIMARY KEY (`idNotification`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `plan`
--

DROP TABLE IF EXISTS `plan`;
CREATE TABLE IF NOT EXISTS `plan` (
  `idPlan` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `urlPhoto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `prixInitial` double NOT NULL,
  `prixPromo` double NOT NULL,
  `nbPlaceTotal` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `nbPlaceDispo` int(11) NOT NULL,
  `statut` int(11) NOT NULL,
  `idAnnonceur` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL,
  `like` int(11) NOT NULL DEFAULT '0',
  `dislike` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPlan`),
  KEY `fk_plan_client` (`idAnnonceur`),
  KEY `fk_plan_categorie` (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `plan`
--

INSERT INTO `plan` (`idPlan`, `titre`, `description`, `urlPhoto`, `prixInitial`, `prixPromo`, `nbPlaceTotal`, `dateDebut`, `dateFin`, `nbPlaceDispo`, `statut`, `idAnnonceur`, `idCategorie`, `like`, `dislike`) VALUES
(16, 'Profitez d\'un menu savoureux chez Le Cap Hammamet à 37 DT', '\r\n        Restaurant Le Cap Hammamet vous invite à déguster ses merveilleuses recettes le temps d\'un déjeuner ou d\'un dîner à 37 DT Seulement!\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal notez le nous vous remboursons 20 BIGFid\r\n\r\nAu menu : \r\n\r\n- 2 Entrées : 2 salades mixtes OU 2 Bricks au thon\r\n\r\n- 2 Suites : 2 plats escalopes grillés avec garniture OU 2 plats émincés de poulet à la crème OU 2 steaks de dinde garnis\r\n\r\n- 2 Desserts : Fruits de saison & 2 thés à la menthe ', 'http://localhost/bon_plans_api/uploads/B1-NEW.png', 66, 37, 20, '2018-05-20', '2018-05-31', 20, 1, 14, 14, 0, 0),
(17, 'Dégustez les délicieuses glaces de Il gelato italiano Menzah 5', 'La star de l\'été vous attend pour la déguster sans modération chez Il Gelato Italiano grâce à une offre super irrésistible. Vous allez certainement fondre pour les tendres glaces aux parfums miltiples et uniques.\r\n\r\nIl est temps de découvrir cette prestigieuse enseigne qui sert ses délices depuis des années grâce à ses  3 points de vente : La Marsa Menzah 5 et Hammamet.\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nL\'offre vous propose :\r\n\r\n- Une réduction de 20% sur les glaces à emporter\r\n\r\nNB : cette offre est valable tous les jours de midi à 16h', 'http://localhost/bon_plans_api/uploads/B3-NEW.png', 5, 1, 20, '2018-05-20', '2018-05-31', 20, 1, 14, 14, 0, 0),
(18, 'Rompez votre jeûne à 2 au restaurant One Way à 49 DT seulement', 'Vous avez envie d’essayer autre chose pendant Ramadan, vous voulez manger dans un endroit différent avec une bonne ambiance, où vous pourrez vous amuser et vous régaler ?\r\n\r\nb!gdeal célèbre l\'arrivée du mois saint et vous propose les meilleurs bons plans ! Découvrez restaurant One Way le temps d\'une rupture du jeûne à 49 DT Seulement.\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nL\'offre vous propose :\r\n\r\nEntrées :\r\n\r\n- Dattes farcies \r\n\r\n- Chorbet fric ( à la viande ou au poisson)\r\n\r\n- Brik à la viande hachée\r\n\r\n- Salade Tunisienne\r\n\r\n- Salade mechwia\r\n\r\nSuite :\r\n\r\n- Riz à l’agneau et aux fruits secs ou Spaghettis aux fruits de mer ou Viande d\'agneau à la gargoulette (pour deux personnes) ou Klaia zaara ( viande, foie, cœur)\r\n\r\nDessert :\r\n\r\n- Asidet boufriwa ou Salade de fruits ou Sorbet citron ou Bouza \r\n\r\n- 2 Thés à la menthe vous serons également offerts', 'http://localhost/bon_plans_api/uploads/B33.png', 70, 40, 20, '2018-05-20', '2018-05-31', 20, 1, 14, 14, 0, 0),
(19, 'Revivez le charme des soirées Ramadanesques à la maison d\'hôtes Dar Kenza', 'Le mois de Ramadan est un moment de générosité et de partage, c’est aussi l’occasion pour se réunir et se rassembler. C\'est pourquoi b!gdeal vous propose un programme 100% Tunisien au cœur de la belle Médina pour découvrir un endroit magique et très charmant, il s\'agit de la maison d\'hôtes Dar Kenza\r\n\r\nL\'offre vous propose :\r\n\r\n- 2 Bouza\r\n\r\n- 2 Thés aux pignons ou aux amandes ou café turc\r\n\r\n- Une bouteille d\'eau de 1L \r\n\r\n- Une chicha ou une assiette de pâtisseries tunisiennes', 'http://localhost/bon_plans_api/uploads/B1b2.png', 40, 26, 20, '2018-05-20', '2018-05-31', 20, 1, 14, 14, 0, 0),
(20, 'rupture du jeûne pour 2 au restaurant Grill Time à 56 DT Seulement', 'Situé aucœur de Megrine, le restaurant Grill Time vous ouvre ses portes pour savourer une excellente rupture du jeûne à partager en couple.\r\n\r\nAu menu : \r\n\r\nEntrées:\r\n\r\n- Amuse-bouches\r\n\r\n- Bsissa et des dattes farcies\r\n\r\n- Soupe \r\n\r\n- Bruschetta\r\n\r\n- Trio de salades (mechwia / houria / tunisienne)\r\n\r\nSuites :\r\n\r\n- Poisson grillé (loup / daurade / rouget)\r\n\r\n- Plat du jour typiquement tunisien\r\n\r\nDesserts:\r\n\r\n- Fruits de saison\r\n\r\n- Pâtisseries\r\n\r\n- Une variété de pain spécial ramadan\r\n\r\n- Thé et Eau offerts', 'http://localhost/bon_plans_api/uploads/B1b2.png', 70, 56, 20, '2018-05-20', '2018-05-31', 20, 1, 14, 14, 0, 0),
(21, 'Forfait relooking à 39 DT Seulement', 'La saison printaniére commence à faire son retour, il est temps de dévoiler votre bonne mine et ressortir toute votre beauté. BonPlan.tn vous propose de découvrir l\'havre de beauté Ramla Skin Center Menzah 5 pour prendre soin de vous des cheveux aux ongles à un prix super attractif : 39 DT seulement!!\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nL\'offre vous propose :\r\n\r\n- Coloration et mèches ou coloration et tie & dye\r\n\r\n- Soin capillaire\r\n\r\n- Coupe & brushing simple ou artistique\r\n\r\n- Soin du visage spécifique\r\n\r\n- Épilation de la lèvre supérieure et du menton\r\n\r\n- Épilation des bras au complet à la cire traditionnelle\r\n\r\n- Soin des mains spécifique\r\n\r\n- Pose du gel sur ongles naturels\r\n\r\n- Pose vernis permanent pour les mains\r\n\r\n- Pédiure médicale ou soin des pieds spécifique\r\n\r\n- Pose vernis simple\r\n\r\n- Extension de cil à cil', 'http://localhost/bon_plans_api/uploads/B3b2png.png', 400, 39, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 15, 0, 0),
(22, 'Forfait de beauté à 29 DT', 'Le centre de beauté Joelle Beauty vous propose une ambiance décalée, un cadre professionnel et une atmosphère zen pour vous offrir un agréable moment de détente. Venez tester cet institut ou vous serez pris en mains par des experts dans le domaine.\r\n\r\nL\'offre vous propose :\r\n\r\n- Soin du visage basique (sans vapozone)\r\n\r\n- Coup d\'éclat d\'or\r\n\r\n- Épilation des sourcils et de la lèvre supérieure\r\n\r\n- Soin des mains\r\n\r\n- Pose vernis permanent\r\n\r\n- Brushing', 'http://localhost/bon_plans_api/uploads/B3b4.png', 100, 29, 20, '2018-05-20', '2018-05-20', 20, 1, 17, 15, 0, 0),
(23, 'Gel capsules et vernis permanent', 'Bonplan vous propose de prendre soin de vos ongles chez Hi Style Ennasr à 21 DT Seulement.\r\n\r\nEssayez la pose d’ongles gel avec capsule et affichez des ongles magnifiques pour un long moment grâce à cette offre.\r\n\r\nAu programme :\r\n\r\n- Gel\r\n\r\n- Capsules\r\n\r\n- Pose vernis permanent', 'http://localhost/bon_plans_api/uploads/B38.png', 90, 21, 20, '2018-05-20', '2018-05-20', 20, 1, 17, 15, 0, 0),
(24, 'Forfait Fiançailles comprenant coiffure', 'Vous avez trouvé enfin le grand Amour et il va bientôt passer la bague à votre doigt ? Un si heureux événemet ne doit pas passer inaperçu, et heureusement pour vous que  bonplan et Hi Style Ennasr sont là pour faire de vous la plus rayonnante des futures-mariées.\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand! Une fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nAu programme :\r\n\r\n- Maquillage\r\n\r\n- Coiffure (chignon ou brushing coiffé)\r\n\r\n- Faux cils\r\n\r\n- Soin des mains : Gel, pose de capsules, pose vernis permanent\r\n\r\n- Épilation du visage à la cire', 'http://localhost/bon_plans_api/uploads/B1bkpng.png', 550, 199, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 15, 0, 0),
(25, 'Un lissage à la kératine avec une panoplie de soins à 99 DT Seulement', 'Toutes vos copines vous parlent du fameux Lissage à la Kératine qui changera votre vie mais vous êtes toujours à la recherche de meilleur produit ?\r\n\r\nNe cherchez pas trop, bonplan en partenairat avec Forever Beauty a pensé à vous et vous offre aujourd\"hui un Lissage nourissant  à base de l\'huile d\'Argan et des huilles essentielles!\r\n\r\nAlors, confiez votre beauté aux mains Expertes de notre partenaire pour un RDV unique avec le bien-être!\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nL\'offre vous propose : \r\n\r\n- Lissage et soin capillaire à la Kératine ou protéines ou botox ou collagène (au choix)\r\n\r\n- Soin spécifique du visage\r\n\r\n- Coup d\'éclat\r\n\r\n- Égalisation des pointes', 'http://localhost/bon_plans_api/uploads/B3b2png.png', 420, 99, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 15, 0, 0),
(26, 'Abonnement d\'un mois', 'Envie de vous remettre au sport ? Envie de vous défouler et évacuer le stress de votre quotidien ? Alors profitez aujourd’hui d’une offre exceptionnelle dans un centre de remise en forme chaleureux : Getfit la Marsa.\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nAu programme :\r\n\r\n- Un abonnement d\'un seul mois avec accès illimité ', 'http://localhost/bon_plans_api/uploads/b1bn.png', 85, 40, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 13, 0, 0),
(27, 'Une cure de bien-être à seulement 36 DT', 'Offrez vous un moment de relaxation dans ce centre de kinésithérapie d\'Excellence dans le but de vous débarrasser des toxines accumulées et de vous sentir léger et zen\r\n\r\n\r\n L\'offre comprend:\r\n\r\n- Une séance de sport d\'une heure\r\n\r\n- Un massage relaxant du dos\r\n\r\n- Un massage relaxant des jambes\r\n\r\n- Un massage facial\r\n\r\n- Une séance d\'électrothérapie\r\n\r\n- Une séance d\'infrarouge', 'http://localhost/bon_plans_api/uploads/B1-bt.png', 95, 36, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 13, 0, 0),
(28, 'Profitez d\'un séjour en Demi pension pour 2 à l\'hôtel Ruspina Monastir', 'La ville de Monastir est une très bonne destination pour les personnes en soif de confort et de plaisir. b!gdeal a négocié pour vous un très bon plan pour y séjourner pendant le mois de Ramadan à un prix très doux : 101 DT Seulement au lieu de 150 DT!\r\n\r\nC\'est l\'occasion où jamais pour éviter la cuisine tout en plongeant dans un monde de paix et de calme.\r\n\r\nL\'offre vous propose :\r\n\r\n- Une nuitée en chambre double en demi-pension pour 2 personnes ( une rupture du jeûne au buffet avec un Shour au buffet) \r\n\r\n- Accès à la piscine de l\'hôtel avec les toboggans à partir de 12h\r\n\r\n- Accès à la plage privée de l\'hôtel à partir du midi (transat & parasol)\r\n\r\n- Rupture du jeûne pour 2 personnes  : un buffet sera ouvert spécial Ramadan', 'http://localhost/bon_plans_api/uploads/B2-NEW.png', 150, 101, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 13, 0, 0),
(29, 'Club d\'été pour enfants', 'bonplan en partenariat avec Artech Mahdia Club des activités artistiques et culturelles  vous invite à développer les connaissances linguistiques de votre enfant en l\'inscrivant chez notre partenaure pendant l\'été. Confiez-le à un organisme expert dans les formations linguistiques et qui utilise des méthodes interactives assurées par une équipe d\'enseignants compétente et  certifiée dans la pédagogie de l\'enfant.\r\n\r\nCe club d\'été est la meilleure adresse pour l\' épanouissement, l\'éducation et le développement de la créativité de vos enfants où ils profitent de plus que 11 activités par semaine sous l\'encadrement des enseignants et des coach professionnels.\r\n\r\nAlors saisissez l\'opportunité à un prix très doux : 150 DT  Seulement!', 'http://localhost/bon_plans_api/uploads/bny.png', 350, 150, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 13, 0, 0),
(30, 'Profitez d\'un séjour de 3 nuitées en demi-pension ', 'Situé sur la magnifique baie de Skanes, et tire son nom de magnifiques jardins qui l\'entourent. L\'hôtel Garden Beach vous invite à venir explorer sa touche de style mauresque typiquement arabe.\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nAu Programme : \r\n\r\n- 3 nuitées pour 1 personne en Demi-pension\r\n\r\nConditions :\r\n\r\n- Enfant ayant de 0 à 6 ans logeant dans la chambre des parents : Gratuit\r\n\r\n- 2éme enfant ayant entre 2 et 12 ans logeant dans la chambre des parents : 50% de réduction\r\n\r\n- 1 adulte +  1enfant : 30% de réduction\r\n\r\n-  1er et 2 éme enfant entre 2 ans et 12 ans logeant dans une chambre séparée : 30%  de réduction\r\n\r\n- 3éme lit : 30% de réduction\r\n\r\n- Supplément single : Prévoyez de rajouter 12 DT\r\n\r\n- Accès discothèque : Gratuit', 'http://localhost/bon_plans_api/uploads/b6.png', 121, 210, 20, '2018-05-20', '2018-05-31', 20, 1, 17, 13, 0, 0),
(31, 'Une protection solaire pour véranda à 1300 DT', 'Bonplan propose une large gamme de stores dédiés à la protection de la chaleur de la véranda et des balcons. Ces stores permettent d’apporter une protection solaire efficace grâce son mécanisme très pratique et au choix!\r\n\r\nPayez 1 DT ou 20 BIGFid Seulement sur notre site et le reste chez le marchand!\r\n\r\nUne fois vous essayez le deal, notez le, nous vous remboursons 20 BIGFid\r\n\r\nL\'offre vous propose : \r\n\r\n- Store Gaviota modèle Smart Beige Manuel de dimension: 3*2\r\n\r\nNB : Existe aussi en motorisé\r\n\r\nNB :  Installation gratuite sur le grand tunis', 'http://localhost/bon_plans_api/uploads/BY.png', 1450, 1300, 20, '2018-05-20', '2018-05-31', 20, 1, 16, 16, 0, 0),
(32, 'Perceuse BOSCH à 180 DT', 'Découvrez la performante perceuse de la marque prestigieuse BOSCH qui allie performance et maniabilité à la fois. Qu\'il s\'agisse de percer du bois, du métal, de la maçonnerie ou du béton, cette perceuse répondra certainement à votre besoin.', 'http://localhost/bon_plans_api/uploads/ze.png', 230, 130, 20, '2018-05-20', '2018-05-31', 20, 1, 16, 16, 0, 0),
(33, 'PROFITEZ D\'OFFRES D\'EXCEPTION A OPTIC CENTER', 'Les lunettes de soleil deviennent l’accessoire indispensable  pour affirmer chaque personnalité. Ces caprices de mode deviennent de plus en plus indispensables, c’est pourquoi b!gdeal a deniché pour vous une bonne affaire.', 'http://localhost/bon_plans_api/uploads/tr.png', 2, 1, 20, '2018-05-20', '2018-05-31', 20, 1, 16, 16, 0, 0),
(34, 'Kit porte sectionnelle', 'Bonplan vous propose de découvrir tous la Porte de garage sectionnelle et basculante de COMETA. Retrouvez un large choix de marques et de références Porte de garage sectionnelle et basculante au meilleur prix : 2300 DT Seulement!\r\n\r\nL\'offre vous propose : \r\n\r\n- Kit Porte sectionnelle avec Duo\r\n\r\nNB : Installation gratuite sur le grand tunis', 'http://localhost/bon_plans_api/uploads/tt.png', 2500, 2300, 20, '2018-05-20', '2018-05-31', 20, 1, 16, 16, 0, 0),
(35, 'Formation professionnelle de 3 mois', 'Techcare est une école de formation privée qui vous propose des formations professionnelles riches et complètes avec des meilleures pratiques pour vous préparer à la vie professionnelle comme il faut. Vous aurez encadrés par des formateurs experts dans le domaine qui vont vous initier à la vie professionnelle avec des cours 80% pratiques.\r\n\r\nChoisissez vite la formation que vous désirez suivre à 1750 DT au lieu de 2500 DT!', 'http://localhost/bon_plans_api/uploads/b1.png', 1750, 2500, 20, '2018-05-20', '2018-05-31', 20, 1, 16, 16, 0, 0);

--
-- Déclencheurs `plan`
--
DROP TRIGGER IF EXISTS `tr_notification_valid_plan`;
DELIMITER $$
CREATE TRIGGER `tr_notification_valid_plan` AFTER UPDATE ON `plan` FOR EACH ROW BEGIN
	IF NEW.statut <> OLD.statut 
    AND NEW.statut = 1 
    THEN
        INSERT INTO notification(idPlan, message) VALUES(NEW.idPlan, 'Votre plan a été validé');
    END IF;
   	IF NEW.statut <> OLD.statut 
    AND NEW.statut = -1 
    THEN
		INSERT INTO notification(idPlan, message) VALUES(NEW.idPlan, 'Votre plan a été refusé');
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `plan_commentaire`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `plan_commentaire`;
CREATE TABLE IF NOT EXISTS `plan_commentaire` (
`idPlan` int(11)
,`idAnnonceur` int(11)
,`idCategorie` int(11)
,`nb` bigint(21)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `plan_reservation`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `plan_reservation`;
CREATE TABLE IF NOT EXISTS `plan_reservation` (
`idPlan` int(11)
,`idAnnonceur` int(11)
,`idCategorie` int(11)
,`nb` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `idReservation` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `UrlBonRes` varchar(500) COLLATE utf8_bin NOT NULL,
  `nbplace` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `fk_reservation_client` (`idClient`),
  KEY `fk_reservation_plan` (`idPlan`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `top`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `top`;
CREATE TABLE IF NOT EXISTS `top` (
`annonce` int(11)
,`nb` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `idGroupe` int(11) NOT NULL,
  `mdp` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `nom` varchar(30) COLLATE utf8_bin NOT NULL,
  `prenom` varchar(30) COLLATE utf8_bin NOT NULL,
  `urlphoto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ville` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `adresse` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `dateCreation` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateAcces` datetime DEFAULT NULL,
  `tempsAcces` timestamp NULL DEFAULT NULL,
  `isBanned` tinyint(1) NOT NULL DEFAULT '0',
  `isActif` tinyint(1) NOT NULL DEFAULT '0',
  `codeActivation` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE KEY `uq_utilisateur_email` (`email`) USING BTREE,
  KEY `fk_utilisateur_groupe` (`idGroupe`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `idGroupe`, `mdp`, `email`, `nom`, `prenom`, `urlphoto`, `ville`, `adresse`, `dateCreation`, `dateAcces`, `tempsAcces`, `isBanned`, `isActif`, `codeActivation`) VALUES
(14, 1, '38b0811a087018d19a7677e0762d561801d7cf1e', 'amine.sadfi@hotmail.fr', 'sadfi', 'amine', NULL, NULL, NULL, '2018-05-20 00:00:00', NULL, NULL, 0, 1, 63688),
(15, 1, 'ffd7908da49302049cb65731745aa15a284ddb3a', 'kays.chetoui@esprit.tn', 'kays', 'chetoui', NULL, NULL, NULL, '2018-05-20 00:00:00', NULL, NULL, 0, 1, 10788),
(16, 1, 'ffd7908da49302049cb65731745aa15a284ddb3a', 'yousra.trabelsi@esprit.tn', 'trabelsi', 'yousra', NULL, NULL, NULL, '2018-05-20 00:00:00', NULL, NULL, 0, 1, 24049),
(17, 1, '38b0811a087018d19a7677e0762d561801d7cf1e', 'mohamedali.touir@esprit.tn', 'touir', 'Mohamed ali', NULL, NULL, NULL, '2018-05-20 00:00:00', NULL, NULL, 0, 1, 17705);

-- --------------------------------------------------------

--
-- Structure de la vue `plan_commentaire`
--
DROP TABLE IF EXISTS `plan_commentaire`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `plan_commentaire`  AS  (select `plan`.`idPlan` AS `idPlan`,`plan`.`idAnnonceur` AS `idAnnonceur`,`plan`.`idCategorie` AS `idCategorie`,count(`commentaire`.`idCommentaire`) AS `nb` from (`plan` join `commentaire`) where (`plan`.`idPlan` = `commentaire`.`idPlan`) group by `plan`.`idPlan`) ;

-- --------------------------------------------------------

--
-- Structure de la vue `plan_reservation`
--
DROP TABLE IF EXISTS `plan_reservation`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `plan_reservation`  AS  (select `plan`.`idPlan` AS `idPlan`,`plan`.`idAnnonceur` AS `idAnnonceur`,`plan`.`idCategorie` AS `idCategorie`,count(`reservation`.`idReservation`) AS `nb` from (`plan` join `reservation`) where (`plan`.`idPlan` = `reservation`.`idPlan`) group by `plan`.`idPlan`) ;

-- --------------------------------------------------------

--
-- Structure de la vue `top`
--
DROP TABLE IF EXISTS `top`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `top`  AS  (select `avis`.`idPlan` AS `annonce`,count(`avis`.`avis`) AS `nb` from `avis` where (`avis`.`avis` = 1) group by `avis`.`idPlan`) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `fk_avis_plan` FOREIGN KEY (`idPlan`) REFERENCES `plan` (`idPlan`),
  ADD CONSTRAINT `fk_avis_utilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_commentaire_client` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `fk_commentaire_plan` FOREIGN KEY (`idPlan`) REFERENCES `plan` (`idPlan`);

--
-- Contraintes pour la table `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `fk_plan_categorie` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`),
  ADD CONSTRAINT `fk_plan_client` FOREIGN KEY (`idAnnonceur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_client` FOREIGN KEY (`idClient`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `fk_reservation_plan` FOREIGN KEY (`idPlan`) REFERENCES `plan` (`idPlan`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_utilisateur_groupe` FOREIGN KEY (`idGroupe`) REFERENCES `groupe` (`idGroupe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
