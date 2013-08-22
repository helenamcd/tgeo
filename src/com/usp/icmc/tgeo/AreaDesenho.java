package com.usp.icmc.tgeo;

import com.usp.icmc.tgeo.listener.GridListener;
import com.usp.icmc.tgeo.og.Ponto;
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
	
	private int mode;	
	
	Context context = MyApplication.getAppContext();
	static AreaDesenho areaDesenho;
	
	@SuppressLint("NewApi")
	public AreaDesenho(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);
		
	}

	public AreaDesenho(Context context, AttributeSet attrs) {
		this(context, null, 0);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);


	}
	

	public AreaDesenho(Context context) {

		this(context, null);
		
		//Cor da area de desenho
		this.setBackgroundColor(Color.WHITE);


	}

	/**
	* Method to set an element
	* @param View view
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public void setObject (View view){
		this.addView(view);
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
				if(event.getPointerCount() == 1){
					mode = PONTO;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:	
				if (mode == PONTO){
					Ponto.createPoint(x, y);
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				break;
		}

        
        
        return true;
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
}
