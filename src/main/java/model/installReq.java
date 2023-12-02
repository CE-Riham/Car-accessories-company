package model;

import database.InsertingData;
import database.RetrievingData;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class installReq {
    private int Userid, Productid, CarMakeModel ;

    LocalDate PreferredData;


    private InsertingData userInserter;
    private RetrievingData userRetriever;

    public installReq(Connection connection){
        userInserter = new InsertingData(connection);
        userRetriever = new RetrievingData(connection);
    }

    public installReq(){
        this.Userid    = -1;
        this.Productid   = -1;
        this.CarMakeModel    = -1;
        this.PreferredData = null;
    }
    public installReq(int Userid, int Productid, int CarMakeModel, LocalDate PreferredData) {
        setUserid(Userid);
        setProductid(Productid);
        setCarMakeModel(CarMakeModel);
        setPreferredData(PreferredData);
    }
    public int getUserid() {
        return Userid;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    public int getProductid() {
        return Productid;
    }

    public void setProductid(int Productid) {
        this.Productid = Productid;
    }

    public int getCarMakeModel() {
        return CarMakeModel;
    }

    public void setCarMakeModel(int CarMakeModel) {
        this.CarMakeModel = CarMakeModel;
    }

    public LocalDate getPreferredData() {
        return PreferredData;
    }

    public void setPreferredData(LocalDate PreferredData) {
        this.PreferredData = PreferredData;
    }


    @Override
    public String toString() {
        return "installationrequests{" +
                "UserId='" + Userid + '\'' +
                ", ProductId='" + Productid + '\'' +
                ", CarMakeModel='" + CarMakeModel + '\'' +
                ", PreferredDate='" + PreferredData +
                '}';
    }

    public void validateAndStore() {
        System.out.println("Not Ready");
    }

    public List<LocalDate> checkInstallerAvailability() {
        return new ArrayList<>();

    }

    public List<LocalDate> getAvailableTimeSlots() {
        return new ArrayList<>();
    }
}
