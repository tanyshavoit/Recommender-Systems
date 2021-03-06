import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
������ 10 ������, �� �����������, �� 10 ,�� �� �����������.
(����� goodmovies and badmovies ��������)
�������� ������ ����� ��� ������� � ���.

�� ��������� ���������� ������������� �������, 
�� ����������� ��� �����, � ����� �������� ������ �����:
 adventure, fantasy, epic, magic, dwarf, elf, music 
 */

public class Task {
	ArrayList<String> allTags;// ������� ��� ����
	ArrayList<ArrayList<String>> label1;//�� ���, �� �������� � �������,�� �����������
	ArrayList<ArrayList<String>> label0;//�� ���, �� �������� � �������, ���� �� �����������
	ArrayList<ArrayList<Integer>> t_label1;//������� ���������� ����,��� ������� ������(�� ��� 0 ���� ���� � ����� 1 ���� �)

	Task(String filename1,String filename2) {
		allTags = new ArrayList<String>();
		label1 = new ArrayList<ArrayList<String>>();
		label0 = new ArrayList<ArrayList<String>>();
		t_label1 = new ArrayList<ArrayList<Integer>>();
		readFile(filename1+".txt", label1);
		readFile(filename2+".txt", label0);
		createTagsTable(label1,t_label1);
	
	}
	
	// ������� ����
	private void readFile(String fileName, ArrayList<ArrayList<String>> tags) {
		String tag = "";
		ArrayList<String> ar = new ArrayList<String>();
		boolean t = false;
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				for (char s : line.toCharArray()) {
					if (s == ';') {
						t = true;
					}
					if (t) {
						if (s == ',' || s == ';') {
							if((!allTags.contains(tag))&&(!tag.equals(""))){	
							allTags.add(tag);}
							ar.add(tag);
							tag = "";
						} else {
							tag += s;
						}
					}
					;
				}
				tags.add((ArrayList<String>) ar.clone());
				ar.clear();
				t = false;
			}
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
//��������� ������� ���������� ����
private void createTagsTable(ArrayList<ArrayList<String>> ar1, ArrayList<ArrayList<Integer>> ar2){
	ArrayList<Integer> ar = new ArrayList<Integer>();
	for(ArrayList<String> a: ar1){
	for(String s: allTags){
	if(a.contains(s)){
		ar.add(1);
	}
	else{
		ar.add(0);
	}
	}
    ar2.add((ArrayList<Integer>) ar.clone());
    ar.clear();
    
	}
}

//������ � ���� ���������� ��� ����������� ����� � ������ ������ �� ��������� ��������� �������������(������ 6)
public double probability(ArrayList<String> tags){
	double probability=1;
	double count=0;
	for(String s:tags){
		if(allTags.contains(s)){
			for(int i=0;i<label1.size();i++){
				if(t_label1.get(i).get(allTags.indexOf(s))==1){count+=1;}	
			}
			probability*=(count/label1.size());	
			count=0;
		}
		else{
           return 0;
		}
	}

	for(String s:allTags){
		if(!tags.contains(s)){
			for(int i=0;i<label1.size();i++){
				if(t_label1.get(i).get(allTags.indexOf(s))==0){
					count+=1;}	
			}
			probability*=(count/label1.size());	
			count=0;
		}

	}
	
	return probability;
}

}
