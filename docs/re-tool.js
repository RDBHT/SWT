// SWT-Portfolio — RE-Tool
// Vanilla JS, persists in localStorage, JSON export/import.

const STORAGE_KEY = "swt-re-tool";

const DEMO_DATA = [
  {
    id: "REQ-001",
    datum: "2026-05-12",
    autor: "RD",
    kurz: "Nutzer kann zwischen Reitern navigieren",
    lang: "Die Top-Navigation der SWT-Portfolio-Site erlaubt das Wechseln zwischen Home, DVC-E1, RE-Tool und REQ-E1.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnahme: "Jeder Reiter ist von jedem anderen Reiter aus per Klick erreichbar.",
    abhaengig: "",
    jur: "nein",
    anmerkung: "Iter 1"
  },
  {
    id: "REQ-002",
    datum: "2026-05-12",
    autor: "RD",
    kurz: "Site ist über GitHub Pages erreichbar",
    lang: "Die Site wird aus dem main-Branch, Ordner /docs deployed.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnahme: "URL https://rdbht.github.io/SWT/ liefert HTTP 200 und rendert die Landing.",
    abhaengig: "",
    jur: "nein",
    anmerkung: "Iter 1"
  },
  {
    id: "REQ-003",
    datum: "2026-05-12",
    autor: "RD",
    kurz: "RE-Tool persistiert Eingaben im Browser",
    lang: "Nach einem Reload sind angelegte Requirements weiterhin sichtbar.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Funktional",
    abnahme: "Nach Reload zeigt die Tabelle dieselben Einträge wie vor dem Reload.",
    abhaengig: "REQ-002",
    jur: "nein",
    anmerkung: "Iter 2"
  },
  {
    id: "REQ-004",
    datum: "2026-05-12",
    autor: "RD",
    kurz: "JSON-Export und -Import",
    lang: "Alle Requirements können als JSON-Datei heruntergeladen und wieder importiert werden.",
    status: "Erledigt",
    prio: "B",
    kategorie: "Funktional",
    abnahme: "Export erzeugt eine Datei; deren Import stellt den gleichen Datenstand wieder her.",
    abhaengig: "REQ-003",
    jur: "nein",
    anmerkung: "Iter 2"
  },
  {
    id: "REQ-005",
    datum: "2026-05-12",
    autor: "RD",
    kurz: "Keine personenbezogenen Daten verarbeitet",
    lang: "Das Tool läuft rein im Browser, kein Backend, kein Tracking.",
    status: "Erledigt",
    prio: "A",
    kategorie: "Sicherheit",
    abnahme: "Keine Netz-Requests an Dritte beim Anlegen oder Speichern eines Requirements (außer Pico.css CDN beim ersten Seitenaufruf).",
    abhaengig: "",
    jur: "ja",
    anmerkung: "DSGVO-relevant"
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
      <td>${esc(r.kurz)}</td>
      <td>${esc(r.status)}</td>
      <td>${esc(r.prio)}</td>
      <td>${esc(r.kategorie)}</td>
      <td>${esc(r.abnahme)}</td>
      <td>${esc(r.abhaengig)}</td>
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
