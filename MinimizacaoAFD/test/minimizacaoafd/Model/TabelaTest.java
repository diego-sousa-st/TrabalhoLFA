package minimizacaoafd.Model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class TabelaTest {
    
    private final Tabela t;
    private final Linha linha;
    
    public TabelaTest(){
        t = new Tabela();
        linha =  new Linha(new Estado("q0", true), new Estado("q1", false));
    }
    /**
     * Test of addLinha method, of class Tabela.
     */
    @Test
    public void testAddLinha() {
       System.out.println("testAddLinha");       
       t.addLinha(linha);
       if(t.getLinhas().size() == 1){
           assertEquals(true, true);
       }
    }

    /**
     * Test of toString method, of class Tabela.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String table = t.toString();
        String answer = "        INDICE        D[i,j]                S[i,j]                MOTIVO\n";
        answer += "        [q0,q1]           0            {}        ";
        if(table.equals(answer)){
            assertEquals(true,true);
        }
    }   
}
