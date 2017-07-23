package minimizacaoafd.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class AfdTest {
    private final Afd afd;
    
    public AfdTest() {
        afd = new Afd("{q0,q1}", "{a,b}", "{\n(q0,a->q1)\n}", "q0", "{q1}");
    }
    
    /**
     * Test of addEstado method, of class Afd.
     */
    @Test
    public void testAddEstado() {
        System.out.println("addEstado");
        int tam = afd.getEstados().size();
        afd.addEstado(new Estado("q2",false));
        int result = afd.getEstados().size();
        assertEquals(result, tam+1);
    }

    /**
     * Test of addTransicao method, of class Afd.
     */
    @Test
    public void testAddTransicao() {
        System.out.println("addTransicao");
        int tam = afd.getTransicoes().size();
        afd.addTransicao(new Transicao(new Estado("q0",false),new Estado("q3",false), "a"));
        int result = afd.getTransicoes().size();
        assertEquals(result, tam+1);
    }
    
    /**
     * Test of toString method, of class Afd.
     */
    @Test
    public void testToString() {
       System.out.println("toString");
       String text = "(\n\t{q0,q1},\n\t{a,b},\n\t{\n\t\t(q0,a->q1)\n\t},\n\tq0,\n\t{q1}\n)";        
       String result = afd.toString();        
       boolean resultado = text.equals(result);
       assertEquals(true, resultado);
    }    
}
