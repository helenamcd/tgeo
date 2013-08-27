package com.usp.icmc.tgeo;

import java.util.ArrayList;

import com.usp.icmc.tgeo.listener.GridListener;
import com.usp.icmc.tgeo.og.*;
import com.usp.icmc.tgeo.support.MyApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/** Class of working area
* @author Helena Macedo Reis e Darlan Santana Farias
* @version 0.1
*/
public class AreaDesenho extends RelativeLayout{
	
	private final static int NULO             = 0;
	private final static int SGRETA           = 1;
	private final static int RETA             = 2;
	private final static int SMRETA           = 3;	
	private final static int PONTO            = 4;
	private final static int CIRCUNFERENCIA   = 5;
	private final static int LIMPAR           = 6;
	private final static int PONTO_MEDIO      = 7;
	private final static int SELECIONAR      = 8;
	
	private int mode;
	
	Context context = MyApplication.getAppContext();
	static AreaDesenho areaDesenho;
	private ArrayList<Object> listaObjetosAtual;
	private ArrayList<Object> objetosSelecionados;
	
	@SuppressLint("NewApi")
	public AreaDesenho(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		criarListaObjetosAtual();
		criarListaObjetosSelecionados();
	}

	public AreaDesenho(Context context, AttributeSet attrs) {
		this(context, null, 0);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		criarListaObjetosAtual();
		criarListaObjetosSelecionados();
	}
	

	public AreaDesenho(Context context) {
		this(context, null);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		criarListaObjetosAtual();
		criarListaObjetosSelecionados();
	}

