import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;

/**
 * {@code FantasyFootballDatabase} represented as {?} with implementations of
 * primary methods.
 *
 * @convention
 * @correspondence
 */
public class FantasyFootballDatabase1L
        extends FantasyFootballDatabaseSecondary {

    /*
     * Private members ---------------------------------------------------------
     */

    /**
     * Representation of{@code this}.
     */
    private Map<String, Sequence<Double>> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Map1L<String, Sequence<Double>>();
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public FantasyFootballDatabase1L() {
        this.createNewRep();
    }

    /*
     * Standard methods --------------------------------------------------------
     */

    @Override
    public final FantasyFootballDatabase1L newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(FantasyFootballDatabase1L source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof FantasyFootballDatabase1L : "Violation of: source is of dynamic type FantasyFootballDatabase1L";

        FantasyFootballDatabase1L localSource = (FantasyFootballDatabase1L) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ----------------------------------------------------------
     */

    @Override
    public final void enterPlayer(String name) {

    }

    @Override
    void enterStat(String stat, double value) {

    }

    @Override
    Map.Pair<String, Sequence<Double>> getStatAndValues(String stat) {

    }

    @Override
    Map.Pair<String, Sequence<Double>> removeStatAndValues(String stat) {

    }

    @Override
    String getName() {

    }

    @Override
    Sequence<String> getStats() {

    }
}
