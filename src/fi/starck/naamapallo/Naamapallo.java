package fi.starck.naamapallo;

import lejos.util.Delay;

/**
 * @author Tuomas Starck
 */
public class Naamapallo {
    private final Tiedote tiedote;

    Naamapallo() {
        tiedote = new Tiedote();
        new Sinihammas(tiedote);
    }

    private void ajele() {
        System.out.println("goto : " + tiedote.getIt());
        Delay.msDelay(2000);
    }

    /**
     * @param argh
     */
    public static void main(String[] argh) {
        new Naamapallo().ajele();
    }
}
