package MyEnterprise;

public abstract class NamedObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;
}
