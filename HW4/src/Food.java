class Food {
    public String name;

    public Food(){
        name = "";
    }
    public Food(String n){
        name = n;
        //System.out.println("A new food has been created: " + name);
    }
    public void prepare(){
        System.out.println("Prepare the " + name);
    }
}
