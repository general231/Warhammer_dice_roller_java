package GUI;

public class Constants {

    static public Integer windowHeight = 600;
    static public Integer windowWidth = 1000;
    static public String windowTitle = "Dice roller";

    static public Integer toolbarHeight = 10;

    static public Integer splitpaneHeight = 800;
    static public Integer graphTabHeight = 600;
    static public Integer graphTabWidth = 600;

    static public String[] ballisticSkill = new String[]{"","2", "3", "4", "5", "6"};
    static public String[] ballisticSkillReversed = new String[]{"","6","5","4","3","2"};
    static public String[] hitModifiers = new String[]{"","3","2","1","0","-1","-2","-3"};
    static public String[] hitReRolls = new String[]{" ", "ones", "failed hits", "hits"};
    static public String[] woundReRolls = new String[]{" ", "ones", "failed wounds"};
    static public String[] possibleNumShots = new String[]{" ", "XD3", "XD6", "XD6 pick the highest", "XD3 pick the highest", "XD6 with re-roll", "XD3 with re-roll"};
    static public String[] saveValues = new String[]{"","2","3","4","5","6","7"};
    static public String[] iterations = new String[]{"small", "medium", "large"};
    static public String[] bonus = new String[]{"","1","2","3","4","5","6","1D3","1D6"};

    static public int stringIterationsToNum(String iteration)
    {
        switch (iteration) {
            case "\"iterations\":\"small\",":
                return 10000;
            case "\"iterations\":\"medium\",":
                return 100000;
            case "\"iterations\":\"large\",":
                return 1000000;
            default:
                return 10000000;
        }
    }
}
