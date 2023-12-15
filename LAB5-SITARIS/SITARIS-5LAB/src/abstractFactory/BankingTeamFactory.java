package abstractFactory;

public class BankingTeamFactory implements ProjectTeamFactory {

    public Developer getDeveloper(){
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }


}
