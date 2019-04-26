package Minimos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Lectura {

	static int [][] matriz;
	static ArrayList<String> arreglo=new ArrayList<String>();
	static int ncolumnas=0;
	static int nfilas=0;
	static Hashtable<String,Integer> costos = new Hashtable<>();
	static int vertices;

	static class Edge implements Comparable<Lectura.Edge>{
		Integer vert1;
		Integer vert2;
		Integer edgeWt;
		public Edge(int vert1, int vert2, int edgeWt) {
			this.vert1 = vert1;
			this.vert2 = vert2;
			this.edgeWt = edgeWt;
		}
		public String toString() {
			return "(" + vert1 + "--" + vert2 + ", " + edgeWt + ")";
		}
		public int compareTo(Edge other) {
			return this.edgeWt.compareTo(other.edgeWt);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader
				(args[0]));

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
					System.out.println("Dijkstra  Nodo: "+i+" a Nodo "+j+" costo minimo: "+Dijkstra(i,j,p));

				}
			}
		}
		
		for (int i = 0; i < nfilas; i++) {
			for (int j = 0; j < ncolumnas; j++) {

				if(i!=j){
					System.out.println("\n"+"BellmanFord");
					bellmanFord(i, j);

				}
			}
		}
		long tfin= System.currentTimeMillis();
		System.out.println("Fin: "+(tfin-tinic));


		System.out.println("\n"+"FloydWarschall");
		FloydWarschall();



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
			if(i!=inicio && adyacentes[i]>0 && adyacentes[i]<costo && visitados[i]!=1){
				lc = costos.get(i+"-"+fin);
				if(lc==null){
					//System.out.println("Dijsktra de: "+i+"-"+fin);
					visitados[i]=1;
					s =  Dijkstra(i, fin, visitados);
					costos.put(i+"-"+fin, s);
				}else{
					s =lc;
				}
				s+=adyacentes[i]; 
				if(s<costo){
					costo=s;

				}
			}


		}
		//System.out.println("Desmarque: "+inicio);
		visitados[inicio]=0;
		return costo;
	}

	public static void bellmanFord  (int inicio , int fin){
		
		vertices = matriz.length;
		int[] SSSP = new int[vertices];
		int[] predecessor = new int[vertices];
		ArrayList<Edge> adyacentes = new ArrayList<Edge>(vertices) ;
		SSSP[inicio] = 0;
		
		for (int i = 0; i < vertices; i++) {
			if(matriz[inicio][i] > 0) {
				Edge edge = new Edge(inicio, i, matriz[inicio][i]);
				adyacentes.add(edge);
			}
		}
		/* runs |V|-1 times */
		for (int i=0; i<vertices-1; i++) {
			for (Edge edge: adyacentes) {
				Integer vert1 = edge.vert1;
				Integer vert2 = edge.vert2;

				int weight = matriz[inicio][fin];
				/* if distance is currently at infinity */
				
				if (weight >= SSSP[vert1]+weight) {
					SSSP[vert2] = SSSP[vert1] + weight;
					predecessor[vert2] = vert1;
				}
				else if (weight < 0) {
					SSSP[vert1] = SSSP[vert2] + weight;
					predecessor[vert1] = vert2;
				}
			}
		}

		System.out.println("Source vertex:     " + inicio);
		System.out.println("Source vertex:     " + fin);
		System.out.println("SSSP array:        " + Arrays.toString(SSSP));
		System.out.println("Predecessor array: " + Arrays.toString(predecessor));
	}



	public static void FloydWarschall(){
		
		vertices = matriz.length;
		int[][] APSP = new int [vertices][vertices] ;
		int[][] successor = matriz;
		ArrayList<Edge> adyacentes = new ArrayList<Edge>(nfilas) ;
		
		/* initializations */
		for (int i=0; i<vertices; i++) {
			APSP[i][i] = 0;
		}
		for (Edge edge: adyacentes) {
			Integer vert1 = edge.vert1;
			Integer vert2 = edge.vert2;
			Integer edgeWt = edge.edgeWt;
			APSP[vert1][vert2] = edgeWt;
			successor[vert1][vert2] = vert2;
		}
		
		/* bottom-up dynamic programming */
		for (int k=0; k<vertices; k++) {
			for (int i=0; i<vertices; i++) {
				if (successor[i][k] == -1) {
					continue;
				}
				for (int j=0; j<vertices; j++) {
					if (APSP[i][k]==-1 || APSP[k][j]==-1) {
						continue;
					}
					if ((APSP[i][j] == -1) ||	// if distance at infinity
						(APSP[i][j] > APSP[i][k] + APSP[k][j])) {
						APSP[i][j] = APSP[i][k] + APSP[k][j];
					    successor[i][j] = successor[i][k];
					}
				}
			}
		}
		
		System.out.println("APSP matrix:");
		imprimirMatriz(APSP);
		System.out.println("Successor matrix:");
		imprimirMatriz(successor);
	
	}

	public static void imprimirMatriz(int[][] aPSP) {
		int filas = aPSP.length;
		for (int i=0; i<filas; i++) {
			System.out.println(Arrays.toString(aPSP[i]));
		}
	}





}

