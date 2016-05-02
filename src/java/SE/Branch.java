
package SE;

import Data_access.DB_controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Branch {
    private int Id;


    private int Address_id;

    private int Manager_id;
    private ArrayList<String> phones;
    private ArrayList <Integer> Subscriber;
    private String notfy_message;
    
    public void setMnager_id(int Mnager_id){
        this.Manager_id = Mnager_id;
    }

    public int getAddress_id() {
        return Address_id;
    }

    public void setAddress_id(int Address_id) {
        this.Address_id = Address_id;
    }

    public int getMnager_id() {
        return Manager_id;
    }

    
    //sala7
    public  void push (String value)
    {
       this.phones.add(value); 
    }
    public int getId() {
        return Id;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

   

    public void setId(int Id) {
        this.Id = Id;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }
    //sala7
     public ArrayList<String> getPhones_branch( int branch_id ) {
         
         ArrayList <String> phone = null;
         DB_controller DB = DB_controller.Get_DB_controller();
         DB.Connect();
         
         ResultSet result = null;
         result = DB.Select(" phone ", " branch_phone ", " Branch_id = " + branch_id);
         String Phone = "";
         try {
            while(result.next())
            {
               phone.add(result.getString("phone"));
            }
            return phone;
        } catch (SQLException ex) {
            ex.printStackTrace();
            DB.Close();
        }
        DB.Close();
        return null;
     }
    
   public double Show_salaris()
   {
       return 0.0;
   }
   //Emad
    public double Get_quality() {
        DB_controller.Get_DB_controller().Connect();
        int NumberOfRows = 0;
        int Summtion = 0;
        double Persatage = 0;
        try {
            ResultSet result = DB_controller.Get_DB_controller().Select("*", "Feedback", "Branch_ID=" + Id);
            while (result.next()) {
                Summtion = Summtion+result.getInt("Service_quality");
                NumberOfRows++;
            }
        } catch (Exception e) {
            System.out.println("Error in Branch Quality");
        }
        Persatage = (Summtion / (NumberOfRows*100))*100;
        DB_controller.Get_DB_controller().Close();
        return Persatage;
    }
   public int Service_guality()
   {
       return 0;
   }
   public ArrayList <Bill> Show_accounting()
   {
       return null;
   }
   public ArrayList <Employee> Show_employee()
   {
    return null;   
   }

    public void SetNotfy_message(Notify notfy) {
        
    }

    public String GetNotfy_message() {
        return "";
    }
   public void Subcribe( int user_id)
   {
       
   }
   public void Desubcribe( int user_id)
   {
       
   }
   public void Notify()
   {
       
   }
   //Mohamed RAdwan 
   public boolean Send_Message_to_all_employee(General_massge message)
   {
        try
       {
           System_manage s =System_manage.Get_System_manage();
            DB_controller.Get_DB_controller().Connect();
            HashMap<String,String> Mass=new HashMap<>(10);
            int id=s.Get_date_iD();
            String time=s.Get_time();
            Mass.put("Content",message.getContent());
            Mass.put("sender_id",String.valueOf(message.getSender_id()));
            Mass.put("Type_id", Integer.toString(message.getMassage_type_id()));
            Mass.put("Date_id", String.valueOf(id));
            Mass.put("Time", time);
            Mass.put("Parent_id", "0");
            int idmass= DB_controller.Get_DB_controller().Insert("message",Mass);
            Mass=new  HashMap<String, String>(5);
            Mass.put("Reciever_id", String.valueOf(message.getReciver()));
            Mass.put("Message_id", String.valueOf(idmass));
            Mass.put("State_id", "5");
            DB_controller.Get_DB_controller().Insert("recieved", Mass);
       }//END Try
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return true;
    
    }//End Send_Message
   
   
   
}
