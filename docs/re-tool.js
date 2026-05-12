// SWT-Portfolio — RE-Tool
// Vanilla JS, 15 Attribute, LocalStorage-Persistenz, JSON-Export/Import.

const STORAGE_KEY = "swt-re-tool";

// Demo-Daten = Abgabe-Requirements (identisch zur Markdown-Tabelle in req-e1/requirements-table.md).
const DEMO_DATA = [
  {
    id: "REQ-001",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "Nutzer kann zwischen Reitern navigieren",
    lang: "Die Top-Navigation der SWT-Portfolio-Site erlaubt das Wechseln zwischen Home, DVC-E1, REQ-E1 und RE-Tool von jeder Unterseite aus.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnehmer: "Modulbetreuung",
    abnahme: "Jeder Reiter ist von jedem anderen Reiter aus per Klick erreichbar; der aktive Reiter wird visuell markiert.",
    abhaengig: "",
    referenz: "iter-01.md / F1.1",
    jur: "nein",
    anmerkung: "Iter 1, abgeschlossen"
  },
  {
    id: "REQ-002",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "Site ist über GitHub Pages erreichbar",
    lang: "Die Site wird aus dem main-Branch, Ordner /docs deployed.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnehmer: "Modulbetreuung",
    abnahme: "URL https://rdbht.github.io/SWT/ liefert HTTP 200 und rendert die Landing innerhalb von 2 Sekunden.",
    abhaengig: "",
    referenz: "iter-01.md / F1.4",
    jur: "nein",
    anmerkung: "Iter 1, abgeschlossen"
  },
  {
    id: "REQ-003",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "RE-Tool persistiert Eingaben im Browser",
    lang: "Eingegebene Requirements werden im LocalStorage unter Key 'swt-re-tool' gehalten und überleben einen Page-Reload.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnehmer: "Tech-Lead",
    abnahme: "Nach Reload zeigt die Tabelle dieselben Einträge wie vor dem Reload.",
    abhaengig: "REQ-002",
    referenz: "iter-02.md / F2.4",
    jur: "nein",
    anmerkung: "Iter 2, abgeschlossen"
  },
  {
    id: "REQ-004",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "JSON-Export und -Import",
    lang: "Alle Requirements können als JSON-Datei heruntergeladen werden; eine valide JSON-Datei kann den Datenstand wiederherstellen.",
    status: "Erledigt",
    prio: "B",
    kategorie: "Funktional",
    abnehmer: "Modulbetreuung",
    abnahme: "Export erzeugt swt-requirements.json; deren Import stellt den identischen Datenstand wieder her.",
    abhaengig: "REQ-003",
    referenz: "iter-02.md / F2.5 + F2.6",
    jur: "nein",
    anmerkung: "Iter 2, abgeschlossen"
  },
  {
    id: "REQ-005",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "Keine personenbezogenen Daten verarbeitet",
    lang: "Das Tool läuft rein im Browser. Es gibt kein Backend, kein Tracking und keine Analytics.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Sicherheit",
    abnehmer: "Datenschutz / Legal",
    abnahme: "Keine Netz-Requests an Dritte beim Anlegen, Speichern oder Löschen eines Requirements (Pico.css wird ausschließlich beim ersten Seitenaufruf vom CDN geladen).",
    abhaengig: "",
    referenz: "DSGVO Art. 5 (Datenminimierung)",
    jur: "ja",
    anmerkung: "DSGVO-relevant"
  },
  {
    id: "REQ-006",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "Site ist mobil bedienbar",
    lang: "Layout und Tabellen passen sich Viewport-Breiten ab 320 px an; breite Tabellen sind horizontal scrollbar.",
    status: "Erledigt",
    prio: "B",
    kategorie: "UX",
    abnehmer: "Modulbetreuung",
    abnahme: "Alle Reiter sind auf 320 px Viewport-Breite lesbar; die Requirements-Tabelle erhält horizontalen Scrollbar bei Bedarf.",
    abhaengig: "REQ-002",
    referenz: "Pico.css responsive defaults",
    jur: "nein",
    anmerkung: "automatisch via Pico.css"
  },
  {
    id: "REQ-007",
    datum: "2026-05-12",
    autor: "RD",
    historie: "v1.0 (2026-05-12)",
    kurz: "Constitution ist vom REQ-E1-Reiter aus erreichbar",
    lang: "Im REQ-E1-Reiter sind mission.md, roadmap.md, techstack.md und die drei Iterations-Feature-Specs verlinkt.",
    status: "Erledigt",
    prio: "B",
    kategorie: "Funktional",
    abnehmer: "Modulbetreuung",
    abnahme: "Alle sieben Constitution-Dateien sind aus dem REQ-E1-Reiter mit einem Klick erreichbar (Repo-Links).",
    abhaengig: "REQ-001",
    referenz: "iter-03.md / F3.4",
    jur: "nein",
    anmerkung: "Iter 3"
  }
];

