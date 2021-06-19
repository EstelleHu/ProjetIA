import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * The Class Graph.
 */
public class Graph {
	
	/** The matrice represents the cities and the distances between them. */
	private int[][] matrice;
	
	/** The taille represents the size of matrice */
	private int taille;
	
	/** The Arcs. */
	private ArrayList<Arc> Arcs = new ArrayList<>();
	
	/** The current represent the current state */
	private ArrayList<Arc> current = new ArrayList<>();
	
	/** The neighbors of current. */
	private ArrayList<ArrayList<Arc>> neighborsOfCurrent;
	
	/**
	 * Instantiates a new graph.
	 *
	 * @param n the n
	 */
	public Graph(int n) {
		this.taille = n+4;
		this.matrice = new int[taille][taille];
		for (int l=0; l<taille; l++) {
			for(int c=0; c<taille; c++) {
				if(l==c) {
					this.matrice[l][c]=0;
				}
				else if(l < c) {
					matrice[l][c] = (int) (Math.random() * ( 600 - 1 ));
				}else {
					matrice[l][c]=matrice[c][l];
				}
			}
		}
		for(int i=0; i<taille; i++) {
			for (int j=0; j<taille; j++) {
				if(i<j) {
					Arc a = new Arc(i,j);
					a.setValeur(matrice[i][j]);
					Arcs.add(a);
				}
			}
		}
		for (int i = 0; i < taille - 1; i++) {
			current.add(getArcLink(i, i + 1, Arcs));
		}
		current.add(getArcLink(0, taille - 1, Arcs));
	}
	
	/**
	 * Randomize current.
	 */
	public void randomizeCurrent() {
		current =new ArrayList<>();
		int i=0;
		ArrayList<Arc> chosen =chooseCurrentRandomly();
		for (Arc c: chosen){
			this.current.add(chosen.get(i));
			i++;
		}
	}

	/**
	 * Choose current randomly.
	 *
	 * @return the array list
	 */
	public ArrayList<Arc> chooseCurrentRandomly() {
		ArrayList<Arc> chosen = new ArrayList<>();
		int[] statesId =new int[taille];
		for (int i=0;i<taille;i++){
			statesId[i]=i;
		}
		Random rnd = new Random();
		for (int i = statesId.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = statesId[index];
			statesId[index] = statesId[i];
			statesId[i] = a;
		}
		for (int i = 0; i < statesId.length-1; i++){
			chosen.add(getArcLink(statesId[i], statesId[i+1] , Arcs));
		}
		//add last
		chosen.add(getArcLink(statesId[0], statesId[taille - 1], Arcs));
//		System.out.println("PROPOSITION "+chosen);
		return chosen;
	}

