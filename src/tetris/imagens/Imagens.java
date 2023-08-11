package tetris.imagens;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import tetris.init.Inicializavel;

public class Imagens implements Inicializavel {

	private static final Map<TipoImagem, BufferedImage> IMAGENS = new HashMap<>();
	
	@Override
	public void iniciar() {
		IMAGENS.clear();
		for (TipoImagem tipo : TipoImagem.values()) {
			IMAGENS.put(tipo, load(tipo.name() + ".png"));
		}
	}
	
	private static BufferedImage load(String file) {
		try {
			return ImageIO.read(Imagens.class.getResource(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage get(TipoImagem tipo) {
		return IMAGENS.get(tipo);
	}
}
