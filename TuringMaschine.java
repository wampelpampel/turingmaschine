import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class TuringMaschine {
    File datei;
    Scanner scan;
    int states;
    char[] eingabeAlphabet;
    char[] bandAlphabet;
    int anfangsZustand;
    int endzustand;
    ArrayList<Transition> transitionsList;
    char blank;
    char[] eingabeWort;
    TreeMap<Integer, Character> band;
    int aktuellerZustand;
    int positionKopf;


    public TuringMaschine(String tmDatei, String eingabeWort) {
        this.eingabeWort = eingabeWort.toCharArray();
        this.datei = new File(tmDatei);
        try {
            this.scan = new Scanner(datei);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        this.states = Integer.parseInt(scan.nextLine());
        this.eingabeAlphabet = scan.nextLine().toCharArray();
        this.bandAlphabet = scan.nextLine().toCharArray();
        this.anfangsZustand = Integer.parseInt(scan.nextLine());
        this.endzustand = Integer.parseInt(scan.nextLine());
        this.blank = bandAlphabet[bandAlphabet.length - 1];
        // ArrayList die alle Transition speichert
        this.transitionsList = new ArrayList<>();
        while (scan.hasNextLine()) {
            Transition currTrans = new Transition(
                    scan.nextInt(),//Aktueller Zustand
                    scan.next().charAt(0),//Gelesenes Symbol
                    scan.nextInt(),//Neuer Zustand
                    scan.next().charAt(0),//Neues Symbol
                    Direction.valueOf(scan.next())); //Kopfbewegung
            this.transitionsList.add(currTrans);
            if(scan.hasNextLine()) {
                scan.nextLine();
            }
        }

        // Band initieren
        band = new TreeMap<>();
        band.put(-1, blank);
        int i = 0;
        while (i < this.eingabeWort.length) {
            band.put(i, this.eingabeWort[i]);
            i++;
        }
        band.put(i, blank);
        // --------------

        // TM in ursprügnliche Konfiguration machen
        this.aktuellerZustand = this.anfangsZustand;
        this.positionKopf = 0;
    }

    public boolean schritt() {
        Transition currTrans = findTransition(aktuellerZustand, band.get(this.positionKopf));
        if (currTrans == null) {
            System.out.println("Die Turingmaschine findet keine passende Transition machen. \n Die Berechnung wurde gestoppt!");
            return false;
        }
        band.put(this.positionKopf, currTrans.b);
        this.aktuellerZustand = currTrans.qStrich;
        if (currTrans.d == Direction.L) {
            this.positionKopf--;
        }
        if (currTrans.d == Direction.R) {
            this.positionKopf++;
        }

        //Erstelle neues Blank symbol falls man auf dem Band
        // zu weit nach links oder rechts geht
        if (band.get(this.positionKopf) == null) {
            band.put(positionKopf, 'B');
        }
        if (endzustand == aktuellerZustand) {
            System.out.println("Die Turingmaschine hat einen Endzustand erreicht. \n Die Berechnung wurde gestoppt!");
            return false;
        }
        return true;
    }

    private Transition findTransition(int aktZustand, char aktBandzeichen) {
        for (Transition transition : this.transitionsList) {
            if (transition.q == aktZustand && transition.a == aktBandzeichen) {
                return transition;
            }
        }
        System.out.println(aktZustand+" " +aktBandzeichen);
        return null;
    }

    public int getStates() {
        return states;
    }

    public char[] getEingabeAlphabet() {
        return eingabeAlphabet;
    }

    public char[] getBandAlphabet() {
        return bandAlphabet;
    }

    public int getAnfangsZustand() {
        return anfangsZustand;
    }

    public int getEndzustand() {
        return endzustand;
    }

    public ArrayList<Transition> getTransitionsList() {
        return transitionsList;
    }

    public char getBlank() {
        return blank;
    }

    public char[] getEingabeWort() {
        return eingabeWort;
    }

    public TreeMap<Integer, Character> getBand() {
        return band;
    }

    public int getAktuellerZustand() {
        return aktuellerZustand;
    }

    public int getPositionKopf() {
        return positionKopf;
    }
}