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
		
		
		
		int[] visitados = new int[tamaño];
		 ArrayList<Integer> rta = bfs(visitados , 0);
		 rta.add(0,0);
		System.out.println(rta.toString());
		
	}
	
	
	public static ArrayList<Integer> bfs ( int [] visitados  , int inicio ){
		
		ArrayList<Integer> llocal = new ArrayList<>();
		
		int[] adyacentes = new int[ visitados.length ] ;
		for (int i = 0; i < adyacentes.length; i++) {
			adyacentes[i] = grafoNoDirigido[inicio][i];
		}
		visitados[inicio]=1;
		
		for (int i = 0; i < adyacentes.length; i++) {
			if(adyacentes[i]==1 && visitados[i]!=1){
				visitados[i]=1;
				llocal.add(i);
				ArrayList<Integer> c = bfs(visitados, i);
				llocal.addAll(c);
			}
		}
		
		return llocal;
	}

}
