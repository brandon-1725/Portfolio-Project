import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Fanstasy Football Database for Portfolio Project.
 */
public class FantasyFootballDatabase {
    /*
     * Private members ---------------------------------------------------------
     */

    /**
     * Map of stat to values.
     */
    private Map<String, Sequence<Integer>> mapOfStatNameToValues;

    /**
     * Database.
     */
    private Map<String, Map<String, Sequence<Integer>>> database;

    /**
     * Creator of inital representation.
     */
    private void createNewRep() {
        this.mapOfStatNameToValues = new Map1L<String, Sequence<Integer>>();
        this.database = new Map1L<String, Map<String, Sequence<Integer>>>();
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No arguement constructor.
     */
    public FantasyFootballDatabase() {
        this.createNewRep();
    }

    /*
     * Standard methods --------------------------------------------------------
     */

    // not important for proof of concept
    // TODO write newInstance, clear, transferFrom

    /*
     * Kernel methods ----------------------------------------------------------
     */

    /**
     * Enter player into the database.
     *
     * @param name
     *            name of player entered into database.
     */
    public final void enterPlayer(String name) {
        this.database.add(name, this.mapOfStatNameToValues);
    }

    /**
     * Enters stat for the player into the database.
     *
     * @param stat
     * @param value
     * @requires Name in database
     */
    public final void enterStat(String stat, int value) {
        Sequence<Integer> statEntries = new Sequence1L<Integer>();
        statEntries.add(statEntries.length() - 1, value);

        if (this.mapOfStatNameToValues.hasKey(stat)) {
            this.mapOfStatNameToValues.value(stat).add(statEntries.length() - 1,
                    value);
        } else {
            this.mapOfStatNameToValues.add(stat, statEntries);
        }
    }

    /**
     * Returns the map of stats and values for the stats.
     *
     * @param stat
     * @return map of stats and values for the stats
     */
    public final Map.Pair<String, Map<String, Sequence<Integer>>> getStatAndValue(
            String stat) {
        return this.database.remove(stat);
    }

    /*
     * Secondary methods -------------------------------------------------------
     */

    /**
     * Averages out the stat.
     * 
     * @param stat
     * @return the average of the stat
     */
    public final int averageStatValue(String stat) {
        int valuesOfStat = 0;

        Map.Pair<String, Map<String, Sequence<Integer>>> statsAndValues = this
                .getStatAndValue(stat);
        Sequence<Integer> statValues = statsAndValues.value().value(stat);

        for (int statValue : statValues) {
            valuesOfStat += statValue;
        }

        return valuesOfStat / statValues.length();
    }

    /**
     * Least stat value.
     * 
     * @param stat
     * @return the smallest value of the stat
     */
    public final int leastStatValue(String stat) {
        Map.Pair<String, Map<String, Sequence<Integer>>> statsAndValues = this
                .getStatAndValue(stat);
        Sequence<Integer> statValues = statsAndValues.value().value(stat);

        int leastStatValue = statValues.entry(0);
        for (int i = 0; i < statValues.length(); i++) {
            if (statValues.entry(i) < leastStatValue) {
                leastStatValue = statValues.entry(i);
            }
        }

        return leastStatValue;
    }

    /**
     * Greatest stat value.
     * 
     * @param stat
     * @return the greatest value of the stat
     */
    public final int greatestStatValue(String stat) {
        Map.Pair<String, Map<String, Sequence<Integer>>> statsAndValues = this
                .getStatAndValue(stat);
        Sequence<Integer> statValues = statsAndValues.value().value(stat);

        int greatestStatValue = statValues.entry(0);
        for (int i = 0; i < statValues.length(); i++) {
            if (statValues.entry(i) > greatestStatValue) {
                greatestStatValue = statValues.entry(i);
            }
        }

        return greatestStatValue;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public void main(String[] args) {

    }
}
