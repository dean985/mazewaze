package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class DGraph implements graph{
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Fields//////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	private int N;		//Number of nodes in graph
	private int E;		// Number of edges in graph
	private ArrayList<Integer> [] connectivity;	// an array of arraylist. connectivity[n] will show n's connections


	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Constructor/////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////

	// Initializing an graph with n nodes and no connections.
	public DGraph(int n){
		if(n < 0) throw new IllegalArgumentException("number of nodes should be non-negative ");
		this.N = n;
		this.E = 0;
		this.connectivity =  new ArrayList[n];
		for (int i = 0; i <n; i++){
			this.connectivity[i] = new ArrayList<Integer>();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Methods ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////


	@Override
	public node_data getNode(int key) {

		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {

		return null;
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		
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
