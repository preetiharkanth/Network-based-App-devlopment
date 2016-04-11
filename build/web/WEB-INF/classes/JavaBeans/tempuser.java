/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 *
 * @author Preeti Harkanth
 */
@Entity
public class tempuser implements Serializable {
    String UName;
    @Id
    String tempuserEmail;
    String Password;
    String IssueDate;
    String Token;

    public tempuser() {
        this.UName = null;
        this.tempuserEmail = null;
        this.Password = null;
        this.IssueDate = null;
        this.Token = null;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public void settempuserEmail(String tempuserEmail) {
        this.tempuserEmail = tempuserEmail;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setIssueDate(String IssueDate) {
        this.IssueDate = IssueDate;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getUName() {
        return UName;
    }

    public String gettempuserEmail() {
        return tempuserEmail;
    }

    public String getPassword() {
        return Password;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public String getToken() {
        return Token;
    }

    @Override
    public String toString() {
        return "tempuser{" + "UName=" + UName + ", tempuserEmail=" + tempuserEmail + ", Password=" + Password + ", IssueDate=" + IssueDate + ", Token=" + Token + '}';
    }
    
    
}
