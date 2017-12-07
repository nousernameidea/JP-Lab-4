package figury;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

public class Star extends Figura{

	private Path2D path = new Path2D.Double();
	
	public Star(Graphics2D bufor, int delay, int w, int h) {
		super(bufor, delay, w, h);
		
		double initialx = 300;
		double initialy = 400;
		double angle = 0;
		path.moveTo(initialx, initialy);
		for (int i = 0; i <= 5; i++) {
            int x2 = w + (int) (Math.cos(angle) * delay);
            int y2 = h + (int) (Math.sin(-angle) * delay);
            path.lineTo(x2, y2);
            w = x2;
            h = y2;
            angle -=  Math.toRadians(144);
        }
        path.closePath();;
		shape = path;
		aft = new AffineTransform();
		area = new Area(shape);
		
	}
}
