import java.util.Scanner ;
public class main {



public static void main(String [] args) {
	IndexLoader d=new IndexLoader(); 
	d.loadFileContents("stop.txt", "dataset.csv");
	Scanner input=new Scanner(System.in);
	int ch=0;

	do {
		System.out.println("1-Reletive a term (using index with list, inverted index with lists, inverted index with BST )");
		System.out.println("2-Boolean retrieval");
		System.out.println("3-Ranked retrieval");
		System.out.println("4-Indexed documents: print all document");
		System.out.println("5-number of documents in the index");
	   System.out.println("6-number of unique words in indexed");
		System.out.println("7-show inverted index with list of lists");
		System.out.println("8-show inverted index with BST");
		System.out.println("9-indexed tokens: to show number of vocabulary and tokens in the index");
		System.out.println("10-exit");
		ch=input.nextInt();
		switch(ch) {
		case 1:
			System.out.println("enter a term to Reletive");
			String Term= input.next();
			Term=Term.toLowerCase().trim();
			System.out.println(":using index with list");
			LinkedList<Integer>R= d.documentIndex.retrieve_doc(Term);
			System.out.print("word:"+Term+"=");
			R.display();
			System.out.println("....................................");
			System.out.println("-inverted index with lists");
			boolean find= d.inverted.search(Term);
			if(find) 
			d.inverted.invindex.retrieve().id.display();
			else
			System.out.println("not found in inverted index with lists");
			System.out.println("-inverted index with BST");
			boolean findN2= d.inverted_BST.searchINV(Term);
			if(findN2) {
			d.inverted_BST.invindex.retrieve().id.display();
			}
			else
			System.out.println("not found in inverted index with lists");
			break;
		case 2:
			input.nextLine();
			System.out.println("Enter a QUERY to retrieve");
			String Q=input.nextLine().toLowerCase().replaceAll(" and"," AND").replaceAll(" or"," OR");
			System.out.println("select which query you want:");
			System.out.println("1-index");
			System.out.println("2-inverted index list oflists");
			System.out.println("3-Bst");
			System.out.println("4-exit");
			int choice= input.nextInt();
			while(choice!=4) {
				if(choice==1) {
				INDEX_Queryprocessing iq= new INDEX_Queryprocessing(d.documentIndex);
				System.out.println("**********"+Q+"**********");
				LinkedList Result=INDEX_Queryprocessing.OrAndQ(Q);
				d.displayDOCid(Result);
				}
				else if(choice==2) {
					Queryprocessing QP = new Queryprocessing(d.inverted);
					System.out.println("**********"+Q+"**********");
					LinkedList Result=Queryprocessing.OrAndQ(Q);
					d.displayDOCid(Result);
				}
				else if(choice==3) {
					bstQuery iq= new bstQuery(d.inverted_BST);
					System.out.println("**********"+Q+"**********");
					LinkedList Result=bstQuery.OrAndQ(Q);
					d.displayDOCid(Result);
				}
				else if(choice==4) {
					break;
				}
				else
					System.out.println("Wrong input");
				
				System.out.println("select which query you want:");
				System.out.println("1-index");
				System.out.println("2-inverted index list oflists");
				System.out.println("3-Bst");
				System.out.println("4-exit");
				choice=input.nextInt();
			}
			break;
		case 3:
			input.nextLine();
			
			System.out.println("Enter a QUERY to Rank");
			
			String qy=input.nextLine().toLowerCase();
			
			Ranking rk=new Ranking( d.inverted_BST,d.documentIndex , qy);
			
			rk.insertinList();
			rk.displayAllDoc();
			
	    break;
		
		case 4:
			d.documentIndex.displayD();
			System.out.println("===================");
			
		break;
		
		case 5:
			System.out.println("number of documents="+d.documentIndex.ALLdoc.num);
		    System.out.println("===================");
		
		break;
			
		case 6:
			System.out.println("number of uniqe word="+d.inverted.invindex.num);
		    System.out.println("===================");
		
		break;
		case 7:
			d.inverted.display();
			break;
		case 8:
			d.inverted_BST.display_invindex();
			break;
		case 9:
			System.out.println("number of uniqe words including stop words"+d.Uvocab.num);
			System.out.println("tokens"+d.numT);
			break;
		case 10:
			System.out.println("Goodbye, Have a good day");
			break;
			
		default :
			System.out.println("Try again");
		}
	}while(ch!=10);
}
}
