package models;

public class employee {
    private int employee_id;
    private String name;
    private String address;
    private String phone;
    private String email;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public employee(int employee_id,String employee_name,String employee_address,String employee_phone,String employee_email) {
        this.employee_id = employee_id;
        this.address = employee_address;
        this.phone = employee_phone;
        this.email = employee_email;
        this.name = employee_name;
    }
}

