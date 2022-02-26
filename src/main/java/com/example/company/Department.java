package com.example.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Department {
    private String dname;
    private Integer dnumber;
    private int mgr_ssn;
    private String mgr_start;

    public Department(String dname, Integer dnumber, int mgr_ssn, String mgr_start) {
        this.dname = dname;
        this.dnumber = dnumber;
        this.mgr_ssn = mgr_ssn;
        this.mgr_start = mgr_start;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDnumber() {
        return dnumber;
    }

    public void setDnumber(Integer dnumber) {
        this.dnumber = dnumber;
    }

    public int getMgr_ssn() {
        return mgr_ssn;
    }

    public void setMgr_ssn(int mgr_ssn) {
        this.mgr_ssn = mgr_ssn;
    }

    public String getMgr_start() {
        return mgr_start;
    }

    public void setMgr_start(String mgr_start) {
        this.mgr_start = mgr_start;
    }

    public static ObservableList<Department> dataForTable(){
        ObservableList<Department> oblist = FXCollections.observableArrayList();
        JDBC jdbc = new JDBC();
        try {
            Connection conn = jdbc.ConnectDB();
            assert conn != null;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM department");
            while (rs.next()) {
                oblist.add(new Department(rs.getString("dname"),
                        rs.getInt("dnumber"),
                        rs.getInt("mgr_ssn"),
                        rs.getString("mgr_start_date")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return oblist;
    }

    public static void addDepartment(String dname, int dnumber, int mgr_ssn, String mgr_start){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "INSERT INTO department (dname, dnumber, mgr_ssn, mgr_start_date) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,dname);
            ps.setInt(2,dnumber);
            ps.setInt(3,mgr_ssn);
            ps.setString(4,mgr_start);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateDepartment(String dname, int dnumber, int mgr_ssn, String mgr_start){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "UPDATE department SET dname = ?, dnumber = ?, mgr_ssn = ?, mgr_start_date = ? WHERE dnumber = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,dname);
            ps.setInt(2,dnumber);
            ps.setInt(3,mgr_ssn);
            ps.setString(4,mgr_start);
            ps.setInt(5,dnumber);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteDepartment(int dnumber){
        JDBC jdbc = new JDBC();
        try{
            Connection connection = jdbc.ConnectDB();
            String query = "DELETE FROM department WHERE dnumber = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,dnumber);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
