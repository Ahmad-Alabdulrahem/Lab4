import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;


public class List extends JFrame {
	
	private ArrayList<String> names;
	private ArrayList<Integer> scores;
	
	private DefaultListModel list;
	private FileWriter fr;

	public List() {
		names = new ArrayList<String>();
		scores = new ArrayList<Integer>();
		list = new DefaultListModel();
	}
	
	public void add(Object score, Object name) {
		
		int points = (Integer) score;
		String initials = (String) name;
		
		if (isTopScore(points)) {
			int insertPos = scores.size() - 1;
			
			while (insertPos >= 0 && scores.get(insertPos) < points)
				insertPos--;
			
			scores.add(insertPos+1, points);
			names.add(insertPos+1, initials);

			// Remove last element if length > 10
			if (scores.size() == 11) {
				scores.remove(10);
				names.remove(10);
			}
		}
		
		list.clear();
		for (int i=0; i < scores.size(); i++) {
			list.addElement( (i+1) + ". " + names.get(i) + " " + scores.get(i));
		}
	}
	
	public boolean isTopScore(Integer score) {
		if (scores.size() < 10 || score > scores.get(9))
			return true;
		return false;
	}
	
	public void writeToaFile() {
		try {
			fr =  new FileWriter ("WriteToFile.txt");
			int max = 0;		
			while(max < list.size()) {
				fr.write(names.get(max) + "," + scores.get(max)+ "\n" );
				System.out.println("good");
				max++;
			}
			fr.close();
		
		}catch(Exception ex) {
			
		}
	}

	public DefaultListModel getlist() {
		return list;
	}
	
	public void skriv() {
		for(int i = 0; i < list.size(); i++)
			System.out.println(list.getElementAt(i));
	}
	
	public int getzize() {
		return list.size();
	}
	
	public Integer getLastElement() {
		return (Integer)list.getElementAt(9);
	}
	
	public void readFile2() {
		try {
			Scanner scan = new Scanner(new File("WriteToFile.txt"));
			while(scan.hasNext()) {
				String[] input = scan.next().split(",");	
				add(Integer.parseInt(input[1]), input[0]);
			}
		}
		catch(Exception e) {
			System.out.println("not good");
		}
	}
}
