package com.usp.icmc.tgeo.og;

import java.util.ArrayList;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.Principal;
import com.usp.icmc.tgeo.support.MyApplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/** Mid-point class
* @author Helena Macedo Reis
* @version 0.1
*/

public class PontoMedio extends Ponto{
	Ponto point1, point2;
	
	public PontoMedio(Ponto point1, Ponto point2) {
		super(MyApplication.getAppContext(), null, (point1.getPointX()+point2.getPointX())/2, (point1.getPointY()+point2.getPointY())/2);
		this.setColor(Color.GREEN);
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public static PontoMedio createMidPoint(Ponto ponto1, Ponto ponto2){
		PontoMedio ponto = new PontoMedio(ponto1, ponto2);
		
		Ponto.areaDesenho = AreaDesenho.getInstance();	
		Ponto.areaDesenho.removeObject(ponto);
		Ponto.areaDesenho.setObject(ponto);
		return ponto;
	}
    
    public void move(int x, int y){
        this.setX((point1.getPointX()+point2.getPointX())/2);
        this.setY((point1.getPointY()+point2.getPointY())/2);
        this.invalidate();
    }
    
    public ArrayList<Ponto> getPoints(){
    	ArrayList<Ponto> pontos = new ArrayList<Ponto>();
    	pontos.add(point1);
    	pontos.add(point2);
    	return pontos;
    }
}
