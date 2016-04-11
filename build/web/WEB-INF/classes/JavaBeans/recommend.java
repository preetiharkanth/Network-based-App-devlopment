/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Preeti Harkanth
 */
@Entity
public class recommend implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
String RId;
String ExistingUserEmail;
String NewUserEmail;

    public recommend() {
        this.RId = null;
        this.ExistingUserEmail = null;
        this.NewUserEmail = null;
    }

    public String getRId() {
        return RId;
    }

    @Override
    public String toString() {
        return "recommend{" + "RId=" + RId + ", ExistingUserEmail=" + ExistingUserEmail + ", NewUserEmail=" + NewUserEmail + '}';
    }

    public String getExistingUserEmail() {
        return ExistingUserEmail;
    }

    public String getNewUserEmail() {
        return NewUserEmail;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public void setExistingUserEmail(String ExistingUserEmail) {
        this.ExistingUserEmail = ExistingUserEmail;
    }

    public void setNewUserEmail(String NewUserEmail) {
        this.NewUserEmail = NewUserEmail;
    }

    
    
}
