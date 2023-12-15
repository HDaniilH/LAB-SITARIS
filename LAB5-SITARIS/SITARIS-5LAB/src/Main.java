import abstractFactory.BankingTeamFactory;
import abstractFactory.Developer;
import abstractFactory.ProjectTeamFactory;
import abstractFactory.*;
import FactoryMethod.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выполняется код Абстрактной фабрики!");
        ProjectTeamFactory projectTeamFactory = new BankingTeamFactory();
        Developer developer = projectTeamFactory.getDeveloper();
        Tester tester = projectTeamFactory.getTester();
        ProjectManager projectManager = projectTeamFactory.getProjectManager();
        System.out.println("Создание приложения для банка...");
        developer.writeCode();
        tester.testCode();
        projectManager.manageProject();
        System.out.println("Выполняется код Фабричного метода!");
        DeveloperFactory developerFactory = new JavaDeveloperFactory();
        FactoryMethod.Developer developer1 = developerFactory.createDeveloper();
        developer1.writeCode();
    }
}