/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE;

import Data_access.DB_controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author moahmed A.Radwan
 */
public class MAin {
    public static void main(String[] args) {
           
        System_manage s = System_manage.Get_System_manage();
        //System.out.println(s.Get_near_branch(17));
          System.out.println(s.Get_time());  
            
            
            
        }
    }


