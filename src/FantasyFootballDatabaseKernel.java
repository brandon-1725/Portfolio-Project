import components.map.Map;
import components.sequence.Sequence;
import components.standard.Standard;

/**
 * Fanstasy Football Database kernel component with primary methods.
 */
public interface FantasyFootballDatabaseKernel
        extends Standard<FantasyFootballDatabaseKernel> {
    /**
     * Enter player into the database.
     *
     * @param name
     *            name of player entered into database.
     * @updates this
     * @ensures the player is entered into the database
     */
    void enterPlayer(String name);

    /**
     * Enters stat for the player into the database.
     *
     * @param stat
     *            the name of the stat
     * @param value
     *            the numerical of the stat
     * @updates this
     * @requires Name in database
     * @ensures the stat is added for the player
     */
    void enterStat(String stat, double value);

    /**
     * Returns a map pair of a stat and the values.
     *
     * @param stat
     *            the name of the stat to get
     * @return map pair of stats and values for the stats
     * @ensures Map.Pair<Name of stat, <stat values>>
     */
    Map.Pair<String, Sequence<Double>> getStatAndValues(String stat);

    /**
     * Removes the stat and returns the stat and values in an map pair.
     *
     * @param stat
     *            the name of the stat to get
     * @replaces this
     * @return map pair of stats and values for the stats
     * @ensures Map.Pair<Name of stat, <stat values>>
     */
    Map.Pair<String, Sequence<Double>> removeStatAndValues(String stat);
}
