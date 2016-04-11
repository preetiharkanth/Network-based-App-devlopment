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
public class answer implements Serializable{
    
    @Id
    String Email;
    @Id
    String SCode;
    Integer Choice;
    String DateSubmitted;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public Integer getChoice() {
        return Choice;
    }

    public void setChoice(Integer Choice) {
        this.Choice = Choice;
    }

    public String getDateSubmitted() {
        return DateSubmitted;
    }

    public void setDateSubmitted(String DateSubmitted) {
        this.DateSubmitted = DateSubmitted;
    }

    public answer() {
        this.Email = null;
        this.SCode = null;
        this.Choice = 0;
        this.DateSubmitted = null;
    }


    
    
}
