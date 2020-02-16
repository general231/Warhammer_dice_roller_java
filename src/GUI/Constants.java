package GUI;

public class Constants {

    static public int windowHeight = 800;
    static public int windowWidth = 1200;
    static public String windowTitle = "Dice roller";

    static public int optionsSceneHeight = 750;
    static public int optionsSceneWidth = 300;

    static public int statisticsSceneHeight = 750;
    static public int statisticsSceneWidth = 700;

    static public int[] ballisticSkill = new int[]{2, 3, 4, 5, 6};
    static public int[] hitModifiers = new int[]{3,2,1,0,-1,-2,-3};
    static public String[] hitReRolls = new String[]{" ", "ones", "failed hits", "hits"};
    static public String[] woundReRolls = new String[]{" ", "ones", "failed wounds"};
    static public String[] possibleDiceRolls = new String[]{" ", "D3", "D6", "2D6 pick the highest", "D6 with re-roll"};
    static public int[] saveValues = new int[]{2,3,4,5,6,7};
}
