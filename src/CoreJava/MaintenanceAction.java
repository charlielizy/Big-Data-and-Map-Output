package CoreJava;

import java.util.List;

public class MaintenanceAction {
	private String actionId;
	private String actionName;
	private MaintenanceType maintenanceType;
	private long meanTime;	
	public List<MaintenanceActionTask> tasks;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public long getMeanTime() {		
		return meanTime;
	}

	public void setMeanTime(long meanTime) {
		this.meanTime = meanTime;
	}

	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public List<MaintenanceActionTask> getTasks() {
		return tasks;
	}

	public void setTask(List<MaintenanceActionTask> tasks) {
		this.tasks = tasks;
	}
	
}
