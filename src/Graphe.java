import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graphe {
	private int[][] matrice;
	private int taille;
	private ArrayList<Arc> Arcs = new ArrayList<>();
	private ArrayList<Arc> current = new ArrayList<>();
	private int scoreCurrent;
	private ArrayList<ArrayList<Arc>> neighborsOfCurrent;
	
	public Graphe(int n) {
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

		//val de distance ==0 normal ?
		//System.out.println("CURRENT 3 "+current);
	}
	public void randomizeCurrent() {
		current =new ArrayList<>();
		int i=0;
		ArrayList<Arc> chosen =chooseCurrentRandomly();
		for (Arc c: chosen){
			this.current.add(chosen.get(i));
			i++;
		}
	}

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
		//on verifie que la combinaison n'a pas déjà été testée ?
		for (int i = 0; i < statesId.length-1; i++){
			chosen.add(getArcLink(statesId[i], statesId[i+1] , Arcs));
		}
		//rajouter la dernière liaison
		chosen.add(getArcLink(statesId[0], statesId[taille - 1], Arcs));
		System.out.println("PROPOSITION "+chosen);
		return chosen;
	}

	public Arc getArcLink(int sommet1, int sommet2, ArrayList<Arc> ArcList) {
		for(Arc a : ArcList) {
			if((a.getSommet1()==sommet1 && a.getSommet2()==sommet2) || (a.getSommet1()==sommet2 && a.getSommet2()==sommet1)) {
				return a;
			}
		}
		return null;
	}
	public int getTaille() {
		return taille;
	}
	
	
	public ArrayList<Arc> twoOpt (){
		int MAX = taille;
		int i1, i2;
		do {
			i1= (int)(Math.random()*MAX);
			i2 =(int)(Math.random()*MAX);
		}while(!current.get(i1).distinctSommets(current.get(i2)));
		
		Arc a1=current.get(i1);
		Arc a2=current.get(i2);
		
//		System.out.println("Arc 1 : "+a1);
//		System.out.println("Arc 2 : "+a2);
		
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
//		System.out.println(current);
//		System.out.println(neighbor);
		return neighbor;
		
		
	}
	public ArrayList<ArrayList<Arc>> twoOptNeighbors(){
		this.neighborsOfCurrent = new ArrayList<>();
		for(int i=0; i<current.size();i++) {
			for(int j=i+1; j<current.size();j++) {
				Arc a1=current.get(i);
				Arc a2=current.get(j);
				if(a1.distinctSommets(a2)) {
//					System.out.println("a1 = " +a1);
//					System.out.println("a2 = " +a2);
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
		
	
	public ArrayList<ArrayList<Arc>> getNeighborsOfCurrent() {
		return neighborsOfCurrent;
	}


	public void affiche() {
		for (int l=0; l<taille; l++) {
			for(int c=0; c<taille; c++) {
				System.out.print(this.matrice[l][c] + " ");
			}
			System.out.println();
		}
	}


	@Override
	public String toString() {
		return "Graphe [matrice=" + Arrays.toString(matrice) + ", taille=" + taille + ", Arcs=" + Arcs + ", current="
				+ current + ", scoreCurrent=" + scoreCurrent + "]";
	}

	public int[][] getMatrice() {
		return matrice;
	}
	public ArrayList<Arc>  getCurrent(){
		return this.current;
	}

	public void setCurrent(ArrayList<Arc> neighbor) {
		int i=0;
		for (Arc c: neighbor){
			current.set(i,c);
			i++;
		}
//		System.out.println("NEW CURRENT : "+this.current);
	}


}
