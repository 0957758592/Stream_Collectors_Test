package main.java.com.limestone.test;

public class Mark {
    private Student student;
    private Subject subject;
    private int rank;

    public Mark() {
    }

    public Mark(Student student, Subject subject, int rank) {
        this.student = student;
        this.subject = subject;
        this.rank = rank;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
