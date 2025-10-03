DROP DATABASE IF EXISTS agent_pay;

CREATE DATABASE agent_pay;

USE agent_pay;

CREATE TABLE departements (
    departementID BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
);

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
    ) NOT NULL,
    departementID BIGINT NOT NULL,
    CONSTRAINT fk_departement FOREIGN KEY (departementID) REFERENCES departements (departementID) ON DELETE CASCADE ON UPDATE CASCADE,
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
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
    condition_validee BOOLEAN DEFAULT TRUE,
    agentID BIGINT NOT NULL,
    CONSTRAINT fk_agent FOREIGN KEY (agentID) REFERENCES agents (agentID) ON DELETE CASCADE ON UPDATE CASCADE,
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- INSERTION DE DEPARTEMENTS PAR DEFAUT
INSERT INTO departements (name) VALUES ('Ressources Humaines');

-- INSERTION DU DIRECTEUR PAR DEFAUT
INSERT INTO agents (firstName, lastName, email, password, type_agent, departementID)
VALUES ('Mouad', 'Hallaffou', 'mouad@gmail.com', '1234', 'DIRECTEUR', 1);

