/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Nitin
 */
@Entity
public class study implements Serializable {

    String SName;
    @Id
    String SCode;
    String DateCreated;
    String Email;
    String Question;
    String ImageURL;
    Integer ReqParticipants;
    Integer ActParticipants;
    String Description;
    String SStatus;

    public study() {
        this.SName = null;
        this.SCode = null;
        this.Description = null;
        this.Email = null;
        this.DateCreated = null;
        this.Question = null;
        this.ImageURL = null;
        this.ReqParticipants = 0;
        this.ActParticipants = 0;
        this.SStatus = null;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String DateCreated) {
        this.DateCreated = DateCreated;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public Integer getReqParticipants() {
        return ReqParticipants;
    }

    public void setReqParticipants(Integer ReqParticipants) {
        this.ReqParticipants = ReqParticipants;
    }

    public Integer getActParticipants() {
        return ActParticipants;
    }

    public void setActParticipants(Integer ActParticipants) {
        this.ActParticipants = ActParticipants;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getSStatus() {
        return SStatus;
    }

    public void setSStatus(String SStatus) {
        this.SStatus = SStatus;
    }

    public Integer getAverage() {
        return null;
    }

    public Integer getMaximum() {
        return null;
    }

    public Integer getMinimum() {
        return null;
    }

    public Integer getSD() {
        return null;
    }

}
