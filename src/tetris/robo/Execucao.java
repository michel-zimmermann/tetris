package tetris.robo;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

import tetris.imagens.TipoImagem;
import tetris.main.Logger;
import tetris.params.Params;

public class Execucao {

	private final long TEMPO_ENTRE_ACAO;
	
	private final Logger log;
	
	private final BuscaImagem buscaInventarioVazio;
	
	private final BuscaImagem buscaSemTentativa;
	
	private final BuscaImagem buscaBauInventario;

	private ControladorMontagem controlador;
	
	public Execucao(Logger log) {
		this.log = log;
		controlador  = new ControladorMontagem();
		
		TEMPO_ENTRE_ACAO = Params.getLong(Params.PRINCIPAL_DELAY);
		
		buscaInventarioVazio = new BuscaImagem(
				new Rectangle(760, 530, 32, 32), 
				TipoImagem.INVENTARIO_VAZIO);
		buscaInventarioVazio.setMoverMouse(false);
		
		buscaSemTentativa = new BuscaImagem(
				new Rectangle(235, 146, 26, 26), 
				TipoImagem.SEM_TENTATIVA);
		buscaSemTentativa.setMoverMouse(false);
		
		buscaBauInventario = new BuscaImagem(
				new Rectangle(630, 270, 165, 300),
				TipoImagem.BAU);
	}
	
	public void run() {
		while (buscaInventarioVazio.buscar()) {
			log.log("Inventario vazio, prosseguindo.");
			montarTetris();
			Robo.sleep(TEMPO_ENTRE_ACAO);
		}
		log.debug("Encheu inventario, fim da execucao.");
	}
	
	private void montarTetris() {
		log.log("Iniciando montagem do tetris.");

		controlador.limpar();
		while (controlador.continuar()) {
			verificarSeTemBauParaTentativa();
			pegarPeca();
			tratarPeca();
		}
		
		log.log("Completou tetris!");
		Robo.sleep(TEMPO_ENTRE_ACAO * 3);
		Robo.moverMouse(400, 352);
		Robo.sleep(TEMPO_ENTRE_ACAO);
		Robo.clicarMouse(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private void verificarSeTemBauParaTentativa() {
		Robo.sleep(TEMPO_ENTRE_ACAO);
		if (buscaSemTentativa.buscar()) {
			log.log("Nao ha mais bau para tentativas, busca no inventario.");
			Robo.sleep(TEMPO_ENTRE_ACAO);
			if (!buscaBauInventario.buscar()) {
				log.log("Acabaram-se os baus. Fim da execucao");
				System.exit(0);
			}
			
			Robo.sleep(TEMPO_ENTRE_ACAO);
			Robo.dragAndDrop(InputEvent.BUTTON1_DOWN_MASK, 255, 160);
			Robo.sleep(TEMPO_ENTRE_ACAO);
		}
	}

	private void pegarPeca() {
		log.log("Pegando peca para montagem.");
		Robo.moverMouse(255, 160);
		Robo.sleep(TEMPO_ENTRE_ACAO);
		Robo.clicarMouse(InputEvent.BUTTON1_DOWN_MASK);
		
		clicarSimConfirmar();
	}

	private void clicarSimConfirmar() {
		Robo.sleep(TEMPO_ENTRE_ACAO);
		Robo.moverMouse(360, 350);
		Robo.sleep(TEMPO_ENTRE_ACAO);
		Robo.clicarMouse(InputEvent.BUTTON1_DOWN_MASK);
		Robo.sleep(TEMPO_ENTRE_ACAO);
	}
	
	private void tratarPeca() {
		PointerInfo info = MouseInfo.getPointerInfo();
		Point localDoMouse = info.getLocation();
		
		BufferedImage cor = Robo.print(new Rectangle(localDoMouse.x, localDoMouse.y, 1, 1));
		int rgb = cor.getRGB(0, 0);

		TipoPeca peca = TipoPeca.getPeca(rgb);
		if (peca != null) {
			Point localDaPeca = controlador.getLocalDaPeca(peca);
			if (localDaPeca != null) {
				log.debug("Encontrou peca, utilizavel " + peca.name());
				Robo.sleep(TEMPO_ENTRE_ACAO);
				Robo.moverMouse(localDaPeca.x, localDaPeca.y);
				Robo.sleep(TEMPO_ENTRE_ACAO);
				Robo.clicarMouse(InputEvent.BUTTON1_DOWN_MASK);
				clicarSimConfirmar();
			} else {
				log.debug("Encontrou peca, mas nao utilizavel, descartando");
				descartarPeca();
			}
		} else {
			log.debug("Tipo de peca nao detectado! " + rgb);
		}
	}

	private void descartarPeca() {
		Robo.sleep(TEMPO_ENTRE_ACAO);
		Robo.clicarMouse(InputEvent.BUTTON3_DOWN_MASK);
		clicarSimConfirmar();
	}
	
}
