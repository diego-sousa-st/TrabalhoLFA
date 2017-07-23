package minimizacaoafd.Controller;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class MinimizacaoTest {
      
    /**
     * Test of executar method, of class Minimizacao.
     */
    @Test
    public void testExecutar() throws Exception {
        System.out.println("executar");
        try{
            Minimizacao min = new Minimizacao("desc_afd1.txt", "saidaTabela.txt", "afdMin.txt");
            min.executar();            
        }
        catch(Exception e){
            fail();
        }        
    }
    
}
