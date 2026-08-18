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
Datenbank muss laufen (siehe oben). Aus der Projekt-Root:

- `./gradlew :Aufgabe3:bootRun`

Danach: http://localhost:8080 (statische Seite) und http://localhost:8080/api/health

Verbindungsparameter und Implementierungsklasse stehen in `db/db.properties`

