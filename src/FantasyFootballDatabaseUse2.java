import components.FantasyFootballDatabase;
import components.FantasyFootballDatabase1L;
import components.sequence.Sequence;
import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Use case: building a fantasy football starting lineup using a Set of
 * FantasyFootballDatabase entries.
 *
 * Lineup: 1 QB, 2 WR, 2 RB, 1 TE, 1 FLEX, 1 K, 1 DEF
 */
abstract class FantasyFootballDatabaseUse2 {
    /**
     * Prints a single player's stats to out.
     */
    private static void printPlayer(FantasyFootballDatabase player,
            String position, SimpleWriter out) {
        out.println("[" + position + "] " + player.getName());
        Sequence<String> stats = player.getStats();
        for (String stat : stats) {
            out.println(
                    "  " + stat + " -> avg: " + player.averageStatValue(stat)
                            + " | low: " + player.leastStatValue(stat)
                            + " | high: " + player.greatestStatValue(stat));
        }
        out.println();
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        FantasyFootballDatabase qb = new FantasyFootballDatabase1L();
        qb.enterPlayer("Josh Allen");
        qb.enterStat("Passing Yards", 320.0);
        qb.enterStat("Passing Yards", 412.0);
        qb.enterStat("Touchdowns", 3.0);
        qb.enterStat("Touchdowns", 4.0);

        FantasyFootballDatabase wr1 = new FantasyFootballDatabase1L();
        wr1.enterPlayer("Davante Adams");
        wr1.enterStat("Receiving Yards", 105.0);
        wr1.enterStat("Receiving Yards", 88.0);
        wr1.enterStat("Receptions", 7.0);
        wr1.enterStat("Receptions", 6.0);

        FantasyFootballDatabase wr2 = new FantasyFootballDatabase1L();
        wr2.enterPlayer("Stefon Diggs");
        wr2.enterStat("Receiving Yards", 90.0);
        wr2.enterStat("Receiving Yards", 73.0);
        wr2.enterStat("Receptions", 5.0);
        wr2.enterStat("Receptions", 8.0);

        FantasyFootballDatabase rb1 = new FantasyFootballDatabase1L();
        rb1.enterPlayer("Derrick Henry");
        rb1.enterStat("Rushing Yards", 134.0);
        rb1.enterStat("Rushing Yards", 98.0);
        rb1.enterStat("Touchdowns", 1.0);
        rb1.enterStat("Touchdowns", 2.0);

        FantasyFootballDatabase rb2 = new FantasyFootballDatabase1L();
        rb2.enterPlayer("Austin Ekeler");
        rb2.enterStat("Rushing Yards", 67.0);
        rb2.enterStat("Rushing Yards", 55.0);
        rb2.enterStat("Receptions", 4.0);
        rb2.enterStat("Receptions", 3.0);

        FantasyFootballDatabase te = new FantasyFootballDatabase1L();
        te.enterPlayer("Travis Kelce");
        te.enterStat("Receiving Yards", 112.0);
        te.enterStat("Receiving Yards", 95.0);
        te.enterStat("Receptions", 8.0);
        te.enterStat("Receptions", 7.0);

        FantasyFootballDatabase flex = new FantasyFootballDatabase1L();
        flex.enterPlayer("Tony Pollard");
        flex.enterStat("Rushing Yards", 78.0);
        flex.enterStat("Rushing Yards", 61.0);
        flex.enterStat("Receptions", 3.0);
        flex.enterStat("Receptions", 2.0);

        FantasyFootballDatabase k = new FantasyFootballDatabase1L();
        k.enterPlayer("Justin Tucker");
        k.enterStat("Field Goals Made", 3.0);
        k.enterStat("Field Goals Made", 2.0);
        k.enterStat("Extra Points Made", 2.0);
        k.enterStat("Extra Points Made", 3.0);

        FantasyFootballDatabase def = new FantasyFootballDatabase1L();
        def.enterPlayer("San Francisco 49ers D/ST");
        def.enterStat("Sacks", 4.0);
        def.enterStat("Sacks", 3.0);
        def.enterStat("Interceptions", 2.0);
        def.enterStat("Interceptions", 1.0);

        Set<FantasyFootballDatabase> lineup = new Set1L<>();
        lineup.add(qb);
        lineup.add(wr1);
        lineup.add(wr2);
        lineup.add(rb1);
        lineup.add(rb2);
        lineup.add(te);
        lineup.add(flex);
        lineup.add(k);
        lineup.add(def);

        out.println("Weekly Lineup");
        out.println();

        printPlayer(qb, "QB", out);
        printPlayer(wr1, "WR1", out);
        printPlayer(wr2, "WR2", out);
        printPlayer(rb1, "RB1", out);
        printPlayer(rb2, "RB2", out);
        printPlayer(te, "TE", out);
        printPlayer(flex, "FLEX", out);
        printPlayer(k, "K", out);
        printPlayer(def, "DEF", out);

        out.close();
    }
}