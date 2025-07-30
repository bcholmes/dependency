package com.electricmind.dependency.graph.shape;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.electricmind.dependency.Node;
import com.electricmind.dependency.graph.TextLabel;

@RunWith(MockitoJUnitRunner.class)
public class PackageNameLabelStrategyTest {

	PackageNameLabelStrategy fixture = new PackageNameLabelStrategy();
	
	@Test
	public void shouldIdentifyCommonPrefix() {
		BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		
		@SuppressWarnings("unchecked")
		Node<String> node1 = mock(Node.class);
		@SuppressWarnings("unchecked")
		Node<String> node2 = mock(Node.class);
		@SuppressWarnings("unchecked")
		Node<String> node3 = mock(Node.class);
		
		Mockito.when(node1.getName()).thenReturn("com.electricmind.first");
		Mockito.when(node2.getName()).thenReturn("com.electricmind.first.with.qualifier");
		Mockito.when(node3.getName()).thenReturn("com.electricmind.third");
		
		this.fixture.initialize(graphics, new TextLabel(new Rectangle2D.Float(0, 0, 100, 50)), Arrays.asList(
				node1, node2, node3));
		
		assertEquals(new PackageName("com.electricmind"), this.fixture.getPrefixOf(new PackageName("com.electricmind.first")));
	}

	@Test
	public void shouldIdentifyMultipleCommonPrefixes() {
		BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		
		@SuppressWarnings("unchecked")
		Node<String> node1 = mock(Node.class);
		@SuppressWarnings("unchecked")
		Node<String> node2 = mock(Node.class);
		@SuppressWarnings("unchecked")
		Node<String> node3 = mock(Node.class);
		@SuppressWarnings("unchecked")
		Node<String> node4 = mock(Node.class);
		
		Mockito.when(node1.getName()).thenReturn("com.electricmind.first");
		Mockito.when(node2.getName()).thenReturn("com.electricmind.second");
		Mockito.when(node3.getName()).thenReturn("ca.intelliware.old1");
		Mockito.when(node4.getName()).thenReturn("ca.intelliware.old2");
		
		this.fixture.initialize(graphics, new TextLabel(new Rectangle2D.Float(0, 0, 100, 50)), Arrays.asList(
				node1, node2, node3, node4));
		
		assertEquals(new PackageName("com.electricmind"), this.fixture.getPrefixOf(new PackageName("com.electricmind.first")));
		assertEquals(new PackageName("ca.intelliware"), this.fixture.getPrefixOf(new PackageName("ca.intelliware.old1")));
	}

}
