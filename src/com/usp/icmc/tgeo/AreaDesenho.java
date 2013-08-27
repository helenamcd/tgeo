package com.usp.icmc.tgeo;

import java.util.ArrayList;

<<<<<<< HEAD
import com.usp.icmc.tgeo.bean.PontoBean;
=======
>>>>>>> pr/1
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
import android.widget.ImageView;
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
<<<<<<< HEAD
	private final static int DESFAZER         = 7;
	private final static int REFAZER          = 8;
=======
	private final static int PONTO_MEDIO      = 7;
	private final static int SELECIONAR      = 8;
>>>>>>> pr/1
	
	private int mode;
	
	int hash;
	
	Context context = MyApplication.getAppContext();
	static AreaDesenho areaDesenho;
	private ArrayList<Object> objectsList;
	private ArrayList<Object> touchedObjects;
	
	private ArrayList listaObjetosAtual = new ArrayList();
	private ArrayList listaObjetosArmazenados = new ArrayList();
	
	private ArrayList armazenaPercurso = new ArrayList();
	
	/*
	ImageView seta_esquerda = new ImageView(MyApplication.getAppContext());
	RelativeLayout seta = new RelativeLayout(MyApplication.getAppContext());
	*/

	
	@SuppressLint("NewApi")
	public AreaDesenho(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		createObjectsList();
		createTouchedObjectsList();
	}

	public AreaDesenho(Context context, AttributeSet attrs) {
		this(context, null, 0);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		createObjectsList();
		createTouchedObjectsList();
	}
	

	public AreaDesenho(Context context) {

		this(context, null);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
		//Inicializa listas de objetos e objetos tocados
		createObjectsList();
		createTouchedObjectsList();
	}

	/**
	* Method to set an element
	* @param View view
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public void setObject (View view){
		this.addView(view);
<<<<<<< HEAD
		this.listaObjetosAtual.add(view);
=======
		
		//adiciona o novo objeto (view) a lista
		objectsList.add(view);
	}
	
	private void createTouchedObjectsList(){
		touchedObjects = new ArrayList<Object>();
	}
	
	private void createObjectsList(){
		if(objectsList == null) objectsList = new ArrayList<Object>();
>>>>>>> pr/1
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
			
        //Log.d("bThere", "X: " + (int)event.getX() + " Y: " + (int)event.getY());
        
        int x = (int)event.getX();
        int y = (int)event.getY();
        
<<<<<<< HEAD
        armazenaPercurso(x, y);
        
        
		switch (event.getAction() & MotionEvent.ACTION_MASK) {		
		
			case MotionEvent.ACTION_DOWN:
				if(event.getPointerCount() == 1){					
=======
		switch (event.getAction() & MotionEvent.ACTION_MASK) {		
		
			case MotionEvent.ACTION_DOWN:
				//verifica se algum objeto esta sendo tocado 
				touchingTest(x, y);
				
				if(event.getPointerCount() == 1 && touchedObjects.isEmpty()){
>>>>>>> pr/1
					mode = PONTO;
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				
			case MotionEvent.ACTION_MOVE:
<<<<<<< HEAD
				if (!tocouLateral(x)){
					
					/*seta_esquerda.setImageDrawable(getResources().getDrawable(R.drawable.seta_esquerda));
					seta.addView(seta_esquerda);
					seta.setX(0);
					seta.setY(0);

					addView(seta);*/