// --- state -----------------------------------------------------------------

let requirements = [];

function load() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    requirements = raw ? JSON.parse(raw) : [];
  } catch (e) {
    console.warn("LocalStorage parse failed, resetting.", e);
    requirements = [];
  }
  if (requirements.length === 0) {
    requirements = [...DEMO_DATA];
    save();
  }
}

function save() {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(requirements));
}

// --- render ----------------------------------------------------------------

function render() {
  const tbody = document.querySelector("#reqTable tbody");
  tbody.innerHTML = "";
  for (const r of requirements) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td><code>${esc(r.id)}</code></td>
      <td>${esc(r.datum)}</td>
      <td>${esc(r.autor)}</td>
      <td>${esc(r.historie)}</td>
      <td>${esc(r.kurz)}</td>
      <td>${esc(r.lang)}</td>
      <td>${esc(r.status)}</td>
      <td>${esc(r.prio)}</td>
      <td>${esc(r.kategorie)}</td>
      <td>${esc(r.abnehmer)}</td>
      <td>${esc(r.abnahme)}</td>
      <td>${esc(r.abhaengig)}</td>
      <td>${esc(r.referenz)}</td>
      <td>${esc(r.jur)}</td>
      <td>${esc(r.anmerkung)}</td>
      <td><button class="secondary outline" data-del="${esc(r.id)}">&times;</button></td>
    `;
    tbody.appendChild(tr);
  }
  document.querySelector("#counter").textContent =
    requirements.length + " Requirements";
}

function esc(s) {
  return (s ?? "")
    .toString()
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

// --- CRUD ------------------------------------------------------------------

function addRequirement(formData) {
  const r = Object.fromEntries(formData.entries());
  if (requirements.some(x => x.id === r.id)) {
    alert(`ID "${r.id}" existiert bereits.`);
    return false;
  }
  requirements.push(r);
  save();
  render();
  return true;
}

function deleteRequirement(id) {
  requirements = requirements.filter(r => r.id !== id);
  save();
  render();
}

function clearAll() {
  if (!confirm("Wirklich alle Requirements löschen?")) return;
  requirements = [];
  save();
  render();
}

function seedDemo() {
  if (requirements.length > 0 &&
      !confirm("Aktuelle Daten überschreiben und Demo-Daten laden?")) return;
  requirements = [...DEMO_DATA];
  save();
  render();
}

// --- JSON IO ---------------------------------------------------------------

function exportJson() {
  const blob = new Blob([JSON.stringify(requirements, null, 2)],
                       { type: "application/json" });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = "swt-requirements.json";
  a.click();
  URL.revokeObjectURL(url);
}

function importJson(file) {
  const reader = new FileReader();
  reader.onload = e => {
    try {
      const parsed = JSON.parse(e.target.result);
      if (!Array.isArray(parsed))
        throw new Error("JSON-Datei muss ein Array sein.");
      requirements = parsed;
      save();
      render();
      alert(`${parsed.length} Requirements importiert.`);
    } catch (err) {
      alert("Import fehlgeschlagen: " + err.message);
    }
  };
  reader.readAsText(file);
}

// --- wiring ----------------------------------------------------------------

window.addEventListener("DOMContentLoaded", () => {
  load();
  render();

  document.querySelector("#reqForm").addEventListener("submit", e => {
    e.preventDefault();
    if (addRequirement(new FormData(e.target))) {
      e.target.reset();
    }
  });

  document.querySelector("#reqTable").addEventListener("click", e => {
    const id = e.target.dataset.del;
    if (id) deleteRequirement(id);
  });

  document.querySelector("#exportBtn").addEventListener("click", exportJson);

  document.querySelector("#importBtn").addEventListener("click", () => {
    document.querySelector("#importInput").click();
  });
  document.querySelector("#importInput").addEventListener("change", e => {
    if (e.target.files[0]) importJson(e.target.files[0]);
    e.target.value = "";
  });

  document.querySelector("#seedBtn").addEventListener("click", seedDemo);
  document.querySelector("#clearBtn").addEventListener("click", clearAll);
});
