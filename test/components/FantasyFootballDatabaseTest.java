package components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code FantasyFootballDatabase}'s constructor, kernel,
 * and secondary methods.
 *
 * @author Brandon Reed
 *
 */
public abstract class FantasyFootballDatabaseTest {

    /**
     * Creates and returns a {@code FantasyFootballDatabase} with the given
     * player name and one stat/value pair already entered.
     *
     * @param name
     *            player name
     * @param stat
     *            stat label
     * @param values
     *            one or more values for that stat
     * @return populated database
     */
    private static FantasyFootballDatabase createFromArgs(String name,
            String stat, double... values) {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer(name);
        for (double v : values) {
            db.enterStat(stat, v);
        }
        return db;
    }

    /*
     * Tests for constructor
     */

    @Test
    public void testConstructor() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        assertTrue(db != null);
    }

    @Test
    public void testConstructorInitialStatsEmpty() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        assertEquals(0, db.getStats().length());
    }

    @Test
    public void testEnterPlayerAndGetName() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        assertEquals("Josh Allen", db.getName());
    }

    @Test
    public void testEnterStatAndGetOneValue() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        Map.Pair<String, Sequence<Double>> pair = db
                .getStatAndValues("Passing Yards");
        assertEquals("Passing Yards", pair.key());
        assertEquals(1, pair.value().length());
        assertEquals(320.0, pair.value().entry(0), 0.001);
    }

    @Test
    public void testEnterStatMultipleValuesOrderPreserved() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0, 173.0, 410.0);
        Sequence<Double> vals = db.getStatAndValues("Passing Yards").value();
        assertEquals(3, vals.length());
        assertEquals(320.0, vals.entry(0), 0.001);
        assertEquals(173.0, vals.entry(1), 0.001);
        assertEquals(410.0, vals.entry(2), 0.001);
    }

    @Test
    public void testEnterStatTwoStats() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        db.enterStat("Passing Yards", 320.0);
        db.enterStat("Rushing Yards", 55.0);

        assertEquals(320.0,
                db.getStatAndValues("Passing Yards").value().entry(0), 0.001);
        assertEquals(55.0,
                db.getStatAndValues("Rushing Yards").value().entry(0), 0.001);
    }

    @Test
    public void testRemoveStatAndValues() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        db.enterStat("Passing Yards", 320.0);
        db.enterStat("Passing Yards", 173.0);

        Map.Pair<String, Sequence<Double>> removed = db
                .removeStatAndValues("Passing Yards");
        assertEquals("Passing Yards", removed.key());
        assertEquals(2, removed.value().length());

        Sequence<String> remaining = db.getStats();
        for (int i = 0; i < remaining.length(); i++) {
            assertFalse(remaining.entry(i).equals("Passing Yards"));
        }
    }

    @Test
    public void testRemoveStatAndValuesOnlyOneStat() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        db.enterStat("Passing Yards", 320.0);
        db.enterStat("Rushing Yards", 55.0);

        db.removeStatAndValues("Passing Yards");

        Sequence<String> stats = db.getStats();
        assertEquals(1, stats.length());
        assertEquals("Rushing Yards", stats.entry(0));
    }

    @Test
    public void testGetStatsNoStats() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        Sequence<String> stats = db.getStats();
        assertEquals(0, stats.length());
    }

    @Test
    public void testGetStatsOneStat() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 300.0);
        Sequence<String> stats = db.getStats();
        assertEquals(1, stats.length());
        assertEquals("Passing Yards", stats.entry(0));
    }

    @Test
    public void testGetStatsThreeStatsCorrectCount() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen");
        db.enterStat("Passing Yards", 320.0);
        db.enterStat("Rushing Yards", 55.0);
        db.enterStat("Touchdowns", 3.0);

        Sequence<String> stats = db.getStats();
        assertEquals(3, stats.length());
    }

    @Test
    public void testAverageStatValueSingleValue() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 300.0);
        assertEquals(300.0, db.averageStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testAverageStatValueTwoEqualValues() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 200.0, 200.0);
        assertEquals(200.0, db.averageStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testAverageStatValueThreeValues() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 100.0, 200.0, 300.0);
        assertEquals(200.0, db.averageStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testAverageStatValueWithZero() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Rushing Yards", 0.0, 60.0);
        assertEquals(30.0, db.averageStatValue("Rushing Yards"), 0.001);
    }

    @Test
    public void testLeastStatValueSingleValue() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 250.0);
        assertEquals(250.0, db.leastStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testLeastStatValueMultipleValues() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0, 173.0, 410.0);
        assertEquals(173.0, db.leastStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testLeastStatValueFirstIsSmallest() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Rushing Yards", 10.0, 55.0, 80.0);
        assertEquals(10.0, db.leastStatValue("Rushing Yards"), 0.001);
    }

    @Test
    public void testLeastStatValueLastIsSmallest() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Rushing Yards", 80.0, 55.0, 10.0);
        assertEquals(10.0, db.leastStatValue("Rushing Yards"), 0.001);
    }

    @Test
    public void testGreatestStatValueSingleValue() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 350.0);
        assertEquals(350.0, db.greatestStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testGreatestStatValueMultipleValues() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0, 173.0, 410.0);
        assertEquals(410.0, db.greatestStatValue("Passing Yards"), 0.001);
    }

    @Test
    public void testGreatestStatValueFirstIsGreatest() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Rushing Yards", 90.0, 55.0, 10.0);
        assertEquals(90.0, db.greatestStatValue("Rushing Yards"), 0.001);
    }

    @Test
    public void testGreatestStatValueLastIsGreatest() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Rushing Yards", 10.0, 55.0, 90.0);
        assertEquals(90.0, db.greatestStatValue("Rushing Yards"), 0.001);
    }

    @Test
    public void testValuesGreaterOrEqualSAllGreater() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 100.0, 200.0, 300.0);
        Sequence<Double> result = db.valuesGreaterOrEqual("Passing Yards",
                100.0);
        assertEquals(3, result.length());
    }

    @Test
    public void testValuesGreaterOrEqualSomeGreater() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 100.0, 200.0, 300.0);
        Sequence<Double> result = db.valuesGreaterOrEqual("Passing Yards",
                200.0);
        assertEquals(2, result.length());
    }

    @Test
    public void testValuesGreaterOrEqualNoneSmaller() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 50.0, 75.0);
        Sequence<Double> result = db.valuesGreaterOrEqual("Passing Yards",
                200.0);
        assertEquals(0, result.length());
    }

    @Test
    public void testValuesGreaterOrEqualExact() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 200.0, 199.0);
        Sequence<Double> result = db.valuesGreaterOrEqual("Passing Yards",
                200.0);
        assertEquals(1, result.length());
        assertEquals(200.0, result.entry(0), 0.001);
    }

    @Test
    public void testHashCodeEqualsNameLength() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("Josh Allen"); // length = 10
        assertEquals(10, db.hashCode());
    }

    @Test
    public void testHashCodeSingleCharName() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        db.enterPlayer("A");
        assertEquals(1, db.hashCode());
    }

    @Test
    public void testHashCodeEqualNamesProduceEqualHash() {
        FantasyFootballDatabase db1 = new FantasyFootballDatabase1L();
        FantasyFootballDatabase db2 = new FantasyFootballDatabase1L();
        db1.enterPlayer("Josh Allen");
        db2.enterPlayer("Josh Allen");
        assertEquals(db1.hashCode(), db2.hashCode());
    }

    @Test
    public void testHashCodeDifferentLengthNames() {
        FantasyFootballDatabase db1 = new FantasyFootballDatabase1L();
        FantasyFootballDatabase db2 = new FantasyFootballDatabase1L();
        db1.enterPlayer("Josh Allen"); // length = 10
        db2.enterPlayer("Lamar Jackson"); // length = 13
        assertTrue(db1.hashCode() != db2.hashCode());
    }

    @Test
    public void testEqualsEqualDatabases() {
        FantasyFootballDatabase db1 = createFromArgs("Josh Allen",
                "Passing Yards", 320.0, 173.0);
        FantasyFootballDatabase db2 = createFromArgs("Josh Allen",
                "Passing Yards", 320.0, 173.0);
        assertEquals(db1, db2);
    }

    @Test
    public void testEqualsDifferentNames() {
        FantasyFootballDatabase db1 = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        FantasyFootballDatabase db2 = createFromArgs("Lamar Jackson",
                "Passing Yards", 320.0);
        assertFalse(db1.equals(db2));
    }

    @Test
    public void testEqualsDifferentValues() {
        FantasyFootballDatabase db1 = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        FantasyFootballDatabase db2 = createFromArgs("Josh Allen",
                "Passing Yards", 200.0);
        assertFalse(db1.equals(db2));
    }

    @Test
    public void testEqualsDifferentStatCount() {
        FantasyFootballDatabase db1 = new FantasyFootballDatabase1L();
        db1.enterPlayer("Josh Allen");
        db1.enterStat("Passing Yards", 320.0);
        db1.enterStat("Rushing Yards", 55.0);

        FantasyFootballDatabase db2 = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        assertFalse(db1.equals(db2));
    }

    @Test
    public void testEqualsSameReference() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        assertTrue(db.equals(db));
    }

    @Test
    public void testEqualsNull() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        assertFalse(db.equals(null));
    }

    @Test
    public void testToStringStartsWithPlayerName() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        String result = db.toString();
        assertTrue(result.startsWith("Josh Allen,"));
    }

    @Test
    public void testToStringEndsWithPeriod() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        assertTrue(db.toString().endsWith("."));
    }

    @Test
    public void testToStringContainsStatName() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        assertTrue(db.toString().contains("Passing Yards"));
    }

    @Test
    public void testClearStatsAreGone() {
        FantasyFootballDatabase db = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        db.clear();
        db.enterPlayer("Josh Allen");
        assertEquals(0, db.getStats().length());
    }

    @Test
    public void testTransferFromReceiverHasCorrectName() {
        FantasyFootballDatabase source = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        FantasyFootballDatabase dest = new FantasyFootballDatabase1L();
        dest.transferFrom(source);
        assertEquals("Josh Allen", dest.getName());
    }

    @Test
    public void testTransferFromReceiverHasStats() {
        FantasyFootballDatabase source = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        FantasyFootballDatabase dest = new FantasyFootballDatabase1L();
        dest.transferFrom(source);
        assertEquals(320.0,
                dest.getStatAndValues("Passing Yards").value().entry(0), 0.001);
    }

    @Test
    public void testTransferFromSourceIsCleared() {
        FantasyFootballDatabase source = createFromArgs("Josh Allen",
                "Passing Yards", 320.0);
        FantasyFootballDatabase dest = new FantasyFootballDatabase1L();
        dest.transferFrom(source);
        // After transfer, source rep is reset; enter a new player to verify
        source.enterPlayer("New Player");
        assertEquals(0, source.getStats().length());
    }

    @Test
    public void testNewInstance_sameType() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        FantasyFootballDatabase newPlayer = db.newInstance();
        assertTrue(newPlayer instanceof FantasyFootballDatabase1L);
    }

    @Test
    public void testNewInstance_distinctReference() {
        FantasyFootballDatabase db = new FantasyFootballDatabase1L();
        FantasyFootballDatabase fresh = db.newInstance();
        assertTrue(fresh != db);
    }

}
