import java.io.IOException;
import java.util.ArrayList;


public class Main {
	public static void main( String[] args ) throws IOException{
     ArrayList<String> tags = new ArrayList<String>();
     tags.add("adventure");
     tags.add("fantasy");
     tags.add("epic");
     tags.add("magic");
     tags.add("dwarf");
     tags.add("elf");
     tags.add("music");
    // tags.add("software developers");
     //tags.add("sci-fi");
   	 Task t = new Task(tags);
   	 System.out.println("Finish");
   	 
   }
}
