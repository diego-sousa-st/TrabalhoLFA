/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimizacaoafd.Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Diego
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({minimizacaoafd.Model.TabelaTest.class, minimizacaoafd.Model.EstadoTest.class, minimizacaoafd.Model.LinhaTest.class, minimizacaoafd.Model.AfdTest.class, minimizacaoafd.Model.TransicaoTest.class})
public class ModelSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}