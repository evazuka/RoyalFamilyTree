package family_tree;

import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class Node{
	public Boolean drawn = false;
	public String key;
	public String name;
	public String birthDate;
	public Node spouse;
	public String spouseString;
	public String parentString;
	public ArrayList<Node> children = new ArrayList<Node>();
	
	public Node(Model m) {
		this.name = getNameFromModel(m);
		this.birthDate = getBirthDateFromModel(m);
		this.key = getKeyFromModel(m);
		this.parentString = getParentFromModel(m);
		this.spouseString =  getSpouseFromModel(m);
	}
	
	public String getKeyFromModel(Model m) {
		String key = "";
		StmtIterator iterator = m.listStatements();
		if(iterator.hasNext()) {
			Statement st = iterator.nextStatement();
			key = st.getSubject().toString();
		}		
		return key;
	}
	
	public String getNameFromModel(Model m) {
		String name = "";
		StmtIterator iterator = m.listStatements();
		for(;iterator.hasNext();) {
			Statement st = iterator.nextStatement();
			if(st.getPredicate().getLocalName().equals("name")) {
				name = st.getObject().toString().replaceAll("@en", "");
			}
		}		
		return name;
	}
	
	public String getParentFromModel(Model m) {
		String parent = "";
		StmtIterator iterator = m.listStatements();
		for(;iterator.hasNext();) {
			Statement st = iterator.nextStatement();
			if(st.getPredicate().getLocalName().equals("relationshipchildOf")) {
				parent = st.getObject().toString();
			}
		}		
		return parent;
	}
	
	public String getSpouseFromModel(Model m) {
		String res = "";
		StmtIterator iterator = m.listStatements();
		for(;iterator.hasNext();) {
			Statement st = iterator.nextStatement();
			if(st.getPredicate().getLocalName().equals("relationshipspouseOf")) {
				res = st.getObject().toString();
				if(res.contains("http://dbpedia.org/resource"))
					break;
			}
		}		
		return res;
	}
	
	public String getBirthDateFromModel(Model m) {
		String birthDate = "";
		StmtIterator iterator = m.listStatements();
		for(;iterator.hasNext();) {
			Statement st = iterator.nextStatement();
			if(st.getPredicate().getLocalName().equals("birthDate")) {
				birthDate = st.getObject().toString().replaceAll("\\^\\^http://www.w3.org/2001/XMLSchema#date", "");
			}
		}		
		return birthDate;
	}
}