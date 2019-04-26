package Minimos;

import java.util.ArrayList;

public class Dfs {

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

		ArrayList<Integer> rta = dfs(0);
		System.out.println(rta.toString());

	}

	public static ArrayList<Integer> dfs(int inicio) {

		int tamaño = 4;

		boolean[] visitados = new boolean[tamaño];
		ArrayList<Integer> secuencia = new ArrayList<Integer>();
		dfs(inicio, visitados, secuencia);
		return secuencia;
	}
	public static void dfs(int inicio, boolean[] visitados, ArrayList<Integer> secuencia) {
		
		int[] adyacentes = new int[ visitados.length ] ;
		for (int i = 0; i < (adyacentes.length)-1; i++) {
			adyacentes[i] = grafoNoDirigido[inicio][i];
		}
		visitados[inicio] = true;
		secuencia.add(inicio);
		for (int i = 0; i < adyacentes.length;i++) {
			if (!visitados[i]) {
				dfs(i, visitados, secuencia);
			}
		}
	}
}
