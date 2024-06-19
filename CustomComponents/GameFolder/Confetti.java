//package GameFolder;
//
//import java.awt.*;
//import java.util.Random;
//
//public class Confetti {
//    private int x, y;
//    private int size;
//    private Color color;
//    private int dy;
//    private ShapeType type;
//    private static final int MAX_DY = 5;
//    private static final Random rand = new Random();
//
//    public Confetti(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.size = rand.nextInt(10) + 5;
//        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
//        this.dy = rand.nextInt(MAX_DY) + 1;
//        this.type = ShapeType.values()[rand.nextInt(ShapeType.values().length)];
//    }
//
//    public void update() {
//        y += dy;
//    }
//
//    public void draw(Graphics g) {
//        g.setColor(color);
//        switch (type) {
//            case RECTANGLE:
//                g.fillRect(x, y, size, size);
//                break;
//            case ELLIPSE:
//                g.fillOval(x, y, size, size);
//                break;
//            case TRIANGLE:
//                Polygon triangle = new Polygon(
//                    new int[]{x, x + size / 2, x + size},
//                    new int[]{y + size, y, y + size},
//                    3
//                );
//                g.fillPolygon(triangle);
//                break;
//            case STAR:
//                Polygon star = createStar(x + size / 2, y + size / 2, size / 2, size / 4, 5);
//                g.fillPolygon(star);
//                break;
//            case RHOMBUS:
//                Polygon rhombus = createRhombus(x + size / 2, y + size / 2, size / 2, size / 2);
//                g.fillPolygon(rhombus);
//                break;
//        }
//    }
//
//    public boolean isOffScreen(int height) {
//        return y > height;
//    }
//
//    private Polygon createRhombus(int centerX, int centerY, int halfWidth, int halfHeight) {
//        Polygon rhombus = new Polygon();
//        rhombus.addPoint(centerX, centerY - halfHeight); // Top
//        rhombus.addPoint(centerX + halfWidth, centerY); // Right
//        rhombus.addPoint(centerX, centerY + halfHeight); // Bottom
//        rhombus.addPoint(centerX - halfWidth, centerY); // Left
//        return rhombus;
//    }
//
//    private Polygon createStar(int centerX, int centerY, int outerRadius, int innerRadius, int numPoints) {
//        Polygon star = new Polygon();
//        double angleStep = Math.PI / numPoints;
//        for (int i = 0; i < numPoints * 2; i++) {
//            double angle = i * angleStep - Math.PI / 2;
//            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
//            int x = (int) (centerX + Math.cos(angle) * radius);
//            int y = (int) (centerY + Math.sin(angle) * radius);
//            star.addPoint(x, y);
//        }
//        return star;
//    }
//
//    private enum ShapeType {
//        RECTANGLE, ELLIPSE, TRIANGLE, STAR, RHOMBUS
//    }
//}

package GameFolder;

import java.awt.*;
import java.util.Random;

public class Confetti {
    private int x, y;
    private int size;
    private Color color;
    private int dy;
    private ShapeType type;
    private static final int MAX_DY = 5;
    private static final Random rand = new Random();

    // Define custom bright colors
    private static final Color[] brightColors = {
        new Color(227, 255, 0), // Lemon green
        new Color(255, 165, 0), // Bright orange
        new Color(125, 249, 255) // Electric blue
    };

    public Confetti(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = rand.nextInt(10) + 25;
        this.color = brightColors[rand.nextInt(brightColors.length)]; // Choose a random bright color
        this.dy = rand.nextInt(MAX_DY) + 1;
        this.type = ShapeType.values()[rand.nextInt(ShapeType.values().length)];
    }

    public void update() {
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        switch (type) {
            case RECTANGLE:
                g.fillRect(x, y, size, size);
                break;
            case ELLIPSE:
                g.fillOval(x, y, size, size);
                break;
            case TRIANGLE:
                Polygon triangle = new Polygon(
                    new int[]{x, x + size / 2, x + size},
                    new int[]{y + size, y, y + size},
                    3
                );
                g.fillPolygon(triangle);
                break;
            case STAR:
                Polygon star = createStar(x + size / 2, y + size / 2, size / 2, size / 4, 5);
                g.fillPolygon(star);
                break;
            case RHOMBUS:
                Polygon rhombus = createRhombus(x + size / 2, y + size / 2, size / 2, size / 2);
                g.fillPolygon(rhombus);
                break;
        }
    }

    public boolean isOffScreen(int height) {
        return y > height;
    }

    private Polygon createRhombus(int centerX, int centerY, int halfWidth, int halfHeight) {
        Polygon rhombus = new Polygon();
        rhombus.addPoint(centerX, centerY - halfHeight); // Top
        rhombus.addPoint(centerX + halfWidth, centerY); // Right
        rhombus.addPoint(centerX, centerY + halfHeight); // Bottom
        rhombus.addPoint(centerX - halfWidth, centerY); // Left
        return rhombus;
    }

    private Polygon createStar(int centerX, int centerY, int outerRadius, int innerRadius, int numPoints) {
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

    private enum ShapeType {
        RECTANGLE, ELLIPSE, TRIANGLE, STAR, RHOMBUS
    }
}

