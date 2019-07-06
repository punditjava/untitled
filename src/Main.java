import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // ***СРАВНИТЬ ОТВЕТ И ЗНАЧЕНИЕ(defision)***
    public static void getAnswer(String definition) {
        String answer;
        Scanner scan = new Scanner(System.in);
        answer = scan.nextLine();
        System.out.println(definition);
        if (answer.equals(definition))    // Сравнение ответов
            System.out.println("Your answer is right!");
        else System.out.println("Your answer is wrong...");
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Card> Cartonki= Import(); // Главный список
        Remove(Cartonki);
        for (Card e: Cartonki)
            System.out.println(e.getCard()+" "+ e.getDefinition());
    }


                           // ***ДОБАВИТЬ КАРТОНКУ В СПИСОК***
    private static void Add (ArrayList<Card> cards){
        int N;
        String C,D;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите нужное количество слов");
        N = scan.nextInt();
        while (N>0){
            System.out.println("The card:");
            C=scan.next();
            System.out.println("The definition:");
            D=scan.next();
            Card card= new Card(C,D);
            cards.add(card);  // добавил в список
            System.out.println("The pair ("+card.getCard()+":"+card.getDefinition()+") is added.");
            N--;
        }
    }
                             // ***УДАЛИТЬ КАРТОНКУ***
      private static void Remove (ArrayList<Card> cards){
          int N;
          String C;
          Scanner scan = new Scanner(System.in);
          System.out.println("Введите нужное количество слов для удаления");
          N = scan.nextInt();
          while (N>0){
              System.out.println("The card:");
              C=scan.next();
              int i=0;
              while (i<cards.size()) {
                   if(C.equals(cards.get(i).getCard())){
                  System.out.println("Картонка "+cards.get(i).getCard()+" удалена");
                   cards.remove(i); i=cards.size();}
                   i++;
              }
              N--;
          }
      }



                               // ***ЗАПИСЬ В ФАИЛ***
   private static void Export(ArrayList<Card> cards) throws Exception {
        FileWriter writer = new FileWriter("test.txt");
      String lineSeparator = System.getProperty("line.separator");
      for (Card e: cards){
           writer.write(e.getCard() + lineSeparator);
          writer.write(e.getDefinition() + lineSeparator);}  // Записываем N карточек в фаил
        writer.flush();
      }
                                     //***ЧИТАЕМ ИЗ ФАИЛА***

    private static ArrayList<Card> Import() throws Exception{
        ArrayList<Card> cards = new ArrayList<>();
        FileInputStream fstream = new FileInputStream("test.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String cardLine= null;
        String defLine;
        int k =1;
        while ((defLine= br.readLine()) != null ){   // прочитали все карточки из фаила
               if(k % 2 ==1){
                   cardLine = defLine;
               } else{
                   Card card= new Card(cardLine,defLine);
                   cards.add(card);
                   cardLine= null;
               }
        k++;}
        return cards;
    }
}







