package fi.starck.naamapallo;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.Delay;

/**
 * @author Tuomas Starck
 */
class Sinihammas implements Runnable {
    private final Tiedote tiedote;

    public Sinihammas(Tiedote t) {
        tiedote = t;
        new Thread(this).start();
    }

    @Override
    public void run() {
        int data = 0;

        System.out.print("Blutuut.");
        NXTConnection yhteys = Bluetooth.waitForConnection(0, NXTConnection.RAW);
        DataInputStream virta = yhteys.openDataInputStream();
        System.out.println(" We haz!");

        while (true) {
            try {
                data = virta.readInt();
                System.out.println(".");
            }
            catch (IOException ioe) {
                System.out.print("\nIO");
                data = 0;
            }
            catch (Exception e) {
                System.out.print("\nE");
                data = 0;
            }

            tiedote.setIt(data);

            Delay.msDelay(100);
        }
    }
}
