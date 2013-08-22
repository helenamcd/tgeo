package com.usp.icmc.tgeo.gui;

import com.usp.icmc.tgeo.R;
import com.usp.icmc.tgeo.listener.GridListener;
import com.usp.icmc.tgeo.support.MyApplication;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.view.View;


public class GridPickerDialog extends Dialog implements View.OnClickListener
{
	private static final int DEFAULT_VALUE = 40;

	private int mMinValue    = DEFAULT_VALUE-1;
	private int mValue       = DEFAULT_VALUE;
	private int mMaxValue    = DEFAULT_VALUE +1;

	private Context context = MyApplication.getAppContext();
	
	TextView anterior;
	TextView atual;
	TextView proximo;

	public GridPickerDialog(Context context)
	{
		this(context, null);
	}

	public GridPickerDialog(Context context, AttributeSet attrs)
	{
		super(context);
				
		setContentView(R.layout.grid_pickerdialog_layout);
		
		setTitle("Teste");
		
		anterior = (TextView) findViewById(R.id.anterior);
		anterior.setText(String.valueOf(mMinValue));
		
		atual = (TextView) findViewById(R.id.atual);
		atual.setText(String.valueOf(mValue));
		
		proximo = (TextView) findViewById(R.id.proximo);
		proximo.setText(String.valueOf(mMaxValue));
		
		Button btn_anterior = (Button) findViewById(R.id.btn_anterior);
		btn_anterior.setOnClickListener(this);
		
		Button btn_proximo = (Button) findViewById(R.id.btn_proximo);
		btn_proximo.setOnClickListener(this);	
		
		
		TextView btn_ok = (TextView) findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(this);		

		
		TextView btn_cancelar = (TextView) findViewById(R.id.btn_cancelar);
		btn_cancelar.setOnClickListener(this);
        
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		
	    switch (id) {
		    case R.id.btn_anterior:
		    	
		    	mMinValue = mMinValue - 1;
		    	mValue    = mValue -1;
		    	mMaxValue = mMaxValue -1;
		    	
				anterior.setText(String.valueOf(mMinValue));
				atual.setText(String.valueOf(mValue));
				proximo.setText(String.valueOf(mMaxValue));
		        
		        break;
		    case R.id.btn_proximo:
		    	mMinValue = mMinValue + 1;
		    	mValue    = mValue +1;
		    	mMaxValue = mMaxValue +1;
		    	
				anterior.setText(String.valueOf(mMinValue));
				atual.setText(String.valueOf(mValue));
				proximo.setText(String.valueOf(mMaxValue));	        
				break;
		    case R.id.btn_ok:
		    	GridListener gl = GridListener.getInstance();
		    	gl.setSize(mValue);
	        	gl.createGrid();
	        	this.dismiss();
		    	break;
		    case R.id.btn_cancelar:
		    	this.dismiss();
		    	break;
	    
	    }
	    
	    anterior.invalidate();
	    atual.invalidate();
	    proximo.invalidate();		
	}

	
}
