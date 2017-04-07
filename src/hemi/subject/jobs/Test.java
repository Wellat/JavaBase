package hemi.subject.jobs;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Vanguard on 2017/4/6.
 */
public class Test {
    @org.junit.Test
    public void test() {
        Graph graph = new Graph();
        graph.addEdge("001", "002", 1);
        graph.addEdge("002", "004", 1);
        graph.addEdge("004", "003", 1);
        graph.addEdge("002", "003", 1);
        graph.dijkstra("001");
        graph.printPath("003");
    }

//    @org.junit.Test
    public void main(String[] args) {
        Graph g = new Graph();
        try {
            FileReader fin = new FileReader(args[0]);
            Scanner graphFile = new Scanner(fin);

            // Read the edges and insert
            String line;
            while (graphFile.hasNextLine()) {
                line = graphFile.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                try {
                    if (st.countTokens() != 3) {
                        System.err.println("Skipping ill-formatted line " + line);
                        continue;
                    }
                    String source = st.nextToken();
                    String dest = st.nextToken();
                    int cost = Integer.parseInt(st.nextToken());
                    g.addEdge(source, dest, cost);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping ill-formatted line " + line);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println("File read...");
        System.out.println(g.vertexMap.size() + " vertices");

        Scanner in = new Scanner(System.in);
        g.printPath("");
    }

    public static boolean processRequest(Scanner in, Graph g) {
        try {

            System.out.print( "Enter start node:" );
            String startName = in.nextLine( );

            System.out.print( "Enter destination node:" );
            String destName = in.nextLine( );

            g.printPath(destName);

        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }
}
