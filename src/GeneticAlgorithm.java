import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GeneticAlgorithm extends Stochastic{
	private Graph g;
	private ArrayList<ArrayList<Arc>> population = new ArrayList<>();

	public GeneticAlgorithm(int k, Graph g) {
		this.g = g;
		for(int i=0; i<k; i++) {
			this.population.add(g.chooseCurrentRandomly());
		}
	}
	public ArrayList<Arc> geneticSearch (int[][] population, int nb){

			ArrayList<ArrayList<Arc>> populationList = new ArrayList<>();
			
			for(int i=0;i<population.length;i++) {
				populationList.add(ArrayToArcs(population[i]));
			}
			ArrayList<Arc> minPath = populationList.get(0);
			for(int i=1; i<populationList.size()-1;i++){
				if(heuristic(populationList.get(i))<heuristic(minPath)) {
					minPath=populationList.get(i);
				}
			}
			System.out.println("Min Path's heuristic = "+ heuristic(minPath));
			System.out.println("Resulting Path= "+ minPath);

			return minPath;
	}
	public ArrayList<ArrayList<Arc>> getPopulation() {
		return population;
	}

	public ArrayList<ArrayList<Arc>> selection(){
		ArrayList<ArrayList<Arc>> couples = new ArrayList<>();
		RandomSelector r = new RandomSelector();
		Iterator<ArrayList<Arc>> it = this.population.iterator();
		while(it.hasNext())
			r.add(heuristic(it.next())); //r va choisir un �l�ment
		// al�atoirement proportionnellement aux valeurs de fitness.

		for(int i = 0;i<this.population.size()/2; i++) {
			//premier parent
			int posX = r.randomChoice();
			int posY;
			ArrayList<Arc> x = this.population.get(posX);
			ArrayList<Arc> y;
			do {
				posY = r.randomChoice();
				y = this.population.get(posY);
			}while(posX==posY); //Ne doit pas etre le meme
			couples.add(x);
			couples.add(y);
		}
		System.out.println("Couples size = "+couples.size());
		return couples;
	}
	public int[][] crossover(ArrayList<ArrayList<Arc>> parents){
		
		int compteur = 0;
		int[][] parentsTab = new int[parents.size()][g.getTaille()];
		int[][] newPop = new int[parents.size()][g.getTaille()];
		for(ArrayList<Arc> list : parents) {
			parentsTab[compteur]=ArcsToArray(list);
			compteur++;
		}
		for(int i=0; i<parents.size();i++) {
			if(i%2==0) {
				System.out.println("PARENT 1");
				arrayToString(parentsTab[i]);
				
				System.out.println("PARENT 2");
				arrayToString(parentsTab[i+1]);
				
				int[] firstChild = new int[g.getTaille()];
				initializeToMinusOne(firstChild);
				firstChild[0]=parentsTab[i][0];
				int[] secondChild = new int[g.getTaille()];
				initializeToMinusOne(secondChild);
				secondChild[0]=parentsTab[i+1][0];
				
				int k=1;
				do {
					int j = contains(parentsTab[i], parentsTab[i+1][k]);
					
					firstChild[j] = parentsTab[i][j];
					secondChild[j] = parentsTab[i+1][j];
					k=j;
					
					//System.out.println(j);
				}while(contains(firstChild, parentsTab[i+1][k])==-1);
				
				for(int l=0; l<firstChild.length;l++) {
					if(firstChild[l]==-1 && contains(firstChild,parentsTab[i+1][l])==-1) {
						firstChild[l]=parentsTab[i+1][l];
					}else if(firstChild[l]==-1 && contains(firstChild,parentsTab[i+1][l])>=0) {
						if(contains(firstChild,parentsTab[i][l])>=0) {
							for(int m=0;m<firstChild.length;m++) {
								if(contains(firstChild,parentsTab[i][m])==-1) {
									firstChild[l]=parentsTab[i][m];
								}
							}
						}else {
							firstChild[l]=parentsTab[i][l];
						}
					}
					if(secondChild[l]==-1 && contains(secondChild,parentsTab[i][l])==-1) {
						secondChild[l]=parentsTab[i][l];
					}else if(secondChild[l]==-1 && contains(secondChild,parentsTab[i][l])>=0) {
						if(contains(secondChild,parentsTab[i+1][l])>=0) {
							for(int m=0;m<firstChild.length;m++) {
						
								if(contains(secondChild,parentsTab[i+1][m])==-1) {
									secondChild[l]=parentsTab[i+1][m];
								}
							}
						}else {
							secondChild[l]=parentsTab[i+1][l];
						}
					}
				}
				Random random = new Random();
				if(random.nextInt(10) == 9){ //1 chance sur 10 que l'enfant fasse une mutation
					mutation(firstChild);
				}
				if(random.nextInt(10) == 9){ //1 chance sur 10 que l'enfant fasse une mutation
					mutation(secondChild);
				}
				newPop[i]=firstChild;
				newPop[i+1]=secondChild;
				System.out.println("CHILD 1");
				arrayToString(newPop[i]);
				System.out.println("CHILD 2");
				arrayToString(newPop[i+1]);
			}
		}
		return newPop;
	}
	
	public void mutation (int[] child) {
		Random random = new Random();
		int a = random.nextInt(child.length);
		int b=a;
		while(a==b) {
			b = random.nextInt(child.length);
		}
		if(a>b) {
			int c = a;
			a = b;
			b = c;
		}
		while(a<b) {
			int tmp = child[a];
			child[a] = child[b];
			child[b] = tmp;
			a++;
			b--;
		}
	}

	public int contains(int[] tab, int sommet) {
		for (int i=0; i<tab.length;i++) {
			if(tab[i]==sommet) {
				return i;
			}
		}return -1;
	}
	public void arrayToString(int[] a) {
		for(int i=0;i<a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	
	public int[] ArcsToArray(ArrayList<Arc> list) {
		int[] tab = new int[list.size()];
		tab[0]=list.get(0).getSommet1();
		tab[1]=list.get(0).getSommet2();
		for(int i=1;i<tab.length-1; i++) {
			for(Arc a : list) {
				if(tab[i]==a.getSommet1() && contains(tab, a.getSommet2())==-1) {
					
					tab[i+1]=a.getSommet2();
				}else if(tab[i]==a.getSommet2() && contains(tab, a.getSommet1())==-1) {
					tab[i+1]=a.getSommet1();
				}
			}
		}
		return tab;
	}
	public void initializeToMinusOne(int[] tab) {
		for(int i=0; i<tab.length;i++) {
			tab[i]=-1;
		}
	}
	public ArrayList<Arc> ArrayToArcs(int[] tab) {
		ArrayList<Arc> arcs = new ArrayList<>();
		for(int i=0;i<tab.length-1;i++) {
			Arc a = new Arc(tab[i], tab[i+1]);
			a.setValeur(g.getMatrice()[tab[i]][tab[i+1]]);
			arcs.add(a);
		}
		Arc a = new Arc(tab[tab.length-1], tab[0]);
		a.setValeur(g.getMatrice()[tab[tab.length-1]][tab[0]]);
		arcs.add(a);
		return arcs;
	}
}
