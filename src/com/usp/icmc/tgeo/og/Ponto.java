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
	
	public Ponto(Context context, AttributeSet attrs, int x, int y) {
        super(context, attrs);
        
   	 	this.x = x;
   	 	this.y = y;  


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
        paint.setColor(Color.BLACK);
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
}
