
public class invertINDEX_BST {

	bst<word> invindex;
	public invertINDEX_BST() {
		invindex = new bst<word>();
	}
	
	public void add(String word, int id) {
		if(!searchINV(word)) {
			word text =new word(word);
			text.id.insert(id);
			invindex.insert(word, text);
		}
		else {
			word w=invindex.retrieve();
			w.addID(id);
		
		}
		
	}
	
	public void add_invList(invertINDEX I) {
		if(I.invindex.empty()) {
			return ;
		}
		I.invindex.findFirst();
		while(!I.invindex.last()) {
			invindex.insert(I.invindex.retrieve().word, I.invindex.retrieve());
			I.invindex.findNext();
		}
		invindex.insert(I.invindex.retrieve().word, I.invindex.retrieve());
		
	}
	
	public boolean searchINV(String kay) {
		return invindex.findkey(kay);
	}
	
	
	public void display_invindex() {
		if(invindex==null) {
			System.out.println("the inverted index is null");
			return;
		}
		else if(invindex.empty()) {
			System.out.println("the inverted index is empty");
			return;
		}
		invindex.inorder();
			
	}
	
}
