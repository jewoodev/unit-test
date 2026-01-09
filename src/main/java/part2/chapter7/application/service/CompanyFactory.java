package part2.chapter7.application.service;

import part2.chapter7.domain.Company;

public class CompanyFactory {
    public static Company create(Object[] data) {
        return new Company((String) data[0], (int) data[1]);
    }
}
