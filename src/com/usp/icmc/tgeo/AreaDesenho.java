package com.usp.icmc.tgeo;

import com.usp.icmc.tgeo.listener.GridListener;

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
	
	Context context;
	static AreaDesenho areaDesenho;
	
	@SuppressLint("NewApi")
	public AreaDesenho(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		this.context = context;
		this.setBackgroundColor(Color.WHITE);
		
	}

	public AreaDesenho(Context context, AttributeSet attrs) {

		this(context, null, 0);
		this.context = context;
		this.setBackgroundColor(Color.WHITE);


	}
	

	public AreaDesenho(Context context) {

		this(context, null);
		this.context = context;
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
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
			
        Log.d("bThere", "X: " + (int)event.getX() + " Y: " + (int)event.getY());
        
        
		switch (event.getAction() & MotionEvent.ACTION_MASK) {		
		
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:	
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
	
	public static AreaDesenho getInstance(){
		return areaDesenho;
	}
}
