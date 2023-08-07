package HeavyFreightCars;

import Exceptions.FreightCarCapacityException;

import java.util.Arrays;

public class ToxicCar extends HeavyFreightCar {
    private boolean isToxic = true;
    private int amountOfToxicMaterial;
    private String[]toxicMaterial = {"Lead" , "Mercury" , "Arsenic" , "Cyanide" , "Asbestos" , "Radon"};
    private String[]liquidToxicMaterial = {"Hydrochloric acid" , "Sulfuric acid" , "Formaldehyde" , "Chlorine" , "Benzene"};
    private boolean chanceToPoison;
    public ToxicCar(){
        genAmountOfToxicMaterial();
        genMaterial();
        genCompWeight();
    }
    public String genMaterial(){
        if(this instanceof ToxicCar && this instanceof LiquidToxicCar){
            setNameOfMaterial(liquidToxicMaterial[(int)(Math.random()*2)]);
            return getNameOfMaterial();
        }
        else {
            setNameOfMaterial(toxicMaterial[(int) (Math.random() * 6)]);
            return getNameOfMaterial();
        }
    }
    public boolean chanceToGetPoisoned(){
        double x = Math.random();
        if (x<0.01){
            return chanceToPoison = true;
        }
        else return chanceToPoison = false;
    }

    public boolean isToxic() {
        return isToxic;
    }

    public int getAmountOfToxicMaterial() {
        return amountOfToxicMaterial;
    }

    public void setAmountOfToxicMaterial(int amountOfToxicMaterial) {
        this.amountOfToxicMaterial = amountOfToxicMaterial;
    }

    public int genAmountOfToxicMaterial(){
        return amountOfToxicMaterial = (int)((Math.random()*(getVolume()-40))+40);
    }
    @Override
    public int genCompWeight() {
        setCompWeight(getWeight() + amountOfToxicMaterial);
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "ToxicCar[ Chance to get poisoned " + chanceToPoison + ".Contains: "+ amountOfToxicMaterial + "kg of " + getNameOfMaterial() + ", weight is: " + getCompWeight() +  ", number is: " + getNumber()  +
        ']';
    }

    public String fullInformation() {
        return "ToxicCar{" +
                "isToxic=" + isToxic +
                ", amountOfToxicMaterial=" + amountOfToxicMaterial +
                ", loadCapacity=" + getLoadCapacity() +
                ", volume=" + getVolume() +
                ", weight=" + getWeight() +
                ", compWeight=" + getCompWeight() +
                ", nameOfMaterial=" + getNameOfMaterial() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
