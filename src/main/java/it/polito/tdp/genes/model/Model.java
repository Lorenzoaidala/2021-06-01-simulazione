package it.polito.tdp.genes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Map<String,Genes> idMap;
	private GenesDao dao;
	private Graph<Genes,DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new GenesDao();
		idMap = new HashMap <String,Genes>();
		dao.getAllGenes(idMap);
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<Genes,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for(Adiacenza a : dao.getAdiacenza(idMap)) {
			if(a.getGene1().getChromosome()==(a.getGene2().getChromosome())) {
				Graphs.addEdgeWithVertices(this.grafo, a.getGene1(), a.getGene2(), Math.abs(2*a.getPeso()));
			}
			else {
				Graphs.addEdgeWithVertices(this.grafo, a.getGene1(), a.getGene2(), Math.abs(a.getPeso()));
			}
		}
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Genes> getVertici(){
		Set <Genes> prov = this.grafo.vertexSet();
		List<Genes> result = new LinkedList<>(prov);
		Collections.sort(result);
		return result;
	}
	public List<Vicini> getVicini(Genes g){
		List<Genes>vicini = Graphs.neighborListOf(this.grafo, g);
		List <Vicini> result = new LinkedList<>();
		
		for(Genes v:vicini) {
			Double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(g, v));
			result.add(new Vicini(v,peso));
		}
		Collections.sort(result);
		return result;
	}
	
	
}
