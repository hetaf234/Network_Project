/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network_Project;

/**
 *
 * @author hetaf
 */
public class Car {
    private  String carId;     
    private  String type;   
    private  int numOfSeates;    

    // ===== Constructor =====
    public Car(String id, String type, int seats) {
        carId = id;
        this.type = type;
        numOfSeates = seats;
    } // end constructor

    public String getType() {
        return type;
    }//getType

    public void setType(String type) {
        this.type = type;
    }//setType

    public int getNumOfSeates() {
        return numOfSeates;
    }//getNumOfSeates

    public void setNumOfSeates(int numOfSeates) {
        this.numOfSeates = numOfSeates;
    }//setNumOfSeates

    public String getCarId() {
        return carId;
    }//getCarId

    public void setCarId(String carId) {
        this.carId = carId;
    }//setCarId

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", type=" + type + ", numOfSeates=" + numOfSeates + '}';
    }//toString
    
    
    
    
}//end of the class 
