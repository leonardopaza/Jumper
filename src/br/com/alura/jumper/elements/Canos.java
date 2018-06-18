package br.com.alura.jumper.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;
import android.graphics.Canvas;
import br.com.alura.jumper.engine.Som;
import br.com.alura.jumper.graphic.Tela;

public class Canos {
	
	private static final int QUANTIDADE_DE_CANOS = 10;
	private static final int DISTANCIA_ENTRE_CANOS = 150;
	private final List<Cano> canos = new ArrayList<Cano>();
	private Tela tela;
	private Pontuacao pontuacao;
	private Context context;
	
	private Som som;
	
	public Canos(Tela tela, Pontuacao pontuacao, Context context) {
		this.tela = tela;
		this.pontuacao = pontuacao;
		this.context = context;
		int posicao = 1000;
		
		som = new Som(context);
		
		for (int i = 0; i < QUANTIDADE_DE_CANOS; i++) {
			posicao += DISTANCIA_ENTRE_CANOS;
			Cano cano = new Cano(tela, posicao, context); 
			canos.add(cano);
		}
	}
	
	public void desenhaNo(Canvas canvas) {
		for (Cano cano : canos) {
			cano.desenhaNo(canvas);
		}
	}
	
	public void move() {
		ListIterator<Cano> iterator = canos.listIterator();
		while(iterator.hasNext()) {
			Cano cano = (Cano) iterator.next();
			cano.move(pontuacao);
			
			if(cano.saiuDaTela()) {
				pontuacao.aumenta();
				som.toca(Som.PONTOS);
				iterator.remove();
				Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
				iterator.add(outroCano);
			}
		}
	}
	
	private int getMaximo() {
		int maximo = 0;
		for (Cano cano : canos) {
			maximo = Math.max(cano.getPosicao(), maximo);
		}
		return maximo;
	}

	public boolean temColisaoCom(Passaro passaro) {
		for (Cano cano : canos) {
			if(cano.temColisaoHorizontalCom(passaro)
					&& cano.temColisaoVerticalCom(passaro)) {
				return true;
			}
		}
		return false;
	}
}