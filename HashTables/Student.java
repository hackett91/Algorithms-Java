public class Student {

    private String firstName;
    private int studentNumber;

    public Student(String firstName, int studentNumber) {
      this.firstName = firstName;
      this.studentNumber = studentNumber;
    }

    public String getFirstName(){ return firstName;}

    public void setFirstName(String firstName){this.firstName = firstName;}

    public int getStudentNumber(){return studentNumber;}

    public void setStudentNumber(int studentNumber){ this.studentNumber = studentNumber;}


}
