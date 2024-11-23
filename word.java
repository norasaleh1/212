
public class word {
	
	String word ; 
	LinkedList<Integer>id; 

	public word(String word){
	this.word=word ; 
	id = new LinkedList<Integer>() ;
	}
	
	// this method takes the id then checks if it exist in the list 
	public boolean exists(Integer ID ){
	// incase the ID list is empty return false
	if (id.empty()) return false ; 
	
	// start from the beginnig of the linked list
	id.findFirst();
	 //this while loop check every element of the linked list 
	 while (!id.last()){ 
	 if (id.retrieve().equals(ID))
	 return true ; 
	 // to go for the next element 
	 id.findNext(); 

	 }
	 // incase we have only one element we need to check if the id is the same 
	 if (id.retrieve().equals(ID))
	 return true ; 
	// there is no existing ID same as the id recevied so return false 
	 return false ; 
	}
	
	public void addID (int ID){
	// this method takes the id then check if it exist in list then adds it to the ID list
	if (!exists(ID))
	id.insert(ID);

	}
	public String toString() {
        return "Word: " + word + ", Document IDs: " + id.toString();
    }
}
