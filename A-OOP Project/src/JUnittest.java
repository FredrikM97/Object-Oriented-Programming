import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import fotochop.Fotochop;

class JUnittest {

	Fotochop fc = new Fotochop();
	
	/**
	 * Methods and variables need to be public to use JUnit test
	 */
	@Test
	void testNewImage() {
		fc.blankImage(50, 50);
		BufferedImage img = (BufferedImage) fc.image.getImage();
		assertEquals(img.getWidth(),fc.image.getIconWidth());
		assertEquals(img.getHeight(),fc.image.getIconHeight());
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				assertEquals(-1,img.getRGB(x,y));
			}
		}
	}

	@Test
	void testgetFilters() {
		/**
		 * Add objects and let classloader load them
		 */
		assertEquals("BlackCircle",fc.getFilters()[0].toString());
		assertEquals("Chess", fc.getFilters()[1].toString());
	}
	@Test
	void testdeepCopy() {
		/**
		 * Testing colorspace & Resolution
		 */
		BufferedImage newBI = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < 100; x++)
			for (int y = 0; y < 100; y++) {
				newBI.setRGB(x, y, (int)(Math.random()*0xFFFFFF));
			}
		fc.image.setImage(newBI);
		BufferedImage dc = (BufferedImage)fc.deepCopy(fc.image).getImage();
		assertNotSame(newBI, dc);
		
		for (int x = 0; x < dc.getWidth(); x++) {
			for (int y = 0; y < dc.getHeight(); y++) {
				assertEquals(newBI.getRGB(x, y), dc.getRGB(x, y));
			}
		}
		assertEquals(newBI.getHeight(), dc.getHeight());
		assertEquals(newBI.getWidth(), dc.getWidth());
	}


}
