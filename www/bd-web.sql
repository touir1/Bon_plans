-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 26 Avril 2018 à 12:51
-- Version du serveur :  5.7.14
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `1cinfo01pi`
--

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int(11) NOT NULL,
  `annonce` int(11) NOT NULL,
  `avi` int(11) NOT NULL DEFAULT '0',
  `note` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `avis`
--

INSERT INTO `avis` (`id`, `annonce`, `avi`, `note`) VALUES
(1, 8, 1, 0),
(2, 14, 1, 0),
(3, 15, 1, 0),
(4, 16, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `idCategorie` int(11) NOT NULL,
  `titre` varchar(30) COLLATE utf8_bin NOT NULL,
  `urlPhoto` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`idCategorie`, `titre`, `urlPhoto`) VALUES
(11, 'Hotellerie', 'uploads/banner.jpg'),
(12, 'SPA', 'uploads/spa.jpg'),
(13, 'Beaute', 'uploads/tÃ©lÃ©chargement.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `idCommentaire` int(11) NOT NULL,
  `texte` varchar(255) COLLATE utf8_bin NOT NULL,
  `date` date DEFAULT NULL,
  `nbJaime` int(11) NOT NULL DEFAULT '0',
  `nbJaimePas` int(11) NOT NULL DEFAULT '0',
  `idClient` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `commentaire`
--

INSERT INTO `commentaire` (`idCommentaire`, `texte`, `date`, `nbJaime`, `nbJaimePas`, `idClient`, `idPlan`) VALUES
(1, 'offre interessante', '2018-04-24', 0, 0, 33, 14);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `idGroupe` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `annonce_id` int(11) NOT NULL,
  `url` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `plan`
--

CREATE TABLE `plan` (
  `idPlan` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` longtext COLLATE utf8_bin NOT NULL,
  `urlPhoto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `prixInitial` double NOT NULL,
  `prixPromo` double NOT NULL,
  `nbPlaceTotal` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `nbPlaceDispo` int(11) NOT NULL,
  `statut` tinyint(4) NOT NULL DEFAULT '0',
  `idAnnonceur` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `plan`
--

INSERT INTO `plan` (`idPlan`, `titre`, `description`, `urlPhoto`, `prixInitial`, `prixPromo`, `nbPlaceTotal`, `dateDebut`, `dateFin`, `nbPlaceDispo`, `statut`, `idAnnonceur`, `idCategorie`) VALUES
(14, 'Offre spÃ©ciale vacances', 'Trouvez votre hÃ´tel idÃ©al au meilleur prix. ', 'uploads/offre1.jpg', 100, 80, 50, '2018-04-24', '2018-04-28', 47, 0, 34, 11),
(15, 'Forfait kÃ©ratine', 'Le spÃ©cialiste des soins capillaires et de kÃ©ratine Moehair', 'uploads/keratine.jpg', 70, 56, 200, '2018-04-24', '2018-05-22', 200, 0, 35, 12),
(16, 'Soin visage', 'description', 'uploads/keratine.jpg', 100, 80, 15, '2018-04-24', '2018-04-28', 13, 0, 33, 13),
(17, 'test', 'java', 'image', 15, 12, 100, '2018-04-26', '2018-04-26', 100, 0, 33, 11);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(11) NOT NULL,
  `date` date NOT NULL,
  `UrlBonRes` varchar(50) COLLATE utf8_bin NOT NULL,
  `Nbplace` int(11) NOT NULL DEFAULT '0',
  `statut` int(11) NOT NULL DEFAULT '0',
  `idClient` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `date`, `UrlBonRes`, `Nbplace`, `statut`, `idClient`, `idPlan`) VALUES
(3, '2018-04-24', 'testurl', 10, 0, 34, 8),
(4, '2018-04-24', 'testurl', 3, 0, 34, 14),
(5, '2018-04-24', 'testurl', 2, 0, 35, 16);

-- --------------------------------------------------------

--
-- Structure de la table `signaler`
--

CREATE TABLE `signaler` (
  `idSignaler` int(11) NOT NULL,
  `idCommentaire` int(11) NOT NULL,
  `idClient` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `top`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `top` (
`annonce` int(11)
,`nb` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int(11) NOT NULL,
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
  `isBanned` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `idGroupe`, `mdp`, `email`, `nom`, `prenom`, `urlphoto`, `ville`, `adresse`, `dateCreation`, `dateAcces`, `tempsAcces`, `isBanned`) VALUES
(33, 1, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'artan@email.com', 'ben slimen', 'Mohamed Ali', 'uploads/artan.jpg', 'Tunis', 'Kabbaria', '2018-04-24 12:15:11', NULL, NULL, 0),
(34, 1, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'yosra@email.com', 'Trabelsi', 'Yosra', 'uploads/yosra.jpg', 'Ariana', 'Ghazzela', '2018-04-24 12:19:51', NULL, NULL, 0),
(35, 1, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'sadfi@email.com', 'Sadfi', 'Amine', 'uploads/sadfi.jpg', 'Ariana', 'Naser', '2018-04-24 12:20:38', NULL, NULL, 0),
(36, 3, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'kays@email.com', 'Chetoui', 'Kays', 'uploads/kays.jpg', 'Ariana', 'Nsit esmha', '2018-04-24 12:24:39', NULL, NULL, 0),
(37, 2, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'touir@email.com', 'Touir', 'Mohamed Ali', 'uploads/dali.jpg', 'Ben Arous', 'Rades', '2018-04-24 12:26:57', NULL, NULL, 0),
(38, 2, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'admin@email.com', 'admin', 'test', 'uploads/banner.jpg', 'Tunis', 'tunis', '2018-04-24 19:19:53', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la vue `top`
--
DROP TABLE IF EXISTS `top`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `top`  AS  (select `avis`.`annonce` AS `annonce`,count(`avis`.`avi`) AS `nb` from `avis` where (`avis`.`avi` = 1) group by `avis`.`annonce`) ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `annonce` (`annonce`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`idCategorie`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`idCommentaire`),
  ADD KEY `fk_commentaire_client` (`idClient`),
  ADD KEY `fk_commentaire_plan` (`idPlan`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`idGroupe`);

--
-- Index pour la table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`idPlan`),
  ADD KEY `fk_plan_client` (`idAnnonceur`),
  ADD KEY `fk_plan_categorie` (`idCategorie`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `fk_reservation_client` (`idClient`),
  ADD KEY `fk_reservation_plan` (`idPlan`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `uq_utilisateur_email` (`email`) USING BTREE,
  ADD KEY `fk_utilisateur_groupe` (`idGroupe`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `idCategorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `idCommentaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `idGroupe` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `plan`
--
ALTER TABLE `plan`
  MODIFY `idPlan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idReservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- Contraintes pour les tables exportées
--

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
