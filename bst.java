public class bst<T>{
private Bnode<T> root;
private Bnode<T> c;

public bst(){
root=null;
c=null;}
public boolean empty(){
return root ==null;}
public boolean FULL(){
return false;}
public T retrieve(){
return c.data;}

public boolean findkey(String x){
Bnode<T> p=root;
while (p!=null){
c=p;
if (x.compareToIgnoreCase(p.key)==0){return true;}
else if (x.compareToIgnoreCase(p.key)<0){p=p.L;}//if String x<0 will go to left
else p=p.R;//if String x>0 will go to right
}return false;}



public boolean insert(String k, T val) 
{ Bnode<T> p, q = c;
 if(findkey(k)) 
 { c = q; 
  return false;  } 
  p = new Bnode<T>(k, val);
   if (empty()) 
   { root = c = p;
    return true; } 
    else { 
     if (k.compareToIgnoreCase(c.key)<0) 
     c.L = p; 
     else c.R = p;
      c = p; 
      return true; } }
      





public void preorder(){
if(root==null)
System.out.println("empty");
else
preorder(root);
}
//for preorder bst
public void preorder(Bnode x){
if(x==null) return;
System.out.println("key:"+x.key);
System.out.println(x.data.toString());
preorder(x.L);
preorder(x.R);}

public void inorder(){
if(root==null)
System.out.println("empty");
else
inorder(root);
}
//for inorder bst
public void inorder(Bnode a){

if(a==null) return;
inorder(a.L);
System.out.println("key:"+a.key);
System.out.println(a.data);
//for we can display lists not only one var
//((LinkedList<T>)a.data).display();
inorder(a.R);
}










}