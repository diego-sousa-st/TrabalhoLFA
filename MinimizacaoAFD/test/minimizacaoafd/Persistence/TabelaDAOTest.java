package minimizacaoafd.Persistence;

import minimizacaoafd.Model.Estado;
import minimizacaoafd.Model.Linha;
import minimizacaoafd.Model.Tabela;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class TabelaDAOTest {
        
    /**
     * Test of saveTabelaMinimizacao method, of class TabelaDAO when the gave 
     * Tabela is correct.
     * .
     */
    @Test
    public void testSaveTabelaMinimizacaoCorrect() throws Exception {
        System.out.println("saveTabelaMinimizacao");
        Tabela tabelaMinimizacao = new Tabela();
        Estado e1 = new Estado("q0", true);
        Estado e2 = new Estado("q1", true);
        tabelaMinimizacao.addLinha(new Linha(e1, e2, false, "final/nao-final"));
        String nomeArqTabela = "JUnit_tabela.txt";
        TabelaDAO instance = new TabelaDAO();
        try{
            instance.saveTabelaMinimizacao(tabelaMinimizacao, nomeArqTabela);
            assertEquals(true,true);
        }        
        catch(Exception e){
            fail();
        }
    }
    
    /**
     * Test of saveTabelaMinimizacao method, of class TabelaDAO when the gave 
     * Tabela is null.
     */
    @Test
    public void testSaveTabelaMinimizacaoWrong() throws Exception {
        System.out.println("saveTabelaMinimizacao");
        Tabela tabelaMinimizacao = null;        
        String nomeArqTabela = "JUnit_tabela.txt";
        TabelaDAO instance = new TabelaDAO();
        try{
            instance.saveTabelaMinimizacao(tabelaMinimizacao, nomeArqTabela);
            fail();            
        }        
        catch(Exception e){
            assertEquals(true,true);
        }
    }
}
