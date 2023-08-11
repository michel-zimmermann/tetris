package tetris.robo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ControladorMontagem {

	private final List<PecaMontagem> gabarito = new ArrayList<>();
	
	public ControladorMontagem() {
		limpar();
	}

	public void limpar() {
		gabarito.clear();
		gabarito.add(new PecaMontagem(TipoPeca.AZUL_ESCURO, 32, 112));
		gabarito.add(new PecaMontagem(TipoPeca.AZUL_CLARO, 170, 80));
		gabarito.add(new PecaMontagem(TipoPeca.VERDE, 64, 145));
		gabarito.add(new PecaMontagem(TipoPeca.VERMELHO, 42, 80));
		gabarito.add(new PecaMontagem(TipoPeca.VERMELHO, 107, 145));
		gabarito.add(new PecaMontagem(TipoPeca.AMARELO, 105, 80));
		gabarito.add(new PecaMontagem(TipoPeca.AMARELO, 170, 145));
	}
	
	public boolean continuar() {
		return gabarito.size() != 0;
	}
	
	public Point getLocalDaPeca(TipoPeca tipo) {
		int idx = gabarito.indexOf(new PecaMontagem(tipo, 0, 0));
		if (idx != -1) {
			PecaMontagem peca = gabarito.get(idx);
			gabarito.remove(peca);
			return new Point(peca.getX(), peca.getY());
		}
		
		return null;		
	}
}
