import java.util.Iterator;
import java.util.LinkedList;
/*
class for the path finder algorithem
 */

public class Dijkstra2 {
	NodeData[] vertices;
	int source;
	public Dijkstra2(NodeData[] vs, int source){
		this.source = source;
		vertices = new   NodeData[vs.length];
		for (int i=0; i<vs.length; i++){
			vertices[i] = vs[i];
		}
	}

	public void computePaths(){
		NodeData s = vertices[source];
		s.setWeight( 0.);
		HeapMin Q = new HeapMin();
		Q.minHeapInsert(s);
		//O(nlogn)
		for (int i=1; i<vertices.length; i++){//O(n)
			Q.minHeapInsert(vertices[i]);//O(logn)
		}
		//O(nlogn) + O(mlogn) = O((m+n)logn)
		while (!Q.isEmpty()) {//O(m)
			NodeData u =(NodeData) Q.heapExtractMin();//O(logn)
			// Visit each edge exiting u
			u.adjacency.forEach((k,e) -> {
				NodeData v = vertices[e.getDest()];
				if (!v.visited){
					double distU = u.getWeight() + e.getWeight();
					if (distU<v.getWeight()) {//relaxation
						v.setWeight(distU) ;
						v.previous = vertices[u.getKey()].getKey();
						Q.heapDecreaseKey(v);//O(logn)
					}
				}
			});

			u.visited = true;
		}
	}

	public void printWeights(){
		System.out.print("weights: ");
		for (NodeData v : vertices) {
			System.out.printf(v.getWeight() + ", ");
		}
		System.out.println();
	}
	public LinkedList<node_data> getPath(int v,DGraph dGraph){
		int t = v;
		LinkedList<node_data> ans = new LinkedList<node_data>();
		ans.add(dGraph.connectivity.get(t));
		while(t != source){
			t = vertices[t].previous;
			ans.add(dGraph.connectivity.get(t));
		}
		LinkedList<node_data> ans2 = new LinkedList<node_data>();

		for(int i = 0; i < ans.size();i++)
		{
				ans2.add(ans.get(ans.size() - i-1));
		}
		return ans2;
	}

	//Todo: delete
	public void printPaths(){
		for (NodeData v : vertices){
			//System.out.println("price of " + v.getKey()+" = " + v.getWeight() + ", path: " +  getPath(v.getKey()));
		}
		System.out.println();
	}
}
