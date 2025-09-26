DROP DATABASE IF EXISTS agent_pay;

CREATE DATABASE agent_pay;

USE agent_pay;

CREATE TABLE agents (
    agentID BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    type_agent ENUM(
        'OUVRIER',
        'RESPONSABLE',
        'DIRECTEUR',
        'STAGIAIRE'
    ) NOT NULL
);

CREATE TABLE departements (
    departementID BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
#     responsableID BIGINT UNIQUE,
#     CONSTRAINT fk_responsable FOREIGN KEY (responsableID) REFERENCES agents (agentID)
);

CREATE TABLE paiements (
    paiementID BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_paiement ENUM(
        'SALAIRE',
        'PRIME',
        'BONUS',
        'INDEMNITE'
    ) NOT NULL,
    montant DECIMAL(12, 2) NOT NULL CHECK (montant >= 0),
    datePaiement DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    motif VARCHAR(255),
    condition_validee BOOLEAN,
    agentID BIGINT NOT NULL,
    CONSTRAINT fk_agent FOREIGN KEY (agentID) REFERENCES agent (agentID)
);

-- Ajout de la colonne departementID à la table agents
ALTER TABLE agents ADD COLUMN departementID BIGINT NULL,
ADD CONSTRAINT fk_departement FOREIGN KEY (departementID) REFERENCES departements (departementID);
ALTER TABLE agents ADD COLUMN is_active BOOLEAN DEFAULT TRUE;

-- INSERTION DU DIRECTEUR PAR DEFAUT
INSERT INTO agents (firstName, lastName, email, password, type_agent)
VALUES ('Mouad', 'Hallaffou', 'mouad@gmail.com', '1234', 'DIRECTEUR');
