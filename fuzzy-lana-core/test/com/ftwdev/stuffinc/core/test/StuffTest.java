package com.ftwdev.stuffinc.core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ftwdev.stuffinc.core.Stuff;

public class StuffTest {

	@Test
	public void test() {
		Stuff s = new Stuff();
		System.out.println(s.getName() + " " + s.getType());
	}

}
