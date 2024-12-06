# Software per a Aplicacions Distribuïdes

## EditableBufferedReader

Aplicació de consola per editar text. S'han desenvolupat dues versions:

### Versió sense MVC

Primera versió del programa. Programació de la classe EditableBufferedReader que extén BufferedReader i afegeix capacitats d'edició.

### Versió amb MVC

- Model (Line): l'estat de l'aplicació, que canvia
- View (Console): presentació de l'aplicació. Un model pot tenir-ne més d'una
- Controller (EditableBufferedReader): gestor d'esdeveniments d'Input. Invoca canvis d'estat en el model

El model notifica la vista dels seus canvis, actualitzant la seva presentació. El model no ha de conèixer els detalls de la vista/vistes.

**Patró Observer/Observable**

Els observers (vistes) es registren amb el observable (model)

El model informa les vistes amb notifyObservers quan el seu estat canvia. Internament manté una llista d'observers registrats. notifyObservers invoca el mètode update de cada vista per actualitzar-la.

## ClientServerChat

Programació d’una aplicació Xat textual amb servidor centralitzat.
MySocket i MyServerSocket encapsulen les excepcions i streams de text BufferedReader i PrintWriter.
https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

## Client gràfic amb Swing

Implementació del client de xat amb la biblioteca gràfica Swing.
https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html

## Projecte: Escacs

Escacs multijugador desenvolupat en java. Implementació del taulell i moviment de les peces.
