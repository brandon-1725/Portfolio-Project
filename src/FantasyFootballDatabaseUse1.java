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

        player.enterStat("passing yards", 317.0);
        player.enterStat("rushing yards", 56.0);
        player.enterStat("touchdowns", 3.0);
        player.enterStat("interceptions", 1.0);

        player.enterStat("passing yards", 263.0);
        player.enterStat("rushing yards", 42.0);
        player.enterStat("touchdowns", 2.0);
        player.enterStat("interceptions", 0.0);

        player.enterStat("passing yards", 384.0);
        player.enterStat("rushing yards", 73.0);
        player.enterStat("touchdowns", 4.0);
        player.enterStat("interceptions", 0.0);

        out.println("Player: " + player.getName());

        Sequence<String> stats = player.getStats();
        out.println("Tracked stats: " + stats);

        Map.Pair<String, Sequence<Double>> passData = player
                .getStatAndValues("passing yards");
        out.println("Passing yards per week: " + passData.value());

        Sequence<Double> passValues = passData.value();
        double total = 0.0;
        for (int i = 0; i < passValues.length(); i++) {
            total += passValues.entry(i);
        }
        double average = total / passValues.length();
        out.println("Avg passing yards/game: " + average);

        Map.Pair<String, Sequence<Double>> removed = player
                .removeStatAndValues("interceptions");
        out.println("Removed stat: " + removed.key());

        Sequence<String> updatedStats = player.getStats();
        out.println("Updated tracked stats: " + updatedStats);

        FantasyFootballDatabase archive = new FantasyFootballDatabase1L();
        archive.transferFrom(player);

        out.println("Archive player: " + archive.getName());

        out.println("Original cleared: " + player.getName());

        out.close();
    }
}
