package edu.cuny.brooklyn.web.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public class GradePoint {
    @Id
    private String grade;

    private double points;

    public GradePoint(String grade, double points) {
        this.grade = grade;
        this.points = points;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public double getPoints() {
        return points;
    }
    
    @Override
    public String toString() {
        return String.format("GradePoint[grade=%s, points=%5.3f]", grade, points);
    }
    
}
