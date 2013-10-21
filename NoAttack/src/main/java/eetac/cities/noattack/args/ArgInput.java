package eetac.cities.noattack.args;

public enum ArgInput {
	INPUT_TYPE("input-type"),
	INPUT("input"),
	DATA_FOLDER("data-folder"),
	CONF_FOLDER("conf-folder")
	;
	
	String key;
	private ArgInput(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}
