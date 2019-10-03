import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class AdventureTimeGUI {
	
	/**
	Note: This class is designed for interoperability with Mr. Bunn's Adventure Time lab:
	https://www.dropbox.com/s/jrv1k54hbtqqcjc/AdventureTime.docx?dl=0
	
	*/

	private JFrame frame;
	private JLabel[] labels;
	private JPanel panel;
	private JTextPane ln,lw,ls,le;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblOtherInfo;

	public AdventureTimeGUI() {

		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 816, 640);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		this.frame.setVisible(true);
		this.labels = new JLabel[5];

		JLabel lblYouAreHere = new JLabel("You are here");
		lblYouAreHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAreHere.setBounds(262, 264, 76, 14);
		frame.getContentPane().add(lblYouAreHere);

		this.textArea = new JTextArea("");
		this.frame.getContentPane().add(textArea);
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setEditable(false);
		this.textArea.setVisible(false);

		this.scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setBounds(602, 131, 195, 240);
		this.frame.getContentPane().add(scrollPane);
		this.scrollPane.setVisible(false);

		this.lblOtherInfo = new JLabel("Other Info");
		this.lblOtherInfo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblOtherInfo.setBounds(602, 106, 195, 14);
		this.frame.getContentPane().add(lblOtherInfo);
		this.lblOtherInfo.setVisible(false);

		JLabel northRoom = new JLabel("");
		northRoom.setHorizontalAlignment(SwingConstants.CENTER);
		northRoom.setBounds(201, 0, 200, 15);
		this.frame.getContentPane().add(northRoom);
		this.labels[1] = northRoom;

		JLabel westRoom = new JLabel("");
		westRoom.setHorizontalAlignment(SwingConstants.CENTER);
		westRoom.setBounds(0, 201, 200, 15);
		this.frame.getContentPane().add(westRoom);
		this.labels[4] = westRoom;

		JLabel currentRoom = new JLabel("");
		currentRoom.setHorizontalAlignment(SwingConstants.CENTER);
		currentRoom.setBounds(201, 201, 200, 15);
		this.frame.getContentPane().add(currentRoom);
		this.labels[0] = currentRoom;

		JLabel eastRoom = new JLabel("");
		eastRoom.setHorizontalAlignment(SwingConstants.CENTER);
		eastRoom.setBounds(401, 201, 200, 15);
		this.frame.getContentPane().add(eastRoom);
		this.labels[2] = eastRoom;

		JLabel southRoom = new JLabel("");
		southRoom.setHorizontalAlignment(SwingConstants.CENTER);
		southRoom.setBounds(201, 401, 200, 15);
		this.frame.getContentPane().add(southRoom);
		this.labels[3] = southRoom;
		
		
		this.ln = new JTextPane();
		this.ln.setBounds(200,0,200,200);
		this.ln.setVisible(true);
		this.frame.getContentPane().add(this.ln);
		
		this.le = new JTextPane();
		this.le.setBounds(400,200,200,200);
		this.le.setVisible(true);
		this.frame.getContentPane().add(this.le);
		
		this.ls = new JTextPane();
		this.ls.setBounds(200,400,200,200);
		this.ls.setVisible(true);
		this.frame.getContentPane().add(this.ls);
		
		this.lw = new JTextPane();
		this.lw.setBounds(0,200,200,200);
		this.lw.setVisible(true);
		this.frame.getContentPane().add(this.lw);
	
		
		this.panel = new Draw();
		this.panel.setBounds(0, 0, 601, 600);
		this.frame.getContentPane().add(this.panel);
		this.panel.setVisible(true);
		JLabel lblCompass = new JLabel("");
		lblCompass.setBounds(627, 401, 150, 150);
		this.frame.getContentPane().add(lblCompass);

		try {
			URL url = new URL("http://fengshuicompass.com/wp-content/uploads/2013/08/compass_rose-150x150.png");
			BufferedImage image = ImageIO.read(url);
			lblCompass.setIcon(new ImageIcon(image));
		} catch (Exception ex) {

		}
	}

	class Draw extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 200, 200);
			g.drawRect(200, 0, 200, 200);
			g.drawRect(0, 200, 200, 200);
			g.fillRect(400, 0, 200, 200);
			g.fillRect(0, 400, 200, 200);
			g.drawRect(400, 200, 200, 200);
			g.drawRect(200, 400, 200, 200);
			g.fillRect(400, 400, 200, 200);
			g.drawRect(600, 0, 1, 600);
			g.setColor(Color.RED);
			g.fillOval(285, 285, 30, 30);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.BLUE);
			g.drawRect(201, 201, 198, 198);
		}
	}

	public void updateUI(Room currentRoom) {
		try {
		    Method m = Room.class.getMethod("getRoomMeta");
		    String meta = (String) m.invoke(currentRoom);
		    this.textArea.setText(meta);
		    this.lblOtherInfo.setVisible(true);
		    this.textArea.setVisible(true);
		    this.scrollPane.setVisible(true);
		} catch (Exception e) {
			
		}
		this.labels[0].setText(currentRoom.getDescription());
		if (currentRoom.getExit("north") != null) {
			this.labels[1].setText(currentRoom.getExit("north").getDescription());
			this.ln.setBackground(new Color(238,238,238));
		} else {
			this.ln.setBackground(Color.BLACK);
			this.labels[1].setText("");
		}

		if (currentRoom.getExit("south") != null) {
			this.labels[3].setText(currentRoom.getExit("south").getDescription());
			this.ls.setBackground(new Color(238,238,238));
		} else {
			this.ls.setBackground(Color.BLACK);
			this.labels[3].setText("");
		}

		if (currentRoom.getExit("east") != null) {
			this.labels[2].setText(currentRoom.getExit("east").getDescription());
			this.le.setBackground(new Color(238,238,238));
		} else {
			this.le.setBackground(Color.BLACK);
			this.labels[2].setText("");
		}

		if (currentRoom.getExit("west") != null) {
			this.labels[4].setText(currentRoom.getExit("west").getDescription());
			this.lw.setBackground(new Color(238,238,238));
		} else {
			this.lw.setBackground(Color.BLACK);
			this.labels[4].setText("");
		}
		this.panel.repaint();
		this.frame.setBounds(frame.getX(), frame.getY(), 818, 640);
		this.frame.setBounds(frame.getX(), frame.getY(), 816, 640);
	}
}