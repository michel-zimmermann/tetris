package tetris.init;

import java.util.ArrayList;
import java.util.List;

import tetris.imagens.Imagens;
import tetris.params.Params;
import tetris.robo.PosicionadorTela;
import tetris.robo.Robo;

public class Inicializacao {

	private Inicializacao() {
		// nop
	}
	
	private static List<Inicializavel> getInicializaveis() {
		List<Inicializavel> inicializaveis = new ArrayList<>(); 
		inicializaveis.add(new Params()); // sempre primeiro
		inicializaveis.add(new Imagens());
		inicializaveis.add(new Robo());
		inicializaveis.add(new PosicionadorTela());
		return inicializaveis;
	}
	
	public static void iniciar() {
		List<Inicializavel> inicializaveis = getInicializaveis();
		inicializaveis.forEach(Inicializavel::iniciar);
	}
}
