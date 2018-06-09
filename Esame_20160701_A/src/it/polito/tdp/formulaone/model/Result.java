package it.polito.tdp.formulaone.model;

public class Result {
	
	private int resultId ;
	private Driver driver ;
	private int position ;
	
	public Result(int resultId, Driver driver, int position) {
		super();
		this.resultId = resultId;
		this.driver = driver;
		this.position = position;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + resultId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (resultId != other.resultId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [resultId=");
		builder.append(resultId);
		builder.append(", driver=");
		builder.append(driver);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}
	
	
}
