import components.sequence.Sequence;

/**
 * FantasyFootballDatabaseKernel enhanced with secondary methods.
 */

public interface FantasyFootballDatabase extends FantasyFootballDatabaseKernel {

    /**
     * Averages out the stat.
     *
     * @param stat
     *            the name of the stat
     * @return the average of the stat
     * @ensures the average stat in the form of a double #.##
     */
    double averageStatValue(String stat);

    /**
     * Least stat value.
     *
     * @param stat
     *            the name of the stat
     * @return the smallest value of the stat
     * @ensures the smallest value of the stat in the form of a double #.##
     */
    double leastStatValue(String stat);

    /**
     * Greatest stat value.
     *
     * @param stat
     *            the name of the stat
     * @return the greatest value of the stat
     * @ensures the greatest value of the stat in the form of a double #.##
     */
    double greatestStatValue(String stat);

    /**
     * Returns a sequence with the amount of times the player has scored more
     * points or the exact points than a value.
     *
     * @param stat
     *            the name of the stat
     * @param value
     *            the value to compare to
     * @return a sequence times >= value
     * @ensures <numbers >= value>
     */
    Sequence<Double> valuesGreaterOrEqual(String stat, double value);
}
