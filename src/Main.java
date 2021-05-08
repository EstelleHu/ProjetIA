import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		int n= 1;
		Graphe g = new Graphe(n);
		System.out.println("Le graphe est de taille= " +(n+4));
		//g.affiche();
		//System.out.println(g.toString());
		ArrayList<Arc> a = g.twoOpt();
		System.out.println(a.size());
	}
}
