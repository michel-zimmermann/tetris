package tetris.main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Teste {

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\miche\\OneDrive\\Área de Trabalho\\tetris\\imgs\\";
		File dir = new File(path);
		for (File arq : dir.listFiles()) {
			
			BufferedImage img = ImageIO.read(arq);
			
			System.out.println();
			System.out.println(arq.getName());
			System.out.println(img.getRGB(19, 10));
			System.out.println(img.getRGB(19, 11));
			System.out.println(img.getRGB(19, 12));
			System.out.println(img.getRGB(19, 13));

		}
	}
}
