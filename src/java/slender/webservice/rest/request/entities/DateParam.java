/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.request.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heinrich
 */
public class DateParam implements Serializable {
    static final DateFormat CRAZY_FORMAT = new SimpleDateFormat("yyyy-dd-MM");
    
    public static DateParam valueOf(String dateString) {
        DateParam param = new DateParam();
        try {
            Date date = new Date(dateString);
            param.setDate(date);
        } catch(Exception e) {
            try {
                Date date = CRAZY_FORMAT.parse(dateString);
                param.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(DateParam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return param;
    }

    private Date date;

    public DateParam() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
