package com.usp.icmc.tgeo;

import com.usp.icmc.tgeo.listener.GridListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class AreaDesenho extends RelativeLayout{
	
	@SuppressLint("NewApi")
	public AreaDesenho(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		this.setBackgroundColor(Color.WHITE);
		
	}

	public AreaDesenho(Context context, AttributeSet attrs) {

		this(context, null, 0);
		this.setBackgroundColor(Color.WHITE);


	}

	public AreaDesenho(Context context) {

		this(context, null);
		this.setBackgroundColor(Color.WHITE);


	}
	
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

        
        
        return true;//super.onTouchEvent(event);
    }
}
