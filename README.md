# Projet Gestion des Étudiants — Partie 4

## Objectif Général
Atteindre un niveau de qualité logicielle professionnel sur le micro-service étudiant en mettant en place une stratégie de test complète, en liant l'outillage de test à la traçabilité Jira via Xray, et en ajoutant un micro-service d'authentification.

## Architecture Microservices (Mis à jour Partie 4)
- **auth-service** : Micro-service Node.js/Express pour l'authentification (port 3001)
- **api-spring-boot** : Service de gestion des étudiants avec stratégie de test complète (port 8081)
- **frontend** : Interface Next.js avec tests E2E Cypress (port 3000)
- **mongodb** : Base de données pour le service d'authentification
- **postgres-etudiants** : Base de données pour le service étudiant
- **eureka-server** : Annuaire des services (port 8761)
- **api-gateway** : Point d'entrée unique (port 8090)

## Stratégie de Test (Couverture ≥ 80%)
- **Tests Unitaires** : JUnit 5 + Mockito (Isolation complète de la couche Service).
- **Tests d'Intégration** : Testcontainers + PostgreSQL (Validation de la couche DAO).
- **Tests E2E** : Cypress (Scénarios utilisateurs complets sur le Frontend).
- **Tests de Stress** : Gatling (Validation des performances sous charge).
- **Mesure de Couverture** : JaCoCo (Échec du build si < 80%).

## Intégration Jira & Xray
- **GitHub ↔ Jira** : Liens automatiques via les clés de tickets (ex: `PROJ-4`).
- **Xray** : Publication automatique des résultats de tests JUnit vers Jira via l'API REST dans le pipeline CI/CD.

## Lancer le projet
```bash
docker compose up --build
```

## URLs importantes
| Service | URL |
|---------|-----|
| Eureka Dashboard | http://localhost:8761 |
| Etudiant Swagger | http://localhost:8081/swagger-ui/index.html |
| API Gateway | http://localhost:8090 |
| Frontend | http://localhost:3000 |
| Auth Service | http://localhost:3001 |

## Structure du dépôt attendue (Partie 4)
```text
/projet-etudiants/
├── api-spring-boot/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   │       ├── java/
│   │       │   ├── unit/        # Tests unitaires JUnit + Mockito
│   │       │   └── integration/ # Tests d'intégration Testcontainers
│   │       └── resources/
│   │           └── features/    # Fichiers Gherkin (Partie 2)
│   └── pom.xml                  # Avec JaCoCo + Testcontainers + Gatling
├── auth-service/                # Micro service Node.js (nouveau)
│   ├── src/
│   │   ├── models/User.js
│   │   ├── routes/auth.js
│   │   └── app.js
│   └── package.json
├── frontend/
│   └── cypress/
│       └── e2e/                 # Tests E2E Cypress (nouveau)
├── .github/
│   ├── workflows/
│   │   └── test-and-report.yml  # Pipeline CI avec publication Xray
│   ├── ISSUE_TEMPLATE/
│   └── pull_request_template.md
└── docker-compose.yml           # Mis à jour avec mongodb + auth-service
```
