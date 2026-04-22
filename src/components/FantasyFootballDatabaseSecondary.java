package components;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Layered implementations of secondary methods for
 * {@code FantasyFootballDatabase}.
 */

public abstract class FantasyFootballDatabaseSecondary
        implements FantasyFootballDatabase {

    /*
     * Public members ---------------------------------------------------------
     */

    /*
     * Common methods (from Object) -------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        int hashValue = 0;
        String name = this.getName();
        for (int i = 0; i < name.length(); i++) {
            hashValue += 1;
        }
        return hashValue;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FantasyFootballDatabase)) {
            return false;
        }
        FantasyFootballDatabase db = (FantasyFootballDatabase) obj;
        if (!this.getName().equals(db.getName())) {
            return false;
        }
        Sequence<String> thisStats = this.getStats();
        Sequence<String> otherStats = db.getStats();
        if (thisStats.length() != otherStats.length()) {
            return false;
        }
        for (int i = 0; i < thisStats.length(); i++) {
            String stat = thisStats.entry(i);
            boolean found = false;
            for (int j = 0; j < otherStats.length(); j++) {
                if (otherStats.entry(j).equals(stat)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
            Sequence<Double> thisValues = this.getStatAndValues(stat).value();
            Sequence<Double> otherValues = db.getStatAndValues(stat).value();
            if (!thisValues.equals(otherValues)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {

        // Had to look this up and here is my attempt
        // returns the player name and stat and the respective values.
        // Will look like this:
        // Josh Allen, Passing Yards - 320, 173; Rushing Yards - 31, 59; etc
        // ends with .

        StringBuilder result = new StringBuilder();
        result.append(this.getName() + ",");
        Sequence<String> stats = this.getStats();
        for (String stat : stats) {
            result.append(stat + " - ");
            Sequence<Double> statValues = this.getStatAndValues(stat).value();
            for (Double value : statValues) {
                result.append(value);
                result.append(",");
            }
            result.append(";");
        }
        result.append(".");
        return result.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public double averageStatValue(String stat) {
        double averageStatValue = 0;
        Sequence<Double> statValues = this.getStatAndValues(stat).value();
        for (double value : statValues) {
            averageStatValue += value;
        }
        return averageStatValue / statValues.length();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public double leastStatValue(String stat) {
        Sequence<Double> statValues = this.getStatAndValues(stat).value();
        double leastStatValue = statValues.entry(0);
        for (double value : statValues) {
            if (value < leastStatValue) {
                leastStatValue = value;
            }
        }
        return leastStatValue;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public double greatestStatValue(String stat) {
        Sequence<Double> statValues = this.getStatAndValues(stat).value();
        double greatestStatValue = 0;
        for (double value : statValues) {
            if (value > greatestStatValue) {
                greatestStatValue = value;
            }
        }
        return greatestStatValue;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public Sequence<Double> valuesGreaterOrEqual(String stat,
            double valueToCompare) {
        Sequence<Double> valuesGreaterOrEqual = new Sequence1L<>();
        for (double value : valuesGreaterOrEqual) {
            if (value >= valueToCompare) {
                valuesGreaterOrEqual.add(valuesGreaterOrEqual.length(), value);
            }
        }
        return valuesGreaterOrEqual;
    }
}
