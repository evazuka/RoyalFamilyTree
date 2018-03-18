package family_tree;

import org.apache.jena.query.ParameterizedSparqlString;

public class DBPediaRequest {
	public String name;
	public ParameterizedSparqlString queryString;
	
	public DBPediaRequest(String name, ParameterizedSparqlString queryString) {
		this.name = name;
		this.queryString = queryString;
	}
}