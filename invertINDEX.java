
public class invertINDEX {

		LinkedList<word> invindex ;
		//word w;
		public invertINDEX(){
		invindex=new LinkedList<word>();
		}

		//same user search method

		public boolean search(String x){
		if(invindex.empty())
		return false;

		invindex.findFirst();
		while(!invindex.last()){
		if(invindex.retrieve().word.equals(x)){
		return true;}
		invindex.findNext();}
		if(invindex.retrieve().word.equals(x)){
		return true;}

		return false;
		}
		//add word to invindex list

		public void add(String x , int id)
		{
		if(search(x)==false){
		word qword =new word(x);
		qword.addID(id);
		invindex.insert(qword);
		}
		else{
		word Word1= invindex.retrieve();
		Word1.addID(id);}
		}
		//same user display method

		public  void display(){

		if(!invindex.empty()){
		invindex.findFirst();
		while(!invindex.last()){
		System.out.println("\n-----------------------------------");//S
	    System.out.print("the word: "+invindex.retrieve().word+" the id: ");
		invindex.retrieve().id.display();
		System.out.println();
		invindex.findNext();
		}
		System.out.println("\n-----------------------------------");
		System.out.print("the word: "+invindex.retrieve().word+" the id: ");
		invindex.retrieve().id.display();
		System.out.println();
		//System.out.println(invindex.retrieve());
		}
		else
		System.out.println("the inverted index is empty ");
		}

		}