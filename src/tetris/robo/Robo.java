package tetris.robo;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import tetris.init.Inicializavel;

public class Robo implements Inicializavel {

	private static Robot BOT;

	@Override
	public void iniciar() {
		try {
			BOT = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage print(Rectangle area) {
		return BOT.createScreenCapture(area);
	}
	
	public static void moverMouse(int x, int y) {
		BOT.mouseMove(x, y);
	}
	
	public static void clicarMouse(int button) {
		try {
			BOT.mousePress(button);
			BOT.delay(40);
			BOT.mouseRelease(button);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dragAndDrop(int button, int x, int y) {
		try {
			BOT.mousePress(button);
			BOT.delay(400);
			BOT.mouseMove(x, y);
			BOT.delay(400);
			BOT.mouseRelease(button);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void keyPress(int key) {
		try {
			BOT.keyPress(key);
			BOT.delay(100);
			BOT.keyRelease(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
