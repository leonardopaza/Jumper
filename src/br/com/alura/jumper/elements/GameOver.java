package br.com.alura.jumper.elements;

import br.com.alura.jumper.graphic.Tela;
import br.com.alura.jumper.graphics.Cores;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameOver {
	
	private static final Paint vermelho = Cores.getCorDoGameOver();
	private Tela tela;
	
	public GameOver(Tela tela) {
		this.tela = tela;
	}
	
	public void desenhaNo(Canvas canvas) {
		String texto = "Game Over";
		int centroDoTexto = centralizaTexto(texto);
		canvas.drawText(texto, centroDoTexto, tela.getAltura()/2, vermelho);
	}
	
	public int centralizaTexto(String texto) {
		Rect limiteDoTexto = new Rect();
		vermelho.getTextBounds(texto, 0, texto.length(), limiteDoTexto);
		int centroHorizontal = tela.getLargura()/2 - (limiteDoTexto.right - limiteDoTexto.left)/2;
		return centroHorizontal;
	}
}
