package Minimos;

import java.util.ArrayList;

public class Bfs {
	
	
	static int[][]  grafoNoDirigido;
	
	public static void main(String[] args) {

		int tamaño = 5+(int)(Math.random()*5);

		System.out.println("Tamaño del grafo no dirigido: "+ tamaño );

	
		 grafoNoDirigido = new int[tamaño][tamaño];
		//llena la matiz de 1 o -1
		int valor= 0;
		for (int i = 0; i < grafoNoDirigido.length; i++) {
			for (int j = i ; j < grafoNoDirigido.length; j++) {
				valor = 1+(int)(Math.random()*100);
				if(i==j){
					grafoNoDirigido[i][j] = -1 ;	
				}else if(valor % 2 == 0){
					grafoNoDirigido[i][j] = 1 ;
					grafoNoDirigido[j][i] = 1 ;
				}else{
					grafoNoDirigido[i][j] = -1 ;
					grafoNoDirigido[j][i] = -1 ;
				}
			}

		}
		
		String fila=" ";
		for (int i = 0; i < tamaño ;i++) {
			for (int j = 0; j < tamaño; j++) {
				fila += grafoNoDirigido[i][j] +"  ";
			}
			System.out.println(fila);
			fila=" ";
		}
		
		
		
	}
	
	
	public ArrayList<Integer[]> bfs ( int [] visitados  , int inicio ){
		
		int[] adyacentes = new int[ visitados.length ] ;
		for (int i = 0; i < adyacentes.length; i++) {
			adyacentes[i] = grafoNoDirigido[inicio][i];
		}
		
		for (int i = 0; i < adyacentes.length; i++) {
			if(adyacentes[i]==1){
				
			}
		}
		
		return null;
	}

}
