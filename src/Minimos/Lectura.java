package Minimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lectura {

	static int[][] matriz;
	static ArrayList<String> arreglo=new ArrayList<String>();
	static int ncolumnas=0;
	static int nfilas=0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\diegosebastian\\Documents\\Sebas\\Dalgo\\Tarea6\\distances5"
				+ ".txt"));
		
		String sCadena;
		
		
		while ((sCadena = bf.readLine())!=null) {
			//System.out.println(sCadena);
			String[] datos = sCadena.split("(?=\\s)");
			for (int i = 0; i < datos.length; i++) {
				arreglo.add(datos[i].trim());
			}
			ncolumnas=datos.length;
			arreglo.add("*");
			nfilas++;
		}
		
//		System.out.println("Filas: "+nfilas);
//		System.out.println("Columnas: "+ncolumnas);
		
		int lfila=0;
		int lcolunma=0;
		matriz = new int [nfilas+1][ncolumnas+1];
		
		for ( int tamaño=0;tamaño<arreglo.size(); tamaño++) {
			String elemento = arreglo.get(tamaño);
			if(!elemento.equals("*")){
				matriz[lfila][lcolunma]=Integer.parseInt(elemento);
				lcolunma++;
			}else{
				lfila++;
				lcolunma=0;
			}
		}
		String fila="";
		for (int i = 0; i < nfilas; i++) {
			for (int j = 0; j < ncolumnas; j++) {
				fila += matriz[i][j] +" ";
			}
			System.out.println(fila);
			fila="";
		}
		
		for (int i = 0; i < nfilas; i++) {
			for (int j = 0; j < ncolumnas; j++) {
				if(i!=j){
				System.out.println("Nodo: "+i+" a Nodo "+j+" costo minimo: " +Dijkstra(i,j ));
				}
			}
		}
		
		
	}
	/*
	 * Recibe 2 parametros, el nodo origen y el número de nodos  
	 */
	public static int Dijkstra (int inicio , int fin) {
		int[] visitados = new int[nfilas] ;
		int costo = 0; 
		
		if(matriz[inicio][fin] > 0 ){
			costo=matriz[inicio][fin];
		}
	
		for(int i = 0;i<visitados.length;i++){
			if(inicio!= i && matriz[inicio][i]>0 && matriz[inicio][i]<costo){
				int s = Dijkstra(i, fin);
				if(s>0){
					costo = matriz[inicio][i] + Dijkstra(i, fin);
				}
				visitados[i]=1;
			}
		}
		return costo;
	}

}

