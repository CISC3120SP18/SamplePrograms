package edu.cuny.brooklyn.design.grade;

import java.util.HashMap;
import java.util.Map;

public class GradeNumberService {
	// page 59 in
	// http://www.brooklyn.cuny.edu/web/off_registrar/2017-2018_Undergraduate_Bulletin.pdf
	public enum LetterGrade {
		APLUS, A, AMINUS, B, BPLUS, BMINUS, CPLUS, C, CMINUS, DPLUS, D, DMINUS, F, AUD, P, CR, FIN, INC, NC, PEN, S, U, W, WA, WD, WF, WN, WU
	};

	// page 61 in
	// http://www.brooklyn.cuny.edu/web/off_registrar/2017-2018_Undergraduate_Bulletin.pdf
	public static double getGradePointNumber(LetterGrade letterGrade) {
		if (!gradeNumberMap.containsKey(letterGrade)) {
			throw new RuntimeException("Letter grade " + letterGrade + " is exclued from GPA calcualtion.");
		}
		return gradeNumberMap.get(letterGrade);
	}
	
	public static boolean hasGradeNumber(LetterGrade letterGrade) {
		return gradeNumberMap.containsKey(letterGrade);
	}

	public static LetterGrade letterGradeValueOf(String grade) {
		switch (grade) {
		case "A+":
			return LetterGrade.APLUS;
		case "A-":
			return LetterGrade.AMINUS;
		case "B+":
			return LetterGrade.BPLUS;
		case "B-":
			return LetterGrade.BMINUS;
		case "C+":
			return LetterGrade.CPLUS;
		case "C-":
			return LetterGrade.CMINUS;
		case "D+":
			return LetterGrade.DPLUS;
		case "D-":
			return LetterGrade.DMINUS;
		default:
			return LetterGrade.valueOf(grade);
		}
	}

	private static Map<LetterGrade, Double> gradeNumberMap = buildGradeNumberMap();

	private static Map<LetterGrade, Double> buildGradeNumberMap() {
		Map<LetterGrade, Double> map = new HashMap<LetterGrade, Double>();

		map.put(LetterGrade.APLUS, 4.0);
		map.put(LetterGrade.A, 4.0);
		map.put(LetterGrade.AMINUS, 3.7);
		map.put(LetterGrade.BPLUS, 3.3);
		map.put(LetterGrade.B, 3.0);
		map.put(LetterGrade.BMINUS, 2.70);
		map.put(LetterGrade.CPLUS, 2.3);
		map.put(LetterGrade.C, 2.0);
		map.put(LetterGrade.CMINUS, 1.70);
		map.put(LetterGrade.DPLUS, 1.3);
		map.put(LetterGrade.D, 1.0);
		map.put(LetterGrade.DMINUS, 0.70);
		map.put(LetterGrade.F, 0.0);
		map.put(LetterGrade.FIN, 0.0);
		map.put(LetterGrade.WF, 0.0);
		map.put(LetterGrade.WU, 0.0);

		return map;
	}
}
