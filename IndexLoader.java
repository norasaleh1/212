
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

 public  class  IndexLoader {
	
	LinkedList<String> skipWords;
	index documentIndex ;
	invertINDEX inverted;
	invertINDEX_BST inverted_BST;
	int numT=0;
	LinkedList<String> Uvocab=new LinkedList<>();
	
	
	public IndexLoader()
	{
		skipWords=new LinkedList<>();
		documentIndex=new index();
		inverted=new invertINDEX();	
		inverted_BST=new invertINDEX_BST();
	}
	//the method 
	public void loadskipWords (String fileName) {
		
		try {
			
			File F=new File(fileName);
			Scanner S=new Scanner(F);
			      while( S.hasNextLine() )
			      {
			    	String textLine =S.nextLine();  
			    	  skipWords.insert(textLine);
			    	  
			    	  }
		          
	               	}  
		
		catch (IOException IE) {
			
			IE.printStackTrace();
			
			
		}
	
catch( Exception FNE ) {
	
		System.out.print("File Not found ");
}
		
	
	}
	 
	
	public void loadFullDocument (String fileName)
	
	{
			String textLine=null;
			try {
				
					File F=new File(fileName);
					Scanner S=new Scanner(F);
					
					
				S.nextLine();
				while  (S.hasNextLine()) {
					
					textLine=S.nextLine();
					if(textLine.trim().length()<3)
					{
						System.out.print("Empty line encountered, Moving to the following line="+textLine);
						break;
					}
							
					String str=textLine.substring(0,textLine.indexOf(','));
					int ID=Integer.parseInt(str.trim());
					String Text=textLine.substring(textLine.indexOf(',')+1).trim();	
					numTandUvocab(Text);
					
					LinkedList<String>wordListInDoc=generateWordLinkedList(Text,ID);
					documentIndex.addD(new doc(ID, wordListInDoc , Text));
				}
				
			}	catch(Exception E) {
					System.out.print(" you reached the end of the file ");
					
					
				}			
				
	}	
			
		public LinkedList<String> generateWordLinkedList(String Text,int ID){
			
			LinkedList<String> wordListInDoc=new LinkedList<String>();
			generateIndexAndInvertedIndex(Text,wordListInDoc,ID);
			
			return wordListInDoc;
			
		}
		
	
	
	public void generateIndexAndInvertedIndex(String Text,LinkedList<String>wordListInDoc,int ID) {
		Text=Text.replaceAll("-", " ");
		Text=Text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]",  "");
		String [] generateTokens=Text.split("\\s+")	;
				
		for(String s:generateTokens) {
					
							
			if(!checkExistenceInSkippedWords(s)) {
				
				wordListInDoc.insert(s);
				inverted.add(s,ID);
				inverted_BST.add(s, ID);
			}
			
		}
		
		}
	
	
	public void numTandUvocab(String Text ) {
		Text=Text.replaceAll("\'", " ");
		Text=Text.replaceAll("-", " ");
		Text=Text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]",  "");
		String [] generateTokens=Text.split("\\s+")	;
		numT=numT+generateTokens.length;
				
		for(String s:generateTokens) {
			if(!Uvocab.search(s)) {
				
				Uvocab.insert(s);
			}
		}
		
		
		
	}
	
	
public boolean checkExistenceInSkippedWords(String Term){
			
			if(skipWords==null|| skipWords.empty())
			    return false;
			skipWords.findFirst();
			while(!skipWords.last()) {
				if(skipWords.retrieve().equals(Term)) {
					
					return true;
				}
			
			skipWords.findNext();
		} if (skipWords.retrieve().equals(Term)) {
			
			       return true;
		
		}
		return false;
	}
	
	public void loadFileContents (String skipWordsfile  ,String FullDocumentfile) {
		loadskipWords(skipWordsfile);
		loadFullDocument(FullDocumentfile);
		
			
	}	
	
	public void displayDOCid(LinkedList<Integer> ID) {
		
		if(ID.empty()) {
			System.out.println("There is no existing document");
			return ;
		}
		
		ID.findFirst();
		while(!ID.last()) {
			doc document = documentIndex.getDOCid(ID.retrieve());
			if(document!=null) {
				System.out.println("Document "+ document.id +"="+document.text);
			}
			ID.findNext();
		}
		doc document = documentIndex.getDOCid(ID.retrieve());
		if(document!=null) {
			System.out.println("Document "+ document.id +"="+document.text);
		}
		System.out.println("------------------------");
	}
	public void displayskipWords() {
		skipWords.display();
		
	}
	
	public static void main(String[]args){
		
		IndexLoader L=new IndexLoader();
		
		L.loadFileContents("stop.txt","dataset.csv");
		L.documentIndex.displayD();
		System.out.print("\n======================");
		L.inverted.display();
		System.out.println("number of Tokens="+L.numT + "tokens");
		System.out.println("number of vocabulary="+L.Uvocab.num +"uniquewords");
		
		
		
		
      Queryprocessing p=new Queryprocessing(L.inverted);
		LinkedList ocm=Queryprocessing.AndQ("market AND sports ");
		L.displayDOCid(ocm);
      Queryprocessing x=new Queryprocessing(L.inverted);
      LinkedList a=Queryprocessing.ORq(" referees OR resilience  ");
      //L.displayDOCid(a);
	}
	
	
	
}

