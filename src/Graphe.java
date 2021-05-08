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
		int MAX = 4;
		int[] sommets = new int [MAX];
		
		for (int i = 0 ; i <= MAX; i++)
		{
		        sommets[0] = (int)(Math.random()*MAX);

		        while (sommets[1] == sommets[0])
		        {
		             sommets[1] = (int)(Math.random()*MAX);
		        }
		        while ((sommets[2] == sommets[0]) || (sommets[2] == sommets[1]) )
		        {
		             sommets[2] = (int)(Math.random()*MAX);
		        }
		        while ((sommets[3] == sommets[0]) || (sommets[3] == sommets[1]) || (sommets[3] == sommets[2]) )
		        {
		             sommets[3] = (int)(Math.random()*MAX);
		        }
		        

		}

		Arc a1=null;
		Arc a2=null;
		
		int i=0;
		int j=0;
		for(i=0; i<3; i++) {
			
			for(j=i+1;j<4;j++) {
				System.out.println("i="+i);
				System.out.println("j="+j);
				
				a1=getArcLink(sommets[i],sommets[j],current);
				System.out.println("a1="+a1);
				if(a1!=null) {
					break;
				}
				
			}
			if(a1!=null) {
				break;
			}
		}

		for(int k=0; k<3; k++) {
			
			for(int l=k+1;l<4 ;l++) {
				//System.out.println(k!=i && k!=j && l!=i && l!=j);
				//if((k!=i && l!=j) || (l!=i && k!=j)) {
					
					System.out.println("k="+k);
					System.out.println("l="+l);
					a2=getArcLink(sommets[k],sommets[l],current);
					System.out.println("a2="+a2);
					if(a2!=null && a2.distinctSommets(a1)) {
						break;
					}
					
				//}
			}
			if(a2!=null && a2.distinctSommets(a1)) {
				break;
			}
		}
		System.out.println("a1 définitif="+a1);
		System.out.println("a2 définitif="+a2);
		ArrayList<Arc> neighbor = new ArrayList<>();
		for(Arc a : current) {
			if(!a.equals(a1) && !a.equals(a2)) {
				neighbor.add(a);
			}
		}
		
		neighbor.add(getArcLink(a1.getSommet1(),a2.getSommet2(),Arcs));
		neighbor.add(getArcLink(a1.getSommet2(),a2.getSommet1(),Arcs));
		
		return neighbor;
		//return current;
		
		
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
