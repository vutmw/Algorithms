package com.vtmw.algorithms;

import java.util.*;

//Dijkstra's Algorithm using Priority Queue
public class Dijkstra {
	
	private int N; // number of nodes of the graph
	private List<List<Node>> adj; // all adjacent nodes of the [i] node
	private int dist[]; // the [i] node distance from the source node
	private PriorityQueue<Node> pq; // queue of nodes to analyze
	private Set<Integer> settled; // set of nodes already analyzed (whose shortest path has been found)
	

	public Dijkstra(int N) {
		this.N = N;
		dist = new int[N];
		settled = new HashSet<Integer>();
		pq = new PriorityQueue<Node>(N, new Node());
	}

	// Dijkstra's Algorithm
	public void dijkstra(List<List<Node>> adj, int src) {
		this.adj = adj;
		for (int i=0; i<N; i++)
			dist[i] = Integer.MAX_VALUE; // distance set to max value
		dist[src] = 0; // distance to the source is 0
		pq.add(new Node(src, 0)); // add source node to the priority queue

		
		while (settled.size() != N) {
			
			if (pq.isEmpty())
				return;

			int nodeID = pq.remove().id;
			if (settled.contains(nodeID))
				continue;
			
			// Process all neighbors of the analyzed node "nodeID"
			for (int i=0; i < adj.get(nodeID).size(); i++) {
				Node neighbor = adj.get(nodeID).get(i);

				if (!settled.contains(neighbor.id)) {
					int newDistance = dist[nodeID] + neighbor.cost;
		             
					if (newDistance < dist[neighbor.id]) // if new distance is cheaper in cost
						dist[neighbor.id] = newDistance;

					// Add the neighbor node to the queue to "recursively" do the same analysis
					pq.add(new Node(neighbor.id, dist[neighbor.id]));
				}
			}
			
			settled.add(nodeID);
		}
	}

	
	public static void main(String arg[]) {
		int N = 5; // number of nodes in the graph
		int src = 0; // id of the source node

		// Constructs the adjacency list
		List<List<Node>> adj = new ArrayList<List<Node>>();
		for (int i=0; i<N; i++) {
			List<Node> item = new ArrayList<Node>();
			adj.add(item);
		}
		adj.get(0).add(new Node(1, 9)); // it takes 9 to travel from [0] to [1]
		adj.get(0).add(new Node(2, 6));
		adj.get(0).add(new Node(3, 5));
		adj.get(0).add(new Node(4, 3));
		adj.get(2).add(new Node(1, 2));
		adj.get(2).add(new Node(3, 4));

		// Resolves Dijkstra
		Dijkstra dijkstra = new Dijkstra(N);
		dijkstra.dijkstra(adj, src);

		// Printing the shortest path from the source node, for all nodes
		System.out.println("The shortest path from source node :");
		for (int i = 0; i < dijkstra.dist.length; i++)
			System.out.println(src + " to " + i + " is " + dijkstra.dist[i]);
	}
}


class Node implements Comparator<Node> {

	int id; // node id
	int cost; // cost in order to reach this node (differs according to the node that wants to reach it)

	public Node() {}
	public Node(int id, int cost) {
		this.id = id;
		this.cost = cost;
	}

	@Override
	public int compare(Node node1, Node node2) {
		if (node1.cost < node2.cost)
			return -1;
		if (node1.cost > node2.cost)
			return 1;
		return 0;
	}
}