=======
				if (mode == SELECIONAR){
					boolean movePm = false;
					PontoMedio pm = null;
					int indexMov = -1;
					for(int i = 0; i < touchedObjects.size(); i++){
						if ((touchedObjects.get(i) instanceof Ponto)){// && !(touchedObjects.get(i) instanceof PontoMedio)){
							Ponto ponto = ((Ponto)touchedObjects.get(i));
							
							//verifica se ha ponto medio dependente do ponto
							for(int j = 0; j < objectsList.size(); j++){
								Object object = objectsList.get(j);
								if(object instanceof PontoMedio){
									pm = (PontoMedio) object;
									if(pm.getPoints().contains(ponto)){
										if(ponto.equals(pm.getPoints().get(0)))
											indexMov = 0;
										else indexMov = 1;
										movePm = true;
									}
								}
								if(ponto.isTouched(x, y) && !(ponto instanceof PontoMedio)) ponto.move(x, y);
								if(movePm == true) pm.move(0, 0);
							}
						}
					}
				}
				if(event.getPointerCount() == 3){
					mode = PONTO_MEDIO;
					
					//testa gesto de criacao do ponto medio
					//if(event.getDownTime() > 2000){
					int touchedPointsNumber = 0;
					float x1, x2, y1, y2;
					ArrayList<Ponto> p = new ArrayList<Ponto>();
					for(int i = 0; i < event.getPointerCount(); i++){
						if (touchingTest(event.getX(i), event.getY(i))){
							int j = 0;
							while(j < objectsList.size() && 
									(!(objectsList.get(j) instanceof Ponto) || 
											!((objectsList.get(j) instanceof Ponto) && 
													((Ponto)objectsList.get(j)).isTouched(event.getX(i), event.getY(i)))))
								j++;
							if(j < objectsList.size() && (objectsList.get(j) instanceof Ponto)){
								touchedPointsNumber++;
								p.add((Ponto)objectsList.get(j));
							}
						}			
					}
					if(touchedPointsNumber == 2 && p.size() == 2){
						PontoMedio.createMidPoint(p.get(0), p.get(1));
					}
>>>>>>> pr/1
				}
				break;
			case MotionEvent.ACTION_UP:	
				if (mode == PONTO){
					Ponto.createPoint(x, y);
				}else if(mode == DESFAZER){
					desfazer();
				}else if (mode == REFAZER){
					refazer();
				}
<<<<<<< HEAD
				break;
			case MotionEvent.ACTION_POINTER_UP:
				
				limparPercurso();
=======
				touchedObjects.clear();
				mode = NULO;
>>>>>>> pr/1
				break;
		}
        
        return true;
    }
	
	protected boolean touchingTest(float x, float y){
		for(int i = 0; i < objectsList.size(); i++){
			Object object = objectsList.get(i);
			if(object instanceof Ponto){
				Ponto ponto = (Ponto) object;
				if(ponto.isTouched(x, y)){
					touchedObjects.add(ponto);
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
	
	private void desfazer(){
		int index = listaObjetosAtual.size()-1;
		if (index != -1){
			View view = (View) listaObjetosAtual.get(index);
			listaObjetosArmazenados.add(view);
			listaObjetosAtual.remove(view);
			this.removeView(view);
		}
		
	}
	
	private void refazer(){
		int index = listaObjetosArmazenados.size()-1;
		if (index != -1){
			View view = (View)listaObjetosArmazenados.get(index);
			listaObjetosArmazenados.remove(view);
			listaObjetosAtual.add(view);
			this.setObject(view);
		}
	}
	
	private boolean tocouLateral(int x){
		
		int index = this.armazenaPercurso.size() -1;
		
		if (index != -1){
			int width= this.getWidth(); 
	        
	        int xPrimeiro = (int) ((PontoBean) this.armazenaPercurso.get(0)).getX();	        
	        int xUltimo = (int) ((PontoBean) this.armazenaPercurso.get(index)).getX();
	    
	        if (xPrimeiro >= 0  && xUltimo <= (width * 0.2) &&  Math.abs(xUltimo - xPrimeiro) >=  (width * 0.2) /2){
				mode = DESFAZER;
				return true;
			}else if (xPrimeiro <= width  && xUltimo >= width - (width * 0.2) &&  Math.abs(xUltimo - xPrimeiro) <= width - (width * 0.2) /2){
				mode = REFAZER;
				return true;
			}
		}
		
		return false;
	}
	
	private void armazenaPercurso(int x, int y){
		PontoBean ponto = new PontoBean(x,y);
		this.armazenaPercurso.add(ponto);
		
	}
	
	private void limparPercurso(){
		this.armazenaPercurso.clear();
	}
	
	
	
}