	/**
	* Method to set an element
	* @param View view
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public void setObject (View view){
		this.addView(view);
		
		//adiciona o novo objeto (view) a lista
		listaObjetosAtual.add(view);
	}
	
	private void criarListaObjetosSelecionados(){
		this.objetosSelecionados = new ArrayList<Object>();
	}
	
	private void criarListaObjetosAtual(){
		if(listaObjetosAtual == null) listaObjetosAtual = new ArrayList<Object>();
	}
	
	/**
	* Method to remove an element
	* @param View view
	* @author Helena Macedo
	* @version 0.0.1
	*/	
	public void removeObject(View view){
		this.removeView(view);
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
			
        Log.d("bThere", "X: " + (int)event.getX() + " Y: " + (int)event.getY());
        
        int x = (int)event.getX();
        int y = (int)event.getY();
        
		switch (event.getAction() & MotionEvent.ACTION_MASK) {		
		
			case MotionEvent.ACTION_DOWN:
				//verifica se algum objeto esta sendo tocado 
				touchingTest(x, y);
				
				// se tem apenas um dedo tocando a tela e nenhum objeto selecionado, muda para o modo ponto
				if(event.getPointerCount() == 1 && this.objetosSelecionados.isEmpty()){
					mode = PONTO;
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				
			case MotionEvent.ACTION_MOVE:
				//se estiver no modo SELECIONAR
				if (mode == SELECIONAR){
					boolean movePm = false; //diz se precisa mover ponto-medio
					PontoMedio pm = null; // referencia para o ponto-medio a ser movido
					//int indexMov = -1; // indice do ponto (da lista de pontos relacionados ao ponto-medio) cuja posicao foi alterada
					
					//percorre a lista de objetos selecionados
					for(int i = 0; i < this.objetosSelecionados.size(); i++){
						
						//se o objeto selecionado for da classe ponto
						if ((this.objetosSelecionados.get(i) instanceof Ponto)){
							Ponto ponto = ((Ponto)this.objetosSelecionados.get(i));
							
							//verifica se ha pontos medios dependentes do ponto
							for(int j = 0; j < listaObjetosAtual.size(); j++){
								Object object = listaObjetosAtual.get(j);
								if(object instanceof PontoMedio){
									pm = (PontoMedio) object;
									//verifica se o ponto cuja posicao foi alterada eh um dos pontos que define esse ponto medio
									if(pm.getPoints().contains(ponto)){
										movePm = true;
									}
								}
								//confere se o esta realmente sendo tocado e nao eh da classe PontoMedio
								// PontoMedio nao pode ser movido, a nao ser movendo um dos pontos que lhe deu origem
								if(ponto.isTouched(x, y) && !(ponto instanceof PontoMedio)) ponto.move(x, y);
								
								//move ponto-medio se um dos seus pontos foi deslocado
								if(movePm == true) pm.move(0, 0);
							}
						}
					}
				}
				if(event.getPointerCount() == 3){
					mode = PONTO_MEDIO;
					
					//testa gesto de criacao do ponto medio
					//if(event.getDownTime() > 2000){
					int touchedPointsNumber = 0; //guarda numero de pontos tocados 
					ArrayList<Ponto> p = new ArrayList<Ponto>(); //guarda os pontos selecionados
					
					//percorre os pontos tocados pelos dedos para encontrar os objetos da classe Ponto selecionados
					for(int i = 0; i < event.getPointerCount(); i++){
						//verifica se o dedo analisado estah tocando um ponto
						if (touchingTest(event.getX(i), event.getY(i))){
							int j = 0;
							//percorre a lista de objetos na tela ateh encontrar o ponto que esta sendo tocado
							while(j < listaObjetosAtual.size() && 
									(!(listaObjetosAtual.get(j) instanceof Ponto) || 
											!((listaObjetosAtual.get(j) instanceof Ponto) && 
													((Ponto)listaObjetosAtual.get(j)).isTouched(event.getX(i), event.getY(i)))))
								j++;//incrementa para encontrar o indice do ponto na lista de objetos
							//se o indice eh valido e o objeto eh um Ponto
							if(j < listaObjetosAtual.size() && (listaObjetosAtual.get(j) instanceof Ponto)){
								touchedPointsNumber++;
								p.add((Ponto)listaObjetosAtual.get(j));//adiciona o ponto tocado ao array
							}
						}			
					}
					//se ha dois objetos Ponto sendo tocados, cria ponto medio deles
					if(touchedPointsNumber == 2 && p.size() == 2){
						PontoMedio.createMidPoint(p.get(0), p.get(1));
					}
				}
				break;
			case MotionEvent.ACTION_UP:	
				//se estiver no modo PONTO, cria o ponto ao tirar o dedo
				if (mode == PONTO){
					Ponto.createPoint(x, y);
				}
				
				//limpa a lista de objetos tocados e altera para o modo NULO (neutro)
				this.objetosSelecionados.clear();
				mode = NULO;
				break;
		}
        
        return true;
    }
	
	protected boolean touchingTest(float x, float y){
		//percorre a lista de objetos verificando se algum deles esta sendo tocado
		for(int i = 0; i < listaObjetosAtual.size(); i++){
			Object object = listaObjetosAtual.get(i);
			/* se for da classe Ponto (generalizar posteriormente pode ser melhor) e estiver sendo tocado,
			muda para o modo selecioar e retorna verdadeiro*/
			if(object instanceof Ponto){
				Ponto ponto = (Ponto) object;
				if(ponto.isTouched(x, y)){
					this.objetosSelecionados.add(ponto);
					if(mode != PONTO_MEDIO) mode = SELECIONAR;
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        for(int i = 0 ; i < getChildCount() ; i++){
            getChildAt(i).layout(l, t, r, b);
        }
    }	
	
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		areaDesenho = this;		
		
	}

	/**
	* Method get the currenct instance
	* @return AreaDesenho
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public static AreaDesenho getInstance(){
		return areaDesenho;
	}
	
	public ArrayList<Object> pegarListaObjetosAtual(){
		return this.listaObjetosAtual;
	}
	
	public ArrayList<Object> pegarObjetosSelecionados(){
		return this.objetosSelecionados;
	}
}
