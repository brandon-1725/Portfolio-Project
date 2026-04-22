import components.FantasyFootballDatabase;
import components.FantasyFootballDatabase1L;
import components.map.Map;
import components.sequence.Sequence;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Documents one use of the database.
 */
abstract class FantasyFootballDatabaseUse1 {
    /**
     * First use case - Track and analyze a fantasy football player's stats
     * across multiple weeks of the season, then retrieve and compare
     * performance.
     *
     * @param args
     */
    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        FantasyFootballDatabase player = new FantasyFootballDatabase1L();
        player.enterPlayer("Josh Allen");

        // Week 1
        player.enterStat("passing yards", 317.0);
        player.enterStat("rushing yards", 56.0);
        player.enterStat("touchdowns", 3.0);
        player.enterStat("interceptions", 1.0);

        // Week 2
        player.enterStat("passing yards", 263.0);
        player.enterStat("rushing yards", 42.0);
        player.enterStat("touchdowns", 2.0);
        player.enterStat("interceptions", 0.0);

        // Week 3
        player.enterStat("passing yards", 384.0);
        player.enterStat("rushing yards", 73.0);
        player.enterStat("touchdowns", 4.0);
        player.enterStat("interceptions", 0.0);

        //  Retrieve player name
        out.println("Player: " + player.getName());
        // Player: Josh Allen

        //  Retrieve all stat names
        Sequence<String> stats = player.getStats();
        out.println("Tracked stats: " + stats);
        // Tracked stats: <passing yards, rushing yards, touchdowns, interceptions>

        //  Retrieve a stat and its values without removing
        Map.Pair<String, Sequence<Double>> passData = player
                .getStatAndValues("passing yards");
        out.println("Passing yards per week: " + passData.value());
        // Passing yards per week: <317.0, 263.0, 384.0>

        //  Calculate average passing yards per game
        Sequence<Double> passValues = passData.value();
        double total = 0.0;
        for (int i = 0; i < passValues.length(); i++) {
            total += passValues.entry(i);
        }
        double average = total / passValues.length();
        out.println("Avg passing yards/game: " + average);
        // Avg passing yards/game: 321.33

        //  Remove a stat entirely (e.g., no longer tracking interceptions)
        Map.Pair<String, Sequence<Double>> removed = player
                .removeStatAndValues("interceptions");
        out.println("Removed stat: " + removed.key());
        // Removed stat: interceptions

        //  Confirm interceptions is no longer tracked
        Sequence<String> updatedStats = player.getStats();
        out.println("Updated tracked stats: " + updatedStats);
        // Updated tracked stats: <passing yards, rushing yards, touchdowns>

        //  Transfer to a new database object (e.g., for archiving)
        FantasyFootballDatabase archive = new FantasyFootballDatabase1L();
        archive.transferFrom(player);

        out.println("Archive player: " + archive.getName());
        // Archive player: Josh Allen

        //  Original is now cleared after transferFrom
        out.println("Original cleared: " + player.getName());
        // Original cleared: null (or empty per convention)

        out.close();
    }
}
