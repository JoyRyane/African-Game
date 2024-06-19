package GameFolder;

import java.awt.Color;
import java.util.List;
import GameFolder.ShapeModel;
import GameFolder.ShapeModel.ShapeInfo;
import GameFolder.ShapeModel.ShapeType;

public class LevelInitializer {
	
	public static final ShapeType SHAPE_RECTANGLE = ShapeType.RECTANGLE;
	public static final ShapeType SHAPE_ELLIPSE = ShapeType.ELLIPSE;
	public static final ShapeType SHAPE_TRIANGLE = ShapeType.TRIANGLE;
	public static final ShapeType SHAPE_STAR = ShapeType.STAR;
	public static final ShapeType SHAPE_RHOMBUS = ShapeType.RHOMBUS;
	public static final ShapeType SHAPE_HEART = ShapeType.HEART;
	public static final ShapeType SHAPE_PARALLELOGRAM = ShapeType.PARALLELOGRAM;
	public static final ShapeType SHAPE_TRAPEZIUM = ShapeType.TRAPEZIUM;
	
	public static final Color CUSTOM_COLOR_1 = CustomColors.CUSTOM_COLOR_1;
	public static final Color CUSTOM_COLOR_2 = CustomColors.CUSTOM_COLOR_2;
	public static final Color CUSTOM_COLOR_3 = CustomColors.CUSTOM_COLOR_3;
	public static final Color CUSTOM_COLOR_4 = CustomColors.CUSTOM_COLOR_4;


    public static void initializeLevel1(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
        movableShapes.add(new ShapeInfo(x + 150, 220, 80, 80, Color.BLUE, SHAPE_RECTANGLE, true, true, true));
        movableShapes.add(new ShapeInfo(x + 140, 400, 80, 80, Color.GREEN, SHAPE_ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 30, 400, 80, 80, Color.MAGENTA, SHAPE_TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 10, 250, 100, 100, Color.YELLOW, SHAPE_STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 100, 100, 60, 60, Color.RED, SHAPE_RHOMBUS, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 550, 365, 80, 80, Color.BLUE, SHAPE_RECTANGLE, false, true, true));
        unmovableShapes.add(new ShapeInfo(x + 650, 80, 80, 80, Color.GREEN, SHAPE_ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 550, 220, 80, 80, Color.MAGENTA, SHAPE_TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 390, 100, 100, Color.YELLOW, SHAPE_STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 680, 250, 60, 60, Color.RED, SHAPE_RHOMBUS, false, false, false));
    }

    public static void initializeLevel2(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
		movableShapes.add(new ShapeInfo(x + 50, 70, 70, 70, Color.BLUE, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 120, 280, 70, 70, Color.GREEN, ShapeType.ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 30, 380, 70, 70, Color.MAGENTA, ShapeType.TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 10, 200, 90, 90, Color.YELLOW, ShapeType.STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 150, 170, 50, 50, Color.RED, ShapeType.RHOMBUS, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 550, 365, 70, 70, Color.BLUE, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 680, 220, 70, 70, Color.GREEN, ShapeType.ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 220, 70, 70, Color.MAGENTA, ShapeType.TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 80, 90, 90, Color.YELLOW, ShapeType.STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 680, 410, 50, 50, Color.RED, ShapeType.RHOMBUS, false, false, false));
	}
    
	public static void initializeLevel3(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
		movableShapes.add(new ShapeInfo(x + 70, 70, 70, 70, Color.ORANGE, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 90, 280, 70, 70, Color.YELLOW, ShapeType.ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 20, 350, 70, 70, Color.RED, ShapeType.TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 10, 190, 90, 90, Color.GREEN, ShapeType.STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 170, 390, 50, 50, Color.MAGENTA, ShapeType.RHOMBUS, true, false, false));
        movableShapes.add(new ShapeInfo(x + 150, 200, 90, 50, Color.CYAN, ShapeType.RECTANGLE, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 680, 255, 70, 70, Color.ORANGE, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 380, 70, 70, Color.YELLOW, ShapeType.ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 120, 70, 70, Color.RED, ShapeType.TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 540, 90, 90, 90, Color.GREEN, ShapeType.STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 250, 50, 50, Color.MAGENTA, ShapeType.RHOMBUS, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 520, 400, 90, 50, Color.CYAN, ShapeType.RECTANGLE, false, false, false));
	}
	
