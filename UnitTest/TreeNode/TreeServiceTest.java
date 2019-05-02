package TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import TreeNode.TreeNode;
import TreeNode.TreeService;

import static org.junit.Assert.*;


public class TreeServiceTest {
    List<TreeNode> treeStructure;
    TreeService treeService;
    public TreeServiceTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        treeStructure = Arrays.asList(
                new TreeNode(1, "R", 0),
                new TreeNode(4, "G", 1),
                new TreeNode(2, "F", 1),
                new TreeNode(8, "Q", 1),
                new TreeNode(6, "H", 4),
                new TreeNode(10, "E", 4),
                new TreeNode(5, "Z", 2),
                new TreeNode(7, "P", 2),
                new TreeNode(11, "L", 2),
                new TreeNode(9, "U", 8),
                new TreeNode(3, "X", 5),
                new TreeNode(12, "T", 9),
                new TreeNode(13, "D", 9)
        );
        treeService = new TreeService(treeStructure);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoot method, of class TreeService.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");       
        TreeNode expResult = new TreeNode(1, "R", 0);
        TreeNode result = treeService.getRoot();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getCharactorByIndex method, of class TreeService.
     */
    @Test
    public void testGetCharactorByIndex() {
        System.out.println("getCharactorByIndex");
        int index = 9;
        String expResult = "U";
        String result = treeService.getCharactorByIndex(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of validateUserEnter method, of class TreeService.
     */
    @Test
    public void testValidateUserEnter() {
        System.out.println("validateUserEnter");
        String str = "a";
        int result = treeService.validateUserEnter(str);
        assertEquals(result,-1);

    }

    
}
