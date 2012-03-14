package fi.starck.naamapallo;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * <p>Lejos NXJ code for Lejos / Android face homing robot.</p>
 *
 * <b>Naamapallo</b> is the main class and is responsible for
 * initiating everything and driving the robot.
 *
 * Handling the motors is done with DifferentialPilot.
 *
 * @see lejos.robotics.navigation.DifferentialPilot
 *
 * @author Tuomas Starck
 */
public class Naamapallo {
    private final Tiedote tieto;
    private final DifferentialPilot wheels;

    /**
     * Initialize everything.
     */
    Naamapallo() {
        // Communication between this and Bluetooth-code.
        tieto = new Tiedote();

        // Initiate Bluetooth server
        new Sinihammas(tieto);

        // This is how I roll
        wheels = new DifferentialPilot(5.66d, 16.66d, Motor.A, Motor.B);
        wheels.setRotateSpeed(42.0d);
    }

    /**
     * Drive the robot according to the instructions received by
     * Bluetooth communiqué.
     */
    private void ajele() {
        Toimi toiminto;

        while (true) {
            /* Ask what should be done.
             */
            toiminto = tieto.nouda();

            if (toiminto == Toimi.STOP) {
                wheels.stop();
            }
            else if (toiminto == Toimi.GO) {
                wheels.forward();
            }
            else {
                /* Threadsafeness breaks apart here, but I don't
                 * mind, because it works most of the time.
                 */
                wheels.rotate(tieto.kulma());
            }

            /* The length of this time delay is calculated using
             * Stetson-Harrison method and it seems to work.
             */
            Delay.msDelay(333);
        }
    }

    /**
     * Start everything.
     *
     * @param argh Not used.
     */
    public static void main(String[] argh) {
        new Naamapallo().ajele();
        System.exit(0);
    }
}
