
import java.io.Serializable;
import java.util.*;

public class DGraph implements graph, Serializable {
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Fields//////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	int changes = 0;	// For MC
	private int N;		//Number of nodes in graph
	private int E;		// Number of edges in graph
	Hashtable<Integer, node_data> connectivity=
			new Hashtable<Integer, node_data>();
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
		NodeData src_node = (NodeData) connectivity.get(src);
		NodeData dest_node = (NodeData) connectivity.get(dest);
		if (dest_node == null || src_node == null){
			throw new IllegalArgumentException("Source or Destination isn't found");
		}
		return src_node.getEdgesByKey(dest);

	}

	@Override
	public void addNode(node_data n) {
		if( this.connectivity.get(n.getKey()) != null)
			throw new IllegalArgumentException("the key is already exist");
		this.connectivity.put(this.N +1 , (NodeData) n);
		this.N ++;
		changes++;
		

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
		changes++;
	}

	@Override
	public Collection<node_data> getV() {
		return this.connectivity.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// returns all edges that goes from node_id
		Collection<edge_data> e = ((NodeData)this.connectivity.get(node_id)).adjacency.values();
		return e;
	}

	@Override
	public node_data removeNode(int key) {
		if (this.connectivity.containsKey(key)){
			//remove the node
			NodeData nr = (NodeData) connectivity.remove(key);
			// run on each node and remove the key in each adjacency so we wont hear from that node again
			connectivity.forEach((i,n) -> {
				edge_data e_deleted = ((NodeData)n).adjacency.remove(key);
				if (e_deleted != null){
					E--;
				}
			} );
			N--;
			changes++;
			return nr;
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.connectivity.containsKey(src) && this.connectivity.containsKey(dest) ){
			edge_data er = ((NodeData)connectivity.get(src)).adjacency.remove(dest);
			E--;
			changes++;
			return er;
		}
		return null;
	}

	@Override
	public int nodeSize() {
		//return this.connectivity.size();
		return N;
	}

	@Override
	public int edgeSize() {
		return E;
	}

	@Override
	public int getMC() {

		return changes;
	}
}
