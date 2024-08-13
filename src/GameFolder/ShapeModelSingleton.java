package GameFolder;

//ShapeModelSingleton.java
public class ShapeModelSingleton {
 private static ShapeModel instance;
// private GameBoardDialog gameBoardDialog;
// private LandingPageFrame landingPageFrame;
// private ShapeLevelSelectDialog shapeLevelSelectDialog;
// private ShapeMatchListener shapeMatchListener;
// private LevelTopBarPanel levelTopBarPanel;
// private int levelNumber, index;
// private ShapeLevel shapeLevel, nextLevel;

 private ShapeModelSingleton() {} // Private constructor to prevent instantiation

 public static ShapeModel getInstance(GameBoardDialog gameBoardDialog, LandingPageFrame landingPageFrame, 
 		ShapeLevelSelectDialog shapeLevelSelectDialog, ShapeMatchListener shapeMatchListener, 
 		LevelTopBarPanel levelTopBarPanel,int levelNumber, int index, ShapeLevel shapeLevel, ShapeLevel nextLevel,
 		int catIndex,GameSelectBoardPanel gameSelectBoardPanel) {
     if (instance == null) {
         instance = new ShapeModel(gameBoardDialog,landingPageFrame,shapeLevelSelectDialog,
        		 shapeMatchListener,levelTopBarPanel,catIndex,levelNumber,index,shapeLevel, nextLevel, gameSelectBoardPanel);
     }
     return instance;
 }
}
