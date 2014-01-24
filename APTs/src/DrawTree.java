import java.util.ArrayList;
import java.util.HashMap;


public class DrawTree {
      public String[] draw(int[] parents, String[] names){
         int index_of_root = findRoot(parents, names);
         String root = names[index_of_root]; 
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         map = recursiveDraw(root, parents, names, map);

         // turn map into output
         ArrayList<String> output = new ArrayList<String>();
         output = mapToDrawing("", root, 0, output, false, map);
         
         String[] out = output.toArray(new String[output.size()]);
         return out;
      }
      
      public ArrayList<String> mapToDrawing(String preface, String root, int depth,
    		  ArrayList<String> list, boolean sibling, 
    		  HashMap<String, ArrayList<String>> map){
    	  
    	  String node = preface + "+-" + root; 
    	  if(sibling){ preface = preface + "| "; }
    	  else{  preface = preface + "  "; }
    	  
//    	  System.out.println(node);
    	  list.add(node); 
    	  if(map.containsKey(root)){
    		  ArrayList<String> children = map.get(root);
    		  String lastChild = children.get(children.size()-1);
        	  if(!children.isEmpty()){
        		  for(String child : children){
        			  if(map.containsKey(child) & !child.equals(lastChild)){
        				  sibling = true; 
        			  }
        			  else{ sibling = false; } 
            		  list = mapToDrawing(preface, child, depth+1, list, sibling, map);
            	  }
        	  }
    	  }
 
    	  return list; 
      }
      
      public HashMap<String, ArrayList<String>> recursiveDraw(String root, 
    		  int[] parents, String[] names, 
    		  HashMap<String, ArrayList<String>> map){
    	  
    	  ArrayList<String> children = new ArrayList<String>(); 
    	  
    	  // find all values whose parent == root
    	  for(int i=0; i < names.length; i++){
    		  int parent = parents[i];
    		  if(parent!=-1){
    			  String name_of_parent = names[parent];
        		  if(name_of_parent.equals(root)){
        			  // add to list of children
        			  children.add(names[i]);
        		  }
    		  }
    		  
    	  }
    	  if(children.isEmpty()){
    		  return map; 
    	  }
    	  else{
    		  map.put(root, children);
    		  for(String child : children){
    			  map = recursiveDraw(child, parents, names, map);
    		  }
    		  return map;
    	  }
    	  
      }
      
      public int findRoot(int[] parents, String[] names){
    	  int index_of_root=0; 
    	  for(int i=0; i < names.length; i++){
    		  int parent = parents[i];
    		  if(parent==-1){
    			  index_of_root=i;
    		  }
    	  }
    	  return index_of_root; 
      }
      

}
