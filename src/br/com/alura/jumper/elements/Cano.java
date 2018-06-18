package br.com.alura.jumper.elements;

import br.com.alura.jumper.R;
import br.com.alura.jumper.graphic.Tela;
import br.com.alura.jumper.graphics.Cores;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Cano {
	
	private Tela tela;
	private int alturaDoCanoInferior;
	private int posicao;
	private int alturaDoCanoSuperior;
	private Bitmap canoInferior;
	private Bitmap canoSuperior;
	private static final int TAMANHO_DO_CANO = 550;
	private static final int LARGURA_DO_CANO = 80;
	private static final Paint VERDE = Cores.getCorDoCano();
	
	public Cano(Tela tela, int posicao, Context context) {
		this.tela = tela;
		this.posicao = posicao;
		alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
		alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
		Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
		canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);
		canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
	}
	
	private int valorAleatorio() {
		return (int)(Math.random() * 225);
	}
	
	public void desenhaNo(Canvas canvas) {
		desenhaCanoSuperiorNo(canvas);
		desenhaCanoInferiorNo(canvas);
	}
	
	private void desenhaCanoSuperiorNo(Canvas canvas) {
//		canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, VERDE);
		canvas.drawBitmap(canoSuperior, posicao, 0, null);
	}
	
	private void desenhaCanoInferiorNo(Canvas canvas) {
//		canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE);
		canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
	}
	
	public void move(Pontuacao pontuacao) {
		this.posicao -= 5;
		
//		if (pontuacao.pontos > 10) {
//			this.posicao -= 5;
//		}
	}
	
	public boolean saiuDaTela() {
		return posicao + LARGURA_DO_CANO < 0;
	}
	
	public int getPosicao() {
		return posicao;
	}
	
	public boolean temColisaoHorizontalCom(Passaro passaro) {
		return this.posicao < passaro.X + passaro.RAIO;
	}
	
	public boolean temColisaoVerticalCom(Passaro passaro) {
		return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior
				|| passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
	}
}