# Eigenes Refactoring-Beispiel

**Refactoring:** Replace Conditional with Polymorphism (Fowler-Katalog).
**Sprache:** JavaScript. **Werkzeug:** VS Code (Extract Function, Rename Symbol,
Move to new file).

## Ausgangslage

Im IT-Service-Monitor entscheidet eine Funktion per `switch` über den Check-Typ.
Jeder neue Typ vergrößert den `switch` und zwingt dazu, eine erprobte Funktion
erneut anzufassen — ein klassischer Fall für Polymorphie.

### Vorher

```js
function runCheck(check) {
  switch (check.type) {
    case 'http': {
      const res = httpGet(check.url, check.timeout);
      return { status: res.code === check.expected ? 'OK' : 'DOWN', latency: res.ms };
    }
    case 'tcp': {
      const probe = tcpProbe(check.host, check.port, check.timeout);
      return { status: probe.open ? 'OK' : 'DOWN', latency: probe.ms };
    }
    case 'dns': {
      const rec = dnsResolve(check.host, check.recordType);
      return { status: rec.found ? 'OK' : 'DOWN', latency: rec.ms };
    }
    default:
      throw new Error('Unbekannter Check-Typ: ' + check.type);
  }
}
```

### Nachher

```js
class Check {
  constructor(config) { this.config = config; }
  execute() { throw new Error('execute() muss überschrieben werden'); }
}

class HttpCheck extends Check {
  execute() {
    const res = httpGet(this.config.url, this.config.timeout);
    return { status: res.code === this.config.expected ? 'OK' : 'DOWN', latency: res.ms };
  }
}

class TcpCheck extends Check {
  execute() {
    const probe = tcpProbe(this.config.host, this.config.port, this.config.timeout);
    return { status: probe.open ? 'OK' : 'DOWN', latency: probe.ms };
  }
}

class DnsCheck extends Check {
  execute() {
    const rec = dnsResolve(this.config.host, this.config.recordType);
    return { status: rec.found ? 'OK' : 'DOWN', latency: rec.ms };
  }
}
```

Die Typunterscheidung verschwindet nicht ganz — sie wandert an genau *eine*
Stelle, eine kleine Factory:

```js
const CHECK_TYPES = { http: HttpCheck, tcp: TcpCheck, dns: DnsCheck };

function createCheck(config) {
  const Cls = CHECK_TYPES[config.type];
  if (!Cls) throw new Error('Unbekannter Check-Typ: ' + config.type);
  return new Cls(config);
}
```

## Schritte mit VS Code

1. Jeden `case`-Block per *Extract to function* in eine eigene Funktion gezogen.
2. Die Funktionen in die Klassen `HttpCheck` / `TcpCheck` / `DnsCheck` überführt
   (Handarbeit — diesen Schritt automatisiert VS Code nicht).
3. *Move to new file* je Klasse, *Rename Symbol* für konsistente Namen.

## Gewinn

Ein neuer Check-Typ ist jetzt eine neue Klasse plus ein Eintrag in `CHECK_TYPES` —
der bestehende Code bleibt unangetastet. Der aufrufende Code ruft nur noch
`check.execute()` auf, ohne den Typ zu kennen.
