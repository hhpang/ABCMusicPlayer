package player;

import static org.junit.Assert.*;

import org.junit.Test;

public class DurationTest {
	/*
	 * Testing strategy:
	 * We test equals() method to make sure it is reflexive, symmetric, transitive, and
	 * test to make sure it returns true when durations are equal, and false otherwise.
	 * 
	 * We test toDouble method, using Durations constructed with both types of constructors.  We test several
	 * different cases (such as 0, 1, fractions, whole numbers)
	 * 
	 * We test toString method, using Durations constructed with both types of constructors.  We test several
	 * different cases (such as 0, 1, fractions, whole numbers)
	 * 
	 * We test deepCopy method by making sure that the copy and the original duration are equal
	 */
	//equals tests
	
	public void durationsAreEqual() {
		Duration dur1 = new Duration(5, 17);
		Duration dur2 = new Duration(5, 17);
		Duration dur3 = new Duration(5, 17);
		assertEquals(true, dur1.equals(dur1)); //reflexivity
		assertEquals(true, dur1.equals(dur2)); //test that dur1 equals dur2 
		assertEquals(true, dur2.equals(dur1)); //symmetry
		assertEquals(true, dur1.equals(dur2) && dur2.equals(dur3) && dur1.equals(dur3)); //transitivity
	}
	
	public void durationsNotEqual() {
		Duration dur1 = new Duration(6, 17);
		Duration dur2 = new Duration(5, 17);
		assertEquals(false, dur1.equals(dur2));
	}
    
    // ToDouble tests - we use our string constructor (see design pdf for details) to construct durations,
	// then we test that the toDouble method correctly translates
    @Test
    public void oneNoteTestConstructWithString() {
        Duration dur = new Duration("1"); 
        assertTrue(dur.toDouble() == 1.0); 
    }
    
    @Test
    public void fractionNoteTestConstructWithString() {
        Duration dur = new Duration("3/16");
        assertTrue(dur.toDouble() == 0.1875); 
    }
    
    @Test
    public void wholeNotesTestConstructWithString() {
        Duration dur = new Duration("3");
        assertTrue(dur.toDouble() == 3.0); 
    }
    
    @Test
    public void zeroStringTestConstructWithString() {
        Duration dur = new Duration("0");
        assertTrue(dur.toDouble() == 0.0);
    }
    
    @Test
    public void oneNoteTestConstructWithNumeratorAndDenominator() {
        Duration dur = new Duration(1, 1); 
        assertTrue(dur.toDouble() == 1.0); 
    }
    
    @Test
    public void fractionNoteTestConstructWithNumeratorAndDenominator() {
        Duration dur = new Duration(3, 16);
        assertTrue(dur.toDouble() == 0.1875); 
    }
    
    @Test
    public void wholeNotesTestConstructWithNumeratorAndDenominator() {
        Duration dur = new Duration(6 , 2);
        assertTrue(dur.toDouble() == 3.0); 
    }
    
    @Test
    public void zeroTestConstructWithNumeratorAndDenominator() {
        Duration dur = new Duration(0, 12);
        assertTrue(dur.toDouble() == 0.0);
    }
    
    //toString tests
    
    @Test
    public void smallFractionConstructWithStringToStringTest() {
        Duration dur = new Duration("5/128");
        assertEquals("5/128", dur.toString()); 
    }
    
    @Test
    public void zeroDurTestConstructWithStringToStringTest() {
        Duration dur = new Duration("0/4");
        assertEquals("0/4", dur.toString());
    }
    
    @Test
    public void smallFractionConstructWithNumeratorAndDenominatorToStringTest() {
        Duration dur = new Duration(5,128);
        assertEquals("5/128", dur.toString()); 
    }
    
    @Test
    public void zeroDurConstructWithNumeratorAndDenominatorToStringTest() {
        Duration dur = new Duration(0,4);
        assertEquals("0/4", dur.toString());
    }
    
}
