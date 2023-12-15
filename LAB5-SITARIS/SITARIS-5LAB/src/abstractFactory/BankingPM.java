package abstractFactory;

public class BankingPM implements ProjectManager{
    @Override
    public void manageProject() {
        System.out.println("Проект-менеждер банка контролирует проекты, которые разрабатываюся для банка...");
    }
}
