package com.example.company;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button employeesBtn;

    @FXML
    private Button departmentBtn;

    @FXML
    private TextField addressField;

    @FXML
    private TableColumn<Employee, String> address_col;

    @FXML
    private TextField bdateField;

    @FXML
    private TableColumn<Employee, String> bdate_col;

    @FXML
    private Pane department;

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TextField dnameField;

    @FXML
    private TableColumn<Department, String> dname_col;

    @FXML
    private TextField dnumField;

    @FXML
    private TableColumn<Employee, Integer> dnum_col;

    @FXML
    private TableColumn<Department, Integer> dnumberDep_col;

    @FXML
    private TextField dnumberField;

    @FXML
    private Pane employees;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TextField fnameField;

    @FXML
    private TableColumn<Employee, String> fname_col;

    @FXML
    private TextField lnameFiled;

    @FXML
    private TableColumn<Employee, String> lname_col;

    @FXML
    private TableColumn<Department, String> mgrStart_col;

    @FXML
    private TableColumn<Department, Integer> mgr_ssn_col1;

    @FXML
    private TextField mgrssnField;

    @FXML
    private TextField mgrstartField;

    @FXML
    private TextField salaryField;

    @FXML
    private TableColumn<Employee, Double> salary_col;

    @FXML
    private TextField sexField;

    @FXML
    private TableColumn<Employee, String> sex_col;

    @FXML
    private TextField ssnField;

    @FXML
    private TableColumn<Employee, Integer> ssn_col;

    @FXML
    private TextField superssnFiled;

    @FXML
    private TableColumn<Employee, Integer> superssn_col;

    @FXML
    void initialize() {
        departmentBtn.setOnAction(event -> {
            department.toFront();
        });
        employeesBtn.setOnAction(event -> {
            employees.toFront();
        });

        employeeTable();
        departmentTable();
    }

    private void employeeTable(){
        ObservableList<Employee> oblist = Employee.dataForTable();
        fname_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("fname"));
        lname_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("lname"));
        ssn_col.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        bdate_col.setCellValueFactory(new PropertyValueFactory<Employee,String>("bdate"));
        address_col.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        sex_col.setCellValueFactory(new PropertyValueFactory<Employee, String>("sex"));
        salary_col.setCellValueFactory(new PropertyValueFactory<Employee,Double>("salary"));
        superssn_col.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("super_ssn"));
        dnum_col.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("dnumber"));

        employeesTable.setItems(oblist);
    }

    @FXML
    int getSelected(){
        int index = -1;

        index = employeesTable.getSelectionModel().getSelectedIndex();

        String fname = fname_col.getCellData(index);
        String lname = lname_col.getCellData(index);
        int ssn = ssn_col.getCellData(index);
        String bdate = bdate_col.getCellData(index);
        String address = address_col.getCellData(index);
        String sex = sex_col.getCellData(index);
        double salary = salary_col.getCellData(index);
        int super_ssn = superssn_col.getCellData(index);
        int dnumber = dnum_col.getCellData(index);


        fnameField.setText(fname);
        lnameFiled.setText(lname);
        ssnField.setText(String.valueOf(ssn));
        bdateField.setText(bdate);
        addressField.setText(address);
        sexField.setText(sex);
        salaryField.setText(String.valueOf(salary));
        superssnFiled.setText(String.valueOf(super_ssn));
        dnumberField.setText(String.valueOf(dnumber));

        return index;
    }

    @FXML
    public void update(){
        String fname = fnameField.getText();
        String lname = lnameFiled.getText();
        int ssn = Integer.parseInt(ssnField.getText());
        String bdate = bdateField.getText();
        String address = addressField.getText();
        String sex = sexField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        int super_ssn = Integer.parseInt(superssnFiled.getText());
        int dnumber = Integer.parseInt(dnumberField.getText());

        Employee.updateEmployee(fname,lname,ssn,bdate,address,sex,salary,super_ssn,dnumber);
        System.out.println("updated");
        employeeTable();
        cleanEmployees();
    }

    @FXML
    public void delete(){
        int index = getSelected();

        int ssn = ssn_col.getCellData(index);

        Employee.deleteEmployee(ssn);
        System.out.println("deleted");
        employeeTable();
        cleanEmployees();
    }

    @FXML
    public void add(){
        String fname = fnameField.getText();
        String lname = lnameFiled.getText();
        int ssn = Integer.parseInt(ssnField.getText());
        String bdate = bdateField.getText();
        String address = addressField.getText();
        String sex = sexField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        int super_ssn = Integer.parseInt(superssnFiled.getText());
        int dnumber = Integer.parseInt(dnumberField.getText());

        Employee.addEmployee(fname,lname,ssn,bdate,address,sex,salary,super_ssn,dnumber);
        System.out.println("added");
        employeeTable();
        cleanEmployees();


    }

    private void departmentTable(){
        ObservableList<Department> oblist = Department.dataForTable();
        dname_col.setCellValueFactory(new PropertyValueFactory<>("dname"));
        dnumberDep_col.setCellValueFactory(new PropertyValueFactory<>("dnumber"));
        mgr_ssn_col1.setCellValueFactory(new PropertyValueFactory<>("mgr_ssn"));
        mgrStart_col.setCellValueFactory(new PropertyValueFactory<>("mgr_start"));

        departmentTable.setItems(oblist);
    }

    @FXML
    int getSelectedDep(){
        int index = -1;

        index = departmentTable.getSelectionModel().getSelectedIndex();

        String dname = dname_col.getCellData(index);
        int dnumber = dnumberDep_col.getCellData(index);
        int mgr_ssn = mgr_ssn_col1.getCellData(index);
        String mgr_start = mgrStart_col.getCellData(index);


        dnameField.setText(dname);
        mgrssnField.setText(String.valueOf(mgr_ssn));
        mgrstartField.setText(mgr_start);
        dnumField.setText(String.valueOf(dnumber));

        return index;
    }

    @FXML
    private void addDepartment(){
        String dname = dnameField.getText();
        int mgr_ssn = Integer.parseInt(mgrssnField.getText());
        String mgr_start = mgrstartField.getText();
        int dnum = Integer.parseInt(dnumField.getText());

        Department.addDepartment(dname, dnum, mgr_ssn, mgr_start);
        System.out.println("added");
        departmentTable();
        cleanDepartment();
    }

    @FXML
    private void updateDepartment(){
        String dname = dnameField.getText();
        int mgr_ssn = Integer.parseInt(mgrssnField.getText());
        String mgr_start = mgrstartField.getText();
        int dnum = Integer.parseInt(dnumField.getText());

        Department.updateDepartment(dname, dnum, mgr_ssn, mgr_start);
        System.out.println("updated");
        departmentTable();
        cleanDepartment();
    }

    @FXML
    private void deleteDepartment(){
        String dname = dnameField.getText();
        int mgr_ssn = Integer.parseInt(mgrssnField.getText());
        String mgr_start = mgrssnField.getText();
        int dnum = Integer.parseInt(dnumField.getText());

        Department.deleteDepartment(dnum);
        System.out.println("deleted");
        departmentTable();
        cleanDepartment();
    }


    @FXML
    private void cleanEmployees(){
        fnameField.setText(null);
        lnameFiled.setText(null);
        ssnField.setText(null);
        bdateField.setText(null);
        addressField.setText(null);
        sexField.setText(null);
        salaryField.setText(null);
        superssnFiled.setText(null);
        dnumberField.setText(null);
    }

    @FXML
    private void cleanDepartment(){
        dnameField.setText(null);
        mgrssnField.setText(null);
        mgrstartField.setText(null);
        dnumField.setText(null);
    }

}
