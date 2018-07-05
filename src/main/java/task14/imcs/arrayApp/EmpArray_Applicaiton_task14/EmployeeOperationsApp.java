package task14.imcs.arrayApp.EmpArray_Applicaiton_task14;

import java.util.Scanner;

import task14.imcs.array.EmpArray_Library.Employee;
import task14.imcs.array.EmpArray_Library.EmployeeNotFoundException;
import task14.imcs.array.EmpArray_Library.EmployeeServices;
import task14.imcs.array.EmpArray_Library.EmployeeServicesImpl;
import task14.imcs.array.EmpArray_Library.EmployeeUtil;
import task14.imcs.array.EmpArray_Library.SalaryException;

public class EmployeeOperationsApp {
	public static Scanner scan = new Scanner(System.in);
	public static Employee empTemp = new Employee();
	public static EmployeeServices empServ = new EmployeeServicesImpl();

	public static void main(String[] args) {
		int option;
		EmployeeUtil.getDepartment();
		System.out.println("----------------Employee System-------------------");
		empServ.readFromFile();

		do {
			System.out.println("\n\n|||Menu|||");
			System.out.println(
					"1.Create Employee\n2.Read(View) Employee.\n3.View all Employees\n4.Update Employee\n5.Delete Employee\n6.Calculate HRA\n7.Calculate Gross Salary\n8.Exit");
			System.out.println("Select your choice: ");
			option = scan.nextInt();

			switch (option) {
			case 1:
				if (empServ.addEmpChck()) {
					System.out.println("Enter Employee Id: ");
					int id = scan.nextInt();
					try {
						empTemp = EmployeeUtil.createEmpObj(id);
						empServ.addEmployee(empTemp);
						System.out.println("Employee created");
					} catch (SalaryException e) {
						e.printStackTrace();
					}
					
				} else {
					System.out.println("Array is full. No other creations allowed.");
				}
				break;
			case 2:
				System.out.println("Enter Employee ID you want to view: ");
				int view = scan.nextInt();
				try {
					if (empServ.isExists(view)) {
						System.out.println("The Employee Details are as follows: ");
						System.out.println(empServ.viewEmployee(view));
					} else
						System.out.println("Employee doesn't exists with the given ID.");
				} catch (EmployeeNotFoundException e1) {
					e1.printStackTrace();
				}
				break;
			case 3:
				if (empServ.viewAllEmployees() != null) {
					System.out.println("Employee info: \n" + empServ.viewAllEmployees());
				} else {
					System.out.println("No elements in the arrray");
				}
				break;
			case 4:
				System.out.println("Enter Employee Id you want to Update: ");
				int idUpd = scan.nextInt();
				try {
					if (empServ.isExists(idUpd)) {
						try {
							empTemp = EmployeeUtil.createEmpObj(idUpd);
						} catch (SalaryException e) {
							System.out.println("Salary Excpetion occurred");
							e.printStackTrace();
						}
						empServ.updateEmployee(idUpd, empTemp);
						System.out.println("Updated the Employee details");
					} else
						System.out.println("No Employee exists with the given id.");
				} catch (EmployeeNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Enter ID of Employee you want to delete: ");
				int delId = scan.nextInt();

				try {
					if (empServ.isExists(delId)) {
						empServ.deleteEmployee(delId);
						System.out.println("Employee deleted.");
					} else
						System.out.println("Given ID doesn't exists.");
				} catch (EmployeeNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Enter ID of Employee to calculate HRA: ");
				int hraId = scan.nextInt();
				try {
					if (empServ.isExists(hraId)) {
						System.out.println("HRA is: " + empServ.getHRASrvc(hraId));
					} else
						System.out.println("Given ID doesn't exists. Please try again.");
				} catch (EmployeeNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Enter ID of Employee to calculate Gross Salary: ");
				int grossId = scan.nextInt();
				try {
					if (empServ.isExists(grossId)) {
						System.out.println("Gross Salary is: " + empServ.getGrossSrvc(grossId));
					} else
						System.out.println("Given ID doesn't exists. Please try again.");
				} catch (EmployeeNotFoundException e) {
					e.printStackTrace();
				}
				break;
				default:
					if(option != 8)
					System.out.println("Invalid value. Please try again");
					break;
			}
		} while (option != 8);
		
		if(empServ.writeToFile())
		System.out.println("Updates written to file");
	}

	
}
