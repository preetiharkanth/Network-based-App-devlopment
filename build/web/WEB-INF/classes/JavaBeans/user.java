/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Nitin
 */
@Entity
public class user implements Serializable {

    String UName;
    @Id
    String Email;
    String Password;
    Integer Coins;
    Integer Participation;
    Integer Studies;
    String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public user() {
        this.UName = null;
        this.Email = null;
        this.Password = null;
        this.Studies = 0;
        this.Participation = 0;
        this.Coins = 0;    
        this.salt = null;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Integer getCoins() {
        return Coins;
    }

    public void setCoins(Integer Coins) {
        this.Coins = Coins;
    }

    public Integer getParticipation() {
        return Participation;
    }

    public void setParticipation(Integer Participation) {
        this.Participation = Participation;
    }

    public Integer getStudies() {
        return Studies;
    }

    public void setStudies(Integer Studies) {
        this.Studies = Studies;
    }
    
    

}
