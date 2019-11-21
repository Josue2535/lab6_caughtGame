package thread;

import javafx.scene.shape.Circle;
import model.Ball;

public class CircleThread extends Thread{
	private Circle circle;
	private Ball ball;

	public CircleThread(Circle circle, Ball ball) {
		this.circle = circle;
		this.ball = ball;
	}


	public Circle getCircle() {
		return circle;
	}


	public void setCircle(Circle circle) {
		this.circle = circle;
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}


	public void run() {
		while(!ball.isStopped()) {
			
		try {
		
				ball.movement();;
				circle.setCenterX(ball.getX());
				circle.setCenterY(ball.getY());
				sleep(ball.getWaitTime());
				
				
			}
		catch(InterruptedException e ) {
			e.printStackTrace();
			
		}
		
		}
	}
}
