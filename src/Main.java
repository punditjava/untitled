import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
                      //***МЕНЮ***
    private static void Menu(ArrayList<Card> cards) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the action (add, remove, import, export, ask, hardest card, reset stats, exit):");
        switch (scan.nextLine()) {
            case "add":
                Add(cards);
                Menu(cards);
                break;
            case "ask":
                ASK(cards);
                Menu(cards);
                break;
            case "hardest card":
                HardestQuestion(cards);
                Menu(cards);
                break;
            case "remove":
                Remove(cards);
                Menu(cards);
                break;
            case "import":
                cards = Import();
                Menu(cards);
                break;
            case "export":
                Export(cards);
                Menu(cards);
                break;
            case "reset stats":
                for (Card e: cards){
                    e.Reset();        // ***Обнулить статистику***
                } Menu(cards); break;
            case "exit":
                System.exit(0);
            default:
                Menu(cards);
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Card> Cartonki= new ArrayList<Card>();// Главный список
        Menu(Cartonki);
    }


                           // ***ДОБАВИТЬ КАРТОНКУ В СПИСОК***
    private static void Add (ArrayList<Card> cards){
        int N;
        String C,D;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите нужное количество слов");
        N = scan.nextInt();
        while (N>0){
            boolean flag=false;
            System.out.println("The card:");
            C=scan.next();
            for(Card e: cards){
                if(C.equals(e.getCard())) {System.out.println("Такая картонка уже есть!"); flag=true;break;}
            } if(flag==false){
            System.out.println("The definition:");
            D=scan.next();
            Card card= new Card(C,D);
            cards.add(card);  // добавил в список
            System.out.println("The pair ("+card.getCard()+":"+card.getDefinition()+") is added.");}
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
                   cards.remove(i); break;}
                   i++;
              }
              N--;
          }
      }



                               // ***ЗАПИСЬ В ФАИЛ***
   private static void Export(ArrayList<Card> cards) throws Exception {
        Scanner scan= new Scanner(System.in);
        String name = scan.next();
        FileWriter writer = new FileWriter(name);
      String lineSeparator = System.getProperty("line.separator");
      for (Card e: cards){
           writer.write(e.getCard() + lineSeparator);
          writer.write(e.getDefinition() + lineSeparator);
          writer.write(e.getMistake() + lineSeparator);}  // Записываем N карточек в фаил
        writer.flush();
      System.out.println("Записанно "+cards.size()+" карточек");
      }
                                     //***ЧИТАЕМ ИЗ ФАИЛА***

    private static ArrayList<Card> Import() throws Exception{
        Scanner scan=new Scanner(System.in);
        String name= scan.next();
        ArrayList<Card> cards = new ArrayList<>();
        FileInputStream fstream = new FileInputStream(name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String cardLine= null;
        String twoLine= null;
        String defLine;
        int mistake=0;
        int k =1;
        while ((defLine= br.readLine()) != null ){   // прочитали все карточки из фаила
               if(k % 3 ==1){
                   cardLine = defLine;
               } else{if(k % 3 ==2)
                  twoLine= defLine;
                else { mistake= Integer.parseInt(defLine);
                Card card= new Card(cardLine,twoLine,mistake);
                cards.add(card);
                cardLine= null;
               defLine = null;}}
        k++;}
        return cards;
    }
    //***НЕСКОЛЬКО КАРТОЧЕК***
    private static void ASK(ArrayList<Card> cards){
        Scanner scan = new Scanner(System.in);
        System.out.println("How many times to ask?");
        int kol=scan.nextInt();
        while (kol>0){
            int i =(int)(Math.random()*cards.size());
            System.out.println(cards.get(i).getCard());
            getAnswer(cards.get(i));
            kol--;
        }
    }

    //***Выдать самый сложный вопрос***
    private static void HardestQuestion(ArrayList<Card> cards){
        int i =0;
        String k=cards.get(0).getCard();
        for(Card e: cards){
            if(e.getMistake()>i)
            {i=e.getMistake();k=e.getCard(); }}
                 System.out.println("The hardest card is "+k+" You have "+i+" errors answering it.");
    }

    // ***СРАВНИТЬ ОТВЕТ И ЗНАЧЕНИЕ(defision)***
    private static void getAnswer(Card card) {
        String answer;
        Scanner scan = new Scanner(System.in);
        answer = scan.nextLine();
        if (answer.equals(card.getDefinition()))    // Сравнение ответов
            System.out.println("Your answer is right!");
        else {System.out.println("Wrong answer (the correct one is" + card.getDefinition());card.Mistake();
    }}

}







