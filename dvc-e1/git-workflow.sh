#!/usr/bin/env bash
# DVC-E1 — Git-Workflow zum manuellen Durcharbeiten.
# Zeile fuer Zeile ausfuehren, dabei die ausgegebenen Commit-Hashes
# in die README eintragen.

set -e

# ---------- 1) Init & Initial-Push ----------
git init
git branch -M main
echo "# SWT — DVC-E1" > README.md   # spaeter durch finale README ersetzen
git add README.md notes.md
git commit -m "chore: initial commit"
git remote add origin git@github.com:RDBHT/SWT.git
git push -u origin main

# ---------- 2) status / diff / commit ----------
echo "weitere Zeile" >> notes.md
git status
git diff
git add notes.md
git commit -m "docs: extend notes"
git push

# ---------- 3) mv / rm ----------
git mv notes.md notizen.md
git commit -m "refactor: rename notes -> notizen"

echo "tmp" > tmp.txt
git add tmp.txt
git commit -m "chore: add tmp file"
git rm tmp.txt
git commit -m "chore: remove tmp file"
git push

# ---------- 4) Zeitreisen ----------
git log --oneline
# Rueckkehr in alten Zustand (detached HEAD), dann zurueck:
INIT_HASH=$(git rev-list --max-parents=0 HEAD)
git checkout "$INIT_HASH"
git checkout main

# Revert demonstrieren:
git revert --no-edit HEAD
git push

# ---------- 5) Branches ----------
git checkout -b feature/a
echo "A" >> notizen.md
git commit -am "feat(a): add A"
git push -u origin feature/a

git checkout main
git checkout -b feature/b
echo "B" >> notizen.md
git commit -am "feat(b): add B"
git push -u origin feature/b

git checkout main
git merge --no-ff feature/a -m "merge: feature/a"
git merge --no-ff feature/b -m "merge: feature/b"   # ggf. Konflikt loesen
git push

# ---------- 6) Pull-Request auf edlich/education ----------
# Manuell via GitHub-Web-UI:
#  1. https://github.com/edlich/education forken
#  2. Im Fork: eine bestehende Datei im student/-Verzeichnis minimal editieren
#  3. PR erstellen, PR-Nummer + Link in README eintragen

# ---------- Pull / log am Ende ----------
git pull --rebase
git log --oneline --graph --all
