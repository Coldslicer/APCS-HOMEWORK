package polymorphismtest;
public class Driver {
    public static void main(String[] args) {
        Person p = new Person("Sharvil",Person.Gender.MALE);
        Child c = Child.construct("Sharvil",Person.Gender.MALE);
        System.out.println(p.equals(c));
    }
}
