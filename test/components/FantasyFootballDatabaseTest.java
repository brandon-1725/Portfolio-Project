package components;

import static org.junit.Assert.assertEquals;

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

    protected abstract FantasyFootballDatabase constructorTest();

    protected abstract Map<String, Map<String, Sequence<Double>>> constructorRef();

    @Test
    public final void testConstructor() {
        FantasyFootballDatabase db = this.constructorTest();
        Map<String, Map<String, Sequence<Double>>> ref = this.constructorRef();
        assertEquals(db, ref);
    }

}
