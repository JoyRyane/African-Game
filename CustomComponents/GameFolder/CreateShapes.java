package GameFolder;

import java.awt.Polygon;

public class CreateShapes {
	
	    public static Polygon createRhombus(int centerX, int centerY, int halfWidth, int halfHeight) {
	        Polygon rhombus = new Polygon();
	        rhombus.addPoint(centerX, centerY - halfHeight - 20); // Top
	        rhombus.addPoint(centerX + halfWidth + 10, centerY); // Right
	        rhombus.addPoint(centerX, centerY + halfHeight + 20); // Bottom
	        rhombus.addPoint(centerX - halfWidth - 10, centerY); // Left
	        return rhombus;
	    }

	    public static Polygon createStar(int centerX, int centerY, int outerRadius, int innerRadius, int numPoints) {
	        Polygon star = new Polygon();
	        double angleStep = Math.PI / numPoints;
	        for (int i = 0; i < numPoints * 2; i++) {
	            double angle = i * angleStep - Math.PI / 2;
	            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
	            int x = (int) (centerX + Math.cos(angle) * radius);
	            int y = (int) (centerY + Math.sin(angle) * radius);
	            star.addPoint(x, y);
	        }
	        return star;
	    }

	    public static Polygon createHeart(int x, int y, int width, int height) {
	        Polygon heart = new Polygon();
	        int centerX = x + width / 2;
	        int bottomY = y + height;

	        heart.addPoint(centerX, bottomY);

	        int leftX = x;
	        int rightX = x + width;
	        int middleY = y + height / 3;

	        // Left half of heart
	        for (double t = Math.PI; t >= 0; t -= 0.1) {
	            int px = (int) (centerX - 0.5 * width * Math.pow(Math.sin(t), 2));
	            int py = (int) (bottomY - (0.5 * height * Math.cos(t) - 0.1 * height * Math.cos(2 * t) - 0.05 * height * Math.cos(3 * t) - 0.02 * height * Math.cos(4 * t)));
	            heart.addPoint(px, py);
	        }

	        // Right half of heart
	        for (double t = 0; t <= Math.PI; t += 0.1) {
	            int px = (int) (centerX + 0.5 * width * Math.pow(Math.sin(t), 2));
	            int py = (int) (bottomY - (0.5 * height * Math.cos(t) - 0.1 * height * Math.cos(2 * t) - 0.05 * height * Math.cos(3 * t) - 0.02 * height * Math.cos(4 * t)));
	            heart.addPoint(px, py);
	        }

	        return heart;
	    }
	    
	    public static Polygon createParallelogram(int x, int y, int width, int height) {
	        int x1 = x;
	        int y1 = y;
	        int x2 = x + width;
	        int y2 = y;
	        int x3 = x + width - height / 2;
	        int y3 = y + height;
	        int x4 = x - height / 2;
	        int y4 = y + height;
	        return new Polygon(new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4);
	    }
	    
	    public static Polygon createTrapezium(int x, int y, int width, int height) {
	        int x1 = x;
	        int y1 = y;
	        int x2 = x + width;
	        int y2 = y;
	        int x3 = x + width + height / 2;
	        int y3 = y + height;
	        int x4 = x - height / 2;
	        int y4 = y + height;
	        return new Polygon(new int[]{x1, x2, x3, x4}, new int[]{y1, y2, y3, y4}, 4);
	    }

}
