package GameFolder;

//ShapeModelSingleton.java
public class ShapeModelSingleton {
 private static ShapeModel instance;
 private GameBoardDialog gameBoardDialog;
 private LandingPageFrame landingPageFrame;
 private ShapeLevelSelectDialog shapeLevelSelectDialog;
 private ShapeMatchListener shapeMatchListener;
 private LevelTopBarPanel levelTopBarPanel;
 private int levelNumber, index;
 private ShapeLevel shapeLevel;

 private ShapeModelSingleton() {} // Private constructor to prevent instantiation

 public static ShapeModel getInstance(GameBoardDialog gameBoardDialog, LandingPageFrame landingPageFrame, 
 		ShapeLevelSelectDialog shapeLevelSelectDialog, ShapeMatchListener shapeMatchListener, 
 		LevelTopBarPanel levelTopBarPanel,int levelNumber, int index, ShapeLevel shapeLevel) {
	 
//	 public ShapeModel(GameBoardDialog parentDialog, LandingPageFrame landingPageFrame, 
//	    		ShapeLevelSelectDialog shapeLevelSelectDialog, ShapeMatchListener shapeMatchListener, 
//	    		LevelTopBarPanel levelTopBarPanel,int levelNumber, int index, ShapeLevel shapeLevel)
     if (instance == null) {
         instance = new ShapeModel(gameBoardDialog,landingPageFrame,shapeLevelSelectDialog,
        		 shapeMatchListener,levelTopBarPanel,levelNumber,index,shapeLevel);
     }
     return instance;
 }
}
