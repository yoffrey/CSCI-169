public class Seafood extends Food{
    public Seafood(){
        name = "";
    }

    public Seafood(String n){
        name = n;
    }

    public void prepare(){
        System.out.println("Peel the " + name);
    }
}
