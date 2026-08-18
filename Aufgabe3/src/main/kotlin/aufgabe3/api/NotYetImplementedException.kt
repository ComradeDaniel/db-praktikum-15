package aufgabe3.api

class NotYetImplementedException(method: String) :
    RuntimeException("Methode '$method' ist noch nicht in HQL implementiert")
