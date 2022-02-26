package com.example.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Employee {
    private String fname;
    private String lname;
    private int ssn;
    private String bdate;
    private String address;
    private String sex;
    private double salary;
    private int super_ssn;
    private int dnumber;

    public Employee(String fname, String lname, int ssn, String bdate, String address, String sex, double salary, int super_ssn, int dnumber) {
        this.fname = fname;
        this.lname = lname;
        this.ssn = ssn;
        this.bdate = bdate;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.super_ssn = super_ssn;
        this.dnumber = dnumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSuper_ssn() {
        return super_ssn;
    }

    public void setSuper_ssn(int super_ssn) {
        this.super_ssn = super_ssn;
    }

    public int getDnumber() {
        return dnumber;
    }

    public void setDnumber(int dnumber) {
        this.dnumber = dnumber;
    }


    public static ObservableList<Employee> dataForTable(){
        ObservableList<Employee> oblist = FXCollections.observableArrayList();
        JDBC jdbc = new JDBC();
        try {
            Connection conn = jdbc.ConnectDB();
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                oblist.add(new Employee(rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getInt("ssn"),
                        rs.getString("bdate"),
                        rs.getString("address"),
                        rs.getString("sex"),
                        rs.getDouble("salary"),
                        rs.getInt("super_ssn"),
                        rs.getInt("dnumber")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return oblist;
    }


    public static void addEmployee(String fname, String lname, int ssn, String bdate, String address, String sex, double salary, int super_ssn, int dnumber){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "INSERT INTO `employee`(`Fname`, `Lname`, `Ssn`, `Bdate`, `Address`, `Sex`, `Salary`, `Super_ssn`, `Dnumber`) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setInt(3,ssn);
            ps.setString(4,bdate);
            ps.setString(5,address);
            ps.setString(6,sex);
            ps.setDouble(7,salary);
            ps.setInt(8,super_ssn);
            ps.setInt(9,dnumber);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateEmployee(String fname, String lname, int ssn, String bdate, String address, String sex, double salary, int super_ssn, int dnumber){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "UPDATE `employee` SET `Fname`= ?,`Lname`= ?,`Ssn`= ?,`Bdate`= ?," +
                    "`Address`= ?,`Sex`= ?,`Salary`= ?,`Super_ssn`= ?,`Dnumber`= ? WHERE Ssn = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setInt(3,ssn);
            ps.setString(4,bdate);
            ps.setString(5,address);
            ps.setString(6,sex);
            ps.setDouble(7,salary);
            ps.setInt(8,super_ssn);
            ps.setInt(9,dnumber);
            ps.setInt(10,ssn);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int ssn){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "DELETE FROM `employee` WHERE `Ssn` = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,ssn);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
