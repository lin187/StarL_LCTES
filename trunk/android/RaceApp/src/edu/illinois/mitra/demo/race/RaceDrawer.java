package edu.illinois.mitra.demo.race;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import edu.illinois.mitra.starl.interfaces.LogicThread;
import edu.illinois.mitra.starl.objects.*;
import edu.illinois.mitra.starlSim.draw.Drawer;

public class RaceDrawer extends Drawer {

	private Stroke stroke = new BasicStroke(8);
	private Color selectColor = new Color(0,0,255,100);
	
	@Override
	public void draw(LogicThread lt, Graphics2D g) {
		RaceApp app = (RaceApp) lt;

		g.setColor(Color.RED);
		for(ItemPosition dest : app.destinations.values()) {
			g.fillRect(dest.getX() - 13, dest.getY() - 13, 26, 26);
		}

		g.setColor(Color.GREEN);
		ObstacleList list = app.obsList;
		for(int i = 0; i < list.ObList.size(); i++)
		{
			Obstacles currobs = list.ObList.get(i);
			Point nextpoint = currobs.obstacle.firstElement();
			Point curpoint = currobs.obstacle.firstElement();
			int[] xs = new int[currobs.obstacle.size()]; 
			int[] ys = new int[currobs.obstacle.size()]; ;
			
			for(int j = 0; j < currobs.obstacle.size() -1 ; j++){
			curpoint = currobs.obstacle.get(j);
			nextpoint = currobs.obstacle.get(j+1);
			g.drawLine(curpoint.x, curpoint.y, nextpoint.x, nextpoint.y);
			xs[j] = curpoint.x;
			ys[j] = curpoint.y;
			}
			xs[currobs.obstacle.size()-1] = nextpoint.x;
			ys[currobs.obstacle.size()-1] = nextpoint.y;
			
			g.drawLine(nextpoint.x, nextpoint.y, currobs.obstacle.firstElement().x, currobs.obstacle.firstElement().y);
			g.fillPolygon(xs,ys,currobs.obstacle.size());
		}
			
		g.setColor(selectColor);
		g.setStroke(stroke);
		if(app.currentDestination != null)
			g.drawOval(app.currentDestination.getX() - 20, app.currentDestination.getY() - 20, 40, 40);
	}

}
