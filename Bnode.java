public class Bnode<T>{
public String key;
public T data;
public Bnode<T> R , L;

public Bnode(String k , T d){
key=k;
data=d;
R=L=null;

}
}
//bainery search tree node class