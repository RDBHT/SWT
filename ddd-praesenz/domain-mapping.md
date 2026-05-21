# Aufgabe 3 — Domain Mapping

Beziehungen zwischen den neun Domains, benannt mit den DDD-Context-Mapping-Patterns.
Die Auswahl deckt bewusst sieben verschiedene Pattern-Typen ab.

| # | Beziehung | Pattern | Begründung |
|---|---|---|---|
| 1 | Identitäts- & Zugriffsverwaltung → alle | Open Host Service + Published Language | IAM stellt Authentifizierung als standardisierten Dienst bereit (OIDC-Token als publizierte Sprache) |
| 2 | Onboarding → IAM | Conformist | Onboarding übernimmt das Benutzer-/Rollenmodell von IAM unverändert |
| 3 | Dokumentenmanagement → Wissenssuche & Retrieval | Conformist + Anticorruption Layer | Das DMS-Dokumentmodell wird nicht verhandelt; die Suche übernimmt es nicht direkt, sondern kapselt sich mit einem ACL ab und übersetzt heterogene Dokumentformate in ihr einheitliches Chunk-Modell |
| 4 | Wissenssuche & Retrieval ↔ AI-Assistent | Shared Kernel | Beide Core-Domains teilen das Embedding-/Chunk-Modell als gemeinsamen Kern (RAG) |
| 5 | Community & Q&A → Wissenssuche & Retrieval | Customer/Supplier | Beantwortete Fragen werden als Wissensquelle indexiert |
| 6 | Mitarbeiterverzeichnis → Wissenssuche & Retrieval | Customer/Supplier | Profile und Experten sind durchsuchbar |
| 7 | Onboarding ↔ Learning & Schulung | Partnership | Eng gekoppelt — erfolgreiches Onboarding hängt an zugewiesenen Schulungen |
| 8 | Onboarding → Mitarbeiterverzeichnis | Customer/Supplier | Onboarding legt initiale Profildaten an |
| 9 | alle → News & Kommunikation | Open Host Service | Notification bietet einen offenen Dienst zum Versand von Benachrichtigungen |

## Verwendete Pattern-Typen

Open Host Service · Published Language · Conformist · Customer/Supplier · Anticorruption Layer · Shared Kernel · Partnership.
