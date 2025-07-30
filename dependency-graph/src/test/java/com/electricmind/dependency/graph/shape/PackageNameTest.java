package com.electricmind.dependency.graph.shape;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PackageNameTest {

	@Test
	public void testGetDepth() throws Exception {
		assertEquals("ca.intelliware.example", 3, new PackageName("ca.intelliware.example").getDepth());
	}
	@Test
	public void testGetCommonPrefix() throws Exception {
		PackageName example = new PackageName("ca.intelliware.example");
		PackageName exampleModel = new PackageName("ca.intelliware.example.model");
		assertEquals("prefix", new PackageName("ca.intelliware.example"), example.getCommonPrefix(exampleModel));
	}
	
	@Test
	public void testGetCommonPrefix2() throws Exception {
		PackageName exampleService = new PackageName("ca.intelliware.example.service");
		PackageName exampleModel = new PackageName("ca.intelliware.example.model");
		assertEquals("prefix", new PackageName("ca.intelliware.example"), exampleService.getCommonPrefix(exampleModel));
	}
	
	@Test
	public void testRemovePrefix() throws Exception {
		PackageName exampleService = new PackageName("ca.intelliware.example.service");
		PackageName prefix = new PackageName("ca.intelliware.example");
		assertEquals("result", new PackageName("service"), exampleService.removePrefix(prefix));
	}
	
	@Test
	public void testGetCommonPrefixWithNoCommonality() throws Exception {
		PackageName exampleService = new PackageName("com.intware.fred.service");
		PackageName exampleModel = new PackageName("ca.intelliware.example.model");
		assertEquals("prefix", new PackageName(""), exampleService.getCommonPrefix(exampleModel));
	}
}
