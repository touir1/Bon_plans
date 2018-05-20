-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 06 mai 2018 à 21:41
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
-- Base de données :  `1cinfo01pi`
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
  PRIMARY KEY (`idAvis`)
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idCategorie`, `titre`, `urlPhoto`) VALUES
(9, 'testing', NULL);

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
  PRIMARY KEY (`idCommentaire`)
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
  PRIMARY KEY (`idPlan`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `plan`
--

INSERT INTO `plan` (`idPlan`, `titre`, `description`, `urlPhoto`, `prixInitial`, `prixPromo`, `nbPlaceTotal`, `dateDebut`, `dateFin`, `nbPlaceDispo`, `statut`, `idAnnonceur`, `idCategorie`) VALUES
(5, 'test plan', 'testing new plan', NULL, 0, 0, 0, '2018-01-01', '2019-01-01', 0, 0, 12, 9),
(6, 'test plan', 'testing new plan', NULL, 0, 0, 0, '2018-01-01', '2019-01-01', 0, 2, 12, 9);

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
  PRIMARY KEY (`idReservation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  UNIQUE KEY `uq_utilisateur_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `idGroupe`, `mdp`, `email`, `nom`, `prenom`, `urlphoto`, `ville`, `adresse`, `dateCreation`, `dateAcces`, `tempsAcces`, `isBanned`, `isActif`, `codeActivation`) VALUES
(11, 2, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'touir.mat@gmail.com', 'Touir', 'Mohamed Ali', NULL, 'Rades', '06 kawafel street rades', '2018-05-01 00:00:00', NULL, NULL, 0, 0, 0),
(12, 1, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'eya.touir@gmail.com', 'Touir', 'Eya', NULL, 'Rades', '06 kawafel street rades', '2018-05-01 00:00:00', NULL, NULL, 0, 0, 0),
(13, 3, 'b3b2b988f52e47986d9f2dd6a2434e1d113502ff', 'admin.admin@gmail.com', 'Super', 'Admin', NULL, 'inconnu', 'inconnu', '2018-05-01 00:00:00', NULL, NULL, 0, 0, 0);

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
