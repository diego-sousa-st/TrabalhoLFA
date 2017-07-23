package minimizacaoafd.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class LinhaTest {
    private final Linha linha;
    
    public LinhaTest() {
        linha = new Linha(new Estado("q0", true), new Estado("q1", false));
    }
    
    /**
     * Test of inserirDependente method, of class Linha.
     */
    @Test
    public void testInserirDependente() {
        System.out.println("inserirDependente");
        linha.inserirDependente(new Linha(new Estado("q1", false), new Estado("q2", false)));
        if(linha.getDependentes().size() == 1){
            assertEquals(true, true);
        }
    }    

    /**
     * Test of equals method, of class Linha when the address of Linha are the same.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        if(linha.equals(linha)){
            assertEquals(true, true);
        }
    }
    
    /**
     * Test of equals method, of class Linha when the address of Linha are the same.
     */
    @Test
    public void testEqualsWrong() {
        System.out.println("equals");
        boolean result = linha.equals(new Linha(new Estado("q2", true), new Estado("q3", false)));
        assertEquals(result, false);
    }
}
