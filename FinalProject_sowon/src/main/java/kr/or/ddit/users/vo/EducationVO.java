package kr.or.ddit.users.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EducationVO implements Serializable{
	
	private String eduType;
	private String resumeNo;
	private String eduGraduate;
	private String eduNo;
	private String eduSchoolnm;
	private String eduMajornm;
	private String eduStdate;
	private String eduEndate;
	private Integer eduGrade;
	private Integer eduStandardGrade;
	private String eduContent;

}
