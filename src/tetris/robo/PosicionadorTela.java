package tetris.robo;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

import tetris.imagens.TipoImagem;
import tetris.init.Inicializavel;

public class PosicionadorTela implements Inicializavel {

	@Override
	public void iniciar() {
		Rectangle telaCheia = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BuscaImagem busca = new BuscaImagem(telaCheia, TipoImagem.LOGO);
		busca.setMoverMouse(true);
		
		if (busca.buscar()) {
			Robo.dragAndDrop(InputEvent.BUTTON1_DOWN_MASK, 0, 0);
		} else {
			throw new IllegalStateException("Tela do jogo nao encontrada.");
		}
	}
	
}
