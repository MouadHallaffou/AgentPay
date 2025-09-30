# strecture de projet:

```md
src/
 ├─ main.java.AgentPay.model/                
 │    ├─ Personne.java
 │    ├─ Agent.java
 │    ├─ Departement.java
 │    ├─ Paiement.java
 │    ├─ PaiementBonus.java
 │    ├─ PaiementIndemnite.java
 │    ├─ enums/
 │    │     ├─ TypeAgent.java
 │    │     └─ TypePaiement.java
 │    └─ main.java.AgentPay.exceptions/
 │          ├─ AgentIntrouvableException.java
 │          ├─ DepartementIntrouvableException.java
 │          └─ PaiementInvalideException.java
 │
 ├─ main.java.AgentPay.repository/            
 │    ├─ interfaces/
 │    └─ interfacesimp/
 │         ├─ AgentRepository.java
 │         ├─ DepartementRepository.java
 │         └─ PaiementRepository.java
 │
 ├─ main.java.AgentPay.service/               
 │    ├─ interfaces/
 │    │     ├─ AgentServiceInterface.java
 │    │     ├─ DepartementServiceInterface.java
 │    │     └─ PaiementServiceInterface.java
 │    ├─ AgentService.java
 │    ├─ DepartementService.java
 │    └─ PaiementService.java
 │
 ├─ main.java.AgentPay.controller/           
 │    ├─ AgentController.java
 │    ├─ DepartementController.java
 │    └─ PaiementController.java
 │
 ├─ main.java.AgentPay.utils/           
 │    └─ Validation.java
 │
 ├─ main.java.AgentPay.view/                  
 │    ├─ MainMenu.java
 │    ├─ AgentView.java
 │    ├─ DepartementView.java
 │    └─ PaiementView.java
 │
 └─ main.java.AgentPay.config/
      └─ DBConnection.java  # Connexion JDBC 
```