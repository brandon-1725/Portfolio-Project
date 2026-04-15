import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code FantasyFootballDatabase} represented as
 * {@code Map<String, Map<String, Sequence<Double>>>} with implementations of
 * primary methods.
 *
 * @convention |this.rep| = 1 this.rep.hasKey(this.playerName) for each key s is
 *             a non-empty String (stat name) AND
 *             this.rep.value(this.playerName).value(s).length() > 0
 * @correspondence this = {this.playerName, {stat1 -> {values for stat1}, stat2
 *                 -> {values for stat2}, etc. }
 */
public class FantasyFootballDatabase1L
        extends FantasyFootballDatabaseSecondary {

    /*
     * Private members ---------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Map<String, Map<String, Sequence<Double>>> rep;

    /**
     * Name of player.
     */
    private String playerName;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Map1L<String, Map<String, Sequence<Double>>>();
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No-arguement constructor.
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
        this.playerName = name;
    }

    @Override
    public final void enterStat(String stat, double value) {
        assert this.playerName != null : "Violation of playerName is not null";
        Map<String, Sequence<Double>> statsMap = this.rep
                .value(this.playerName);

        if (!statsMap.hasKey(stat)) {
            statsMap.add(stat, new Sequence1L<>());
        }

        Sequence<Double> statValues = statsMap.value(stat);
        statValues.add(statValues.length(), value);
    }

    @Override
    public final Map.Pair<String, Sequence<Double>> getStatAndValues(
            String stat) {
        assert this.playerName != null : "Violation of playerName is not null";

        Map<String, Sequence<Double>> statsMap = this.rep
                .value(this.playerName);

        Map.Pair<String, Sequence<Double>> statsAndValues = statsMap
                .remove(stat);
        statsMap.add(statsAndValues.key(), statsAndValues.value());

        return statsAndValues;
    }

    @Override
    public final Map.Pair<String, Sequence<Double>> removeStatAndValues(
            String stat) {
        assert this.playerName != null : "Violation of playerName is not null";

        Map<String, Sequence<Double>> statsMap = this.rep
                .value(this.playerName);

        return statsMap.remove(stat);
    }

    @Override
    public final String getName() {
        assert this.playerName != null : "Violation of playerName is not null";
        return this.playerName;
    }

    @Override
    public final Sequence<String> getStats() {
        assert this.playerName != null : "Violation of playerName is not null";
        Sequence<String> stats = new Sequence1L<>();
        int i = 0;
        while (this.rep.size() != 0) {
            Map.Pair<String, Map<String, Sequence<Double>>> nameToStatsAndValues = this.rep
                    .removeAny();
            Map.Pair<String, Sequence<Double>> statsAndValues = nameToStatsAndValues
                    .value().removeAny();

            String stat = statsAndValues.key();
            stats.add(i, stat);

            nameToStatsAndValues.value().add(statsAndValues.key(),
                    statsAndValues.value());
            this.rep.add(nameToStatsAndValues.key(),
                    nameToStatsAndValues.value());
            i++;
        }
        return stats;
    }
}
