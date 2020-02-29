package GUI;

public class Constants {

    static public Integer windowHeight = 600;
    static public Integer windowWidth = 1000;
    static public String windowTitle = "Dice roller";

    static public Integer toolbarHeight = 10;

    static public Integer optionsSceneHeight = 750;
    static public Integer optionsSceneWidth = 300;

    static public Integer statisticsSceneHeight = 750;
    static public Integer statisticsSceneWidth = 700;

    static public String[] ballisticSkill = new String[]{"","2", "3", "4", "5", "6"};
    static public String[] hitModifiers = new String[]{"","3","2","1","0","-1","-2","-3"};
    static public String[] hitReRolls = new String[]{" ", "ones", "failed hits", "hits"};
    static public String[] woundReRolls = new String[]{" ", "ones", "failed wounds"};
    static public String[] possibleNumShots = new String[]{" ", "XD3", "XD6", "XD6 pick the highest", "XD3 pick the highest", "XD6 with re-roll", "XD3 with re-roll"};
    static public String[] saveValues = new String[]{"","2","3","4","5","6","7"};
    static public String[] iterations = new String[]{"small", "medium", "large"};

    static public int stringIterationsToNum(String iteration)
    {
        switch (iteration) {
            case "small":
                return 1000;
            case "medium":
                return 10000;
            case "large":
                return 100000;
            default:
                return 10000000;
        }
    }
}
