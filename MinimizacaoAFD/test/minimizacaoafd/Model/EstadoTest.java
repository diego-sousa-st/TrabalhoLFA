package minimizacaoafd.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class EstadoTest {
    private final Estado e;
    
    public EstadoTest() {
        e = new Estado("q0", true);
    }    

    /**
     * Test of equals method, of class Estado with the same address.
     */
    @Test
    public void testEqualsAddress() {
        System.out.println("equals");
        boolean result = e.equals(e);
        assertEquals(result, true);
    }

    /**
     * Test of equals method, of class Estado with address diferent but representing the same Estado.
     */
    @Test
    public void testEqualsDiffEstados() {
        System.out.println("equals");
        boolean result = e.equals(new Estado("q0", true));
        assertEquals(result, true);
        
    }
        
    /**
     * Test of equals method, of class Estado when they are diferents.
     */
    @Test
    public void testEqualsWrong() {
        System.out.println("equals");
        boolean result = e.equals(new Estado("q1", true));
        assertEquals(result, false);
    }
    
    /**
     * Test of hashCode method, of class Estado.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");        
        int expResult = 0;
        int result = e.hashCode();
        assertEquals(expResult, result);        
    }    
}
