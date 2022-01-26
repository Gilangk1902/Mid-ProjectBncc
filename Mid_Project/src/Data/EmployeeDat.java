package Data;

public class EmployeeDat {
    private String name, code, gender, position;
    private double wage;
    
    public EmployeeDat(String name, String code, String gender, String position, double wage) {
        this.name = name;
        this.code = code;
        this.gender = gender;
        this.position = position;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    
}
