// docs.js — listet Aufgaben-Dokumente auf der GitHub-Pages-Seite und zeigt sie an.
// Markdown-Dateien werden live aus dem Repo (raw) geladen und mit marked.js gerendert.
// Eine Quelle: die .md-Dateien im Repo — keine Doppelpflege im HTML.
(function () {
  "use strict";

  var RAW = "https://raw.githubusercontent.com/RDBHT/SWT/main/";
  var BLOB = "https://github.com/RDBHT/SWT/blob/main/";

  function loadMarked() {
    return new Promise(function (resolve, reject) {
      if (window.marked) return resolve();
      var s = document.createElement("script");
      s.src = "https://cdn.jsdelivr.net/npm/marked@12/marked.min.js";
      s.onload = resolve;
      s.onerror = reject;
      document.head.appendChild(s);
    });
  }

  function sourceLink(path) {
    return (
      '<p class="doc-source"><a href="' +
      BLOB +
      path +
      '" target="_blank" rel="noopener noreferrer">Datei auf GitHub ansehen ↗</a></p>'
    );
  }

  function renderDoc(el) {
    var path = el.getAttribute("data-doc");
    var body = el.querySelector(".doc-body");
    return fetch(RAW + path, { cache: "no-cache" })
      .then(function (res) {
        if (!res.ok) throw new Error(String(res.status));
        return res.text();
      })
      .then(function (md) {
        body.innerHTML = sourceLink(path) + window.marked.parse(md);
        body.querySelectorAll('a[href^="http"]').forEach(function (a) {
          a.target = "_blank";
          a.rel = "noopener noreferrer";
        });
      })
      .catch(function () {
        body.innerHTML =
          "<p><em>Inhalt konnte nicht geladen werden — die Datei ist " +
          "evtl. noch nicht nach GitHub gepusht.</em></p>" + sourceLink(path);
      });
  }

  function init() {
    var docs = document.querySelectorAll("details.doc[data-doc]");
    if (!docs.length) return;
    loadMarked()
      .then(function () {
        docs.forEach(function (el) {
          var loaded = false;
          el.addEventListener("toggle", function () {
            if (el.open && !loaded) {
              loaded = true;
              renderDoc(el);
            }
          });
        });
      })
      .catch(function () {
        docs.forEach(function (el) {
          el.querySelector(".doc-body").innerHTML = sourceLink(
            el.getAttribute("data-doc")
          );
        });
      });
  }

  if (document.readyState === "loading") {
    document.addEventListener("DOMContentLoaded", init);
  } else {
    init();
  }
})();