	/**
	 * Gets the arc link.
	 *
	 * @param sommet1 the sommet 1
	 * @param sommet2 the sommet 2
	 * @param ArcList the arc list
	 * @return the arc link
	 */
	public Arc getArcLink(int sommet1, int sommet2, ArrayList<Arc> ArcList) {
		for(Arc a : ArcList) {
			if((a.getSommet1()==sommet1 && a.getSommet2()==sommet2) || (a.getSommet1()==sommet2 && a.getSommet2()==sommet1)) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Gets the taille.
	 *
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}
	
	
	/**
	 * Two opt.
	 *
	 * @return the array list
	 */
	public ArrayList<Arc> twoOpt (){
		int MAX = taille;
		int i1, i2;
		do {
			i1= (int)(Math.random()*MAX);
			i2 =(int)(Math.random()*MAX);
		}while(!current.get(i1).distinctSommets(current.get(i2)));
		
		Arc a1=current.get(i1);
		Arc a2=current.get(i2);
		
		ArrayList<Arc> neighbor = new ArrayList<>();
		for(Arc a : current) {
			if(!a.equals(a1) && !a.equals(a2)) {
				neighbor.add(a);
			}
		}
		if(current.contains(getArcLink(a1.getSommet1(),a2.getSommet1(),Arcs)) || current.contains(getArcLink(a1.getSommet2(),a2.getSommet2(),Arcs))){
			neighbor.add(getArcLink(a1.getSommet1(),a2.getSommet2(),Arcs));
			neighbor.add(getArcLink(a1.getSommet2(),a2.getSommet1(),Arcs));
		}else {
			neighbor.add(getArcLink(a1.getSommet1(), a2.getSommet1(), Arcs));
			neighbor.add(getArcLink(a1.getSommet2(), a2.getSommet2(), Arcs));
		}
		return neighbor;
		
		
	}

	/**
	 * Two opt neighbors.
	 *
	 * @return the array list
	 */
	public ArrayList<ArrayList<Arc>> twoOptNeighbors(){
		this.neighborsOfCurrent = new ArrayList<>();
		for(int i=0; i<current.size();i++) {
			for(int j=i+1; j<current.size();j++) {
				Arc a1=current.get(i);
				Arc a2=current.get(j);
				if(a1.distinctSommets(a2)) {
					ArrayList<Arc> neighbor = new ArrayList<>();
					for(Arc a : current) {
						if(!a.equals(a1) && !a.equals(a2)) {
							neighbor.add(a);
						}
					}
					if(current.contains(getArcLink(a1.getSommet1(),a2.getSommet1(),Arcs)) || current.contains(getArcLink(a1.getSommet2(),a2.getSommet2(),Arcs))){
						neighbor.add(getArcLink(a1.getSommet1(),a2.getSommet2(),Arcs));
						neighbor.add(getArcLink(a1.getSommet2(),a2.getSommet1(),Arcs));
					}else {
						neighbor.add(getArcLink(a1.getSommet1(), a2.getSommet1(), Arcs));
						neighbor.add(getArcLink(a1.getSommet2(), a2.getSommet2(), Arcs));
					}
					neighborsOfCurrent.add(neighbor);
				}
			}
		}
		return neighborsOfCurrent;
	}

	/**
	 * Two opt neighbors of.
	 *
	 * @param cur the cur
	 * @return the array list
	 */
	public ArrayList<ArrayList<Arc>> twoOptNeighborsOf(ArrayList<Arc> cur){
		this.neighborsOfCurrent = new ArrayList<>();
		for(int i=0; i<cur.size();i++) {
			for(int j=i+1; j<cur.size();j++) {
				Arc a1=cur.get(i);
				Arc a2=cur.get(j);
				if(a1.distinctSommets(a2)) {
					ArrayList<Arc> neighbor = new ArrayList<>();
					for(Arc a : cur) {
						if(!a.equals(a1) && !a.equals(a2)) {
							neighbor.add(a);
						}
					}
					if(cur.contains(getArcLink(a1.getSommet1(),a2.getSommet1(),Arcs)) || cur.contains(getArcLink(a1.getSommet2(),a2.getSommet2(),Arcs))){
						neighbor.add(getArcLink(a1.getSommet1(),a2.getSommet2(),Arcs));
						neighbor.add(getArcLink(a1.getSommet2(),a2.getSommet1(),Arcs));
					}else{
						neighbor.add(getArcLink(a1.getSommet1(), a2.getSommet1(), Arcs));
						neighbor.add(getArcLink(a1.getSommet2(), a2.getSommet2(), Arcs));
					}
					neighborsOfCurrent.add(neighbor);
				}
			}
		}

		return neighborsOfCurrent;
	}
	
	/**
	 * Gets the neighbors of current.
	 *
	 * @return the neighbors of current
	 */
	public ArrayList<ArrayList<Arc>> getNeighborsOfCurrent() {
		return neighborsOfCurrent;
	}


	/**
	 * Affiche.
	 */
	public void affiche() {
		System.out.print("     ");
		for (int l=0; l<taille; l++) {
			System.out.print(l+"    ");
		}
		System.out.println();
		for (int l=0; l<taille; l++) {
			for(int c=-1; c<taille; c++) {
				if(c==-1){
					System.out.print((c+l+1)+"   ");
				}else{
					System.out.print( " "+this.matrice[l][c] + " ");
				}
			}
			System.out.println();
		}
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Graphe [matrice=" + Arrays.toString(matrice) + ", taille=" + taille + ", Arcs=" + Arcs + ", current="
				+ current + ", scoreCurrent= ]";
	}

	/**
	 * Gets the matrice.
	 *
	 * @return the matrice
	 */
	public int[][] getMatrice() {
		return matrice;
	}
	
	/**
	 * Gets the current.
	 *
	 * @return the current
	 */
	public ArrayList<Arc>  getCurrent(){
		return this.current;
	}

	/**
	 * Sets the current.
	 *
	 * @param neighbor the new current
	 */
	public void setCurrent(ArrayList<Arc> neighbor) {
		int i=0;
		for (Arc c: neighbor){
			current.set(i,c);
			i++;
		}
	}
	
	/**
	 * PathSize.
	 *
	 * @param current the current
	 * @return the int
	 */
	public static int pathSize(ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}


}
