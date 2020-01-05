

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	public graph dGraph;


	@Override
	public void init(graph g) {
		dGraph = g;
	}

	@Override
	public void init(String file_name)  {
		File f = new File(file_name);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			dGraph = (graph) ois.readObject();
			fis.close();


		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {

			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}


	}

	@Override
	public void save(String file_name) {
		File f = new File(file_name);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dGraph);
			fos.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private void DFS (DGraph dg,NodeData start)
	{
		start.visited = true;


		Collection<edge_data> edge_list = dg.getE(start.getKey());

		for (edge_data e: edge_list)
		{

			if(!((Edge) e).node.visited)
			{
					DFS(dg , ((Edge)e).node);
			}

		}


	}

	@Override
	public boolean isConnected() {

		Iterator<node_data> iterG =  dGraph.getV().iterator();
		//1. MAKE DEEP SEARCH TO COLOR ALL NODS
		//2. CHECK IF REALLY ALL THE NODES WERE COLORD
		//3. RETURN FALSE IF ONE NOT COLORED

		while (iterG.hasNext())
		{
			node_data node = iterG.next();
			((DGraph)dGraph).connectivity.forEach((k,v)->{
				((NodeData)v).visited = false;
			});
			DFS((DGraph) dGraph,(NodeData) node);

			for (node_data _node : dGraph.getV())
			{

					if(!((NodeData)_node).visited)
					{
						return false;
					}

			}
			
		}



		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {

	    NodeData [] nodeData = new NodeData[dGraph.getV().size()];

        for (int i = 0; i <dGraph.getV().size(); i++) {

            nodeData[i] = (NodeData) dGraph.getNode(i);
        }
        Dijkstra2 ds = new Dijkstra2(nodeData, src);
        ds.computePaths();

		if (dGraph.getNode(dest).getWeight() == Double.MAX_VALUE) {
			throw new RuntimeException("the graph may not been connected");
		}
        return dGraph.getNode(dest).getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if(src==dest)return null;

		NodeData [] nodeData = new NodeData[dGraph.getV().size()];

		for (int i = 0; i <dGraph.getV().size(); i++) {

			nodeData[i] = (NodeData) dGraph.getNode(i);
		}
		Dijkstra2 ds = new Dijkstra2(nodeData, src);
		ds.computePaths();

		if (dGraph.getNode(dest).getWeight() == Double.MAX_VALUE) {
			throw new RuntimeException("the graph may not been connected");
		}

		return ds.getPath(dest,(DGraph) dGraph);
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {

		ArrayList<node_data> path = new ArrayList<node_data>();
		NodeData src;
		boolean finished = false;

		Collection edge_list;
		Iterator<edge_data> edge_dataIterator;

		if(!this.isConnected())
			return path;

		for (int i = 0; i < targets.size() && !finished; i++)
		{
			src = (NodeData) dGraph.getNode(targets.get(i));
			path.add(src);
			edge_list	=  dGraph.getE(src.getKey());
				edge_dataIterator = edge_list.iterator();

			for (int j = 0; edge_dataIterator.hasNext() && !finished; j++)
				{
					if(targets.contains(edge_dataIterator.next().getDest()))
						{
							src = (NodeData) dGraph.getNode(targets.get(j));
							j = 0;
							edge_list	=  dGraph.getE(src.getKey());
							edge_dataIterator = edge_list.iterator();
							path.add(src);
						}
					int counter = 0;
					for (int k = 0; k < targets.size() ; k++)
					{
						if(path.contains(dGraph.getNode(targets.get(k))))
						{
							counter ++;
							//path.clear();
							//finished = false;
							//break;
						}
					}
					if(counter == targets.size())
					{
						finished = true;
					}
				}



				//while ()

		}

		return path;
	}

	@Override
	public graph copy() {
		graph g = new DGraph((DGraph) dGraph);
		return g;
	}
	

}
