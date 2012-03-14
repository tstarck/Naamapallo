package fi.starck.naamapallo;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.Delay;

/**
 * <p>Bluetooth server thread.</p>
 *
 * Create Bluetooth server and receive messages allowing <i>Naamakyyla</i>
 * (Android part of the robot) to connect to it.
 *
 * @see fi.starck.naamakyyla
 *
 * @author Tuomas Starck
 */
class Sinihammas implements Runnable {
    private final Tiedote tiedote;

    /**
     * Initialize inter-thread communication and start Bluetooth-server.
     *
     * @param t Tiedote object for inter-thread communication.
     */
    public Sinihammas(Tiedote t) {
        tiedote = t;
        new Thread(this).start();
    }

    /**
     * Bluetooth server initialization and listener.
     */
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
                System.out.print("."); // DEBUG
            }
            catch (IOException ioe) {
                System.out.println("\nIO");
                data = 0;
            }
            catch (Exception e) {
                System.out.println("\nE");
                data = 0;
            }
            finally {
                // Whatever happened, save the result.
                tiedote.aseta(data);
            }

            // Try not to consume all available clock cycles.
            Delay.msDelay(100);
        }
    }
}
