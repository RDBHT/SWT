# Refactoring-Katalog — meine Eindrücke

Ich habe den Refactoring-Katalog von Martin Fowler durchgesehen. Hier, was mir
aufgefallen ist.

## Beeindruckt haben mich

**Replace Conditional with Polymorphism.** Dieses Refactoring wandelt verzweigte
Typ-Fallunterscheidungen in eine Klassenhierarchie um. Es hat mich beeindruckt,
weil es kein kosmetischer Eingriff ist, sondern die Struktur verändert: Aus einem
ständig wachsenden `switch` wird ein offenes Set von Klassen — neue Fälle kommen
hinzu, ohne bestehenden Code anzufassen (Open-Closed-Prinzip).

**Split Phase.** Weniger bekannt, aber elegant: Es trennt einen Codeblock, der
zwei verschiedene Dinge tut, in zwei nacheinander laufende Phasen mit einer
Zwischendatenstruktur. Mich hat beeindruckt, dass es ein Problem *benennt*, das
ich oft gespürt, aber nie klar gefasst hatte — vermischte Verantwortlichkeiten
innerhalb einer Funktion.

## Überflüssig finde ich

**Slide Statements** (Anweisungen verschieben). Zusammengehörige Zeilen näher
zusammenrücken ist für mich gesunder Schreibstil, kein benennenswertes eigenes
Refactoring. Als Vorbereitungsschritt für „Extract Function" hat es Sinn — als
eigenständiger Katalogeintrag wirkt es auf mich überflüssig.

## Nicht sofort verstanden habe ich

**Replace Subclass with Delegate.** Das Ziel — Komposition statt Vererbung — war
mir klar. Wann sich der Tausch aber wirklich lohnt und wie die Schrittfolge
sauber bleibt, musste ich mehrfach lesen: Vererbung „kostet" nur eine Beziehung,
Delegation bringt zusätzliche Verdrahtung. Die Abwägung erschloss sich mir erst
am zweiten Beispiel.
