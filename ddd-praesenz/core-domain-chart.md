# Aufgabe 2 — Core Domain Chart

Einordnung der neun Domains nach **Business Differentiation** (X-Achse) und **Model Complexity** (Y-Achse) in die Zonen Core / Supporting / Generic.

Leitgedanke: Das Alleinstellungsmerkmal einer Wissensplattform ist die **intelligente Suche und der AI-Assistent** — der Grund, die Plattform statt verstreuter Ordner zu nutzen. Diese Domains gehören in den Core und sind selbst zu bauen und zu schützen. Standardfunktionen (Auth, News, LMS) gehören in den Generic-Bereich und können zugekauft werden.

| Domain | Zone | Business Differentiation | Model Complexity | Begründung |
|---|---|---|---|---|
| Wissenssuche & Retrieval | Core | hoch | hoch | Alleinstellungsmerkmal — semantische Suche und Ranking |
| AI-Assistent | Core | hoch | hoch | RAG und Antwortgenerierung; "High AI Field" |
| Dokumentenmanagement | Supporting | mittel | mittel | notwendig, aber kein Differenzierungsmerkmal |
| Community & Q&A | Supporting | mittel | mittel-niedrig | organisationsspezifisch, aber kein USP |
| Mitarbeiterverzeichnis | Supporting | mittel | mittel | Experten-Matching hat fachliche Tiefe |
| Onboarding | Supporting | niedrig-mittel | niedrig | etwas firmenspezifisch, aber simpel |
| Identitäts- & Zugriffsverwaltung | Generic | niedrig | mittel | Standardproblem — Entra ID / Keycloak / Auth0 |
| News & Kommunikation | Generic | niedrig | niedrig | Standard-Feed |
| Learning & Schulung | Generic | niedrig | niedrig-mittel | LMS als fertiges Produkt verfügbar |

**Verteilung:** 2 Core · 4 Supporting · 3 Generic.

## AI-Bezug (Dozenten-Hinweis)

Der Dozent betont: *"Core = High AI Field, Generic = Playground for lame AI (Chatbots)."* Das spiegelt sich hier: starker AI-Einsatz (RAG, semantische Suche) lohnt sich im Core (Wissenssuche, AI-Assistent), wo er echte Differenzierung schafft. Ein simpler News-Chatbot im Generic-Bereich wäre dagegen "lame AI" — nett, aber ohne strategischen Wert.
