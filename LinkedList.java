public class LinkedList<T> implements List<T> {
	private Node<T> head; 
	private Node<T> current; 
     int num=0;
	public LinkedList () 
	{ head = current = null; } 

	public boolean empty () 
	{ return head == null; } 

	public boolean last() {
	    if (current == null) {
	        System.out.println("List is empty or no current element!");
	        return true; 
	    }
	    return current.next == null;
	}


	public boolean full ()
	 { return false; } 
	 
	public void findFirst() {
	    if (!empty()) {
	        current = head; 	    } else {
	        current = null;
	        System.out.println("Attempting to access empty list in findFirst!");
	    }
	}

	 
	 public void findNext () 
	 { current = current.next; } 
	 
	 public T retrieve () 
	 { return current.data; } 
	 
	 public void update (T val) 
	 { current.data = val; }


	public void insert (T val) 
	
	{ num++;
		Node<T> tmp; 
	if (empty()) 
	{ current = head = new Node<T> (val); } 
	else { tmp = current.next; 
	current.next = new Node<T> (val); 
	current = current.next; 
	current.next = tmp; } }

	public void remove () 
	{ if (current == head) 
	{ head = head.next; } 
	else { Node<T> tmp = head; 
	while (tmp.next != current) 
	tmp = tmp.next; 
	tmp.next = current.next; } 
	if (current.next == null) 
	current = head; 
	else 
	current = current.next; }

	public boolean search(T x){
	Node<T> t=head;
	while (t!=null){
	if (t.data.equals(x))
	return true;
	else
		t=t.next;}
	 return false;}



	public void display(){
	Node<T> t=head;
	while (t!=null){
	System.out.println(t.data);
	t=t.next;
	}
	}

	
	

}