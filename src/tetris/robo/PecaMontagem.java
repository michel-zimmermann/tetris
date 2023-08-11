package tetris.robo;

import java.util.Objects;

public class PecaMontagem {

	private final TipoPeca tipoPeca;
	private final int x;
	private final int y;

	public PecaMontagem(TipoPeca tipoPeca, int x, int y) {
		this.tipoPeca = tipoPeca;
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipoPeca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PecaMontagem other = (PecaMontagem) obj;
		return tipoPeca == other.tipoPeca;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
