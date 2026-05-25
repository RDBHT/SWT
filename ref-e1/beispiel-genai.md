# Refactoring mit generativer KI

**Refactoring:** Extract Function + Decompose Conditional (über „Extract Method"
hinaus). **KI:** Claude (Anthropic). **Sprache:** JavaScript.

## Ausgangscode

Eine Funktion des IT-Service-Monitors, die zu viel auf einmal tut: zählen,
mitteln, Status ableiten, Alarm entscheiden — mit verschachtelten Bedingungen
und Zwischenvariablen.

```js
function evaluateService(service) {
  let downCount = 0;
  let totalLatency = 0;
  for (let i = 0; i < service.results.length; i++) {
    const r = service.results[i];
    if (r.status === 'DOWN') {
      downCount++;
    }
    totalLatency += r.latency;
  }
  const avgLatency = totalLatency / service.results.length;
  let status;
  if (downCount === service.results.length) {
    status = 'DOWN';
  } else if (downCount > 0 || avgLatency > service.latencyThreshold) {
    status = 'DEGRADED';
  } else {
    status = 'OK';
  }
  let alert = false;
  if (status === 'DOWN' && service.alertOnDown) {
    alert = true;
  } else if (status === 'DEGRADED' && service.alertOnDegraded) {
    alert = true;
  }
  return { status, avgLatency, alert };
}
```

## Prompt

> „Refactore die folgende Funktion: extrahiere sinnvolle Teilfunktionen und
> zerlege die verschachtelten Bedingungen. Das Verhalten muss exakt gleich
> bleiben — keine funktionalen Änderungen."

## Was passiert ist

**Hat es sofort geklappt? Nein.** Der erste Versuch der KI extrahierte zwar
sinnvoll drei Teilfunktionen — aber sie modernisierte ungefragt mit: Die
`for`-Schleifen wurden zu `reduce`/`filter`, eine Variable wurde umbenannt. Das
Ergebnis war lesbarer, aber eben *nicht mehr nur extrahiert*. Bei der Umstellung
`for` → `reduce` war ein subtiler Verhaltensunterschied (leeres Array) nicht
ausgeschlossen.

**Nachfass-Prompt:** „Nur extrahieren und Bedingungen zerlegen — Schleifen,
Variablennamen und Logik unverändert lassen." Daraufhin lieferte die KI eine
konservative, nachweislich verhaltensgleiche Fassung.

**Erkenntnis:** Die KI ist stark darin, sinnvolle Extraktionsgrenzen zu erkennen,
neigt aber dazu, mehr zu „verbessern" als verlangt. Refactoring heißt
definitionsgemäß: Struktur ändern, Verhalten *nicht*. Diese Disziplin muss man
der KI ausdrücklich auferlegen — sonst wird aus dem Refactoring eine
Umschreibung.

## Ergebnis (nach dem Nachfassen)

```js
function evaluateService(service) {
  const avgLatency = averageLatency(service.results);
  const status = deriveStatus(service, avgLatency);
  const alert = shouldAlert(service, status);
  return { status, avgLatency, alert };
}

function countDown(results) {
  let n = 0;
  for (let i = 0; i < results.length; i++) {
    if (results[i].status === 'DOWN') n++;
  }
  return n;
}

function averageLatency(results) {
  let total = 0;
  for (let i = 0; i < results.length; i++) {
    total += results[i].latency;
  }
  return total / results.length;
}

function deriveStatus(service, avgLatency) {
  const downCount = countDown(service.results);
  if (downCount === service.results.length) return 'DOWN';
  if (downCount > 0 || avgLatency > service.latencyThreshold) return 'DEGRADED';
  return 'OK';
}

function shouldAlert(service, status) {
  if (status === 'DOWN') return service.alertOnDown;
  if (status === 'DEGRADED') return service.alertOnDegraded;
  return false;
}
```

Die ursprüngliche `for`-Mechanik bleibt erhalten; verändert ist nur die Struktur:
vier benannte Funktionen statt eines Blocks, die Bedingungen über Guard Clauses
(früher `return`) entschachtelt.
