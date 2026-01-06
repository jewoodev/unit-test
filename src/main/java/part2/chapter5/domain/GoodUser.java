package part2.chapter5.domain;

public class GoodUser {
    private String name;

    public GoodUser(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void modifyName(String newName) {
        this.name = this.normalizeName(newName);
    }

    private String normalizeName(String name) {
        name = name.trim();

        if (name.length() > 50)
            return name.substring(0, 50);
        return name;
    }
}
