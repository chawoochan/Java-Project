package javagame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameEnviorment extends JFrame{
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../image/background.jpg")).getImage();
	private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("../image/exitbutton.png"));
	private ImageIcon exitButtonEnter = new ImageIcon(Main.class.getResource("../image/enteredexit.png"));
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../image/menu_bar.png")));
	private JButton exitButton = new JButton(exitButtonImage);
	
	private int mouseX, mouseY;
	
	public GameEnviorment() {
		setUndecorated(true);
		setTitle("Rhythm Beat"); //제목 설정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //main에서 지정한 크기로 해상도 지정
		setResizable(false);  //해상도 재지정 false
		setLocationRelativeTo(null); //화면 가운데에 창을 위치하게함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //이걸 사용하지 않을 시 창을 닫아서 프로세스에서 작동함
		setVisible(true); //창 띄우기
		
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnter);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//Music buttonEnteredMusic = new Music("",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		
		add(exitButton);
		
		menuBar.setBounds(0,0,1280,30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY);
			}
		});
		add(menuBar);
		
		Music introMusic = new Music("intro_music.mp3", true);
		introMusic.start();
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null); //배경같이 고정되지 않는 이미지를 그려줌
		paintComponents(g);
		this.repaint();
	}
}