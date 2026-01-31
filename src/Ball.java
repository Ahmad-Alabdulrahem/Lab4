import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ball extends Sprite {
	private int MOVE_X = 2;
	private int MOVE_Y =5;


	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard) {
		setY(getY() + MOVE_Y);
		setX(getX() + MOVE_X);
		
		if(getY() <= Constants.LINE_Y) 
			MOVE_Y *= -1;
		if( getX() <= 0 || getX() + getWidth() + 8 >= Constants.WINDOW_WIDTH2) 
			MOVE_X *= -1;
		if(getY() > Constants.WINDOW_HEIGHT) {
			Constants.BALLSLEFT -= 1;
			setY(300);
			setX(370);
		}

		
		
		
			

		
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.red);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
		
	}

	public boolean isColliding(Bat bat) {
		Rectangle ballrec = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle batrec = new Rectangle(bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight());
		return ballrec.intersects(batrec);
	}

	public void Chanedirection() {
		MOVE_Y *= -1;
	}
	public void Chanedirection2() {
		MOVE_X *= -1;
	}

	public boolean isColliding(Brickor brickor) {
		Rectangle ballrec = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle batrec = new Rectangle(brickor.getX(), brickor.getY(), brickor.getWidth(), brickor.getHeight());

		return ballrec.intersects(batrec);
	}
	
	public boolean isWin() {
		if(Constants.POINTS == 280)
			return true;
		else 
			return false;
	}


	

}
