import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception { 
        int i=1;
        ArrayList<String> bugs = new ArrayList<>();
        while(i<=3){
            bugs.add("Id" +i);
            i++;
        }
        try {
            bugs.get(2);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Bug index id not found: " +e.getMessage());
        }finally{
            System.out.println("Search completed");
        }
    }
}