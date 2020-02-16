package DiceRoller;

public class ModelObject {
    private int myWoundCharacteristic;
    private boolean myIsFnp;
    private int myFnp;
    private DiceRoller myDiceRoller;
    private boolean myHalveDamage;
    private boolean myIsReduceDamage;
    private int myReduceDamageAmount;

    private int myTotalDamageReceived;
    private int myTotalLostModels;
    private int myWoundsRemaining;

    public void setHalveDamage(boolean value)
    {
        myHalveDamage = value;
    }
    public void setMyReduceDamageAmount(int value)
    {
        myReduceDamageAmount = value;
        myIsReduceDamage = true;
    }
    public void setMyFnp(int value)
    {
        myFnp = value;
        myIsFnp = true;
    }
    public int getMyTotalDamageReceived() { return myTotalDamageReceived; }
    public int getMyTotalLostModels() { return myTotalLostModels; }
    public void reset()
    {
        myTotalDamageReceived = 0;
        myTotalLostModels = 0;
        myWoundsRemaining = 0;
    }

    public void applyDamage(DamageObject someDamage)
    {
        if (myHalveDamage)
        {
            someDamage.halveDamage();
        }
        if (myIsReduceDamage)
        {
            someDamage.reduceDamage(myReduceDamageAmount);
        }
        int damageToApply = someDamage.getDamage();
        for (int i = 0; i < someDamage.getDamage(); i++)
        {
            int fnpDiceValue = myDiceRoller.rollDiceD6();
            if (myFnp >= fnpDiceValue)
            {
                damageToApply--;
            }
        }
        myTotalDamageReceived += damageToApply;
        myWoundsRemaining -= damageToApply;
        if (myWoundsRemaining < 2)
        {
            myTotalLostModels += 1;
            myWoundsRemaining = myWoundCharacteristic;
        }
    }

    public ModelObject(int woundCharacteristic)
    {
        myWoundCharacteristic = woundCharacteristic;
        myIsReduceDamage = false;
        myIsFnp = false;
        myHalveDamage = false;
        myDiceRoller = new DiceRoller();
    }
}
