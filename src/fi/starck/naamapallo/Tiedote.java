package fi.starck.naamapallo;

/**
 * @author Tuomas Starck
 */
class Tiedote {
    private int tieto;

    Tiedote() {
        tieto = 0;
    }

    synchronized void setIt(int t) {
        tieto = t;
    }

    synchronized int getIt() {
        return tieto;
    }
}
