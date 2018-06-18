package br.com.alura.jumper.engine;

import br.com.alura.jumper.R;
import br.com.alura.jumper.elements.Canos;
import br.com.alura.jumper.elements.GameOver;
import br.com.alura.jumper.elements.Passaro;
import br.com.alura.jumper.elements.Pontuacao;
import br.com.alura.jumper.graphic.Tela;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class Game extends SurfaceView implements Runnable, OnTouchListener{
	
	private boolean isRunning = true;
	private SurfaceHolder holder = getHolder();
	private Passaro passaro;
	private Bitmap background;
	private Tela tela;
	private Canos canos;
	private Pontuacao pontuacao;
	private Context context;
	private Som som;
	
	public Game(Context context) {
		super(context);
		this.context = context;
		
		tela = new Tela(context);
		som = new Som(context);
		
		inicializaElementos();
		setOnTouchListener(this);
	}
	
	private void inicializaElementos() {
		passaro = new Passaro(tela, context, som);
		pontuacao = new Pontuacao();
		canos = new Canos(tela, pontuacao, context);
		Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
	}
	
	@Override
	public void run() {
		while(isRunning) {
			if(!holder.getSurface().isValid()) continue;
			Canvas canvas = holder.lockCanvas();
			
			//desenho dos componentes do jogo
			canvas.drawBitmap(background, 0, 0, null);
			passaro.desenhaNo(canvas);
			passaro.cai();
			
			canos.desenhaNo(canvas);
			canos.move();
			
			pontuacao.desenhaNo(canvas);
			
			if(new VerificadorDeColisao(passaro, canos).temColisao()) {
				som.toca(Som.COLISAO);
				new GameOver(tela).desenhaNo(canvas);
				isRunning = false;
			}
			
			holder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void inicia() {
		isRunning = true;
	}
	
	public void pausa() {
		isRunning = false;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		passaro.pula();
		return false;
	}
}