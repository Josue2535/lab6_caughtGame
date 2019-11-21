package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game {
	private String file;
	private Player[][] bestPlayers;
	private int level;
	private ArrayList<Ball> balls;
	
	public Game(String file) {
		this.file = file;
		this.bestPlayers = bestPlayers;
		this.level = level;
		this.balls = balls;
		try {
			readGame(file);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public Player[][] getBestPlayers() {
		return bestPlayers;
	}
	public void setBestPlayers(Player[][] bestPlayers) {
		this.bestPlayers = bestPlayers;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	public boolean win() {
		boolean ce = true;
		for (int j = 0; j < balls.size(); j++) {
			if(!balls.get(j).stopped) {
				ce = false;
			}
		}
		return ce;
		
	}
	public int Winscore() {
		int i = 0;
		for (int j = 0; j < balls.size(); j++) {
			i += balls.get(j).getBounces();
		}
		return i;
	}
	public boolean savePlayerScore(String name) {
		boolean ce = false;
		for (int i = 0; i < bestPlayers.length; i++) {
			if(!ce) {
				if(bestPlayers[level][i].getScore()>Winscore()) {
					ce = true;
				}
			}
		}
		return ce;
	}
	
	public void addPlayer(String name) {
		boolean ce = false;
		Player tem = new Player(name, Winscore());
		for (int i = 0; i < bestPlayers.length; i++) {
			if(!ce) {
				if(bestPlayers[level][i].getScore()>Winscore()) {
					bestPlayers[level][i]=tem;
					tem =bestPlayers[level][i];
					ce = true;
					
				}else {
					if(i == bestPlayers.length) {
						bestPlayers[level][i]=tem;
					}else {
						bestPlayers[level][i]=tem;
						tem =bestPlayers[level][i];
					}
				}
			}
		}
	}
	public void  writeHall() throws FileNotFoundException, IOException {
		File f =new File ("./reader/hall.txt");
		ObjectOutputStream n = new 	ObjectOutputStream(new FileOutputStream(f));
		n.writeObject(bestPlayers);
		n.close();
	}
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File("./reader/hall.txt");
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		bestPlayers = (Player[][]) obj.readObject();
		obj.close();
	}
	public void saveGame() throws IOException {
		String s ="#nivel"+"\n";
		s += "radio posX posY espera direccion rebotes"+"\n";
		for(int i =0;i<balls.size();i++) {
			s+=balls.get(i).toString()+"\n";
		}
		File f = new File("./reader/gameSave.txt");
		BufferedWriter bw = new BufferedWriter (new FileWriter(f));
		bw.write(s);
		bw.close();
	}
	public void readGame(String file) throws IOException {
		File f = new File(file);
		BufferedReader bw = new BufferedReader(new FileReader(f));
		String line = "";
		while ((line = bw.readLine())!=null) {
			if (line.charAt(0) != '#' ) {
				String[] s = line.split("\t");
				if (s.length == 1) {
					level = Integer.parseInt(s[0]);
					
				}else {
					Ball bal = new Ball(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),s[4],Integer.parseInt(s[5]),Boolean.parseBoolean(s[6]));
					balls.add(bal);
				}
			}
		}
		
		bw.close();
	}
	public String theBest() {
		String hall = "";
		for (int i = 0; i < bestPlayers.length; i++) {
			hall += "nivel " + i + "\n";
			for (int j = 0; j < bestPlayers[0].length; j++) {
				hall = bestPlayers[i][j].toString()+"\n";
			}
		}
		
		return hall;
	}
	public void loadLevel() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File("./reader/" + level + ".txt");
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		balls = (ArrayList<Ball>) obj.readObject();
		obj.close();
	}

}
