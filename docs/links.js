// Externe Links (Repo, .md-Dateien etc.) in neuem Tab öffnen.
// Interne Reiter-Navigation (relative .html-Links) bleibt im selben Tab.
document.querySelectorAll('a[href^="http"]').forEach(function (a) {
  a.target = "_blank";
  a.rel = "noopener noreferrer";
});
