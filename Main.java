package family_tree;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class Main {
	public static void main(String[] args) {
		
		DBPediaRequest[] requests = {
				new DBPediaRequest("Elizabeth II", DBPediaRequestQueryString.level1("Elizabeth II", 4)),
				new DBPediaRequest("Prince Philip, Duke of Edinburgh", DBPediaRequestQueryString.level1("Prince Philip, Duke of Edinburgh", 1)),
				new DBPediaRequest("Anne, Princess Royal", DBPediaRequestQueryString.level2("Anne, Princess Royal", 2)),
				new DBPediaRequest("Charles, Prince of Wales", DBPediaRequestQueryString.level2_4("Charles, Prince of Wales", 2)),
				new DBPediaRequest("Prince Edward, Earl of Wessex", DBPediaRequestQueryString.level2("Prince Edward, Earl of Wessex", 2)),
				new DBPediaRequest("Prince Andrew, Duke of York", DBPediaRequestQueryString.level2("Prince Andrew, Duke of York", 2)),
				new DBPediaRequest("Camilla, Duchess of Cornwall", DBPediaRequestQueryString.level2_2("Camilla, Duchess of Cornwall", 1)),
				new DBPediaRequest("Mark Phillips", DBPediaRequestQueryString.level2_6("Mark Phillips", 5)),
				new DBPediaRequest("Sarah, Duchess of York", DBPediaRequestQueryString.level2_2("Sarah, Duchess of York", 1)),
				new DBPediaRequest("Sophie, Countess of Wessex", DBPediaRequestQueryString.level2_2("Sophie, Countess of Wessex", 1)),
				new DBPediaRequest("Prince William, Duke of Cambridge", DBPediaRequestQueryString.level2("Prince William, Duke of Cambridge", 2)),
				new DBPediaRequest("Catherine, Duchess of Cambridge", DBPediaRequestQueryString.level2_2("Catherine, Duchess of Cambridge", 1)),
				new DBPediaRequest("Prince Harry", DBPediaRequestQueryString.level3("Prince Harry", 2)),
				new DBPediaRequest("Peter Phillips", DBPediaRequestQueryString.level2_3("Peter Phillips", 3)),
				new DBPediaRequest("Autumn Phillips", DBPediaRequestQueryString.level2_2("Autumn Phillips", 1)),
				new DBPediaRequest("Zara Phillips", DBPediaRequestQueryString.level2_3("Zara Phillips", 2)),
				new DBPediaRequest("Mike Tindall", DBPediaRequestQueryString.level2_2("Mike Tindall", 1)),
				new DBPediaRequest("Princess Beatrice of York", DBPediaRequestQueryString.level3("Princess Beatrice of York", 2)),
				new DBPediaRequest("Princess Eugenie of York", DBPediaRequestQueryString.level3("Princess Eugenie of York", 2)),
				new DBPediaRequest("Lady Louise Windsor", DBPediaRequestQueryString.level3("Lady Louise Windsor", 2)),
				new DBPediaRequest("James, Viscount Severn", DBPediaRequestQueryString.level3("James, Viscount Severn", 2)),
				new DBPediaRequest("Prince George of Cambridge", DBPediaRequestQueryString.level3("Prince George of Cambridge", 2)),
				new DBPediaRequest("Princess Charlotte of Cambridge", DBPediaRequestQueryString.level3("Princess Charlotte of Cambridge", 2))
		};
		
		String[] requests2 = {
				"C:/Users/eva/Desktop/PD_Royal_Family_Tree/Isla Phillips.rdf",
				"C:/Users/eva/Desktop/PD_Royal_Family_Tree/Mia Tindall.rdf",
				"C:/Users/eva/Desktop/PD_Royal_Family_Tree/Savannah Phillips.rdf"
				
		};
		
		FamilyTreeGraph graph = new FamilyTreeGraph();
		
		for(int i = 0; i < requests.length; i++) {
			DBPediaRequest req = requests[i];

	        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", req.queryString.asQuery());
	        
	        Model model = exec.execConstruct();
			Node n = new Node(model);
			
			graph.addSpouse(n);
			graph.add(n);
		}
		
		for(int i = 0; i < requests2.length; i++) {
			Model model = ModelFactory.createDefaultModel();
			model.read(requests2[i]) ;
			Node n = new Node(model);
			graph.addSpouse(n);
			graph.add(n);	
		}
		

		JFrame frame = RoyalFamilyTree(graph);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
         
    }
	
	public static JFrame RoyalFamilyTree(FamilyTreeGraph familyGraph)
	{
		JFrame frame = new JFrame("Royal Family Tree");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
			
		ArrayList<Node> nodes = familyGraph.get(familyGraph.root);
		
		try
		{
			familyGraph.createDrawGraph(graph);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		frame.getContentPane().add(graphComponent);
		
		return frame;
	}
}