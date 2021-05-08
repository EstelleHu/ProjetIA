import java.util.ArrayList;
import java.util.Arrays;

public class Graphe {
	private int[][] matrice;
	private int taille;
	private ArrayList<Arc> Arcs = new ArrayList<>();
	private ArrayList<Arc> current = new ArrayList<>();
	public int scoreCurrent;
	
	
	public Graphe(int taille) {
		this.taille = taille;
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
		for (int i=0; i<taille-1; i++) {
			current.add(getArcLink(i,i+1, Arcs));
		}
		current.add(getArcLink(0,taille-1, Arcs));
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
		
		System.out.println(a1);
		System.out.println(a2);
		
		ArrayList<Arc> neighbor = new ArrayList<>();
		for(Arc a : current) {
			if(!a.equals(a1) && !a.equals(a2)) {
				neighbor.add(a);
			}
		}
		
		neighbor.add(getArcLink(a1.getSommet1(),a2.getSommet1(),Arcs));
		neighbor.add(getArcLink(a1.getSommet2(),a2.getSommet2(),Arcs));
		System.out.println(current);
		System.out.println(neighbor);
		return neighbor;
		
		
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

}
