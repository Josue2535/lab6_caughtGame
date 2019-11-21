package model;

public class Ball {
	private int x;
	private int y;
	private int radius;
	private int waitTime;
	private String initialAddress;
	private int bounces;
	public boolean stopped;

	public Ball(int x, int y, int radius, int waitTime, String initialAddress, int bounces,
			boolean stopped) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.waitTime = waitTime;
		this.initialAddress = initialAddress;
		this.bounces = bounces;
		this.stopped = stopped;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public String getInitialAddress() {
		return initialAddress;
	}

	public void setInitialAddress(String initialAddress) {
		this.initialAddress = initialAddress;
	}

	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public void movement() {
		if (!stopped) {
			if (initialAddress.equalsIgnoreCase("IZQUIERDA")) {
				x--;
				if (x-radius==0) {
					initialAddress ="DERECHA";
					bounces++;
					
				}
			}
			if (initialAddress.equalsIgnoreCase("DERECHA")) {
				x++;
				if (x+radius==400) {
					initialAddress = "IZQUIERDA";	
					bounces++;
				}	
			}
			if (initialAddress.equalsIgnoreCase("ARRIBA")) {
				y ++;
				if (y-radius==30) {
					initialAddress ="ABAJO";
					bounces++;
				}
				
			}
			if (initialAddress.equalsIgnoreCase("ABAJO")) {
				y--;
				if (y+radius==500) {
					initialAddress ="ARRIBA";
					bounces++;
				}
			}
		}
	}
	
	public void reboundChange() {
		
	}
}
