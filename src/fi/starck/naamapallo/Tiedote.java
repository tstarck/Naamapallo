package fi.starck.naamapallo;

/**
 * @author Tuomas Starck
 */
class Tiedote {
    private Toimi toiminto;
    private int asteluku;

    Tiedote() {
        toiminto = Toimi.STOP;
        asteluku = 0;
    }

    synchronized void aseta(int t) {
        if (t == 0) {
            toiminto = Toimi.STOP;
        }
        else if (t == 1) {
            toiminto = Toimi.GO;
        }
        else {
            toiminto = Toimi.TURN;
            asteluku = -1 * (t >>> 8) + 44;
        }
    }

    synchronized Toimi nouda() {
        return toiminto;
    }

    /**
     * @fixme Huom! Ei oo säieturvallinen!
     */
    synchronized double kulma() {
        return asteluku;
    }
}
