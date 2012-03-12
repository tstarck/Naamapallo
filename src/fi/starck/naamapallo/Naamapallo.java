package fi.starck.naamapallo;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * @author Tuomas Starck
 */
public class Naamapallo {
    private final Tiedote tieto;
    private final DifferentialPilot wheels;

    Naamapallo() {
        // Säieturvallinen kommunikointi
        tieto = new Tiedote();

        // Blutuut-viestintä
        new Sinihammas(tieto);

        // This is how I roll
        wheels = new DifferentialPilot(5.66d, 16.66d, Motor.A, Motor.B);
        wheels.setRotateSpeed(42.0d);
    }

    private void ajele() {
        Toimi toiminto;

        while (true) {
            toiminto = tieto.nouda();

            if (toiminto == Toimi.STOP) {
                wheels.stop();
            }
            else if (toiminto == Toimi.GO) {
                wheels.forward();
            }
            else {
                wheels.rotate(tieto.kulma());
            }

            Delay.msDelay(333);
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
