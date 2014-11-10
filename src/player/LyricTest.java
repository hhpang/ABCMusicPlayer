package player;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Testing strategy:
 * We test the equals method to make sure it is reflexive, symmetric, transitive, and
	 * test to make sure it returns true when lyrics are equal, and false otherwise.
	 * 
	 * We test toString method.  We test several cases: a normal/base case, a case with special characters, and an empty string.
	 * 
	 * We test deepCopy method by making sure that the copy and the original Lyric are equal.
 */

public class LyricTest {
	
	//equals tests
	@Test
	public void lyricsAreEqualTest() {
		Lyric lyric1 = new Lyric("bah", 3);
		Lyric lyric2 = new Lyric("bah", 3);
		Lyric lyric3 = new Lyric("bah", 3);
		assertEquals(true, lyric1.equals(lyric1)); //reflexivity
		assertEquals(true, lyric1.equals(lyric2)); //test that dur1 equals dur2 
		assertEquals(true, lyric2.equals(lyric1)); //symmetry
		assertEquals(true, lyric1.equals(lyric2) && lyric2.equals(lyric3) && lyric1.equals(lyric3)); //transitivity
	}
	
	@Test
	public void syllablesNotEqualTest() {
		Lyric lyric1 = new Lyric("bahh", 2);
		Lyric lyric2 = new Lyric("bah", 2);
		assertEquals(false, lyric1.equals(lyric2));
	}
	
	@Test
	public void numberOfNotesNotEqualTest() {
		Lyric lyric1 = new Lyric("bah", 3);
		Lyric lyric2 = new Lyric("bah", 2);
		assertEquals(false, lyric1.equals(lyric2));
	}
	
	//toString tests
	
	@Test
	public void basicToStringTest() {
		Lyric lyric1 = new Lyric("YO", 3);
		assertEquals("YO", lyric1.toString());
	}
	
	@Test
	public void specialCharactersToStringTest() {
		Lyric lyric1 = new Lyric("YO~ho~yo~ho~A~pirate's~life~for~me*", 3);
		assertEquals("YO~ho~yo~ho~A~pirate's~life~for~me*", lyric1.toString());
	}
	
	@Test
	public void emptyToStringTest() {
		Lyric lyric1 = new Lyric("", 3);
		assertEquals("", lyric1.toString());
	}
	
//getDeepCopy tests
	
	@Test
	public void basicGetDeepCopyTest() {
		Lyric lyric1 = new Lyric("Hi", 3);
		assertEquals(lyric1, lyric1.getDeepCopy());
	}
	
}
