/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Network_Project;
import java.time.*;
/**
 *
 * @author hetaf
 */
public class Reservation {
    private  String id;          // booking ID
    private  String username;    // renter username/email
    private  String carId;       // car identifier
    private  LocalDate startDate;// start date
    private  int days;           // number of days (>=1, <=7)

    public Reservation(String id, String username, String carId, LocalDate startDate, int days) {
        this.id = id;
        this.username = username;
        this.carId = carId;
        this.startDate = startDate;
        this.days = days;
    }//end of constructor

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
      // ===== Helper: end date (exclusive) =====
    // For range checks: occupies [startDate, getEndDateExclusive())
    public LocalDate getEndDateExclusive() {
        return startDate.plusDays(days);
    } // end getEndDateExclusive

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", username=" + username + ", carId=" + carId + ", startDate=" + startDate + ", days=" + days + '}';
    }
    
    
    
}//end of class 
