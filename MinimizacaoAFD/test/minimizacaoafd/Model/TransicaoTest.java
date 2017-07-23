package minimizacaoafd.Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class TransicaoTest {

    /**
     * Test of equals method, of class Transicao when the 2 objects are the same.
     */
    @Test
    public void testEqualsAddress() {
        System.out.println("equals");
        Transicao t = new Transicao(new Estado("q0", true), new Estado("q1", false), "a");
        Transicao t2 = t;
        boolean result = t.equals(t2);
        assertEquals(true, result);
    }

    /**
     * Test of equals method, of class Transicao when the objects have differents address but they represent the same
     * transition.
     */
    @Test
    public void testEqualsDiffTransitions() {
        System.out.println("equals");
        Transicao t = new Transicao(new Estado("q0", true), new Estado("q1", false), "a");
        Transicao t2 = new Transicao(new Estado("q0", true), new Estado("q1", false), "a");;
        boolean result = t.equals(t2);
        assertEquals(true, result);
    }

    /**
     * Test of equals method, of class Transicao when the 2 objects are diferent.
     */
    @Test
    public void testEqualsWrong() {
        System.out.println("equals");
        Transicao t = new Transicao(new Estado("q0", true), new Estado("q1", false), "a");
        Transicao t2 = new Transicao(new Estado("q1", true), new Estado("q1", false), "a");;
        boolean result = t.equals(t2);
        assertEquals(false, result);
    }
}
