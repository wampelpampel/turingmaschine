public class Transition {
    int q; //Aktueller Zustand
    char a; //Das Symbol welches die Maschine auf dem Band liest

    int qStrich; //Zustand in den die Maschine wecheselt
    char b; //Das Symbol das an der aktuellen Stelle auf dem Band geschrieben wird
    Direction d; //Richtung in die der Lesekopf bewegt wird

    public Transition(int q, char a, int qStrich, char b, Direction d) {
        this.q = q;
        this.a = a;
        
        this.qStrich = qStrich;
        this.b = b;
        this.d = d;
    }

    public int getQ() {
        return q;
    }

    public char getA() {
        return a;
    }

    public int getqStrich() {
        return qStrich;
    }

    public char getB() {
        return b;
    }

    public Direction getD() {
        return d;
    }
}
