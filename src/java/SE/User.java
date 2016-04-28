package SE;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moroclash
 */
public class User {
    
    private int ID;
    private String F_name;
    private String L_name;
    private String Email;
    private String Password;
    private int Type_id;
    private String Gander;
    private HashMap<String, String> Additional_data;
    private HashMap<Integer,String> Phones;
    private HashMap<Integer,String> Addresses;
 
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    //omar
    public boolean setF_name(String F_name) {
        if(!Validations.Is_alpha(F_name))
            return false;
        this.F_name = F_name;
        return true;
    }

    public String getF_name() {
        return F_name;
    }

    
    //omar
    public boolean setEmail(String Email) {
        if(!Validations.Is_email(Email))
            return false;
        this.Email = Email;
        return true;
    }

    public String getEmail() {
        return Email;
    }
    //omar
    public boolean setGander(String Gander) {
        if(!Validations.Is_gender(Gander))
            return false;
        this.Gander = Gander;
        return true;
    }

    public String getGander() {
        return Gander;
    }
    //omar
    public boolean setL_name(String L_name) {
        if(!Validations.Is_alpha(L_name))
            return false;
        this.L_name = L_name;
        return true;
    }

    public String getL_name() {
        return L_name;
    }
    
    //omar
    public boolean setPassword(String Password) {
        if(!Validations.Is_passord(Password))
            return false;
        this.Password = Password;
        return true;
    }

    public String getPassword() {
        return Password;
    }
    
    //omar
    public void setType_id(int Type_id) {
        this.Type_id = Type_id;
    }

    public int getType_id() {
        return Type_id;
    }

    public HashMap<Integer, String> getAddresses() {
        return Addresses;
    }    
    
    //omar
    public boolean Update_address(int address_id,String new_address) {
        if(Validations.Is_alphanumaric(new_address))
        { 
            Addresses.replace(address_id, new_address);
            return true;
        }
        return false;
    }

    //omar
    public ArrayList<General_massge> Show_all_my_rescived_massage() {
        try {
            ArrayList<General_massge> m = new ArrayList<>();
            Data_access.DB_controller.Connect();
            ResultSet res = Data_access.DB_controller.Select("Message_id", "recieved", "Reciever_id="+getID());
            int x=0;
            while(res.next())
            {
                x = res.getInt("Message_id");
                General_massge ms =(General_massge) new System_manage().Search_Massage(x);
                m.add(ms);
            }
            Data_access.DB_controller.Close();
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    //omar
    public ArrayList<General_massge> Show_my_massage_send() {
        try {
            ArrayList<General_massge> m = new ArrayList<>();
            Data_access.DB_controller.Connect();
            ResultSet res = Data_access.DB_controller.Select("Message_id", "message", "sender_id="+getID());
            int x=0;
            while(res.next())
            {
                x = res.getInt("Message_id");
                General_massge ms =(General_massge) new System_manage().Search_Massage(x);
                m.add(ms);
            }
            Data_access.DB_controller.Close();
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //omar
    public ArrayList<General_massge> Show_my_massage(int State_id) {
        try {
            ArrayList<General_massge> m = new ArrayList<>();
            ResultSet res = Data_access.DB_controller.Select("Message_id", "recieved", "Reciever_id="+getID()+",State_id="+State_id);
            int x=0;
            while(res.next())
            {
                x = res.getInt("Message_id");
                General_massge ms =(General_massge) new System_manage().Search_Massage(x);
                m.add(ms);
            }
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    //omar
    public void Add_massage(General_massge New_massage) {
        new System_manage().Send_Message(New_massage);
    }
    
    

    //omar
    public boolean DeleteMassge_that_send(int Massage_id) {
            boolean z = false;
        try {
            Data_access.DB_controller.Connect();
            ResultSet res2 = Data_access.DB_controller.Select("Reciever_id","recieved", "Message_id="+Massage_id);
            int x = 0;
            while (res2.next()) {
               x = res2.getInt("Reciever_id");
            }
            z = new System_manage().Update_massage_state(Massage_id, x, 6);
            Data_access.DB_controller.Close();
            } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        return z;
    }
    
    //omar
    public boolean Delete_Massge_that_resived(int Massage_id) {
            Data_access.DB_controller.Connect();
            boolean z = new System_manage().Update_massage_state(Massage_id, getID(), 7);
            Data_access.DB_controller.Close();
            return z;
    }

    public void setPhones(HashMap<Integer, String> Phones) {
        this.Phones = Phones;
    }

    public HashMap<Integer, String> getPhones() {
        return Phones;
    }

    
    //////////////////////////////
    
    public HashMap<String, String> getAdditional_data() {
        return Additional_data;
    }

    public boolean Add_new_additional_info(String Key, String Value) {
        return false;
    }

    public boolean Delete_additional_info(String Key) {
        return false;
    }

    public boolean Update_additional_info(String Old_key, String New_key, String New_value) {
        return false;
    }
    
    
    
 
    
    

}