	public static void initializeLevel4(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
		movableShapes.add(new ShapeInfo(x + 180, 400, 60, 60, Color.GREEN, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 30, 100, 60, 60, Color.MAGENTA, ShapeType.ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 40, 340, 60, 60, Color.ORANGE, ShapeType.TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 150, 90, 80, 80, Color.BLUE, ShapeType.STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 170, 280, 40, 40, CustomColors.CUSTOM_COLOR_3, ShapeType.RHOMBUS, true, false, false));
        movableShapes.add(new ShapeInfo(x + 50, 250, 80, 40, Color.CYAN, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 20, 390, 70, 70, Color.RED, ShapeType.HEART, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 500, 140, 60, 60, Color.GREEN, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 380, 60, 60, Color.MAGENTA, ShapeType.ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 670, 270, 60, 60, Color.ORANGE, ShapeType.TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 540, 370, 80, 80, Color.BLUE, ShapeType.STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 590, 250, 40, 40, CustomColors.CUSTOM_COLOR_3, ShapeType.RHOMBUS, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 120, 80, 40, Color.CYAN, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 490, 220, 70, 70, Color.RED, ShapeType.HEART, false, false, false));
	}
	
	public static void initializeLevel5(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
		movableShapes.add(new ShapeInfo(x + 40, 190, 50, 50, Color.GREEN, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 150, 230, 50, 50, Color.MAGENTA, ShapeType.ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 140, 300, 50, 50, CustomColors.CUSTOM_COLOR_4, ShapeType.TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 20, 390, 70, 70, Color.BLUE, ShapeType.STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 40, 320, 30, 30, Color.PINK, ShapeType.RHOMBUS, true, false, false));
        movableShapes.add(new ShapeInfo(x + 130, 400, 70, 30, Color.CYAN, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 130, 90, 60, 60, Color.RED, ShapeType.HEART, true, false, false));
        movableShapes.add(new ShapeInfo(x + 30, 100, 70, 40, Color.PINK, ShapeType.PARALLELOGRAM, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 480, 400, 50, 50, Color.GREEN, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 680, 380, 50, 50, Color.MAGENTA, ShapeType.ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 520, 250, 50, 50, CustomColors.CUSTOM_COLOR_4, ShapeType.TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 650, 120, 70, 70, Color.BLUE, ShapeType.STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 550, 140, 30, 30, Color.PINK, ShapeType.RHOMBUS, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 350, 70, 30, Color.CYAN, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 670, 220, 60, 60, Color.RED, ShapeType.HEART, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 600, 450, 70, 40, Color.PINK, ShapeType.PARALLELOGRAM, false, false, false));
	}
	
	public static void initializeLevel6(List<ShapeInfo> movableShapes, List<ShapeInfo> unmovableShapes, int x) {
		movableShapes.add(new ShapeInfo(x + 40, 390, 40, 40, CustomColors.CUSTOM_COLOR_1, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 150, 230, 40, 40, Color.MAGENTA, ShapeType.ELLIPSE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 140, 300, 40, 40, Color.ORANGE, ShapeType.TRIANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 20, 170, 60, 60, Color.BLUE, ShapeType.STAR, true, false, false));
        movableShapes.add(new ShapeInfo(x + 40, 320, 20, 20, CustomColors.CUSTOM_COLOR_2, ShapeType.RHOMBUS, true, false, false));
        movableShapes.add(new ShapeInfo(x + 30, 100, 60, 20, Color.CYAN, ShapeType.RECTANGLE, true, false, false));
        movableShapes.add(new ShapeInfo(x + 50, 220, 50, 50, Color.RED, ShapeType.HEART, true, false, false));
        movableShapes.add(new ShapeInfo(x + 130, 140, 60, 30, Color.PINK, ShapeType.PARALLELOGRAM, true, false, false));
        movableShapes.add(new ShapeInfo(x + 130, 400, 60, 30, Color.PINK, ShapeType.TRAPEZIUM, true, false, false));

        unmovableShapes.add(new ShapeInfo(x + 680, 220, 40, 40, CustomColors.CUSTOM_COLOR_1, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 250, 40, 40, Color.MAGENTA, ShapeType.ELLIPSE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 680, 380, 40, 40, Color.ORANGE, ShapeType.TRIANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 600, 430, 60, 60, Color.BLUE, ShapeType.STAR, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 140, 20, 20, CustomColors.CUSTOM_COLOR_2, ShapeType.RHOMBUS, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 580, 350, 60, 20, Color.CYAN, ShapeType.RECTANGLE, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 500, 400, 50, 50, Color.RED, ShapeType.HEART, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 570, 200, 60, 30, Color.PINK, ShapeType.PARALLELOGRAM, false, false, false));
        unmovableShapes.add(new ShapeInfo(x + 660, 120, 60, 30, Color.PINK, ShapeType.TRAPEZIUM, false, false, false));
	}
}

