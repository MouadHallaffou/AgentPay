# AgentPay – Gestion des Paiements des Agents

## Contexte

AgentPay est une application console Java destinée à digitaliser la gestion des paiements des agents et le suivi des départements d'une organisation. Elle vise à fournir un outil simple, fiable et sécurisé pour gérer les paiements (salaires, primes, bonus, indemnités) et assurer un suivi précis avec des statistiques détaillées.

## Fonctionnalités principales

### Pour les agents

- Consultation des informations personnelles et du département.
- Affichage et filtrage de l’historique des paiements (type, montant, date).
- Calcul du total des paiements reçus.

### Pour les responsables de département

- Création, modification et suppression de départements.
- Gestion des agents (ajout, modification, suppression, affectation à un département).
- Ajout de paiements (salaire, prime, bonus, indemnité) selon l’éligibilité.
- Consultation et filtrage des paiements par agent ou département.
- Calcul automatique du total et de la moyenne des paiements.
- Identification des paiements inhabituels ou incorrects.

### Statistiques

- Par agent : salaire annuel, nombre de primes/bonus/indemnités, paiement le plus élevé/faible.
- Par département : total et moyenne des paiements, classement des agents.
- Global : nombre total d’agents/départements, répartition des paiements, agent le mieux payé.

## Règles de gestion

- Un agent appartient à un seul département.
- Chaque département a un responsable (Agent).
- Un agent peut avoir plusieurs paiements.
- Gestion des exceptions (montant négatif, entités introuvables, conditions non remplies).

## Modélisation des entités

- **Personne** (abstraite) : nom, prénom, email, motDePasse.
- **Agent** : idAgent, typeAgent (Enum), département, paiements.
- **Département** : idDepartement, nom, responsable, agents.
- **Paiement** : idPaiement, type, montant, date, motif/événement, agent.
- **Bonus/Indemnité** : attribut `conditionValidee` (boolean).
- **Enums** : TypeAgent (OUVRIER, RESPONSABLE_DEPARTEMENT, DIRECTEUR, STAGIAIRE), TypePaiement (SALAIRE, PRIME, BONUS, INDEMNITE).

## Architecture technique

- **MVC** : séparation Model / View / Controller.
- **Collections** : gestion des agents, départements, paiements.
- **Streams & Lambda** : filtrage, tri, agrégation.
- **Functional Interfaces** : Predicate, Function, Consumer, Supplier.
- **Optional** : gestion des valeurs absentes.
- **Java Time API** : gestion des dates.
- **Exceptions** : gestion robuste des erreurs métier.
- **Persistance** : JDBC pour stockage et récupération des données.

## Structure du projet

```md
src/
├─ main.java.com.AgentPay.model/  
 │ ├─ Personne.java
│ ├─ Agent.java
│ ├─ Departement.java
│ ├─ Paiement.java
│ ├─ PaiementBonus.java
│ ├─ PaiementIndemnite.java
│ ├─ enums/
│ │ ├─ TypeAgent.java
│ │ └─ TypePaiement.java
│ main.java.com.AgentPay.exceptions/
│ ├─ AgentIntrouvableException.java
│ ├─ DepartementIntrouvableException.java
│ └─ PaiementInvalideException.java
│
├─ main.java.com.AgentPay.repository/  
 │ ├─ interfaces/
│ ├─ AgentRepository.java
│ ├─ DepartementRepository.java
│ └─ PaiementRepository.java
│ └─ interfacesimp/
│ ├─ AgentRepositoryImpl.java
│ ├─ DepartementRepositoryImpl.java
│ └─ PaiementRepositoryImpl.java
│
├─ main.java.com.AgentPay.service/  
 │ ├─ interfaces/
│ │ ├─ AuthService.java
│ │ ├─ AgentService.java
│ │ ├─ DepartementService.java
│ │ └─ PaiementService.java
│ ├─ AgentServiceImpl.java
│ ├─ AuthServiceImpl.java
│ ├─ DepartementServiceImpl.java
│ └─ PaiementServiceImpl.java
│
├─ main.java.com.AgentPay.controller/  
 │ ├─ AgentController.java
│ ├─ DepartementController.java
│ └─ PaiementController.java
│
├─ main.java.com.AgentPay.utils/  
 │ └─ Validation.java
│
├─ main.java.com.AgentPay.view/  
 │ ├─ MainMenu.java
│ ├─ AgentView.java
│ ├─ DepartementView.java
│ └─ PaiementView.java
│
└─ main.java.com.AgentPay.config/
└─ DBConnection.java # Connexion JDBC
```

## Liens utiles

- [Diagramme de conception (Lucidchart)](https://lucid.app/lucidchart/cfbeffee-d9ee-4d6b-a85d-62523dd54125/edit?viewport_loc=-3094%2C-949%2C9032%2C3913%2C0_0&invitationId=inv_b54f0a3e-bd3a-490b-a434-cf26fd08f088)
- [Repository GitHub](https://github.com/MouadHallaffou/AgentPay)

## Technologies utilisées

- Java 17+
- Collections, Streams, Lambda, Optional
- Java Time API
- JDBC (persistance)
- Architecture MVC

---

> Ce projet met en pratique la programmation orientée objet, la gestion des collections, les streams, la persistance JDBC et la robustesse logicielle via la gestion des exceptions.

