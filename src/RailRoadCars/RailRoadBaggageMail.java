package RailRoadCars;
public class RailRoadBaggageMail extends RailRoadPostOffice{
    private int baggageWeight = 5;
    private int amountOfBaggage;
    private int finalBaggageWeight;
    private boolean isMessy;

    public RailRoadBaggageMail() {
        genAmountOfBaggage();
        genCompWeight();
        genFinalWeightOfBaggage();
        isMess();
    }
    public int genAmountOfBaggage(){
        return this.amountOfBaggage = (int)((Math.random()*51)+30);
    }
    public int genFinalWeightOfBaggage(){
        return finalBaggageWeight = amountOfBaggage * baggageWeight;
    }
    public boolean isMess(){
        double x = Math.random();
        if (x <= 0.1){
            return isMessy = true;
        }
        else return isMessy = false;
    }

    @Override
    public int genCompWeight() {
        setCompWeight(getWeight()+((getAmountOfMail() * getMailWeight())+(baggageWeight*amountOfBaggage)));
        return getCompWeight();
    }

    @Override
    public String toString() {
        return "RailRoadBaggageMail["+ isMessy + "Contains: "+ finalBaggageWeight + "kg of baggage and " + getFinalMailWeight() + "kg of mail" + ", weight is: " + getCompWeight() + ", number is: " + getNumber()  +
                ']';
    }

    public String fullInformation() {
        return "RailRoadBaggageMail{" +
                " amountOfBaggage=" + amountOfBaggage +
                ", baggageWeight=" + baggageWeight +
                ", amountOfMail=" + getAmountOfMail() +
                ", mailWeight=" + getMailWeight() +
                ", compWeight=" + getCompWeight() +
                ", number='" + getNumber()  + '\'' +
                '}';
    }
}
