package edu.ucuccs.LabUandroid;

public class SchedulesTask {
	private int id;
	private String labNameSched;
	private String code;
	private String description;
	private String subject;
	private String day;
	private String from;
	private String to;
	private String instructor;

	public SchedulesTask(int id) {
		this.id = id;
	}

	public SchedulesTask(int id, String labNameSched, String code,
			String description, String subject, String day, String from,
			String to, String instructor) {
		this.id = id;
		this.labNameSched = labNameSched;
		this.code = code;
		this.description = description;
		this.subject = subject;
		this.day = day;
		this.from = from;
		this.to = to;
		this.instructor = instructor;
	}

	public SchedulesTask(String labNameSched, String code, String description,
			String subject, String day, String from, String to,
			String instructor) {

		this.labNameSched = labNameSched;
		this.code = code;
		this.description = description;
		this.subject = subject;
		this.day = day;
		this.from = from;
		this.to = to;
		this.instructor = instructor;
	}

	public SchedulesTask() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getlabNameSched() {
		return labNameSched;
	}

	public void setlabNameSched(String labNameSched) {
		this.labNameSched = labNameSched;
	}

	public String getcode() {
		return code;
	}

	public void setcode(String code) {
		this.code = code;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public String getsubject() {
		return subject;
	}

	public void setsubject(String subject) {
		this.subject = subject;
	}

	public String getday() {
		return day;
	}

	public void setday(String day) {
		this.day = day;
	}

	public String getfrom() {
		return from;
	}

	public void setfrom(String from) {
		this.from = from;
	}

	public String getto() {
		return to;
	}

	public void setto(String to) {
		this.to = to;
	}

	public String getinstructor() {
		return instructor;
	}

	public void setinstructor(String instructor) {
		this.instructor = instructor;
	}

}
