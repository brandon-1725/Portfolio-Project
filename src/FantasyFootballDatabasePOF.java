import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * FantasyFootballDatabasePOF Database for Portfolio Project.
 */
public class FantasyFootballDatabasePOF {
    /*
     * Private members ---------------------------------------------------------
     */

    private String playerName;

    /**
     * Map of stat to values.
     */
    private Map<String, Sequence<Double>> mapOfStatNameToValues;

    /**
     * Database.
     */
    private Map<String, Map<String, Sequence<Double>>> database;

    /**
     * Creator of inital representation.
     */
    private void createNewRep() {
        this.mapOfStatNameToValues = new Map1L<String, Sequence<Double>>();
        this.database = new Map1L<String, Map<String, Sequence<Double>>>();
    }

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * No arguement constructor.
     */
    public FantasyFootballDatabasePOF() {
        this.createNewRep();
    }

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
        this.playerName = name;
        this.database.add(name, this.mapOfStatNameToValues);
    }

    /**
     * Enters stat for the player into the database.
     *
     * @param stat
     * @param value
     * @requires Name in database
     */
    public final void enterStat(String stat, double value) {
        Sequence<Double> statEntries = new Sequence1L<Double>();
        statEntries.add(statEntries.length(), value);

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
    public final Map.Pair<String, Sequence<Double>> getStatAndValues(
            String stat) {

        Map.Pair<String, Sequence<Double>> statAndValues = this.database
                .value(stat).remove(stat);
        this.database.add(this.playerName, this.mapOfStatNameToValues);
        return statAndValues;
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
    public final double averageStatValue(String stat) {
        double valuesOfStat = 0;

        Pair<String, Map<String, Sequence<Double>>> statsAndValues = this.database
                .remove(this.playerName);
        Sequence<Double> statValues = statsAndValues.value().value(stat);

        for (double statValue : statValues) {
            valuesOfStat += statValue;
        }

        this.database.add(this.playerName, this.mapOfStatNameToValues);

        return valuesOfStat / statValues.length();
    }

    /**
     * Least stat value.
     *
     * @param stat
     * @return the smallest value of the stat
     */
    public final double leastStatValue(String stat) {
        Pair<String, Map<String, Sequence<Double>>> statsAndValues = this.database
                .remove(this.playerName);
        Sequence<Double> statValues = statsAndValues.value().value(stat);

        double leastStatValue = statValues.entry(0);
        for (int i = 0; i < statValues.length(); i++) {
            if (statValues.entry(i) < leastStatValue) {
                leastStatValue = statValues.entry(i);
            }
        }
        this.database.add(this.playerName, this.mapOfStatNameToValues);

        return leastStatValue;
    }

    /**
     * Greatest stat value.
     *
     * @param stat
     * @return the greatest value of the stat
     */
    public final double greatestStatValue(String stat) {
        Pair<String, Map<String, Sequence<Double>>> statsAndValues = this.database
                .remove(this.playerName);
        Sequence<Double> statValues = statsAndValues.value().value(stat);

        double greatestStatValue = statValues.entry(0);
        for (int i = 0; i < statValues.length(); i++) {
            if (statValues.entry(i) > greatestStatValue) {
                greatestStatValue = statValues.entry(i);
            }
        }
        this.database.add(this.playerName, this.mapOfStatNameToValues);

        return greatestStatValue;
    }

}

/**
 * Main method.
 *
 * @param args
 */
public void main(String[] args) {

    // Did research and choose this
    // #.0 means that the result of the format is all the digits before the
    // decimal point, then at max one digit with a 0 showing if there is no
    // digits after decimal point.
    // 14.0 is formatted as 14.0 while 14.2576 is formatted as 14.3
    // Choose just one number after the decimal point because ESPN NFL stats
    // only show one number after decimal point for averages
    DecimalFormat df = new DecimalFormat("#.0");

    SimpleWriter out = new SimpleWriter1L();
    FantasyFootballDatabasePOF quarterback = new FantasyFootballDatabasePOF();
    quarterback.enterPlayer("Player1");
    quarterback.enterStat("yards", 100);
    quarterback.enterStat("yards", 106);
    quarterback.enterStat("yards", 300);

    out.println(quarterback.playerName + " passed for "
            + df.format(quarterback.averageStatValue("yards"))
            + " yards per game on average in this season.");
    out.println("The least amount of yards the quaterback passed for was "
            + df.format(quarterback.leastStatValue("yards"))
            + " yards in a game this season.");

    quarterback.enterStat("TD", 0);
    quarterback.enterStat("TD", 4);
    quarterback.enterStat("TD", 0);

    out.println(quarterback.playerName + " passed for "
            + df.format(quarterback.averageStatValue("TD"))
            + " TDs per game on average in this season.");
    out.println("The most touchdowns the quaterback passed for was "
            + df.format(quarterback.greatestStatValue("TD"))
            + " TDs in a game this season.");

    out.close();
}
