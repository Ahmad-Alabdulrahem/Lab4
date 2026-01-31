import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bat extends Sprite {

	public Bat(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard) {
		if(keyboard.isKeyDown(Key.Right) && getX() < 800 - getWidth() - 18)
			setX(getX() + 10);
		if(keyboard.isKeyDown(Key.Left) && getX() > 0)
			setX(getX() - 10);
		
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public boolean isColliding(Ball ball) {
		Rectangle ballrec1 = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		Rectangle ballrec2 = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		Rectangle ballrec3 = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		Rectangle batrec = new Rectangle(getX(), getY(), getWidth(), getHeight());
		return ballrec1.intersects(batrec);
	}

	

}
