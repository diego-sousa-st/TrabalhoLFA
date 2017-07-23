/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minimizacaoafd;

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
@Suite.SuiteClasses({minimizacaoafd.MinimizacaoAFDTest.class,minimizacaoafd.Controller.ControllerSuite.class,minimizacaoafd.Model.ModelSuite.class,minimizacaoafd.Persistence.PersistenceSuite.class})
public class MinimizacaoafdSuite {

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