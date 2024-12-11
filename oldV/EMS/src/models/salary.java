package models;

public class salary {


    private int salary_id;
    private String date;
    private employee employee;
    private int hours;
    private float wage_per_hour;
    private float gross_salary;
    private float net_salary;
    private final float tax = 0.2F;




    public salary(int salary_id ,float wage_per_hour, int hours, employee e) {
        this.salary_id = salary_id;
        this.wage_per_hour = wage_per_hour;
        this.hours = hours;
        this.employee = e;
        this.gross_salary = wage_per_hour * hours;
        this.net_salary = wage_per_hour * hours - this.gross_salary * this.tax;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }
    public employee getE() {
        return employee;
    }

    public void setE(employee e) {
        this.employee = e;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public float getTax_per_hour() {
        return wage_per_hour;
    }

    public void setTax_per_hour(float tax_per_hour) {
        this.wage_per_hour = tax_per_hour;
    }

    public float getGross_salary() {
        return gross_salary;
    }

    public void setGross_salary(float gross_salary) {
        this.gross_salary = gross_salary;
    }

    public float getNet_salary() {
        return net_salary;
    }

    public void setNet_salary(float net_salary) {
        this.net_salary = net_salary;
    }

    public float getTax() {
        return tax;
    }



}
