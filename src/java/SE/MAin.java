/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE;

import Data_access.DB_controller;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author moahmed A.Radwan
 */
public class MAin {
    public static void main(String[] args) {

        
        
        try {
            Message_Controller send =  Message_Controller.Get_Message_Controller();
            send.Send_Email(1, "title", "message");
            /*
            System_manage s= null;
            Customer c = new Customer();
            HashMap <Integer , String > ph = null;
            HashMap <Integer , String > ad = null;
            HashMap<Integer, String> Additional_data = null;
            ph.put(1, "01006977751");
            ad.put(1, "cairo");
            
            int id = 1;
            c.setF_name("salah");
            c.setL_name("mohamed");
            c.setBlock(1);
            c.setEmail("medo@yahoo.com");
            c.setPassword("12345784");
            c.setType_id(5);
            c.setGander("male");
            c.setAddresses(ad);
            c.setPhones(ph);
            //s.add_address_to_customer(c ,id);
            
            System.out.println(s.Get_near_branch(17));
            
            
            //System.out.println(s.Get_near_branch(17));
            System.out.println(s.Get_time());
            
            */
        } catch (MessagingException ex) {
            Logger.getLogger(MAin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MAin.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        }

    }


