package family_tree;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;

public class Main {
	public static void main(String[] args) {
		
		DBPediaRequest[] requests = {
				new DBPediaRequest("Elizabeth II", DBPediaRequestQueryString.level1("Elizabeth II", 4)),
				new DBPediaRequest("Prince Philip, Duke of Edinburgh", DBPediaRequestQueryString.level1("Prince Philip, Duke of Edinburgh", 1)),
				new DBPediaRequest("Anne, Princess Royal", DBPediaRequestQueryString.level2("Anne, Princess Royal", 2)),
				//new DBPediaRequest("Charles, Prince of Wales", DBPediaRequestQueryString.level2("Charles, Prince of Wales", 2)),
				new DBPediaRequest("Prince Edward, Earl of Wessex", DBPediaRequestQueryString.level2("Prince Edward, Earl of Wessex", 2)),
				new DBPediaRequest("Prince Andrew, Duke of York", DBPediaRequestQueryString.level2("Prince Andrew, Duke of York", 2)),
				new DBPediaRequest("Diana, Princess of Wales", DBPediaRequestQueryString.level1("Diana, Princess of Wales", 2)),
				new DBPediaRequest("Camilla, Duchess of Cornwall", DBPediaRequestQueryString.level2_2("Camilla, Duchess of Cornwall", 1)),
/*				new DBPediaRequest("Mark Phillips", DBPediaRequestQueryString.level2_2("Mark Phillips", 2)),
				new DBPediaRequest("Timothy Laurence", DBPediaRequestQueryString.level2_2("Timothy Laurence", 2))*/
				new DBPediaRequest("Sarah, Duchess of York", DBPediaRequestQueryString.level2_2("Sarah, Duchess of York", 1)),
				new DBPediaRequest("Sophie, Countess of Wessex", DBPediaRequestQueryString.level2_2("Sophie, Countess of Wessex", 1)),
				new DBPediaRequest("Prince William, Duke of Cambridge", DBPediaRequestQueryString.level2("Prince William, Duke of Cambridge", 2)),
				new DBPediaRequest("Catherine, Duchess of Cambridge", DBPediaRequestQueryString.level2_2("Catherine, Duchess of Cambridge", 1)),
				new DBPediaRequest("Prince Harry", DBPediaRequestQueryString.level3("Prince Harry", 1)),
				new DBPediaRequest("Autumn Phillips", DBPediaRequestQueryString.level2_2("Autumn Phillips", 1)),
				new DBPediaRequest("Peter Phillips", DBPediaRequestQueryString.level2_3("Peter Phillips", 3)),
				new DBPediaRequest("Zara Phillips", DBPediaRequestQueryString.level2_3("Zara Phillips", 2)),
				new DBPediaRequest("Mike Tindall", DBPediaRequestQueryString.level2_2("Mike Tindall", 1)),
				new DBPediaRequest("Princess Beatrice of York", DBPediaRequestQueryString.level3("Princess Beatrice of York", 2)),
				new DBPediaRequest("Princess Eugenie of York", DBPediaRequestQueryString.level3("Princess Eugenie of York", 2)),
				new DBPediaRequest("Lady Louise Windsor", DBPediaRequestQueryString.level3("Lady Louise Windsor", 2)),
				new DBPediaRequest("James, Viscount Severn", DBPediaRequestQueryString.level3("James, Viscount Severn", 2)),
				new DBPediaRequest("Prince George of Cambridge", DBPediaRequestQueryString.level3("Prince George of Cambridge", 2)),
				new DBPediaRequest("Princess Charlotte of Cambridge", DBPediaRequestQueryString.level3("Princess Charlotte of Cambridge", 2)),
				//new DBPediaRequest("Isla Phillips", DBPediaRequestQueryString.level3_2("Isla Phillips", 2))
				//new DBPediaRequest("Savannah Phillips", DBPediaRequestQueryString.level3("Savannah Phillips", 2))
			
		};
		
		for(int i = 0; i < requests.length; i++) {
			DBPediaRequest req = requests[i];
			
	        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", req.queryString.asQuery());

	        Model model = exec.execConstruct();
	        
	        try {
				FileWriter fileOut = new FileWriter(req.name+".rdf");
				RDFDataMgr.write(fileOut, model, RDFFormat.RDFXML_PLAIN);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
