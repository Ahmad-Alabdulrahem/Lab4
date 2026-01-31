import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Brickor extends Sprite {
	private Color color;
	private int life;


	public Brickor(int x, int y, int width, int height, Color color, int life) {
		super(x, y, width, height);
		this.color = color;
		this.life = life;
	}

	@Override
	public void update(Keyboard keyboard) {
		
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}
	public boolean IsDestroyed() {
		if(life == 0)
			return true;
		else 
			return false;
	}
	
	public int getLife() {
		return life;
	}
	
	
	public Color getcolor() {
		return color;
	}
	
	public void changelife() {
		life -= 1;
		if (life == 0)
			setX(-700);
	}
	public boolean isColliding(Ball ball) {
		boolean hit = false;
		Rectangle recball = new Rectangle (ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		Rectangle leftside = new Rectangle(getX() - 2,getY(), 2, getHeight());
		Rectangle rightside = new Rectangle(getX() + getWidth(), getY(), 2, getHeight());
		Rectangle top = new Rectangle(getX(), getY() - 2, getWidth(), 2);
		Rectangle bottom = new Rectangle(getX(), getY() + getHeight(), getWidth(), 2);
		if(leftside.intersects(recball)) {
			hit = true;
			ball.Chanedirection2();
		}
		else if(rightside.intersects(recball)) {
			hit = true;
			ball.Chanedirection2();
		}
		
		else if(top.intersects(recball)) {
			hit = true;
			ball.Chanedirection();
		}
		else if(bottom.intersects(recball)) {
			hit = true;
			ball.Chanedirection();
		}
		
		return hit;
			
		
	}
	
	


}
