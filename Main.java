public class Main {
    public static void main(String[] args) {
        String dateiName = args[0];
        String eingabeWort = args[1];
        TuringMaschine tm = new TuringMaschine("./" + dateiName, eingabeWort);
        printTM(tm);
        printKonfiguration(tm, 0);
        int schritt = 1;
        while(tm.schritt()) {
            printKonfiguration(tm, schritt);
            schritt++;
        }
        System.out.println("Letzte Konfiguration");
        printKonfiguration(tm, schritt);
    }

    static void printTM(TuringMaschine tm) {
        System.out.println("Turing Maschinen Konfiguration:");
        System.out.println("-------------------------------");
        System.out.println("Anzahl der Zustäde: " + tm.getStates());
        
        System.out.print("Eingabealphabet: ");
        for (char c : tm.getEingabeAlphabet()) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        System.out.print("Bandalphabet: ");
        for (char c : tm.getBandAlphabet()) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        System.out.println("Anfangszustand: " + tm.getAnfangsZustand());
        
        System.out.print("Endzustände: "+ tm.getEndzustand());
        System.out.println();
        
        System.out.println("Leersymbol: " + tm.getBlank());
        
        System.out.print("Eingabewort: ");
        for (char c : tm.getEingabeWort()) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        System.out.println("Transitionen:");
        for (Transition transition : tm.getTransitionsList()) {
            System.out.printf("Zustand %d Bandsymbol '%c' -> Schreibe '%c', Bewege %s, Nächster Zustand %d%n", 
                transition.getQ(), transition.getA(), transition.getB(), transition.getD(), transition.getqStrich());
        }
    }
    static void printKonfiguration(TuringMaschine tm, int schritt) {
        System.out.println("-------------------------------");
        System.out.println("Aktueller Berechnungsschritt: " + schritt);
        System.out.println("Aktueller Zustand: " + tm.getAktuellerZustand());
        System.out.println("Position des Kopfes: " + tm.getPositionKopf());
        System.out.print("Band: ");
        for (int i = tm.getBand().firstKey(); i <= tm.getBand().lastKey(); i++) {
            if (tm.getBand().containsKey(i)) {
                if (i == tm.getPositionKopf()) {
                    System.out.print("[" + tm.getBand().get(i) + "] ");
                } else {
                    System.out.print(tm.getBand().get(i) + " ");
                }
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }    
}