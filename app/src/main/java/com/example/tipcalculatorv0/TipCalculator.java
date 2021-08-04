package com.example.tipcalculatorv0;

public class TipCalculator {
    private float tip;
    private float bill;
    private float no_Of_People;

    public TipCalculator(float newTip, float newBill, float no_Of_People){
        setTip(newTip);
        setBill(newBill);
        setNo_Of_People(no_Of_People);
    }

    public void setTip(float newTip){
        if(newTip > 0)
            this.tip = newTip;
    }

    public void setBill(float newBill) {
        if(newBill > 0) 
            this.bill = newBill;
    }

    public void setNo_Of_People(float no_Of_People) {
        if (no_Of_People > 0)
            this.no_Of_People = no_Of_People;
    }

    public float getTip() {
        return tip;
    }

    public float getBill() {
        return bill;
    }

    public float getNo_Of_People() {
        return no_Of_People;
    }

    public float tipAmount(){
        return tip * bill;
    }

    public float totalAmount(){
        return bill + tipAmount();
    }

    public float totalPerPerson(){
        return totalAmount() / no_Of_People;
    }
}
