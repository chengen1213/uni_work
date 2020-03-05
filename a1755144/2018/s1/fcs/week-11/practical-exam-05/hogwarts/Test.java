public class Test{

    public static void main(String [] args){
        Queue howgarts = new Queue();

        howgarts.enqueue(new Student("Cedric Diggory", 14, 1));
        howgarts.enqueue(new Student("Harry Potter", 14, 1));
        howgarts.enqueue(new Student("Hermione Granger", 12,1));
        howgarts.enqueue(new Student("Ron Weasley", 15, 1));
        howgarts.enqueue(new Student("Fred Weasley", 16, 3));
        howgarts.enqueue(new Student("George Weasley", 16, 3));

        Student student1 = howgarts.dequeue();
        System.out.println(student1.getName()+" left the queue!");
        Student student2 = howgarts.dequeue();
        System.out.println(student2.getName()+" left the queue!");

        howgarts.enqueue(new Student("Draco Malfoy", 15, 2));

        howgarts.displayQueue();

        while (!howgarts.isEmpty()) {
            howgarts.dequeue();
        }
        howgarts.dequeue();
    }
}