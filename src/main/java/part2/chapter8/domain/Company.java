package part2.chapter8.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companyId;
    private String domainName;
    private int numberOfEmployees;

    public Company() { }

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

    // ↓ getter
    public Integer getCompanyId() {
        return companyId;
    }

    public String getDomainName() {
        return domainName;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
}
