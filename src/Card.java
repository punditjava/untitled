public class Card {
    private String Card;
    private String Definition;
    private int Mistake;

    public Card(String c,String d,int m){
        Card=c; // название
        Definition=d;// значение
        Mistake=m; //ошибки
    }
    public Card(String c,String d){
        Card=c; // название
        Definition=d;// значение
    }
    public void Mistake(){
        Mistake++;
    }
    public void Reset(){
        Mistake=0;
    }
    public String getCard() {
        return Card;
    }

    public String getDefinition() {
        return Definition;
    }
    public int getMistake(){
        return Mistake;
    }
}

