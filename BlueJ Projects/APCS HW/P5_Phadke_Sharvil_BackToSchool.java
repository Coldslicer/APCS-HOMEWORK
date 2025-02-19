/*
 * Sharvil Phadke P5
 * Nov 24, 2024
 * Working: yes
 * HW question:
 *   Why can't you extend multiple classes? Give both the reason why you can't
 *   acording to how java was coded, as well as the reason why Sun Microsystems
 *   (creators of java) would have created it that way. Why can you
 *   implement multiple interfaces but not extend multiple classes?
 */
public class P5_Phadke_Sharvil_BackToSchool {
    public static void main(String[] args) {
        Person curse =  new Person("The curse of Demise",Integer.MAX_VALUE, "NB");  
        System.out.println(curse);

        Student me =  new  Student("Sharvil Phadke", 15, "M", "5231382", 4.0);  
        System.out.println(me);

        Teacher mrFerrante =  new  Teacher("Mr. Ferrante", 20, "M", "Computer Science", Integer.MAX_VALUE);  
        System.out.println(mrFerrante);

        CollegeStudent genius =  new  CollegeStudent("Gene Iaus", -1, "NB", "1",  
        Double.MAX_VALUE, Integer.MIN_VALUE, "All of them");  
        System.out.println(genius);
    }
}
class Person {
    private String myName ; // name of the person
    private int myAge; // person's age
    private String myGender; // "M" for male, "F" for female, "NB" for non-binary
    // constructor
    public Person(String name, int age, String gender){
        myName = name;
        myAge = age;
        myGender = gender;
    }
    public String getName(){
        return myName;
    }
    public int getAge(){
        return myAge;
    }
    public String getGender(){
        return myGender;
    }
    public void setName(String name){
        myName = name;
    }
    public void setAge(int age){
        myAge = age;
    }
    public void setGender(String gender){
        myGender = gender;
    }
    public String toString(){
        return myName + ", age: " + myAge + ", gender: " +
        myGender;
    }
}
class Student extends Person{
    private String myIdNum; // Student Id Number
    private double myGPA; // grade point average
    // constructor
    public Student(String name, int age, String gender,
        String idNum, double gpa){
        // use the super class' constructor
        super(name, age, gender);
        // initialize what's new to Student
        myIdNum = idNum;
        myGPA = gpa;
    }
    public String getIdNum(){
        return myIdNum;
    }
    public double getGPA(){
        return myGPA;
    }
    public void setIdNum(String idNum){
        myIdNum = idNum;
    }
    public void setGPA(double gpa){
        myGPA = gpa;
    }
    // overrides the toString method in the parent class
    public String toString(){
        return super.toString() + ", student id: " + myIdNum + ", gpa: " + myGPA;
    }
}
class Teacher extends Person {
    public String subject;
    public double salary;
    
    public Teacher(String myName, int myAge, String myGender, String subject, double salary) {
        super(myName,myAge,myGender);
        this.subject = subject;
        this.salary = salary;
    }
    
    public void setSubject(String subject) { this.subject = subject; }
    public String getSubject() { return subject; }
    public void setSalary(double salary) { this.salary = salary; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return String.format("%s, subject: %s, salary: %.1f",super.toString(),subject,salary);
    }
}
class CollegeStudent extends Student {
    private String major;
    private int year;
    
    public CollegeStudent(String myName, int myAge, String myGender, String myIdNum, double myGPA, int year, String major) {
        super(myName,myAge,myGender,myIdNum,myGPA);
        this.major = major;
        this.year = year;
    }
    
    public void setMajor(String major) { this.major = major; }
    public String getSubject() { return major; }
    public void setYear(int year) { this.year = year; }
    public double getYear() { return year; }
    
    @Override
    public String toString() {
        return String.format("%s, major: %s, year: %d",super.toString(),major,year);
    }
}