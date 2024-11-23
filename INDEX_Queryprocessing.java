
public class INDEX_Queryprocessing {
	static index Index;
	
	
public INDEX_Queryprocessing(index i ) {
		
		Index=i;	
		
	}
public static LinkedList<Integer>  kindOfQuery(String x){
//here chekcing if it is (and) sentense or (or) sintence or(and or (both))sintence
if(!x.contains("AND")&&!x.contains("OR"))
return AndQ(x);
else if(x.contains("AND")&&!x.contains("OR"))
return AndQ(x);
else if(!x.contains("AND")&&x.contains("OR"))
return ORq(x);
else
return OrAndQ(x);
}

public static LinkedList<Integer>OrAndQ(String q){
    		LinkedList<Integer> listone=new  LinkedList<Integer>();
LinkedList<Integer> listtwo=new  LinkedList<Integer>();
if(q.length()==0) return listone;

String item[]=q.split("OR");

listone=AndQ(item[0]);
for(int i=1;i<item.length;i++){
listtwo=AndQ(item[i]);
listone=ORq(listone,listtwo);
}return listone;

}
	





	public static LinkedList<Integer>AndQ (String Q ){

      		LinkedList<Integer> listone=new  LinkedList<Integer>();
LinkedList<Integer> listtwo=new  LinkedList<Integer>();
String Item[]=Q.split("AND");
//for(int i=0 ; i<Item.length;i++) for checking only split
//System.out.println(Item[i]);


		// Split the query into words using "And"
if(Item.length==0)return listone;

listone=Index.retrieve_doc(Item[0].trim().toLowerCase());

		
		for (int i=1;i<Item.length;i++)
		{ 
				listtwo=Index.retrieve_doc(Item[i].trim().toLowerCase());

			  // If found, add the doc to the second list
	       listone=AndQ(listone, listtwo);
           // Merge the two lists using AND operation

			}
			
			
		
	return listone;
	}
	
	
	
	
	
	
	
	
	
	public static LinkedList<Integer>AndQ(LinkedList<Integer>ListOne,LinkedList<Integer>ListTwo){
	    // Method to merge two lists using AND operation

		LinkedList<Integer> OUTCOME =new LinkedList<Integer>();
          if(ListOne.empty()||ListTwo.empty())
		      return OUTCOME ;
        // If either of the lists is empty, return an empty list

	
		   ListOne.findFirst();
		    while(true)
		{
			
		boolean	Detected=presentINOUTCOME(OUTCOME,ListOne.retrieve());
		if(!Detected) { //in case not found in INOUTCOMElist
			
			ListTwo.findFirst();
			while(true)
			{
				if(ListTwo.retrieve().equals(ListOne.retrieve())) {
					OUTCOME.insert(ListOne.retrieve());
					break;
				}
				
				if(!ListTwo.last())
					ListTwo.findNext();
				else 
					  break;
					
			}//close of inner while

		}//close of in case not found
			
			
			if(!ListOne.last())
				ListOne.findNext();
		
			else 
				break;
		
		
		}//close of first while
	
	return OUTCOME;//turn a list that contains only the intersection of two lists using an AND condition
	
	
	
	
	
	
	
	}
	
	
	public static boolean presentINOUTCOME(LinkedList<Integer>OUTCOME,Integer id) {
		 //check if id present IN OUTCOMElist
		if(OUTCOME.empty()) return false;
		OUTCOME.findFirst();
		
while(!OUTCOME.last()) {
if(OUTCOME.retrieve().equals(id))
{
				
				return true;
				
			}
			
			OUTCOME.findNext();
		}
		
if(OUTCOME.retrieve().equals(id)) {
 return true;

}
return false;	
}
   
public static LinkedList<Integer> ORq(String q){
		// Create two linked lists for query processing (OR)
LinkedList<Integer> listone=new  LinkedList<Integer>();
LinkedList<Integer> listtwo=new  LinkedList<Integer>();
String Item[]=q.split("OR");
		// Split the query into words using "OR"
if(Item.length==0)return listone;
//using sarch in class inverted to find word if it exixte
		

listone=Index.retrieve_doc(Item[0].trim().toLowerCase());

		
		for (int i=1;i<Item.length;i++)
		{ 
				listtwo=Index.retrieve_doc(Item[0].trim().toLowerCase());

			  // If found, add the doc to the second list
	       listone=AndQ(listone, listtwo);
           // Merge the two lists using AND operation

			}
return listone;
}
	
	
	
public static LinkedList<Integer>ORq(LinkedList<Integer>ListOne,LinkedList<Integer>ListTwo){
 // Method to merge two lists using Or operation
LinkedList<Integer> OUTCOME =new LinkedList<Integer>();
if(ListOne.empty()&&ListTwo.empty())
 return OUTCOME ;
// If both of the lists is empty, return an empty list
ListOne.findFirst();
if(!ListOne.empty()){
 while(true)
{
boolean	Detected=presentINOUTCOME(OUTCOME,ListOne.retrieve());
if(!Detected) { //in case not found in INOUTCOMElist
OUTCOME.insert(ListOne.retrieve());//just will added in list outcome
}
if(!ListOne.last())
ListOne.findNext();
else break;
}}

if(!ListTwo.empty()){
 while(true)
{
boolean	Detected=presentINOUTCOME(OUTCOME,ListTwo.retrieve());
if(!Detected) { //in case not found in INOUTCOMElist
OUTCOME.insert(ListTwo.retrieve());//just will added in list outcome
}
if(!ListTwo.last())
ListTwo.findNext();
else break;
}}
return OUTCOME;
}	}