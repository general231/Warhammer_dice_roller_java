package DiceRoller;


public class DamageObject {
    private int myDamage;
    private int myAp;
    private Types.e_DamageType myType;

    public DamageObject(int damage, int ap, Types.e_DamageType type)
    {
        myDamage = damage;
        myAp = ap;
        myType = type;
    }

    public int getDamage()
    {
        return myDamage;
    }
    public int getAp()
    {
        return myAp;
    }
    public Types.e_DamageType getType()
    {
        return myType;
    }

    public void halveDamage()
    {
        myDamage = (myDamage + 1) / 2;
    }
    public void reduceDamage(int value)
    {
        myDamage -= value;
        if (myDamage < 1) { myDamage = 1; }
    }

}