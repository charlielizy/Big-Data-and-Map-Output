package MathProblemSolving;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataProcessorTest {
	DataProcessor helper = new DataProcessor();
	@BeforeEach
	void setUp() throws Exception {
		
	}
	@Test
	void testAutomatedEnter() {
		
	}
	@Test
	void testCalculateFromFile() {
		helper.AutomatedEnter();
		double expResult = 1;
		double result = helper.calculateFromFile();
		assertEquals(expResult,result);
	}

}
