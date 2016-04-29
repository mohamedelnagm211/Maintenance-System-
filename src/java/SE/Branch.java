
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
    
    
    
     //Emad D
   public double Show_salaris()
   {
       DB_controller DB=DB_controller.Get_DB_controller();
       ArrayList<Integer> EmployeesSalaries=new ArrayList<Integer>();
       DB.Connect();
       int Total_Salary=0;
       int ID_SALARY=-1;
       int Branch_ID=-1;       
       try
       {
           ResultSet result =DB.Select("*", "User_OPTION","Name='Salary'");
           while(result.next())
           {
               ID_SALARY=result.getInt("User_Option_ID");
           }
            result =DB.Select("*", "user_selected_option","Name='Branch_ID'");
           while(result.next())
           {
               Branch_ID=result.getInt("User_Option_ID");
           }           
           result=DB.Select("*", "user_option", "User_Option_ID="+Branch_ID);
           while(result.next())
           {
               EmployeesSalaries.add(result.getInt("User_Type_ID"));
           }
           System_manage S=System_manage.Get_System_manage();
           for(int i=0;i<EmployeesSalaries.size();i++)
           {
               result=DB.Select("*", "user_selected_option_values", " User_ID="+EmployeesSalaries.get(i)+" User_Option_ID="+ID_SALARY);
               while(result.next())
               {
                   Total_Salary +=Integer.getInteger(result.getString("Value"));
               }
           }
       }
       catch(Exception E)
       {
           System.out.println("Error in Show Salaries");
       }
       DB.Close();
       return Total_Salary;
   }
   //Emad T
    public double Get_quality() {
        DB_controller.Get_DB_controller().Connect();
        double NumberOfRows = 0;
        double Summtion = 0;
        double Persatage = 0;
        try {
            ResultSet result = DB_controller.Get_DB_controller().Select("*", "Feedback", "Branch_ID=" + Id);
            while (result.next()) {
                Summtion = Summtion+result.getInt("System_quality");
                NumberOfRows++;
            }
        } catch (Exception e) {
            System.out.println("Error in GET Quality");
        }
        Persatage = (Summtion / (NumberOfRows*100))*100;
        DB_controller.Get_DB_controller().Close();
        return Persatage;
    }
    //Emad T
   public double Service_guality()
   {
        DB_controller.Get_DB_controller().Connect();
        int NumberOfRows = 0;
        int Summtion = 0;
        double Persatage = 0;
        DB_controller DB=DB_controller.Get_DB_controller();
        DB.Connect();
        ResultSet result=DB.Select("System_quality", "feedback","1");
        try
        {
            while(result.next())
            {
                Summtion = Summtion+result.getInt("Service_quality");
                NumberOfRows++;                
            }
        }
        catch(Exception E)
        {
            System.out.println("Error in Service Quality");
        }
        Persatage = (Summtion / (NumberOfRows*100))*100;
        DB.Close();
        return Persatage;
   }
   
   //Sala7
   public ArrayList <Bill_inf> Show_accounting()
   {   
       ArrayList <Bill_inf> b = null;
       Bill bill = new Bill();
       Bill_inf bi_inf = new Bill_inf();
       Payment_Method pay = new Payment_Method();
       Spare_parts s = new Spare_parts();
       DB_controller DB = DB_controller.Get_DB_controller();
       DB.Connect();
       ResultSet result = null;
       
       result = DB.Select(" * ", " bill ", " 1 ");
    
       try {
            while (result.next())
            {
             bill.setId(result.getInt("BILL_id"));
             bill.setCost(result.getDouble("Cost"));
             bill.setDate_id(result.getInt("Date_id"));
             bill.setMy_order(result.getInt("Order_id"));
             bill.setTime(result.getString("Time"));
             pay.Get_payment_type_in_bill(result.getInt("BILL_id"));
             bi_inf.Get_spare_parts(result.getInt("BILL_id"));
             bi_inf.setMy_bill(bill);
             bi_inf.setPayment_Method_id(pay);
           
             b.add(bi_inf);
             
            }
            return b;
        } catch (SQLException ex) {
                ex.printStackTrace();
                DB.Close();
                return null;
        }

   }
   //Emad T
   public ArrayList <Employee> Show_employee()
   {
       System_manage S=System_manage.Get_System_manage();
       Service_Management Ser=Service_Management.Get_Serive_Management();
       ArrayList <Employee> E=new ArrayList<Employee>();
    DB_controller DB=DB_controller.Get_DB_controller();
    DB.Connect();
    int Gender_ID;
    ResultSet result=DB.Select("*","user","Type_ID=1 Or Type_ID=2 Or Type_ID=3 Or Type_ID=4");
    int USer_ID=-1;
        try 
        {
            while(result.next())
            {
                Employee Em=new Employee();
                USer_ID=result.getInt("User_ID");
                Em.setID(USer_ID);
                Em.setAdditional_data(S.Get_Option_Values_OF_USER(USer_ID));
                Gender_ID=result.getInt("Gender");
                Em.setF_name(result.getString("Fname"));
                Em.SetMyBranchID(getId());
                Em.setL_name(result.getString("Lname"));
                Em.setEmail(result.getString("Email"));
                Em.setPassword(result.getString("Password"));
                Em.setType_id(result.getInt("Type_ID"));
                Em.setBlock(result.getInt("Block"));
                Em.setPhones(Ser.Get_User_Phone(USer_ID));
                Em.setAddresses(Ser.Get_User_Address(USer_ID));
                if(Gender_ID==1)
                {
                    Em.setGander("Male");
                }
                else
                {
                    Em.setGander("Female");
                }
                E.add(Em);
            }
        }
        catch(Exception Er)
        {
            System.out.println("Error in Show Employee");
        }
        return null;
   }
   

   public void SetNotfy_message(String notfy) {
        this.notfy_message = notfy;
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
