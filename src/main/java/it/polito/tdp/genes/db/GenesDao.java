package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {

	public void getAllGenes(Map<String,Genes>idMap){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes WHERE Essential = 'Essential'";
		//List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!idMap.containsKey(res.getString("GeneID"))) {
					Genes genes = new Genes(res.getString("GeneID"), 
							res.getString("Essential"), 
							res.getInt("Chromosome"));
					idMap.put(res.getString("GeneID"), genes);
				}
			}
			res.close();
			st.close();
			conn.close();


		} catch (SQLException e) {
			throw new RuntimeException("Error Connection Database", e);

		}
	}

	public List<Adiacenza> getAdiacenza(Map<String,Genes>idMap){
		String sql="SELECT GeneID1,GeneID2, Expression_Corr " + 
				"FROM interactions " + 
				"WHERE GeneID1<>GeneID2";
		List<Adiacenza> result = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				if(idMap.containsKey(res.getString("GeneID1")) && idMap.containsKey(res.getString("GeneID2"))) {
					Adiacenza a = new Adiacenza(idMap.get(res.getString("GeneID1")), idMap.get(res.getString("GeneID2")), res.getDouble("Expression_Corr"));
					result.add(a);
				}
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}




}
