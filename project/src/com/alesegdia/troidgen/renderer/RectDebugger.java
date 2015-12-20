package com.alesegdia.troidgen.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

/**
 * Renders a list of rectangles in a JComponent canvas
 */
public class RectDebugger extends JComponent {
	
	private static final long serialVersionUID = 1L;

	private Dimension dimension;
	private int scale = 14;
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
		g.setColor(Color.LIGHT_GRAY);
		renderGrid(g);
		renderRects(g);
	}

	private void renderRects(Graphics g) {
		for( Rect r : rects )
		{
			int s2 = scale/4;
			int x1 = (int) ((r.position.x) * scale + center.x) - scale/2;
			int y1 = (int) ((r.position.y) * scale + center.y) - scale/2;
			int x2 = (int) ((r.size.x) * scale);
			int y2 = (int) ((r.size.y) * scale);
			g.setColor(Color.BLUE);
			g.fillRect(x1, y1, x2, y2);
			g.setColor(Color.MAGENTA);
			g.fillRect(x1 + s2, y1 + s2, x2 - 2*s2, y2 - 2*s2);
		}
	}

	private void renderGrid(Graphics g) {
		for( int i = 0; i < this.dimension.getWidth(); i += scale )
		{
			g.drawLine(0, i-2, (int) this.dimension.getWidth(), i);
		}
		
		for( int i = 0; i < this.dimension.getWidth(); i += scale )
		{
			g.drawLine(i, 0, i, (int) this.dimension.getWidth());
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
		JFrame mainFrame = new JFrame("RectDebugger");
		mainFrame.getContentPane().add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}