# Techstack

## Stack

- **HTML5** — semantisch, ein File pro Reiter
- **CSS** — [Pico.css](https://picocss.com/) (class-less, ~3 KB) für Basis-Styling; eigene Ergänzungen in `style.css`
- **JavaScript** — Vanilla (kein Framework), nur für das RE-Tool
- **Hosting** — GitHub Pages, Source: `main/docs`
- **Persistenz (RE-Tool)** — `localStorage` + JSON-Export/Import

## Begründung

- Keine Build-Tools, kein npm — die Site läuft direkt aus dem Repo, ein `git push` deployt
- Pico.css liefert anständige Optik ohne Class-Inflation
- Vanilla JS hält die Lernkurve flach und vermeidet Dependency-Bloat
- LocalStorage genügt für Single-User-Demo; JSON-Export erlaubt der Modulbetreuung den Import von Demo-Daten
