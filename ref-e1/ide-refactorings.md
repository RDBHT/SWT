# IDE und unterstützte Refactorings

## Genutzte IDE: Visual Studio Code

Ich arbeite mit **Visual Studio Code**. Die Refactoring-Unterstützung für
JavaScript und TypeScript stammt aus dem eingebauten TypeScript-Language-Service
und wird über die Glühbirne bzw. *Refactor…* (`Strg+.`) angeboten.

## Unterstützte Refactorings (JS/TS)

| VS-Code-Aktion | Entspricht im Fowler-Katalog |
|---|---|
| Extract to function / method | Extract Function |
| Extract to constant | Extract Variable |
| Inline variable | Inline Variable |
| Move to new file | Move Function / Move Class |
| Rename Symbol (`F2`) | Rename Variable / Change Function Declaration |
| Convert parameters to destructured object | Introduce Parameter Object |
| Extract type / interface (TypeScript) | ähnlich Extract Class (für Typen) |
| Convert to template string / async | syntaktische Modernisierung |

## Einordnung

VS Code deckt die kleinen, lokalen Refactorings zuverlässig ab — Extrahieren,
Inlining, Umbenennen, Verschieben. Die großen, strukturellen Refactorings
(Replace Conditional with Polymorphism, Extract Class mit Verhalten) sind **nicht
automatisiert**; sie entstehen aus mehreren kleinen Schritten plus Handarbeit.
Spezialisierte IDEs wie IntelliJ IDEA oder Eclipse bieten hier ein deutlich
größeres Refactoring-Menü.
