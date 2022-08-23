package demo.atomicIntegerFieldTest;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class test {
    public static void main(String[] args) {
        User user=new User(1,5);
        for (int i = 0; i < 5; i++) {
            new SubThread(user).start();

        }
        System.out.println(user);
    }
}
class SubThread extends Thread{
    private User user;
    private AtomicIntegerFieldUpdater<User> updater=AtomicIntegerFieldUpdater.newUpdater(User.class,"age");
    public SubThread(User user){
        this.user=user;
    }
    @Override
    public void run() {
        for (int i = 0; i <10; i++) {
            System.out.println(updater.getAndIncrement(user));
        }
    }
}
class User{
    int id;
    volatile int age;
    @Override
    public String toString() {
        return "test{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }
}