package tetris.robo;

import java.util.Arrays;

public enum TipoPeca {

	AZUL_CLARO(-15138817),
	AZUL_ESCURO(-16749057),
	VERMELHO(-48090),
	LARANJA(-23016),
	AMARELO(-4042),
	VERDE(-14024919);
	
	private final int rgb;
	
	private TipoPeca(int rgb) {
		this.rgb = rgb;
	}

	public static TipoPeca getPeca(int rgb) {
		return Arrays.asList(values()).stream()
				.filter(tp -> tp.rgb == rgb)
				.findFirst().orElse(null);
	}
}
