# DVC-E1 — Versionsverwaltung mit Git

Abgabe zur Einsendeaufgabe DVC-E1 (Softwaretechnik, Kapitel 11).
Repository: https://github.com/RDBHT/SWT

---

## 1. Repository anlegen

Repository auf GitHub erstellt: `RDBHT/SWT` (public).

## 2. Projekt hochpushen

Initialer Commit: `117a7bd` — `chore: initial commit with DVC-E1`

## 3. Git-Methoden beweisbar angewendet

Alle in der Lerneinheit genannten Operationen ausgeführt. Nachweise in der Commit-History:

| Befehl   | Commit / Nachweis |
|----------|-------------------|
| `add`    | `7ecafef` (tmp.txt hinzugefügt) |
| `commit` | jeder Commit in der History |
| `status` | Workflow-Log |
| `diff`   | Workflow-Log (vor `32f7789`) |
| `push`   | sichtbar an allen `origin/main`-Aktualisierungen |
| `pull`   | `babf08e` (Änderung via GitHub-Web-UI eingespielt) |
| `mv`     | `2efd9da` (Rename `notes.md` → `notizen.md`) |
| `rm`     | `0f4607f` (Entfernen `tmp.txt`) |
| `log`    | `git log --oneline --graph --all` |

## 4. Zeitreisen

- `git checkout 117a7bd` — detached HEAD im Initial-Zustand, anschließend `git checkout main`
- Demo-Commit `f3780bb` (`chore: demo line for revert`)
- `git revert` davon: `e7ed216` (`Revert "chore: demo line for revert"`)

## 5. Branches

Zwei Branches mit Änderungen an unterschiedlichen Dateien (kein Konflikt):

- `feature/a` — Zeile in `notizen.md`: Commit `928a851`
- `feature/b` — Zeile in `README.md`: Commit `a54b9f0`
- Merge `feature/a` → `main`: `ad4a2e7`
- Merge `feature/b` → `main`: `3d7013e`

## 6. Pull-Request auf edlich/education

Pull-Request: `#<NUMMER>` — <LINK>

Kleine Änderung in bestehender Datei im Student-Verzeichnis (kein neuer Ordner), erstellt via GitHub-Web-UI.
