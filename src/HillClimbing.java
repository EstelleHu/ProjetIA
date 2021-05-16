import java.util.ArrayList;

public class HillClimbing {
	
	public int heuristic (ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}
	public ArrayList<Arc> search(Graphe g) {
		int i=0;
		while (true){
			ArrayList<Arc> neighbor =  g.twoOpt();
			System.out.println( "Etape "+i+"  neighbor : "+neighbor);
			System.out.println("current heuristic "+heuristic(g.getCurrent()) +" VS neighbor heuristic "+heuristic(neighbor));
			if(heuristic(g.getCurrent())<= heuristic(neighbor) ){
				System.out.println("Chemin le plus court, resultant du HILL CLIMBING SEARCH :\n"+g.getCurrent());
				System.out.println("Longueur du chemin = "+ heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			i++;
			g.setCurrent( neighbor);
		}
	}
	
	public int h(int[]ligne, int[]resultat) {
		int min = ligne[0];
		for(int i=1;i<ligne.length-1;i++) {
			if(ligne[i]<min) {
				min=ligne[i];
			}
		}
		return min;
	}

//	public ArrayList<Arc> searchRandomRestartHC(Graphe g) {
//		int i = 0;
//		ArrayList<Arc> retenu=new ArrayList<>();
//		ArrayList<Arc> ignored =new ArrayList<>();
//		while(true) {
//			System.out.println("TEST " + i + "  with current chosen randomly :" + g.getCurrent());
//			ArrayList<Arc> resTest1 = search(g);
////			ArrayList<Arc> copieT1= new ArrayList<>();
////			int e=0;
////			for (Arc a : resTest1){
////				copieT1.add(e,resTest1.get(e));
////				e++;
////			}
//			// On choisit l'état initial du test 2 aléatoirement
//			g.setCurrent(g.chooseCurrentRandomly());
//			int hTest1 = heuristic(copieT1);
//			System.out.println("TEST " + (i + 1) + " with current chosen randomly :" + g.getCurrent());
//			if(retenu.size()==0) {
//				ArrayList<Arc> resTest2 = search(g);
//				System.out.println("H : " + copieT1 + " VS " + resTest2 + "\n \n");
//				System.out.println("COMPARING DISTANCES : " + hTest1 + " with " + heuristic(resTest2));
//				if (hTest1 <= heuristic(resTest2)) {
//					System.out.println("Fin de la recherche Random-restart hill climbing :");
//					System.out.println("Chemin le plus court, resultant du Random-restart HILL CLIMBING SEARCH :\n" + copieT1);
//					System.out.println("Longueur du chemin = " + hTest1);
//					return copieT1;
//				}
//				i++;
//				g.setCurrent(g.chooseCurrentRandomly());
//				int f=0;
//				for (Arc a : resTest2){
//					if(retenu.size()==0) {
//						retenu.add(f, resTest2.get(f));
//					}else {
//						retenu.set(f, resTest2.get(f));
//					}
//					f++;
//				}
//			}else{
//				System.out.println("COMPARING DISTANCES : " + heuristic(retenu) + " with " + heuristic(resTest1));
//				if (heuristic(retenu) <= heuristic(resTest1)) {
//					System.out.println("Fin de la recherche Random-restart hill climbing :");
//					System.out.println("Chemin le plus court, resultant du Random-restart HILL CLIMBING SEARCH :\n" + retenu);
//					System.out.println("Longueur du chemin = " + heuristic(retenu));
//					return retenu;
//				}
//				i++;
//				g.setCurrent(g.chooseCurrentRandomly());
//			}
//
//		}
//	}
	public ArrayList<Arc> searchRandomRestartHC(Graphe g) {
		ArrayList<Arc> lastChosen= new ArrayList<>();
		ArrayList<Arc> res = search(g);
		int e=0;
		for (Arc a : res){
			lastChosen.add(e,a);
			e++;
		}
		while (true) {
			g.setCurrent(g.chooseCurrentRandomly());
			ArrayList<Arc> resOfHC = search(g);
			System.out.println(" LAST CHOSEN "+lastChosen);
			System.out.println(" NEW RES OF HS "+resOfHC);
			System.out.println("COMPARING DISTANCES : " + heuristic(lastChosen) + " with " + heuristic(resOfHC));
			if (heuristic(lastChosen) <= heuristic(resOfHC)) {
				System.out.println("Fin de la recherche Random-restart hill climbing :");
				System.out.println("Chemin le plus court, resultant du Random-restart HILL CLIMBING SEARCH :\n" + lastChosen);
				System.out.println("Longueur du chemin = " + heuristic(lastChosen));
				return lastChosen;
			}
			int k=0;
			for (Arc a : resOfHC){
				lastChosen.set(k,a);
				k++;
			}
		}
	}
}
