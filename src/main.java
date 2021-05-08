import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		Graphe g = new Graphe(5);
		//g.affiche();
		//System.out.println(g.toString());
		ArrayList<Arc> a = g.twoOpt();
		System.out.println(a.size());
		
	}
}
