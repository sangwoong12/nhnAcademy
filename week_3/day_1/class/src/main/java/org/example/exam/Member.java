package org.example.exam;


public class Member  implements Comparable<Member>{ //implements Comparable<User> {
    private final int userNo;
    private final String userName;
    private final int userAge;

    public Member(int userNo, String userName, int userAge) {
        this.userNo = userNo;
        this.userName = userName;
        this.userAge = userAge;
    }

    @Override
    public int compareTo(Member o) {
        if(this.getClass().equals(Professor.class) && o.getClass().equals(Student.class)){
            return -1;
        } else if(this.getClass().equals(Professor.class) && o.getClass().equals(Professor.class)){
            return Integer.compare(o.userAge, this.userAge);
        } else if(this.getClass().equals(Student.class) && o.getClass().equals(Student.class)) {
            return Integer.compare(o.userAge, this.userAge);
        } else {
            return 0;
        }
    }
    //
    // To-do: override compareTo method here
    //

    //
    // To-do: override toString method here
    //

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}