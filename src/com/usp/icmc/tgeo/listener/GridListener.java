package com.usp.icmc.tgeo.listener;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/** Class of Grid
* @author Helena Macedo Reis
* @version 0.1
*/
public class GridListener extends View{
	
	Context context;
	AreaDesenho areaDesenho;
	
	public GridListener(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		
	}

	/**
	* Method to set grid view to the working area
	* @param GridListener gl
	* @author Helena Macedo
	* @version 0.0.1
	*/
	public void criarGrid(GridListener gl){
		areaDesenho = AreaDesenho.getInstance();		
		areaDesenho.setObject(gl);
				
	}
	
	
	@Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        //canvas.drawCircle(20, 30, 50, paint);
        canvas.drawRect(20, 30, 50, 50, paint);
    }

}
