
import java.util.*;

public class DGraph implements graph{
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Fields//////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	private int N;		//Number of nodes in graph
	private int E;		// Number of edges in graph
	Hashtable<Integer, NodeData> connectivity=
			new Hashtable<Integer, NodeData>();
	// Hashtable: <key, value>
	// key - node.key
	// value - node that has that key.

	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Constructor/////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////

	// Initializing an graph with n nodes and no connections.
	public DGraph(int n){
		if(n < 0) throw new IllegalArgumentException("number of nodes should be non-negative ");
		this.N = n;
		this.E = 0;
		for (int i =0; i< n; i++){
			NodeData node = new NodeData(i);
			connectivity.put(node.getKey(), node);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Methods ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////


	@Override
	public node_data getNode(int key) {
		return connectivity.get(key);

	}

	@Override
	public edge_data getEdge(int src, int dest) {
		NodeData src_node = connectivity.get(src);
		NodeData dest_node = connectivity.get(dest);
		if (dest_node == null || src_node == null){
			throw new IllegalArgumentException("Source or Destination isn't found");
		}
		return src_node.getEdgesByKey(dest);

	}

	@Override
	public void addNode(node_data n) {
		this.connectivity.put(this.N +1 , (NodeData) n);
		this.N ++;
		

	}

	@Override
	public void connect(int src, int dest, double w) {
		node_data src_node = connectivity.get(src);
				if (src_node == null){
					throw new IllegalArgumentException("Source key not found");
				}
				node_data dest_node = connectivity.get(dest);
				if (dest_node == null){
					throw new IllegalArgumentException("Source key not found");
				}
		Edge e = new Edge((NodeData) dest_node,w);
		((NodeData) src_node).adjacency.put(dest_node.getKey() ,e );
		this.E++;
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
