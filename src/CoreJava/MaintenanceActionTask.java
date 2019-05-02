package CoreJava;

public class MaintenanceActionTask {
	private String actionId;
	private String taskId;
	private String taskName;
	private String personnelType;
	private long duration;
	private float hourlyRate;
	private int personnelNumber;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public float getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public int getPersonnelNumber() {
		return personnelNumber;
	}

	public void setPersonelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public void setPersonnelNumber(int personnelNumber) {
		this.personnelNumber = personnelNumber;
	}

}
