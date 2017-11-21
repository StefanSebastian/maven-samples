package common;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class Person {
    private String name1;
    private String name2;
    private String name3;
    private String cnp;
    private String email;

    public Person(String name1, String name2, String name3, String cnp, String email) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.cnp = cnp;
        this.email = email;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name1 + "~" + name2 + "~" + name3 + "~" + cnp + "~" + email + "%";
    }
}
