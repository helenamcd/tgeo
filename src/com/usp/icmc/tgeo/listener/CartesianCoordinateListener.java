package com.usp.icmc.tgeo.listener;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.Principal;
import com.usp.icmc.tgeo.support.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/** Class of Cartesian Coordinate System
* @author Helena Macedo Reis
* @version 0.1
*/
public class CartesianCoordinateListener extends View {
	
	static AreaDesenho areaDesenho;
	private static CartesianCoordinateListener cc = new CartesianCoordinateListener(MyApplication.getAppContext(),null);


	public CartesianCoordinateListener(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	public static CartesianCoordinateListener getInstance(){
		return cc;
	}
	
	/**
	* Method to set create a Cartesian Coordinate System
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public static void createCartesian(){		
		areaDesenho = AreaDesenho.getInstance();	
		areaDesenho.removeObject(cc);
		
		if (GridListener.getInstance() != null){
			areaDesenho.removeObject(GridListener.getInstance());
		}
		
		areaDesenho.setObject(cc);
				
		Principal principal = Principal.getInstance();
		principal.collapseMenu();
		
	}
	
	@Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	Paint paint = new Paint();
    	Paint paint1 = new Paint();

        
        int height=this.getHeight();
        int width= this.getWidth();  
        
        int size = 60;        

        for (int x = 0; x < width; x+=size){
        	if (x - (size/2) == width/2 || x == width/2){
        		
                paint.setColor(Color.BLACK);
            	canvas.drawLine(x, 0, x, height, paint);
                TextView textX = new TextView(MyApplication.getAppContext());
                textX.setText("x");
                textX.setX(size);
                textX.setY(x-size);

        	}else{
                paint.setColor(Color.LTGRAY);
            	canvas.drawLine(x, 0, x, height, paint);
            	
            	paint1.setColor(Color.BLACK);
            	canvas.drawLine(x, height/2 + size/2, x, height/2 + size, paint1);

        	}
        	
        }
        
        
        for (int y = 0; y < height; y+=size){
        	if (y - (size/2) == height/2 || y == height/2){
        		        		
                paint.setColor(Color.BLACK);
                canvas.drawLine(0, y, width, y, paint);
                TextView textX = new TextView(MyApplication.getAppContext());
                textX.setText("y");
                textX.setX(size);
                textX.setY(y-size);
                
            	//paint1.setColor(Color.BLACK);
            	//canvas.drawLine(0, y, 10, y, paint1);

                
        	}else{
        		paint.setColor(Color.LTGRAY);
        		canvas.drawLine(0, y, width, y, paint);
        		
        		paint1.setColor(Color.BLACK);
            	canvas.drawLine(width/2, y, width/2 - size/2, y, paint1);

        	}
        }
        
    }
}
