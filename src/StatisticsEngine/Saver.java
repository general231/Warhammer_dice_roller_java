package StatisticsEngine;

public class Saver {
    private ModelObject myModelObject;
    private int myArmourSave;
    private int myInvulnerableSave;
    private DiceRoller myDiceRoller;

    public Saver(int armourSave, int invulnerableSave, int woundCharacteristic, int fnp)
    {
        myArmourSave = armourSave;
        myInvulnerableSave = invulnerableSave;
        myModelObject = new ModelObject(woundCharacteristic);
        myModelObject.setMyFnp(fnp);
        myDiceRoller = new DiceRoller();
    }

    public boolean rollToSave(DamageObject someDamage)
    {
        int diceValue = myDiceRoller.rollDiceD6();
        if (someDamage.getType() != Types.e_DamageType.MORTAL)
        {
            myModelObject.applyDamage(someDamage);
            return false;
        }
        else if (diceValue >= myInvulnerableSave || diceValue - someDamage.getAp() >= myArmourSave)
        {
            return true;
        }
        myModelObject.applyDamage(someDamage);
        return false;
    }
}
