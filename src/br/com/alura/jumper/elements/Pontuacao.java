package br.com.alura.jumper.elements;

import br.com.alura.jumper.graphics.Cores;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Pontuacao {

	private static final Paint Branco = Cores.getCorDaPontuacao();
	public int pontos = 0;

	public void desenhaNo(Canvas canvas) {		
		canvas.drawText(String.valueOf(pontos), 100, 100, Branco);
	}

	public void aumenta() {	
		pontos++;
	}
}