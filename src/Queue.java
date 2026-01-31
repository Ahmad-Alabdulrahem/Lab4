import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

public class Queue extends JFrame{
	private DefaultListModel lr;
	private ArrayList<Integer> scores;
	private FileWriter fr;
	
	public Queue() {
		lr = new DefaultListModel();
		scores = new ArrayList<Integer>();
	}
	
	public void enqueue(Object points) {
		scores.add((Integer)points);
		if(scores.size() > 3)
			scores.remove(0);
		
		lr.clear();
		for (int i=0; i < scores.size(); i++) {
			lr.addElement( (i+1) + ". " + scores.get(i));
		}
	}
	
	public DefaultListModel getlist2() {
		return lr;
	}
	public void writeToaFile2() {
		try {
			fr =  new FileWriter ("Latest runes.txt");
			int max = 0;		
			while(max < scores.size()) {
				fr.write(scores.get(max) + "\n" );
				System.out.println("good");
				max++;
			}
			fr.close();
		
		}catch(Exception ex) {
			
		}
	}
	
	public void readFile() {
		try {
			Scanner scan = new Scanner(new File("Latest runes.txt"));
			while(scan.hasNext())
				enqueue(Integer.parseInt(scan.next()));
		}catch(Exception e) {
			System.out.println("not good");
		}
	}
}
