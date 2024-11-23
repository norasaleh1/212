
public class index {
LinkedList<doc>ALLdoc;
public index() {
	ALLdoc=new LinkedList<doc>();
}

public void addD(doc D) {
	ALLdoc.insert(D);
}

public doc getDOCid(int ID) {
	
	if(ALLdoc.empty()) {
		System.out.println("There is no existing document");
		return null;
	}
	ALLdoc.findFirst();
	while(!ALLdoc.last()) {
		if(ALLdoc.retrieve().id==ID)
			return ALLdoc.retrieve();
		ALLdoc.findNext();
	}
	if(ALLdoc.retrieve().id==ID)
		return ALLdoc.retrieve();
	return null;
}

public void displayD() {
	if(ALLdoc==null) {
		System.out.println("NULL DOCUMENTS");
		return;
	}
	else if(ALLdoc.empty()) {
		System.out.println("EMPTY DOCUMENT");
		return;
	}
	
	ALLdoc.findFirst();
	while(!ALLdoc.last()) {
		doc D =ALLdoc.retrieve();
		System.out.println("\n---------------");
		System.out.println("id:"+ D.id);
		//all words in this document are:
		D.word.display();
		ALLdoc.findNext();
		
	}
	doc D =ALLdoc.retrieve();
	System.out.println("\n--------------");
	System.out.println("id:"+ D.id);
	//the last word in this document
	D.word.display();
	
}
public LinkedList<Integer> retrieve_doc(String x){
LinkedList<Integer> list = new LinkedList<Integer>();
if(ALLdoc.empty()){
	System.out.println("there is no documents");
   return null;}
   ALLdoc.findFirst();
   while(!ALLdoc.last()){
   if(ALLdoc.retrieve().word.search(x.toLowerCase().trim()))
   list.insert(ALLdoc.retrieve().id);
   ALLdoc.findNext();}
  if(ALLdoc.retrieve().word.search(x.toLowerCase().trim()))
   list.insert(ALLdoc.retrieve().id);
   return list;}
   


//simple test
public static void main(String[]args) {
	index ind1=new index();
	LinkedList<String>word=new LinkedList<>();
	word.insert("national");
	word.insert("flag");
	//doc D1=new doc(1,word);
	//ind1.addD(D1);
	
	LinkedList<String>word2=new LinkedList<>();
	word2.insert("green");
	word2.insert("color");
	//doc D2=new doc(2,word2);
	//ind1.addD(D2);
	
	ind1.displayD();
}
}
