
class DocRanking{
	int id;
	int ranked1;
public DocRanking(int id, int r)	{
	this.id=id;
	
	ranked1=r;
	
}
	
public void displayR() {
	
	
System.out.printf("%-8d%-8d\n",id,ranked1);	
	
}}






public class Ranking {
 static String QR;
 static invertINDEX_BST inverted1;
 static index indexx;
 static LinkedList<Integer>	fulldocqury;
 static LinkedList<DocRanking> fulldocR;
	
public Ranking(invertINDEX_BST inverted1,index indexx,String QR ) {
	this.inverted1=inverted1;
	
	this.indexx=indexx;
	
	this.QR=QR;
	
	fulldocqury=new LinkedList<Integer>();
	
	fulldocR=new LinkedList<DocRanking>();
	
	
}

//return the doc of id fouded 

public static doc getDoccId (int id) {
	
	
	return indexx.getDOCid(id)	;
	
}

//count and return the frequency of item  
public static int calculateWordFrequency(doc DOC,String item) {
	
	int countF=0;
	LinkedList<String>word=DOC.word;
	if(word.empty())  
		return 0;
	word.findFirst();
	while (!word.last()) {
		
		if(word.retrieve().equalsIgnoreCase(item))
			countF++;
		
		word.findNext();
		
	}
	
	if(word.retrieve().equalsIgnoreCase(item))
		countF++;
	return countF;
	
	
}


//count and return the frequency of query 
public static int totalRANK (doc DOC ,String QR ) {
	
	if(QR.length()==0)
	       return 0;
	String item[]=QR.split(" ");
	int totalF=0;
	for (int i =0;i<item.length;i++)
	{
		totalF=totalF+calculateWordFrequency(DOC,item[i].trim().toLowerCase());
			
	}
	return totalF;
}


public static void RANKEDQU(String QR ) {
	LinkedList<Integer> qr=new LinkedList<Integer>();
	if(QR.length()==0)
	return;
	String item[]=QR.split("\\s+");
	boolean locate=false;
	
	for (int i =0;i<item.length;i++)
	{
		locate=inverted1.searchINV(item[i].trim().toLowerCase());
		if(locate)
			qr=inverted1.invindex.retrieve().id;
		ADDl(qr);
		
		
	}
}
//To insert IDs in order
public static void ADDl(LinkedList<Integer> qr) {
	if(qr.empty())
	   return;
	   qr.findFirst();
	   while(!qr.empty()) {
		   boolean locate=presentINOUTCOME1(fulldocqury,qr.retrieve());
		   if(!locate) {
			   insertId(qr.retrieve());  
			   
			  
		   }//close if 
		   if(!qr.last()) {
			   qr.findNext();
			   
		   }
		   else {
			   break;
		   }
			    
		   
		   
	   }	//end loop
	  
}

public static void insertId(Integer id) {
	if(fulldocqury.empty()) {
		fulldocqury.insert(id);
		return;
	}
	fulldocqury.findFirst();
	
	while(!fulldocqury.last()) {
		if(id<fulldocqury.retrieve()) {
			Integer fulldocquryID =fulldocqury.retrieve() ;
			fulldocqury.update(id);
			fulldocqury.insert(fulldocquryID);//.update(fulldocquryID);
			return;
		}
		else
			fulldocqury.findNext();
	}
	if(id<fulldocqury.retrieve()) {
		Integer fulldocquryID =fulldocqury.retrieve() ;
		fulldocqury.update(id);
		fulldocqury.insert(fulldocquryID);//.update(fulldocquryID);
		return;
	}
	else {
		fulldocqury.insert(id);
	}
	
}

public static void insertinList() {
	RANKEDQU(QR);
	//System.out.println("DOne");
	if(fulldocqury.empty()) {
		System.out.println("the Query is Empty");
		return;
	}
	
	fulldocqury.findFirst();
	while(!fulldocqury.last()) {
		doc document = getDoccId(fulldocqury.retrieve());
		int rankScore=totalRANK(document ,QR);
		insertList(new DocRanking(fulldocqury.retrieve(),rankScore));
		fulldocqury.findNext();
		
	}
	doc document = getDoccId(fulldocqury.retrieve());
	int rankScore=totalRANK(document,QR);
	insertList(new DocRanking(fulldocqury.retrieve(),rankScore));
	
}
public static void insertList(DocRanking docR) {
	if(fulldocR.empty()) {
		fulldocR.insert(docR);
		return;
	}
	fulldocR.findFirst();
	while(!fulldocR.last()) {
		if(docR.ranked1>fulldocR.retrieve().ranked1) {
			DocRanking DocRank = fulldocR.retrieve();
			fulldocR.update(docR);
			fulldocR.update(DocRank);
			return ;
		}
		else
			fulldocR.findNext();
	}
	if(docR.ranked1>fulldocR.retrieve().ranked1) {
		DocRanking DocRank = fulldocR.retrieve();
		fulldocR.update(docR);
		fulldocR.update(DocRank);
		return ;
	}
	else
		fulldocR.insert(docR);
	
}


//check if id present in outcomelist
public static boolean presentINOUTCOME1(LinkedList<Integer>OUTCOME1,Integer id) {
	if(OUTCOME1.empty())
		return false;
	OUTCOME1.findFirst();
	while(!OUTCOME1.last()) {
		if(OUTCOME1.retrieve().equals(id)) {
			
			return true;
		}
		
		OUTCOME1.findNext();
	} 
	if (OUTCOME1.retrieve().equals(id)) {
		
		return true;
		
	}
	
	return false;
}

public static void displayAllDoc() {
	if(fulldocR.empty()) {
		System.out.println("All Document Rank Empty");
		return;
	}
	System.out.printf("%-8s%-8s\n","ID","total");
	fulldocR.findFirst();
	while(!fulldocR.last()) {
		fulldocR.retrieve().displayR();
		fulldocR.findNext();
	}
	fulldocR.retrieve().displayR();
}



}