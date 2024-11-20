# EditableBufferedReader

Model (Line): l'estat de l'aplicació, que canvia
View (Console): presentació de l'aplicació. Un model pot tenir-ne més d'una
Controller (EditableBufferedReader): gestor d'esdeveniments d'Input. Invoca canvis d'estat en el model

El model notifica la vista dels seus canvis, actualitzant la seva presentació. El model no ha de conèixer els detalls de la vista/vistes

Patró Observer/Observable (JAVA)
Els observers (vistes) es registren amb el observable (model)
El model informa les vistes amb notifyObservers quan el seu estat canvia. Internament manté una llista d'observers registrats. notifyObservers invoca el mètode update de cada vista per actualitzar-la

# ClientServerChat

Programació d’una aplicació Xat textual amb servidor centralitzat.
MySocket i MyServerSocket encapsulen les excepcions i streams de text BufferedReader i PrintWriter.
https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

# Client gràfic amb Swing

https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html

# Projecte: ChessGame

Escacs multijugador