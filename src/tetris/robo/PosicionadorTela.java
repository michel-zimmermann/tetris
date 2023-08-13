package tetris.robo;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
			PointerInfo info = MouseInfo.getPointerInfo();
			Point localDoMouse = info.getLocation();
			// x,y = 8,7 : diferenca entre o icone do jogo e a ponta da janela
			Robo.moverMouse(localDoMouse.x-8, localDoMouse.y-7);
			Robo.dragAndDrop(InputEvent.BUTTON1_DOWN_MASK, 0, 0);
		}
	}
}
