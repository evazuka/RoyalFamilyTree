package family_tree;

import org.apache.jena.query.ParameterizedSparqlString;

public class DBPediaRequestQueryString {
	public static ParameterizedSparqlString level1(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbp:  <http://dbpedia.org/property/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:spouseOf ?spouse ."
	            + "?person rel:parentOf ?children. }"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:spouse ?spouse ."
	            + "?person dbp:issue ?children. "
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
	
	public static ParameterizedSparqlString level2(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbp:  <http://dbpedia.org/property/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:spouseOf ?spouse ."
	            + "?person rel:childOf ?parent ."
	            + "?person rel:parentOf ?children. }"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:spouse ?spouse ."
	            + "?person dbo:parent ?parent ."
	            + "?person dbp:issue ?children. "
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
	
	public static ParameterizedSparqlString level2_3(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbp:  <http://dbpedia.org/property/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:spouseOf ?spouse ."
	            + "?person rel:childOf ?parent ."
	            + "?person rel:parentOf ?children. }"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:spouse ?spouse ."
	            + "?person dbo:parent ?parent ."
	            + "?person dbp:children ?children. "
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
	
	public static ParameterizedSparqlString level2_2(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbp:  <http://dbpedia.org/property/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:spouseOf ?spouse .}"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:spouse ?spouse ."
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
	
	public static ParameterizedSparqlString level3(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:childOf ?parent .}"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:parent ?parent ."
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
	
	public static ParameterizedSparqlString level3_2(String name, int limit) { 
		return new ParameterizedSparqlString(""
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
	            + "PREFIX dbo:  <http://dbpedia.org/ontology/>\n"
	            + "PREFIX rel:  <http://purl.org/vocab/relationship>\n"
	            + "CONSTRUCT { "
	            + "?person foaf:name ?name ."
	            + "?person dbo:birthDate ?birth ."
	            + "?person rel:childOf ?parent .}"
	            + "WHERE {"
	            + "?person foaf:name ?name ."
	            + "?person foaf:name \""+name+"\"@en."
	            + "?person dbo:birthDate ?birth ."
	            + "?person dbo:child ?parent ."
	            + "FILTER (LANG(?name) = 'en') ."
	            + "} LIMIT "+limit);
	}
}
