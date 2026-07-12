# Spec — Modul `mock-service` (mit Cline gebaut)

## Zweck
Kontrollierbares Test-Target: ein kleiner HTTP-Server, dessen `/health`-Antwortcode
zur Laufzeit gesetzt werden kann — so lassen sich Checks deterministisch testen,
ohne echte Dienste. **Werkzeug-Nachweis:** dieses Modul entsteht mit dem
VS-Code-Plugin **Cline** (nicht mit der CLI) und belegt so die zweite Werkzeug-Art.

## Öffentliche Schnittstelle
- `class MockService { void setHealthCode(int); int healthCode(); void start(int port); void stop(); }`
- HTTP: `GET /health` → aktueller Code; `GET /admin?code=NNN` → setzt den Code
- `main(String[] args)` startet den Server (Default-Port 8081)

## Fachregeln
- Startwert des Health-Codes ist `200`
- `/admin?code=500` schaltet `/health` auf `500` (simulierter Ausfall)
- Zustand thread-sicher (`AtomicInteger`)

## Akzeptanztests (siehe `MockServiceTest`)
- neuer `MockService` liefert `healthCode() == 200`
- nach `setHealthCode(503)` liefert `healthCode() == 503`

## Abhängigkeiten
keine internen Module (eigenständiges Test-Target)
