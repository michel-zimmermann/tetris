package tetris.main;

import tetris.init.Inicializacao;
import tetris.robo.Execucao;

public class Main {

	public static void main(String[] args) throws Exception {
		Inicializacao.iniciar();
		executar();
	}

	private static void executar() {
		new Execucao(new SysoutLogger()).run();
	}
	
}
