package it.polito.tdp.genes.model;

public class Vicini implements Comparable<Vicini>{

	private Genes g1;
	private Double peso;
	public Vicini(Genes g1, Double peso) {
		super();
		this.g1 = g1;
		this.peso = peso;
	}
	public Genes getG1() {
		return g1;
	}
	public Double getPeso() {
		return peso;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Vicini o) {
		return o.getPeso().compareTo(this.getPeso());
	}
	public String toString() {
		return this.g1.getGeneId()+" "+this.peso;
	}
	
	
}
