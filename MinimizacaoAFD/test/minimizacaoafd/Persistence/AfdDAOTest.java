package minimizacaoafd.Persistence;

import java.util.ArrayList;
import java.util.List;
import minimizacaoafd.Model.Afd;
import minimizacaoafd.Model.Estado;
import minimizacaoafd.Model.Transicao;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego, Nechelley e Maurício
 */
public class AfdDAOTest {    
        
    /**
     * Test of openAfd method, of class AfdDAO when the archive exists.
     */
    @Test
    public void testOpenAfdCorrect() throws Exception {
        System.out.println("openAfd");
        String nomeArqAfd = "desc_afd1.txt";
        AfdDAO instance = new AfdDAO();
        try{
            Afd result = instance.openAfd(nomeArqAfd);
            boolean right = false;
            if(result != null)
                right = true;
            assertEquals(true, right);
        }
        catch(Exception e){
            fail();
        }                       
    }

    /**
     * Test of openAfd method, of class AfdDAO when the archive doesn't exists.
     * @throws Exception 
     */
    @Test
    public void testOpenAfdWrong() throws Exception {
        System.out.println("openAfd");
        String nomeArqAfd = "nomeErrado.txt";
        AfdDAO instance = new AfdDAO();
        try{
            Afd result = instance.openAfd(nomeArqAfd);
            fail();
        }
        catch(Exception e){            
            assertEquals(true, true);
        }                       
    }
    
    /**
     * Test of saveAfd method, of class AfdDAO when the gave Afd is null.
     */
    @Test
    public void testSaveAfdWrong() throws Exception {
        System.out.println("saveAfd");
        Afd afd = null;
        String nomeArqAfdMin = "JUnit_afdMin_wrong.txt";
        AfdDAO instance = new AfdDAO();
        try{
            instance.saveAfd(afd, nomeArqAfdMin);
            fail();
        }                
        catch(Exception e){
            assertEquals(true, true);
        }
    }
    
    /**
     * Test of saveAfd method, of class AfdDAO when the gave Afd is correct.
     */
    @Test
    public void testSaveAfdCorrect() throws Exception {
        System.out.println("saveAfd");
        Estado e = new Estado("q0", true);
        List<Estado> estados = new ArrayList<Estado>();
        estados.add(e);
        List<String> alfabetoDeEntrada = new ArrayList<String>();
        alfabetoDeEntrada.add("a");
        List<Transicao> transicoes = new ArrayList<Transicao>();
        transicoes.add(new Transicao(e, e, "a"));
        Afd afd = new Afd(estados, e, alfabetoDeEntrada, transicoes);
        String nomeArqAfdMin = "JUnit_afdMin_correct.txt";
        AfdDAO instance = new AfdDAO();
        try{
            instance.saveAfd(afd, nomeArqAfdMin);
            assertEquals(true, true);            
        }                
        catch(Exception exception){            
            fail();
        }
    }    
}
