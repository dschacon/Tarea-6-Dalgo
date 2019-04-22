package Minimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Lectura {

	static int[][] matriz;
	static ArrayList<String> arreglo=new ArrayList<String>();
	static int ncolumnas=0;
	static int nfilas=0;
	static ArrayList<Integer> ruta ;
	static ArrayList<Integer> subruta ;
	
	static Hashtable<String,Integer> costos = new Hashtable<>();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader
				(".\\data\\distances5.txt"));

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
		String fila=" ";
		for (int i = 0; i < nfilas; i++) {
			for (int j = 0; j < ncolumnas; j++) {
				fila += matriz[i][j] +"   ";
			}
			System.out.println(fila);
			fila=" ";
		}

		long tinic= System.currentTimeMillis();
		System.out.println("Inicio: "+tinic);
		
		

		for (int i = 0; i < nfilas; i++) {
			for (int j = 0; j < ncolumnas; j++) {
				
				if(i!=j){
					int[] p = new int[nfilas] ;
					ruta = new ArrayList<>();
					subruta = new ArrayList<>();
					System.out.println("Nodo: "+i+" a Nodo "+j+" costo minimo: "+Dijkstra(i,j,p));
					if(true){
						
					}
					ruta.add(0,i);
					ruta.add(j);
					System.out.println("Ruta: "+ruta.toString());
				}
			}
		}
		long tfin= System.currentTimeMillis();
		System.out.println("Fin: "+(tfin-tinic));



	}
	/*
	 * Recibe 3 parametros, el nodo inicio, nodo fin , nodos vistados 
	 */
	public static int Dijkstra (int inicio , int fin , int[]pvisitados) {

		//System.out.println("Inicio: "+inicio+" Fin: "+fin);
		int[] adyacentes = new int[nfilas] ;
		int costo = 999999999 ;
		int[] visitados = pvisitados ;
		visitados[inicio]=1;

		int s = 0;

		for (int i = 0; i < nfilas; i++) {
			adyacentes[i] = matriz[inicio][i];
		}

		if(matriz[inicio][fin]>0 ){
			costo=matriz[inicio][fin];
		}

		Integer lc;
		
		
		for (int i = 0; i < adyacentes.length ; i++) {
			//subruta = new ArrayList<>();
			if(i!=inicio && adyacentes[i]>0 && adyacentes[i]<costo && visitados[i]!=1){
				lc = costos.get(i+"-"+fin);
				if(lc==null){
					//System.out.println("Dijsktra de: "+i+"-"+fin);
					visitados[i]=1;
					subruta.add(i);
					s =  Dijkstra(i, fin, visitados);
					costos.put(i+"-"+fin, s);
				}else{
					s =lc;
				}
				s+=adyacentes[i]; 
				if(s<costo){
					costo=s;
					ruta.clear();
					ruta.addAll(subruta);
				}else{
					subruta.clear();
					ruta.clear();
				}
			}

		}
		//System.out.println("Desmarque: "+inicio);
		visitados[inicio]=0;
		return costo;
	}
	
	public static int bellmanFord  (int inicio , int fin , int[]pvisitados){
		
		
		
		return fin;
	}
	
	
	
	public static int FloydWarschall  (int inicio , int fin , int[]pvisitados){
		
		
		
		return fin;
	}
	
	
	
	
}

