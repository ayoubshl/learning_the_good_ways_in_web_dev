import management.*;
import ihm.*;
public class Main {
    public static void main(String[] args) {
        // Initialize managers
        EmployeManager employeManager = new EmployeManager();
        SalaryManager salaryManager = new SalaryManager();

        // Initialize consoleIHM with managers
        consoleIHM console = new consoleIHM(employeManager, salaryManager);

        // Start the console interface
        console.start();
    }
}
