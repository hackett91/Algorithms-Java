public class Main{


    public static void main(String[] args) {

        Student janeJones = new Student("Jane",122);
        Student johnDoe = new Student("John",4567);
        Student marySmith = new Student("Mary",22);
        Student mikeWilson = new Student("Mike",3245);
        Student billEnd = new Student("Bill",78);

        SimpleHashtable ht = new SimpleHashtable();

        ht.put(123, janeJones);
        ht.put(4567, johnDoe);
        ht.put(3245, mikeWilson);
        ht.put(23, marySmith);

      //  ht.printHashTable();

        System.out.println("Retrive key Wilson: " + ht.get(3245));

    }
}
