package edu.cuny.brooklyn.web;

public class Student {
    private double gpa;
    private String sid;

    public Student(String sid, double gpa) {
        this.sid = sid;
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public String getSid() {
        return sid;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public void setSid(String sid) {
        this.sid = sid;
    }
}
