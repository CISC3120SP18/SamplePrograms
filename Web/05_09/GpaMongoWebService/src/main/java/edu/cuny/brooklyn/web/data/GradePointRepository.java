package edu.cuny.brooklyn.web.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.cuny.brooklyn.web.service.GradePoint;

public interface GradePointRepository extends CrudRepository<GradePoint, String> {
    public double findByGrade(String grade);
    public List<GradePoint> findAll();
}
