import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Game implements ActionListener {
	private State state = State.pause;
	private Bat bat;
	private Ball ball;
	private ArrayList<Brickor> brickor;
	private Brickor brickor2;
	private List list;
	private Queue queue;
	private JList jlist;
	private JList jlist2;
	private JButton button, button2, button3;
	private JScrollPane scroll, scroll2;
	private JLabel jlabel, jlabel2;
	private String temp;


	public Game(GameBoard board) {
		list = new List();
		queue = new Queue();
		jlist = new JList(list.getlist());
		jlist2 = new JList(queue.getlist2());
		jlabel = new JLabel("High Scores",JLabel.CENTER);
		jlabel2 = new JLabel("Latest runs", JLabel.CENTER);
		scroll = new JScrollPane(jlist,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(jlist2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		button = new JButton("Finish");
		button2 = new JButton("Restart");
		button3 = new JButton("Start");
		
		list.add(jlabel);
		//list.add(jlist);
		list.add(scroll);
		list.add(jlabel2);
		//list.add(jlist2);
		list.add(scroll2);
		list.add(button3);
		list.add(button);
		list.add(button2);


		
		//list.add((String)"High scores");
		button.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		queue.readFile();
		list.readFile2();
		
		newGame();
	}
	
	
	public void newGame() {
		bat = new Bat(Constants.POSITION_X,Constants.POSITION_Y,Constants.BAT_WIDTH,Constants.BAT_HEIGHT);
		ball = new Ball(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2, Constants.BALL_SIZE, Constants.BALL_SIZE);
		brickor = new ArrayList<Brickor>();
		//list = new List();
	
		
		int i = 0;
		for( i = 0; i < 8; i++) {
			Brickor temp = new Brickor(Constants.X+(i * Constants.DIF2),Constants.Y,Constants.BRICKOR_BREDD,Constants.BRICKOR_HÖJD,Color.yellow,1);
			brickor.add(temp);
		}
		for( i = 0; i < 8; i++) {
			Brickor temp = new Brickor(Constants.X+(i * Constants.DIF2),Constants.Y-40,Constants.BRICKOR_BREDD,Constants.BRICKOR_HÖJD,Color.blue,2);
			brickor.add(temp);
			}
		for( i = 0; i < 8; i++) {
			Brickor temp = new Brickor(Constants.X+(i * Constants.DIF2),Constants.Y-80,Constants.BRICKOR_BREDD,Constants.BRICKOR_HÖJD,Color.yellow,1);
			brickor.add(temp);
		}
		
		for( i = 0; i < 8; i++) {
			Brickor temp = new Brickor(Constants.X+(i * Constants.DIF2),Constants.Y-120,Constants.BRICKOR_BREDD,Constants.BRICKOR_HÖJD,Color.red,3);
			brickor.add(temp);
		}
		list.setSize(600, 800);
		list.setVisible(true);
		list.setLayout(new GridLayout(0,1));
		list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Constants.POINTS = 0;
		Constants.BALLSLEFT = 3;
		
	}

	public void update(Keyboard keyboard) {

		if(state == State.gaming) {
			
			bat.update(keyboard);
			ball.update(keyboard);
			if(ball.isColliding(bat)) 
					ball.Chanedirection();
		
		
			
			for(Brickor bricka: brickor) {
				if(bricka.isColliding(ball)) {
					if(bricka.getLife() != 0) {
						bricka.changelife();
						System.out.println(bricka.getLife());
						if(bricka.getcolor() == Color.yellow && bricka.IsDestroyed())
							Constants.POINTS +=5;
						else if(bricka.getcolor() == Color.blue && bricka.IsDestroyed())
							Constants.POINTS +=10;
						else if(bricka.getcolor() == Color.red && bricka.IsDestroyed())
						Constants.POINTS +=15;
					}
				}
			}
		}
		
		
		
		if(ball.isWin()) {
			state = State.win;
		
		}
		if(Constants.BALLSLEFT == 0 && state != State.lose) {
			state = State.lose;
			
			if (list.isTopScore((Integer) Constants.POINTS)) {
				String tmp =JOptionPane.showInputDialog("Enter your initials.");
				while (tmp.length() > 3)
					tmp =JOptionPane.showInputDialog("Enter your initials. Three letters or less.");
				list.add((Integer)Constants.POINTS, tmp);
			}
			
			queue.enqueue(Constants.POINTS);
			//newGame();
		}

	}


	public void draw(Graphics2D graphics) {
		
		if(state == State.lose) {
			String s3 = "Game is over!"+ System.lineSeparator() + "Your score is: " + Constants.POINTS;
			graphics.setColor(Color.red);
			graphics.setFont(new Font("Serif", Font.ITALIC, 30));
			graphics.drawString(s3, 200, 400);			
		}
		
		
		if(state != State.lose) {
			String s = "Points:  " + Constants.POINTS;
			graphics.setColor(Color.white);
			graphics.setFont(new Font("Serif", Font.ITALIC, 20));
			graphics.drawString(s, Constants.TEXT_X1, Constants.TEXT_Y);
			String s2 = "Balls left:  "+ Constants.BALLSLEFT;
			graphics.drawString(s2, Constants.TEXT_X2, Constants.TEXT_Y);
			String s5 = "Yellow gives 5 points";
			graphics.setFont(new Font("Serif", Font.ITALIC, 15));
			graphics.drawString(s5, 600, 50);
			String s6 = "Blue gives 10 points";
			graphics.drawString(s6, 600, 80);
			String s7 = "Red gives 15 points";
			graphics.drawString(s7, 600, 110);
		
		
		
			graphics.drawLine(Constants.LINE_X, Constants.LINE_Y, Constants.WINDOW_WIDTH, Constants.LINE_Y);
			graphics.drawLine(Constants.WINDOW_WIDTH2, Constants.LINE_Y, Constants.WINDOW_WIDTH2, 600);

			bat.draw(graphics);
			ball.draw(graphics);
			for(Brickor i: brickor) {
				if(!i.IsDestroyed()) 
					i.draw(graphics);
			}
			
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Finish") {
			state = State.lose;
			
			list.writeToaFile();
			queue.writeToaFile2();
			System.exit(0);
		}
		else if(e.getActionCommand() == "Restart") {
			newGame();
			state = State.pause;
		}
		else if(e.getActionCommand() == "Start") {
			if (state != State.lose)
				state = State.gaming;
		}
	}
}
