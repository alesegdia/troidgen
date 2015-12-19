package com.alesegdia.troidgen.overlapsolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

public class RectDebugger extends JComponent {
	
	private Dimension dimension;
	private int scale = 8;
	private List<Rect> rects = new LinkedList<Rect>();
	private Vec2 center;
	
	public RectDebugger(List<Rect> rects, int width, int height)
	{
		this.dimension = new Dimension(width, height);
		this.center = new Vec2(width/2, height/2);
		for( Rect r : rects )
		{
			this.rects.add(new Rect(r));
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		for( int i = 0; i < this.dimension.getWidth(); i += scale )
		{
			g.drawLine(0, i, (int) this.dimension.getWidth(), i);
		}
		for( int i = 0; i < this.dimension.getHeight(); i += scale )
		{
			g.drawLine(i, 0, i, (int) this.dimension.getHeight());
		}
		for( Rect r : rects )
		{
			int x1 = (int) (r.position.x * scale + center.x);
			int y1 = (int) (r.position.y * scale + center.y);
			g.setColor(Color.MAGENTA);
			g.fillRect(x1-2, y1-2, (int)r.size.x * scale + 4, (int)r.size.y * scale + 4);
			g.setColor(Color.BLUE);
			g.fillRect(x1, y1, (int)r.size.x * scale, (int)r.size.y * scale);
		}
	}

	public Dimension getPreferredSize() {
		return this.dimension;
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	public void Show()
	{
		JFrame mainFrame = new JFrame("Map renderer");
		mainFrame.getContentPane().add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
