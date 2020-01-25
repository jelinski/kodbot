package pl.jellysoft.kodbot.resolver.statistic;

public class StatisticDTO {

	private int overall;
	private int moveCount;
	private int jumpCount;
	private int turnLeftCount;
	private int turnRightCount;
	private int definedFunctionCount;
	private int calledFunctionCount;
	private int repeatCount;
	private int assignWithAdditionCount;
	private int assignWithSubtractionCount;
	private int assignCount;
	private int incrementCount;
	private int decrementCount;

	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	public int getJumpCount() {
		return jumpCount;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}

	public int getTurnLeftCount() {
		return turnLeftCount;
	}

	public void setTurnLeftCount(int turnLeftCount) {
		this.turnLeftCount = turnLeftCount;
	}

	public int getTurnRightCount() {
		return turnRightCount;
	}

	public void setTurnRightCount(int turnRightCount) {
		this.turnRightCount = turnRightCount;
	}

	public int getDefinedFunctionCount() {
		return definedFunctionCount;
	}

	public void setDefinedFunctionCount(int definedFunctionCount) {
		this.definedFunctionCount = definedFunctionCount;
	}

	public int getCalledFunctionCount() {
		return calledFunctionCount;
	}

	public void setCalledFunctionCount(int calledFunctionCount) {
		this.calledFunctionCount = calledFunctionCount;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int getAssignWithAdditionCount() {
		return assignWithAdditionCount;
	}

	public void setAssignWithAdditionCount(int assignWithAdditionCount) {
		this.assignWithAdditionCount = assignWithAdditionCount;
	}

	public int getAssignWithSubtractionCount() {
		return assignWithSubtractionCount;
	}

	public void setAssignWithSubtractionCount(int assignWithSubtractionCount) {
		this.assignWithSubtractionCount = assignWithSubtractionCount;
	}

	public int getAssignCount() {
		return assignCount;
	}

	public void setAssignCount(int assignCount) {
		this.assignCount = assignCount;
	}

	public int getIncrementCount() {
		return incrementCount;
	}

	public void setIncrementCount(int incrementCount) {
		this.incrementCount = incrementCount;
	}

	public int getDecrementCount() {
		return decrementCount;
	}

	public void setDecrementCount(int decrementCount) {
		this.decrementCount = decrementCount;
	}

	@Override
	public String toString() {
		return "StatisticDTO [overall=" + overall + ", moveCount=" + moveCount + ", jumpCount=" + jumpCount + ", turnLeftCount=" + turnLeftCount + ", turnRightCount=" + turnRightCount + ", definedFunctionCount=" + definedFunctionCount
				+ ", calledFunctionCount=" + calledFunctionCount + ", repeatCount=" + repeatCount + ", assignWithAdditionCount=" + assignWithAdditionCount + ", assignWithSubtractionCount=" + assignWithSubtractionCount + ", assignCount="
				+ assignCount + ", incrementCount=" + incrementCount + ", decrementCount=" + decrementCount + "]";
	}

}
