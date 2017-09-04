package hemi.xmu.jobs;

import java.util.*;

/**
 * Created by Vanguard on 2017/4/6.
 */
public class Graph {
    public static final double INFINITY = Double.MAX_VALUE;
    protected Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

    /**
     * Single-source unweighted shortest-path algorithm.
     * 无权单源最短路径算法——广度优先搜索
     *
     * @param startName
     */
    public void unweighted(String startName) {
        clearAll();
        Vertex start = vertexMap.get(startName);
        if (start == null) {
            throw new NoSuchElementException("Start vertex not fond.");
        }
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.dist = 0;
        while (!q.isEmpty()) {
            Vertex v = q.remove();
            for (Edge e : v.adj) {
                Vertex w = e.dest;
                if (w.dist == INFINITY) {
                    w.dist = v.dist + 1;
                    w.prev = v;
                    q.add(w);
                }
            }
        }
    }

/*    public boolean dfs(String startName ){
        clearAll();
        Vertex start = vertexMap.get(startName);
        if (start == null) {
            throw new NoSuchElementException("Start vertex not fond.");
        }
        Stack<Vertex> s = new Stack<>();
        s.push(start);
        start.dist=0;
        start.scratch=1;
        while (!s.isEmpty()){
            Vertex v = s.pop();
            for(Edge e:v.adj){
                Vertex w = e.dest;
                if(dfs(w.name)){
                    s.pop();
                }
            }
        }
    }*/

    /**
     * Single-source weighted shortest-path algorithm
     * 非负权值最短路径算法——Dijkstra
     *
     * @param startName
     */
    public void dijkstra(String startName) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Vertex start = vertexMap.get(startName);
        if (start == null) {
            throw new NoSuchElementException("Start vertex not fond.");
        }
        clearAll();
        pq.add(new Edge(start, 0));
        start.dist = 0;
        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
            Edge edge = pq.remove();
            Vertex v = edge.dest;
            if (v.scratch != 0) //already processed v
                continue;
            nodesSeen++;
            v.scratch = 1;
            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double costvw = e.cost;
                if (costvw < 0) {
                    System.out.println("Graph has negative edges.");
                    return;
                }
                if (w.dist > v.dist + costvw) {
                    w.dist = v.dist + costvw;
                    w.prev = v;
                    pq.add(new Edge(w, w.dist));
                }
            }
        }
    }

    /**
     * Single-source negative-weighted shortest-path algorithm.
     * 带负权值得最短路径算法
     *
     * @param startName
     */
    public void negative(String startName) {
        clearAll();

        Vertex start = vertexMap.get(startName);
        if (start == null) {
            throw new NoSuchElementException("Start vertex not fond.");
        }
        Queue<Vertex> q = new LinkedList<>();
        q.add(start);
        start.dist = 0;
        start.scratch++;
        while (!q.isEmpty()) {
            Vertex v = q.remove();
            if (v.scratch++ > 2 * vertexMap.size()) {
                System.out.println("Negative cycle detected.");
            }
            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double costvw = e.cost;
                if (w.dist > v.dist + costvw) {
                    w.dist = v.dist + costvw;
                    w.prev = v;
                    //Enqueue only if not already on the queue
                    if (w.scratch++ % 2 == 0)
                        q.add(w);
                    else
                        w.scratch--;
                }
            }

        }
    }

    public void addEdge(String sourceName, String destName, double cost) {
        Vertex v = getVertex(sourceName);
        Vertex w = getVertex(destName);
        v.adj.add(new Edge(w, cost));
    }

    /**
     * 通过查询图的表，打印最短路径
     *
     * @param destName
     */
    public void printPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null) {
            System.out.println("NoSuchElementException");
            return;
        } else if (w.dist == INFINITY) {
            System.out.println(destName + " is unreachable.");
        } else {
            System.out.print("(Cost is: " + w.dist + ") ");
            printPath(w);
            System.out.println();
        }
    }

    private void printPath(Vertex dest) {
        if (dest.prev != null) {
            printPath(dest.prev);
            System.out.print(" --> ");
        }
        System.out.print(dest.name);
    }


    private Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {    //create if not exist.
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    private void clearAll() {
        for (Vertex v : vertexMap.values()) {
            v.reset();
        }
    }
}

/**
 * Represents a vertex in the graph
 * 存储每个顶点信息
 */
class Vertex {
    public String name;
    public List<Edge> adj;//Adjacent vertices
    public double dist;
    public Vertex prev;
    public int scratch;//Extra variable used in algorithm

    public Vertex(String name) {
        this.name = name;
        adj = new LinkedList<Edge>();
        reset();
    }

    public void reset() {
        dist = Graph.INFINITY;
        prev = null;
        scratch = 0;
    }

    @Override
    public String toString() {
        return "Vertex{" + "name='" + name + '\'' + ", prev=" + prev + ", adj=" + adj + ", dist=" + dist + '}';
    }
}

/**
 * Represents an edge in the graph
 * 存储在邻接表中的基本项
 */
class Edge implements Comparable<Edge> {
    public Vertex dest;//Second vertex in Edge
    public double cost;//Edge cost

    public Edge(Vertex d, double c) {
        dest = d;
        cost = c;
    }

    @Override
    public int compareTo(Edge o) {
        double otherCost = o.cost;
        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Edge{" + "dest=" + dest + ", cost=" + cost + '}';
    }
}

