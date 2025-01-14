# Software per a Aplicacions Distribuïdes
Repositori per l'assignatura SAD. Tardor 2024
# Practiques
## P1: EditableBufferedReader

Aplicació de consola per editar text. S'han desenvolupat dues versions:

### Versió sense MVC

Primera versió del programa. Programació de la classe EditableBufferedReader que extén BufferedReader i afegeix capacitats d'edició.

- right, left: caràcter dreta, caràcter esquerra amb les fletxes.
- home, end: principi, final de línia.
- ins: commuta mode inserció/sobre-escriptura.
- del, bksp: esborra caràcter actual o caràcter a l’esquerra.

### Versió amb MVC

- Model (Line): l'estat de l'aplicació, que canvia
- View (Console): presentació de l'aplicació. Un model pot tenir-ne més d'una
- Controller (EditableBufferedReader): gestor d'esdeveniments d'Input. Invoca canvis d'estat en el model

El model notifica la vista dels seus canvis, actualitzant la seva presentació. El model no ha de conèixer els detalls de la vista/vistes.

**Patró Observer/Observable**

Els observers (vistes) es registren amb el observable (model)

El model informa les vistes amb notifyObservers quan el seu estat canvia. Internament manté una llista d'observers registrats. notifyObservers invoca el mètode update de cada vista per actualitzar-la.

## P2: ClientServerChat

Programació d’una aplicació Xat textual amb servidor centralitzat.
MySocket i MyServerSocket encapsulen les excepcions i streams de text BufferedReader i PrintWriter.
https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

## P3: Client gràfic amb Swing

Implementació del client de xat amb la biblioteca gràfica Swing.
https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html

# Projecte: Escacs

La carpeta Escacs conté el projecte. Per fer-ho servir s'ha d'executar el main de la carpeta main.

El main crea un View, que s'encarrega de mostrar el taulell. Aquest view té associat un Controller i un Ratolí, que també forma part del controller peró és una classe apart, per tal de poder controlar els estats del joc. El controller
utilitza els Models, que són les peces i el propi taulell (GameBoard), que tenen quin tipus de peça són i on de quin jugador són, i el taulell les mides i altres aspecte perqué les peces surtin centrades i les columnes i files detectables.

Finalment el Controller distingueix entre torns i jugadors, els peons es poden moure 2 caselles d'inici i si un dels dos reis es menjat el joc s'acaba.
