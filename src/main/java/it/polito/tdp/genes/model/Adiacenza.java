package it.polito.tdp.genes.model;

public class Adiacenza {

	private Genes gene1;
	private Genes gene2;
	private double peso;
	public Adiacenza(Genes gene1, Genes gene2, double peso) {
		super();
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.peso = peso;
	}
	public Genes getGene1() {
		return gene1;
	}
	public Genes getGene2() {
		return gene2;
	}
	public double getPeso() {
		return peso;
	}
	public void setGene1(Genes gene1) {
		this.gene1 = gene1;
	}
	public void setGene2(Genes gene2) {
		this.gene2 = gene2;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
}
