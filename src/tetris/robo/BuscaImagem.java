package tetris.robo;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import tetris.imagens.Imagens;
import tetris.imagens.TipoImagem;
import tetris.params.Params;

public class BuscaImagem {
	
	private boolean mouseMove;
	private Rectangle areaBusca;
	private List<BufferedImage> subimagens;
	
	public BuscaImagem(Rectangle areaBusca, TipoImagem ... tipos) {
		this.areaBusca = areaBusca;
		this.mouseMove = true;	
		
		subimagens = new ArrayList<>();
		Arrays.asList(tipos).forEach(tp -> subimagens.add(Imagens.get(tp)));
	}

	public void setMoverMouse(boolean mouseMove) {
		this.mouseMove = mouseMove;
	}
	
	public boolean buscar() {
    	try {
			BufferedImage image = Robo.print(areaBusca);
			gravarPrint(image);
			
			for (BufferedImage subimagem : subimagens) {
	    		Point point = verificarImagem(subimagem, image);
	    		if (point != null) {
	    			if (mouseMove) {
	    				Robo.moverMouse(point.x, point.y);
	    			}
	    			return true;
	    		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
    }

	private void gravarPrint(BufferedImage image) {
		if (Params.getBoolean(Params.PRINCIPAL_PRINT)) {
			try {
				String path = Params.getString(Params.PRINCIPAL_PRINT_PATH);
				if (!path.endsWith(File.separator)) {
					path += File.separator;
				}
				long current = System.currentTimeMillis();
				File outputfile = new File(path + current + ".png");
				ImageIO.write(image, "png", outputfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
    private Point verificarImagem(BufferedImage subimage, BufferedImage image) {
        // brute force N^2 check all places in the image
        for (int i = 0; i <= image.getWidth() - subimage.getWidth(); i++) {
            check_subimage:
            for (int j = 0; j <= image.getHeight() - subimage.getHeight(); j++) {
                for (int ii = 0; ii < subimage.getWidth(); ii++) {
                    for (int jj = 0; jj < subimage.getHeight(); jj++) {
                        if (subimage.getRGB(ii, jj) != image.getRGB(i + ii, j + jj)) {
                            continue check_subimage;
                        }
                    }
                }
                // if here, all pixels matched
                return new Point(i + areaBusca.x, j + areaBusca.y);
            }
        }
        return null;
    }


}
