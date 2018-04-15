-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 15 avr. 2018 à 12:17
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
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) COLLATE utf8_bin NOT NULL,
  `urlPhoto` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idCategorie`, `titre`, `urlPhoto`) VALUES
(3, 'loisirs', 'C:/Users/SadfiAmine/Desktop/loisirs.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `texte` varchar(255) COLLATE utf8_bin NOT NULL,
  `date` date NOT NULL,
  `nbJaime` int(11) NOT NULL,
  `nbJaimePas` int(11) NOT NULL,
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
  `idGroupe` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `plan`
--

DROP TABLE IF EXISTS `plan`;
CREATE TABLE IF NOT EXISTS `plan` (
  `idPlan` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `urlPhoto` int(255) DEFAULT NULL,
  `prixInitial` double NOT NULL,
  `prixPromo` double NOT NULL,
  `nbPlaceTotal` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `nbPlaceDispo` int(11) NOT NULL,
  `statut` tinyint(4) NOT NULL,
  `nbJaime` int(11) NOT NULL,
  `nbJaimePas` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `idAnnonceur` int(11) NOT NULL,
  `idCategorie` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idPlan`),
  KEY `fk_plan_client` (`idAnnonceur`),
  KEY `fk_plan_categorie` (`idCategorie`),
  KEY `idClient` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `idReservation` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `UrlBonRes` varchar(50) COLLATE utf8_bin NOT NULL,
  `idClient` int(11) NOT NULL,
  `idPlan` int(11) NOT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `fk_reservation_client` (`idClient`),
  KEY `fk_reservation_plan` (`idPlan`)
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
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE KEY `uq_utilisateur_email` (`email`) USING BTREE,
  KEY `fk_utilisateur_groupe` (`idGroupe`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables déchargées
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
