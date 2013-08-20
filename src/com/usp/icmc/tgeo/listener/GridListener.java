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
import android.widget.RelativeLayout;

public class GridListener extends View{
	
	Context context;
	
	public GridListener(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		
	}

	public void criarGrid(GridListener gl){
		        
		//LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View inflatedLayout = inflater.inflate ( R.layout.activity_layer_stack, null );
        
        /*
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative_area);
        final View inflatedLayout = LayoutInflater.from(getContext()).inflate(R.layout.activity_layer_stack, relative);
        
		AreaDesenho areaDesenho = (AreaDesenho) inflatedLayout.findViewById(R.id.area_desenho);	
		
		System.out.println("areaDesenho: " + areaDesenho);*/
		
		
		LayoutInflater inflater = LayoutInflater.from(context);
	    inflater.inflate(R.layout.activity_layer_stack, null, true);

	    AreaDesenho areaDesenho = (AreaDesenho)findViewById(R.id.area_desenho);
		
	    System.out.println("areaDesenho: " + areaDesenho);
		
		areaDesenho.setObject(gl);

				
	}
	
	
	@Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(20, 30, 50, paint);
    }

}
