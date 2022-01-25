package es.jmmluna.tim.domain.model.work;

public enum WorkStatus {
	INITIATED, FINALIZED, ALL;

	private static Integer INITIATED_CODE = 0;
	private static Integer FINALIZED_CODE = 1;
	
	public static WorkStatus of(int statusCode) {
		return statusCode == INITIATED_CODE ? INITIATED : FINALIZED;
	}
	
	public static Integer getCode(WorkStatus workStatus) {
		return workStatus == INITIATED?INITIATED_CODE:FINALIZED_CODE;
	}
}
