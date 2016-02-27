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
   	 Task task = new Task("goodmovies","badmovies");//моњ ф≥льми
   	 System.out.println("…мов≥рн≥сть = "+task.probability(tags));
   	 tags.clear();
     tags.add("software developers");
     tags.add("sci-fi");
   	 System.out.println("…мов≥рн≥сть = "+task.probability(tags));
   	 Task task2 = new Task("1","2");//ф≥льми з прикладу
   	 tags.clear();
     tags.add("recommender");
     tags.add("intelligent");
     System.out.println("…мов≥рн≥сть = "+task2.probability(tags));
   	 System.out.println("Finish");
   	 
   }
}
