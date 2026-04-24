package components;

import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;

/**
 * JUnit test.
 *
 * @author Brandon Reed
 *
 */
public class FantasyFootballDatabase1LTest extends FantasyFootballDatabaseTest {

    @Override
    protected final FantasyFootballDatabase constructorTest() {
        return new FantasyFootballDatabase1L();
    }

    @Override
    protected final Map<String, Map<String, Sequence<Double>>> constructorRef() {
        return new Map1L<String, Map<String, Sequence<Double>>>();
    }
}
