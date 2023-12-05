package com.api.grg.envy;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.api.grg.envy.Utils.Utils;

// include it if you want to test your
// spring boot app
// @SpringBootTest
class ApiApplicationTests {

	@Test
	public void contextLoads() {
		String msg = "hello world";
		System.out.println(msg);
	}

	// works pretty well...
	@Test
	public void tt ( ) {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(2);
		ints.add(8);
		System.out.println(Utils.aL2Csv(ints));
		// System.out.println(s);
		// System.out.println("eeh");
	
	}

}
