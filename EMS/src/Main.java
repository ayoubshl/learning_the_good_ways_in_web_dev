import management.*;
import ihm.*;
import repository.*;
import repositoryHandler.arrayListEmployeHandler;
import repositoryHandler.arrayListSalaryHandler;

public class Main {
    public static void main(String[] args) {
        // Initialize managers
        salaryRepository salaryRepository = new arrayListSalaryHandler();
        employeeRepository employeeRepository = new arrayListEmployeHandler() ;
        EmployeManager employeManager = new EmployeManager(employeeRepository);
        SalaryManager salaryManager = new SalaryManager(salaryRepository);

        // Initialize consoleIHM with managers
        consoleIHM console = new consoleIHM(employeManager, salaryManager);

        // Start the console interface
        console.start();
    }
}
