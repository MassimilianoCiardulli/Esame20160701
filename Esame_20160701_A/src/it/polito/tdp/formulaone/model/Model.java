package it.polito.tdp.formulaone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	private FormulaOneDAO dao ;
	private Graph<Driver, DefaultWeightedEdge> graph ;
	private DriverIdMap driverIdMap ;
	
	public Model() {
		driverIdMap = new DriverIdMap();
	}
	
	public List<Season> getAllSeasons() {
		dao = new FormulaOneDAO();
		List<Season> seasons = dao.getAllSeasons();
		
		return seasons;
	}

	public void createGraph(String year) {
		
		graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		List<Driver> vertices = dao.getDriversOfYear(year, driverIdMap) ;
		Graphs.addAllVertices(this.graph, vertices);
		
		List<Result> results = dao.getResultsOfYear(year, driverIdMap);
		
		for(Result r:results) {
			Driver d = driverIdMap.get(r.getDriver());
			
			for(Result r1:results) {
				Driver d1 = driverIdMap.get(r1.getDriver());
				if(!d.equals(d1) && r.getPosition()>r1.getPosition()) {
					if(graph.containsEdge(d, d1)) {
						graph.setEdgeWeight(graph.getEdge(d, d1), 
							graph.getEdgeWeight(graph.getEdge(d,d1))+1);
					}
					else
						Graphs.addEdge(this.graph, d, d1, 1);
				}
				else {
					if(!d.equals(d1) && r.getPosition()<r1.getPosition()) {
						if(graph.containsEdge(d1, d)) {
							graph.setEdgeWeight(graph.getEdge(d1, d), 
								graph.getEdgeWeight(graph.getEdge(d1,d))+1);
						}
						else
							Graphs.addEdge(this.graph, d1, d, 1);
					}
				}
			}
		}
		
	}

	public Driver getBestDriver() {
		
		int best_score = 0;
		Driver best_driver = null ;
		
		for(Driver d:graph.vertexSet()) {
			Set<DefaultWeightedEdge> in = graph.incomingEdgesOf(d);
			int inint = 0;
			for(DefaultWeightedEdge e:in)
				inint += (int) graph.getEdgeWeight(e);
			
			Set<DefaultWeightedEdge> out = graph.outgoingEdgesOf(d);
			int outint = 0;
			for(DefaultWeightedEdge e:out)
				outint += (int) graph.getEdgeWeight(e);
			
			int score = outint - inint ;
			if(score > best_score) {
				best_score = score ;
				best_driver = d ;
			}
		}
		return best_driver;
	}

	public List<Driver> dreamTeam(int k) {
		
		List<Driver> parziale = new ArrayList<>();
		
		recursive(0, parziale, k);
			
		return parziale;
	}

	private void recursive(int livello, List<Driver> parziale, int k) {
		
		
	}


}
