package part2.chapter7.domain;

public class Company {
    private String domainName;
    private int numberOfEmployees;

    public Company(String domainName, int numberOfEmployees) {
        this.domainName = domainName;
        this.numberOfEmployees = numberOfEmployees;
    }

    public void changeNumberOfEmployees(int delta) {
        if (this.numberOfEmployees + delta < 0)
            throw new IllegalArgumentException("Negative number of employees is not allowed.");

        this.numberOfEmployees += delta;
    }

    public boolean isEmailCorporate(String email) {
        String emailDomain = email.split("@")[1];
        return emailDomain.equals(this.domainName);
    }
}
