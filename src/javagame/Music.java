package javagame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	
	private Player player;
	private boolean isLoop; // 노래 반복 여부를 판단해줄 boolean값
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch(Exception e) {
			System.out.println(e.getMessage()); //에러 메세지 출력
		}
				
	}
	public int getTime() {
		if(player == null) { return 0;}
		return player.getPosition(); //나중에 노트 판정을 위한 시간 측정
	}
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //isLoop가 True일 경우 계속 반복
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}