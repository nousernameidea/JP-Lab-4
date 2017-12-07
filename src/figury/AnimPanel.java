package figury;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private int delay = 30;

	static Timer timer;

	private static int numer = 0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();
		
		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		buffer.setBackground(Color.LIGHT_GRAY);

	}

	void addFig() {
		int nummod = numer++ % 4;
		Figura fig = (nummod == 0) ? new Kwadrat(buffer, delay, getWidth(), getHeight())
				: new Elipsa(buffer, delay, getWidth(), getHeight());
		if(nummod == 2)fig = new Ko³o(buffer, delay, getWidth(), getHeight());
		else if(nummod == 3)fig = new Star(buffer, delay, getWidth(), getHeight());
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//buffer.translate((Math.abs(Frame.LEFT_ALIGNMENT)+Math.abs(Frame.RIGHT_ALIGNMENT)/2), (Math.abs(Frame.TOP_ALIGNMENT)+Math.abs(Frame.BOTTOM_ALIGNMENT)/2));
		//device.translate((Math.abs(Frame.LEFT_ALIGNMENT)+Math.abs(Frame.RIGHT_ALIGNMENT)/2), (Math.abs(Frame.TOP_ALIGNMENT)+Math.abs(Frame.BOTTOM_ALIGNMENT)/2));
		device.drawImage(image, Frame.WIDTH/2, Frame.HEIGHT/2, null);
		buffer.clearRect(Frame.WIDTH/2, Frame.HEIGHT/2, getWidth(), getHeight());
		//buffer.translate(Frame.WIDTH, Frame.HEIGHT);
	}
}
