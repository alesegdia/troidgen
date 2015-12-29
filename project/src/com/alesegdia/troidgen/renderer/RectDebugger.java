package com.alesegdia.troidgen.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.alesegdia.troidgen.room.Link;
import com.alesegdia.troidgen.room.Room;
import com.alesegdia.troidgen.util.Rect;
import com.alesegdia.troidgen.util.Vec2;

/**
 * Renders a list of rectangles in a JComponent canvas
 */
public class RectDebugger extends JComponent {
	
	private static final long serialVersionUID = 1L;

	private Dimension dimension;
	private int scale = 14;
	private List<Room> rects = new LinkedList<Room>();
	private Vec2 center;

	private Rect enclosing;
	
	public RectDebugger(List<Room> rects, int width, int height)
	{
		this(rects, width, height, null);
	}
	
	public RectDebugger(List<Room> rects, int width, int height, Rect enclosingRect) {
		this.dimension = new Dimension(width, height);
		this.center = new Vec2(width/2, height/2);
		for( Room r : rects )
		{
			this.rects.add(new Room(r));
		}
		this.rects = rects;
		this.enclosing = enclosingRect;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		renderRects(g);
		renderGrid(g);

	}

	private void renderRects(Graphics g) {
		
		if( enclosing != null )
		{
			int x1 = (int) ((enclosing.position.x) * scale + center.x) - scale/2;
			int y1 = (int) ((enclosing.position.y) * scale + center.y) - scale/2;
			int x2 = (int) ((enclosing.size.x) * scale);
			int y2 = (int) ((enclosing.size.y) * scale);
			g.setColor(new Color(220, 220, 255));
			g.fillRect(x1, y1, x2, y2);
		}
		
		for( Room r : rects )
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
			
			for( Link l : r.links )
			{
				int xrel = (int) (((r.position.x + l.relCoord.x) * scale + center.x) - scale/2);
				int yrel = (int) (((r.position.y + l.relCoord.y) * scale + center.y) - scale/2);
				
				float mid = scale / 2;
				
				if( l.isConnected() )
				{
					g.setColor(Color.GREEN);
				}
				else
				{
					g.setColor(new Color(0, 0, 128));
				}

				switch( l.direction )
				{
				case TOP:
					g.fillRect((int) (xrel + mid - 2), y1 + y2 - 4, 4, 4);
					break;
				case DOWN:
					g.fillRect((int) (xrel + mid - 2), y1, 4, 4);
					break;
				case LEFT:
					g.fillRect(x1, (int) (yrel + mid - 2), 4, 4);
					break;
				case RIGHT:
					g.fillRect(x1 + x2 - 4, (int) (yrel + mid - 2), 4, 4);
					break;
				case NODIR:
					break;
				}
			}
		}
	}

	private void renderGrid(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);

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
