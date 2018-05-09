package edu.cuny.brooklyn.web.MongoDB;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import edu.cuny.brooklyn.web.service.GradePoint;

public class GradePointInitializer  {
    private final static Logger LOGGER = LoggerFactory.getLogger(GradePointInitializer.class);

    public static void main(String[] args) {
        String[] grades = {
                "A+",
                "A",
                "A-",
                "B+",
                "B",
                "B-",
                "C+",
                "C",
                "C-",
                "D+",
                "D",
                "D-",
                "F",
                "FIN",
                "WF",
                "WU"
                };
        double[] gradePoints = {
                4.00,
                4.00,
                3.70,
                3.30,
                3.00,
                2.70,
                2.30,
                2.00,
                1.70,
                1.30,
                1.00,
                0.70,
                0.00,
                0.00,
                0.00,
                0.00
        };
        MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "GpaAppDb");
        mongoOps.dropCollection(GradePoint.class);
        
        // based on
        // http://www.brooklyn.cuny.edu/web/off_registrar/2017-2018_Undergraduate_Bulletin.pdf#page=58
        // page 58
        for (int i=0; i<grades.length; i++) {
            LOGGER.info("Insert Grade Point: " + grades[i] + "," + gradePoints[i]);
            mongoOps.insert(new GradePoint(grades[i], gradePoints[i]));
        }

        List<GradePoint> gradePointList =  mongoOps.findAll(GradePoint.class);
        gradePointList.forEach((gradePoint) -> {
            LOGGER.info(gradePoint.toString());
        });
    }
    
}
