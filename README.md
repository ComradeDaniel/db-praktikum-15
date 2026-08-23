# DB Praktikum Gruppe 15

## Starten der Datenbank:
Docker wird benötigt:
`cd db && docker compose up -d`

## Starten des Ladeprogramms:
### IntelliJ Idea:
- `Aufgabe 1` als Run Configuration oben rechts auswählen und starten

### Kommandozeile:
Windows:
- `.\gradlew.bat Aufgabe1:run` (Aus der Projekt-Root)

Unix-ähnliches Systeme:
- `./gradlew Aufgabe1:run`

## Starten der Aufgabe-3-Anwendung (Kotlin / Spring Boot / Hibernate):
Datenbank muss laufen (siehe oben). Node.js/npm wird für das Frontend benötigt.

Zuerst das Frontend bauen

- `./gradlew :Aufgabe3:buildFrontend`
- oder `cd Aufgabe3/frontend && npm install && npm run build`

Danach aus der Projekt-Root:

- `./gradlew :Aufgabe3:bootRun`

Dann: http://localhost:8080 (React-Frontend) und http://localhost:8080/api/health

Entwicklung mit Hot-Reload: Backend auf Port 8080 starten, danach `npm run dev` in `Aufgabe3/frontend` (Proxy nach `/api`).

Verbindungsparameter und Implementierungsklasse stehen in `db/db.properties`

