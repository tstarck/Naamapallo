package fi.starck.naamapallo;

/**
 * <p>Threadsafe communication.</p>
 *
 * Provide safe communication between Bluetooth and driving threads.
 *
 * @fixme Actually this is not completely threadsafe (@see #kulma()).
 *
 * @author Tuomas Starck
 */
class Tiedote {
    private Toimi toiminto;
    private int asteluku;

    /**
     * Initialize defaults.
     */
    Tiedote() {
        toiminto = Toimi.STOP;
        asteluku = 0;
    }

    /**
     * @param i The integer from Bluetooth communiqué.
     */
    synchronized void aseta(int i) {
        if (i == 0) {
            toiminto = Toimi.STOP;
        }
        else if (i == 1) {
            toiminto = Toimi.GO;
        }
        else {
            toiminto = Toimi.TURN;
            asteluku = -1 * (i >>> 8) + 44;
        }
    }

    /**
     * @return The action to be performed.
     */
    synchronized Toimi nouda() {
        return toiminto;
    }

    /**
     * @return The angle to be turned.
     *
     * @fixme Threadsafeness breaks apart here, because reading
     *        the angle of turn requires separate method call.
     *        No time to fix it now.
     */
    synchronized double kulma() {
        return asteluku;
    }
}
