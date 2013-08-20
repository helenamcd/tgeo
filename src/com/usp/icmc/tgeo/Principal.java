


package com.usp.icmc.tgeo;

import java.util.ArrayList;

import com.usp.icmc.tgeo.gui.AdapterListView;
import com.usp.icmc.tgeo.gui.CollapseAnimation;
import com.usp.icmc.tgeo.gui.ExpandAnimation;
import com.usp.icmc.tgeo.gui.ItemListView;
import com.usp.icmc.tgeo.listener.GridListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;



/** Main Class of application
* @author Helena Macedo Reis
* @version 0.1
*/
public class Principal extends Activity implements OnItemClickListener{
	
	//Declare
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private RelativeLayout menuPanel;
	private int panelWidth;
	
    private ListView listView;
    private AdapterListView adapterListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layer_stack);

		createMenu(0.40f);
		
		
	}	
	
	
	/**
	* Method to create the side menu
	* @param final float tamanho
	* @author Helena Macedo
	* @version 0.0.1
	* @see createListView();
	*/
	private void createMenu(final float width){		
		
		FrameLayout.LayoutParams menuPanelParameters;
		FrameLayout.LayoutParams slidingPanelParameters;
		LinearLayout.LayoutParams headerPanelParameters;
		
		
		//Indica tamanho do menu lateral
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		panelWidth = (int) ((metrics.widthPixels)*width);
	
		//Coloca o header do painel
		RelativeLayout headerPanel = (RelativeLayout) findViewById(R.id.header);
		headerPanelParameters = (LinearLayout.LayoutParams) headerPanel.getLayoutParams();
		headerPanelParameters.width = metrics.widthPixels;
		headerPanel.setLayoutParams(headerPanelParameters);
		
		//Coloca um panel onde vai ficar a lista
		menuPanel = (RelativeLayout) findViewById(R.id.menuPanel);
		menuPanelParameters = (FrameLayout.LayoutParams) menuPanel.getLayoutParams();
		menuPanelParameters.width = panelWidth;
		menuPanel.setLayoutParams(menuPanelParameters);
		
		//O panel que fará o sliding
		slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);
				
		//Lista que conterá os elementos do menu
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
 
        //Cria a lista dos elementos
        createListView();
	

		//Botão com a ação de sliding	
        ImageView menuViewButton = (ImageView) findViewById(R.id.menuViewButton);
		menuViewButton.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	if(!isExpanded){
		    		isExpanded = true;   		    				        		
		        	
		    		//Expande
		    		new ExpandAnimation(slidingPanel, panelWidth,
		    	    Animation.RELATIVE_TO_SELF, 0.0f,
		    	    Animation.RELATIVE_TO_SELF, width, 0, 0.0f, 0, 0.0f);		    			         	    
		    	}else{
		    		isExpanded = false;
		    		
		    		//Colapsa
		    		new CollapseAnimation(slidingPanel,panelWidth,
            	    TranslateAnimation.RELATIVE_TO_SELF, width,
            	    TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);
		   
					
		    	}         	   
		    }
		});		
	}

	/**
	* Method to create the list of listview
	* @author Helena Macedo
	* @version 0.0.1
	*/
	private void createListView()
    {	    
		Resources res = getResources();

        //Lista que preenchera o ListView
	    ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
        ItemListView item1 = new ItemListView(String.format(res.getString(R.string.novo)), R.drawable.icon_novo2);
        ItemListView item2 = new ItemListView(String.format(res.getString(R.string.abrir)), R.drawable.icon_novo2);
        ItemListView item3 = new ItemListView(String.format(res.getString(R.string.salvar)), R.drawable.icon_novo2);
        ItemListView item4 = new ItemListView(String.format(res.getString(R.string.salvar_como)), R.drawable.icon_novo2);
        ItemListView item5 = new ItemListView(String.format(res.getString(R.string.mostrar_grid)), R.drawable.icon_novo2);
        ItemListView item6 = new ItemListView(String.format(res.getString(R.string.mostrar_eixo)), R.drawable.icon_novo2);

 
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
        itens.add(item5);
        itens.add(item6);
 
        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);
 
        //Define o Adapter
        listView.setAdapter(adapterListView);
        
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }
 
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
    {
        //Pega o item que foi selecionado.
        ItemListView item = adapterListView.getItem(arg2);
        
        //Demostração
        Toast.makeText(this, "Você Clicou em: " + item.getTexto(), Toast.LENGTH_LONG).show();
        
        if (item.getTexto().equals("Grid")){
        	//GridListener.criarGrid(this);
        	
        	GridListener gl = new GridListener(this, null);
        	gl.criarGrid(gl);
        	
        }
        
    }
}



