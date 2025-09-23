# strecture de projet:

```md
src/
 ├─ model/                
 │    ├─ Personne.java
 │    ├─ Agent.java
 │    ├─ Departement.java
 │    ├─ Paiement.java
 │    ├─ PaiementBonus.java
 │    ├─ PaiementIndemnite.java
 │    ├─ enums/
 │    │     ├─ TypeAgent.java
 │    │     └─ TypePaiement.java
 │    └─ exceptions/
 │          ├─ AgentIntrouvableException.java
 │          ├─ DepartementIntrouvableException.java
 │          └─ PaiementInvalideException.java
 │
 ├─ repository/            
 │    ├─ AgentRepository.java
 │    ├─ DepartementRepository.java
 │    └─ PaiementRepository.java
 │
 ├─ service/               
 │    ├─ interfaces/
 │    │     ├─ AgentServiceInterface.java
 │    │     ├─ DepartementServiceInterface.java
 │    │     └─ PaiementServiceInterface.java
 │    ├─ AgentService.java
 │    ├─ DepartementService.java
 │    └─ PaiementService.java
 │
 ├─ controller/           
 │    ├─ AgentController.java
 │    ├─ DepartementController.java
 │    └─ PaiementController.java
 │
 ├─ utils/           
 │    └─ Validation.java
 │
 ├─ view/                  
 │    ├─ MainMenu.java
 │    ├─ AgentView.java
 │    ├─ DepartementView.java
 │    └─ PaiementView.java
 │
 └─ config/
      └─ DBConnection.java  # Connexion JDBC 
```