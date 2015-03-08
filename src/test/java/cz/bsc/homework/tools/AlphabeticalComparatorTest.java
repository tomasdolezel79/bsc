package cz.bsc.homework.tools;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import cz.bsc.homework.domain.Payment;

public class AlphabeticalComparatorTest {

	@Test
	public void testOrder(){
		
		Payment [] p = new Payment[]{new Payment("CZK"),new Payment("AAA"),new Payment("ZZZ")};
		List<Payment> l = new ArrayList<Payment>(Arrays.asList(p));
		Collections.sort(l,new AlphabeticalComparator());
		
		assertTrue(l.get(0).getCurrency().equals("AAA"));
		assertTrue(l.get(1).getCurrency().equals("CZK"));
		assertTrue(l.get(2).getCurrency().equals("ZZZ"));
		
	}
	
}
