package polymorphismtest;
public class Person {
    public static enum Gender {
        MALE,FEMALE,UNKNOWN;
    }
    String name;
    Gender gender;
    
    public Person() {
        this("");
    }
    
    public Person(String name) {
        this(name,Gender.UNKNOWN);
    }
    
    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
    
    public boolean equals(Person other) {
        return name == other.name && gender == other.gender;
    }
}
