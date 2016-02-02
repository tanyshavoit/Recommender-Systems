package Tetiana_Voitovych.Lesson_3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.opencsv.CSVReader;
/**
*
* @author Tetiana Voitovych
* 2016
*/

//class calculates top10 similarity between the two movies using the formula:
//((x ∧ y)/x)/((!x ∧ y)/!x)
//and damped mean for k=5
public class Top10 {
	String best_movie;
	List<String[]> movies;
	List<String[]> ratings;
	HashMap<String, ArrayList<Double>> hm = new HashMap<String, ArrayList<Double>>();
	HashMap<String, String> hm2 = new HashMap<String, String>();
	TreeMap<Double, String> damped_mean = new TreeMap<Double, String>();
	TreeMap<Double, String> similar = new TreeMap<Double, String> ();
    String text ;
    
	Top10(String filename, String filename2) throws IOException {

		this.text = "";
		best_movie = "";
		ratings = readCVSfile(filename);
		movies = readCVSfile(filename2);
		calculate();
		dampedMeanTop10();
		similar();
		write(text);
	}
    
	private void calculate(){
		for (int i = 1; i < ratings.size(); i++) {
			if (!(hm.containsKey(ratings.get(i)[1]))) {
				hm.put(ratings.get(i)[1], new ArrayList<Double>());
				hm.get(ratings.get(i)[1]).add(whichNumber(ratings.get(i)[2]));
				
			} else {
				hm.get(ratings.get(i)[1]).add(whichNumber(ratings.get(i)[2]));

			}
		}
		for(int i=1; i<movies.size();i++){
			hm2.put(movies.get(i)[0], movies.get(i)[1]);
		}
	}
	
	//damped mean for k=5
	private void dampedMeanTop10() {
		double d_m=0; //damped mean for k=5
		for (Entry<String, ArrayList<Double>> entry : hm.entrySet()){
			for(double d: entry.getValue()){
				d_m+=d;
			}
			d_m=(d_m+5*2.5)/(entry.getValue().size()+5);
			damped_mean.put(d_m,entry.getKey() );
			d_m=0;
		}
		text+="damped_mean top 10 \n";
		int N = 10;
        int i = 0;
        for (Entry<Double, String> entry : damped_mean.descendingMap().entrySet()) {
            if (i++ < N) {
                text+=+entry.getKey()+"  "+hm2.get(entry.getValue())+" "+entry.getValue()+"\n";
                if(best_movie.equals("")){best_movie=entry.getValue();}
            }
            else{
            	break;
            }
        }
	}
	//similar
	private void similar(){
		double similarity=0;
		double count1=0;//x and y
		double count2=0;//!x and y
		int a=0;
		int b=0;
		String previous_user=ratings.get(1)[0];
		for(int j=1; j<movies.size();j++){
			if(!movies.get(j)[0].equals(best_movie)){
		for (int i = 1; i < ratings.size(); i++){
			if(!previous_user.equals(ratings.get(i)[0])){
				if((a==1)&&(b==1)){count1++;}
				if((a==0)&&(b==1)){count2++;}
				a=0;b=0;
			}
			previous_user=ratings.get(i)[0];
			if(ratings.get(i)[1].equals(best_movie)){a=1;}
			if(ratings.get(i)[1].equals(movies.get(j)[0])){b=1;}
		}
		
		similarity=(count1*(1+hm.get(best_movie).size()))/(1+(count2/(1+(1-hm.get(best_movie).size()))));
		similar.put(similarity, movies.get(j)[0]);
		count1=0;
		count2=0;
		a=0;
		b=0;
			}
		}
		
		text+="for best top 10 \n";
		int N = 10;
        int i = 0;
		   for (Entry<Double, String> entry : similar.descendingMap().entrySet()) {
	            if (i++ < N) {
	                text+=entry.getKey()+"  "+hm2.get(entry.getValue())+"\n";
	                if(best_movie.equals("")){best_movie=entry.getValue();}
	            }
	            else{
	            	break;
	            }
	        }
	}
	// read all from cvs file
	private List<String[]> readCVSfile(String filename) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(filename));
		//ratings = reader.readAll();
		List<String[]> list = reader.readAll();
        return list;
	}

	private double whichNumber(String s) {
		double number = 0;
		if (s.charAt(0) == '1') {
			number = 1;
			if(s.charAt(2)=='5'){
				number+=0.5;
			}
		}
		else if (s.charAt(0) == '2') {
			number = 2;
			if(s.charAt(2)=='5'){
				number+=0.5;
			}
		}
		else if (s.charAt(0) == '3') {
			number = 3;
			if(s.charAt(2)=='5'){
				number+=0.5;
			}
		}
		else if (s.charAt(0) == '4') {
			number = 4;
			if(s.charAt(2)=='5'){
				number+=0.5;
			}
		}
		else if (s.charAt(0) == '5') {
			number = 5;
			if(s.charAt(2)=='5'){
				number+=0.5;
			}
		}
       
		return number;
	}

	// write to txt file
	private void write(String text) {
		String fileName = "Tops.txt";
		File file = new File(fileName);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
