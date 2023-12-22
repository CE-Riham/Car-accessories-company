package model;

import java.sql.Connection;
import java.time.LocalDate;

public class InstallReq {
    private int userId;
    private int productId;
    private int carMakeModel;

    LocalDate preferredData;



    public InstallReq(Connection connection){

    }

    public InstallReq(){
        this.userId = -1;
        this.productId = -1;
        this.carMakeModel = -1;
        this.preferredData = null;
    }
    public InstallReq(int userId, int productId, int carMakeModel, LocalDate preferredData) {
        setUserId(userId);
        setProductId(productId);
        setCarMakeModel(carMakeModel);
        setPreferredData(preferredData);
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCarMakeModel() {
        return carMakeModel;
    }

    public void setCarMakeModel(int carMakeModel) {
        this.carMakeModel = carMakeModel;
    }

    public LocalDate getPreferredData() {
        return preferredData;
    }

    public void setPreferredData(LocalDate preferredData) {
        this.preferredData = preferredData;
    }


    @Override
    public String toString() {
        return "installationrequests{" +
                "UserId='" + userId + '\'' +
                ", ProductId='" + productId + '\'' +
                ", CarMakeModel='" + carMakeModel + '\'' +
                ", PreferredDate='" + preferredData +
                '}';
    }
}
