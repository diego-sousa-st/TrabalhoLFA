package minimizacaoafd;

import org.junit.Test;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class MinimizacaoAFDTest {
       
    /**
     * Test of main method, of class MinimizacaoAFD.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = new String[3];
        args[0] = "desc_afd1.txt";
        args[1] = "saidaTabela.txt";
        args[2] = "afdMin.txt";
        MinimizacaoAFD.main(args);        
    }   
}
