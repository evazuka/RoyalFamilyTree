package family_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class FamilyTreeGraph {
	public Node root;
	
	private boolean[][] map = new boolean[100][100];
	
	public void add(Node node) {
		
		Node parent = find(node.parentString, root);
		
		if(parent != null) {
			parent.children.add(node);
			if(parent.spouse != null)
				parent.spouse.children.add(node);
		} else {
			if(root == null)
				root = node;
		}
	}
	
	public void createDrawGraph(mxGraph graph) {
		Object parent = graph.getDefaultParent();
		Object draw = drawForNode(graph, parent, root, 0, 0, true);
	}
	
	private Object drawForNode(mxGraph graph, Object parent, Node node, Integer x, Integer y, Boolean drawNext) {
		if(node == null || node.drawn)
			return null;
		
		while(map[x][y]) {
			x++;
		}
		
		Object draw = graph.insertVertex(parent, null, node.name + '\n' + node.birthDate, x*190, y*150, 165, 100);
		node.drawn = true;
		
		map[x][y] = true;
		
		if(!drawNext)
			return draw;
		
		if(node.spouse != null) {
			Object draw2 = drawForNode(graph, parent, node.spouse, x+1, y, false);
			if(draw2 != null) {
				graph.insertEdge(parent, null, "", draw, draw2);
			}
		}
		
		for(Integer i = 0; i < node.children.size(); i++) {
			Object draw3 = drawForNode(graph, parent, node.children.get(i), x+i, y+1, true);
			if(draw3 != null) {
				graph.insertEdge(parent, null, "", draw, draw3);
			}
		}
				
		return draw;
	}
	
	public ArrayList<Node> get(Node node) {
		ArrayList<Node> ret = new ArrayList<Node>();
		
		if(node != null) {
			ret.add(node);
			ret.addAll(node.children);
			for(Integer i = 0; i < node.children.size(); i++) {
				ret.addAll(get(node.children.get(i)));
			}
		}
		
		return ret;		
	}
	
	public void addSpouse(Node node) {
		Node existingSpouse = find(node.spouseString, root);
		if(existingSpouse != null) {
			existingSpouse.spouse = node;
			node.spouse = existingSpouse;
		}
	}
	
	private Node find(String key, Node node) {
		Node ret = null;
		
		if(node == null)
			return null;
		
		if(node.key.equals(key))
			ret =  node;
		else {
			if(node.spouse != null && node.spouse.key.equals(key)) {
				ret = node.spouse;
			} else {
				for(Integer i = 0; i < node.children.size(); i++) {
					ret = find(key, node.children.get(i));
					if(ret != null) {
						break;
					}
				}
			}
		}
				
		return ret;
	}
}
