package com.usp.icmc.tgeo.listener;

import com.usp.icmc.tgeo.AreaDesenho;
import com.usp.icmc.tgeo.Principal;

/** Class of New working area
* @author Helena Macedo Reis
* @version 0.1
*/
public class CleanAllListener {

	static AreaDesenho areaDesenho;
	private static CleanAllListener clean = new CleanAllListener();
	
	public static CleanAllListener getInstance(){
		return clean;
	}
	
	public static void cleanScreen(){		
		areaDesenho = AreaDesenho.getInstance();	
		areaDesenho.limparTela();
		
		Principal principal = Principal.getInstance();
		principal.collapseMenu();
	}

}
