/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE;

/**
 *
 * @author moroclash
 */
public class Feedback {
    private int Id;
    private Order My_order;
    private int System_quality;
    private int Service_quality;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setMy_order(Order My_order) {
        this.My_order = My_order;
    }

    public void setService_quality(int Service_quality) {
        this.Service_quality = Service_quality;
    }

    public void setSystem_quality(int System_quality) {
        this.System_quality = System_quality;
    }

    public int getId() {
        return Id;
    }

    public Order getMy_order() {
        return My_order;
    }

    public int getService_quality() {
        return Service_quality;
    }

    public int getSystem_quality() {
        return System_quality;
    }
    
}
