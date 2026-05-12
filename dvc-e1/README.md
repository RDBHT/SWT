# DVC-E1 — Versionsverwaltung mit Git

Abgabe zur Einsendeaufgabe DVC-E1 (Softwaretechnik, Kapitel 11).

---

## 1. Repository anlegen

Repository auf GitHub erstellt: `RDBHT/SWT` (public).

## 2. Projekt hochpushen

Initialer Push dieses Repos.
Commit: `<HASH_INIT>`

## 3. Git-Methoden beweisbar angewendet

Alle in der Lerneinheit genannten Operationen wurden ausgeführt. Nachweise über `git log`.

| Befehl   | Commit / Nachweis |
|----------|-------------------|
| `add`    | `<HASH>` |
| `commit` | `<HASH>` |
| `status` | siehe Workflow-Log |
| `diff`   | siehe Workflow-Log |
| `push`   | sichtbar in Repo-Historie |
| `pull`   | siehe Workflow-Log |
| `mv`     | `<HASH>` (Rename `notes.md` → `notizen.md`) |
| `rm`     | `<HASH>` (Entfernen `tmp.txt`) |
| `log`    | siehe Workflow-Log |

## 4. Zeitreisen

- `git checkout <HASH_INIT>` — Rückkehr zum Initial-Commit (detached HEAD)
- `git revert <HASH>` — Rückgängigmachen einer Änderung als neuer Commit: `<HASH_REVERT>`
- `git reset --soft HEAD~1` — Demonstration im Workflow

## 5. Branches

Zwei Branches erstellt, gewechselt und gemerged:

- `feature/a` — kleine Änderung A
- `feature/b` — kleine Änderung B
- Merge: `<HASH_MERGE>` (Merge `feature/b` in `main`)

## 6. Pull-Request auf edlich/education

Pull-Request: `#<NUMMER>` — <LINK>

Kleine Änderung in bestehender Datei im Student-Verzeichnis (kein neuer Ordner).

---

**Hinweis:** Diese README dient als Abgabe-Dokument. Hash-Platzhalter werden nach Ausführung der Workflow-Schritte eingetragen.
