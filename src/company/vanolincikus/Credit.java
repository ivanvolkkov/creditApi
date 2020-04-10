package company.vanolincikus;


import java.util.*;

// DAO -data access object
public class Credit {



    private String creditName;
    private   String amountDate;
    private Date finalMonth;
    private double monthAmount;

    public Credit(){

    }

    double getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(double monthAmount) {
        this.monthAmount = monthAmount;
    }
    public Date getFinalMonth() {
        return finalMonth;
    }

    public void setFinalMonth(Date finalMonth) {
        this.finalMonth = finalMonth;
    }
    public String getAmountDate() {
        return amountDate;
    }

    public void setAmountDate(String amountDate) {
        this.amountDate = amountDate;
    }
    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
}
