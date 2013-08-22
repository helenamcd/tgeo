package com.usp.icmc.tgeo.listener;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.Principal;
import com.usp.icmc.tgeo.R;
import com.usp.icmc.tgeo.gui.CollapseAnimation;
import com.usp.icmc.tgeo.support.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/** Class of Grid
* @author Helena Macedo Reis
* @version 0.1
*/
public class GridListener extends View{
	
	static AreaDesenho areaDesenho;
	int size = 40;
	private static GridListener gl = new GridListener(MyApplication.getAppContext(),null);
	
	public GridListener(Context context, AttributeSet attrs) {
		super(context, attrs);		
	}
	
	public static GridListener getInstance(){
		return gl;
	}

	/**
	* Method to set grid view to the working area
	* @param GridListener gl
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public static void createGrid(){		
		areaDesenho = AreaDesenho.getInstance();	
		areaDesenho.removeObject(gl);
		
		if (CartesianCoordinateListener.getInstance() != null){
			areaDesenho.removeObject(CartesianCoordinateListener.getInstance());
		}
		
		areaDesenho.setObject(gl);
		
		Principal principal = Principal.getInstance();
		principal.collapseMenu();
	}
	
	
	public void setSize(int size){
		this.size = size;
	}
	
	
	@Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Style.STROKE);
        
        int height=this.getHeight();
        int width= this.getWidth();    
        
        for (int x = 0; x < width; x+=size){
        	canvas.drawLine(x, 0, x, height, paint);
        }
        
        
        for (int y = 0; y < height; y+=size){
        	canvas.drawLine(0, y, width, y, paint);
        }
        
        
    }

}
