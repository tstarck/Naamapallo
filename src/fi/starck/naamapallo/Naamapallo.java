package fi.starck.naamapallo;

import lejos.nxt.Button;
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
        int data = 0;

        while (!Button.ESCAPE.isDown()) {
            data = tiedote.getIt();

            if (data != 0) {
                System.out.print(tiedote.getIt() + " ");
            }

            Delay.msDelay(500);
        }
    }

    /**
     * @param argh
     */
    public static void main(String[] argh) {
        new Naamapallo().ajele();
        System.exit(0);
    }
}
