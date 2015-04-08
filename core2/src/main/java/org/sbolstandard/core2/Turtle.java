package org.sbolstandard.core2;

public class Turtle {
	
	private String turtleStr;
	
	public Turtle(String turtleStr) {
		this.turtleStr = turtleStr;
	}
	
	private Turtle(Turtle turtle) {
		this.setTurtleStr(turtle.getTurtleStr());
	}

	public String getTurtleStr() {
		return turtleStr;
	}

	public void setTurtleStr(String turtleStr) {
		this.turtleStr = turtleStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turtleStr == null) ? 0 : turtleStr.hashCode());
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
		Turtle other = (Turtle) obj;
		if (turtleStr == null) {
			if (other.turtleStr != null)
				return false;
		} else if (!turtleStr.equals(other.turtleStr))
			return false;
		return true;
	}
	
//	private Turtle deepCopy() {
//		return new Turtle(this);
//	}	
}
