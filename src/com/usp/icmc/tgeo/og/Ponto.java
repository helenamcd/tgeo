package com.usp.icmc.tgeo.og;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.Principal;
import com.usp.icmc.tgeo.support.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/** Point class
* @author Helena Macedo Reis
* @version 0.1
*/
public class Ponto extends View{
	static AreaDesenho areaDesenho;
	private float x = 0 , y = 0, r = 10;
	private int color;
	
	public Ponto(Context context, AttributeSet attrs, float x, float y) {
        super(context, attrs);
   	 	this.x = x;
   	 	this.y = y;  
   	 	this.color = Color.BLACK;
	}
	
	public static void createPoint(int x, int y){
		
		Ponto ponto = new Ponto(MyApplication.getAppContext(), null, x, y);	

		areaDesenho = AreaDesenho.getInstance();	
		areaDesenho.removeObject(ponto);
		areaDesenho.setObject(ponto);
	}
	
	
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawCircle(this.x, this.y, this.r, paint);
    }
    
    public boolean isTouched(float x, float y)
    {
    	int nr = (int) (r + 30);
    	if((x <= (this.x + (nr))) && (x >= (this.x - (nr))))
    	{
    		if((y <= (this.y + (nr))) && (y >= (this.y - (nr))))
    		{
    			
    			return true;
    		}
    	}
    	return false;
    }
    
    public void move(float x, float y){
    	setX(x);
    	setY(y);
    	this.invalidate();
    }
    
    public float getPointX(){
    	return this.x;
    }
    
    public float getPointY(){
    	return this.y;
    }
    
    public float getR(){
    	return this.r;
    }
    
    public int getColor(){
    	return this.color;
    }
    
    public void setX(float x){
    	if(x > 0) this.x = x;
    }
    
    public void setY(float y){
    	if(y > 0) this.y = y;
    }
    
    public void setR(float r){
    	if(r > 0) this.r = r;
    }
    
    public void setColor(int color){
    	this.color = color;
    }
}
