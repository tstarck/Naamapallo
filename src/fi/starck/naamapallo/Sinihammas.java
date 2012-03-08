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
        NXTConnection yhteys = Bluetooth.waitForConnection(0, NXTConnection.RAW);
        DataInputStream virta = yhteys.openDataInputStream();

        while (true) {
            try {
                data = virta.readInt();
            }
            catch (IOException ioe) {
                System.out.println("IOE");
                break;
            }

            tiedote.setIt(data);

            Delay.msDelay(100);
        }
    }
}
