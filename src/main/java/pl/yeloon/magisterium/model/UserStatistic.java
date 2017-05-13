package pl.yeloon.magisterium.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_statistic")
public class UserStatistic extends BaseEntity {

	@Column(name = "user_id")
	private Integer userId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	private User user;
	@Column(name = "overall_score")
	private Integer overallScore;

	@Column(name = "move_counter")
	private Integer moveCounter;

	@Column(name = "jump_counter")
	private Integer jumpCounter;

	@Column(name = "turn_left_counter")
	private Integer turnLeftCounter;

	@Column(name = "turn_right_counter")
	private Integer turnRightCounter;

	@Column(name = "assign_with_addition_counter")
	private Integer assignWithAdditionCounter;

	@Column(name = "assign_with_subtraction_counter")
	private Integer assignWithSubtractionCounter;

	@Column(name = "assign_counter")
	private Integer assignCounter;

	@Column(name = "repeat_counter")
	private Integer repeatCounter;

	@Column(name = "defined_function_counter")
	private Integer definedFunctionCounter;

	@Column(name = "called_function_counter")
	private Integer calledFunctionCounter;

	@Column(name = "increment_counter")
	private Integer incrementCounter;

	@Column(name = "decrement_counter")
	private Integer decrementCounter;

	public Integer getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Integer overallScore) {
		this.overallScore = overallScore;
	}

	public Integer getMoveCounter() {
		return moveCounter;
	}

	public void setMoveCounter(Integer moveCounter) {
		this.moveCounter = moveCounter;
	}

	public Integer getJumpCounter() {
		return jumpCounter;
	}

	public void setJumpCounter(Integer jumpCounter) {
		this.jumpCounter = jumpCounter;
	}

	public Integer getTurnLeftCounter() {
		return turnLeftCounter;
	}

	public void setTurnLeftCounter(Integer turnLeftCounter) {
		this.turnLeftCounter = turnLeftCounter;
	}

	public Integer getTurnRightCounter() {
		return turnRightCounter;
	}

	public void setTurnRightCounter(Integer turnRightCounter) {
		this.turnRightCounter = turnRightCounter;
	}

	public Integer getAssignWithAdditionCounter() {
		return assignWithAdditionCounter;
	}

	public void setAssignWithAdditionCounter(Integer assignWithAdditionCounter) {
		this.assignWithAdditionCounter = assignWithAdditionCounter;
	}

	public Integer getAssignWithSubtractionCounter() {
		return assignWithSubtractionCounter;
	}

	public void setAssignWithSubtractionCounter(Integer assignWithSubtractionCounter) {
		this.assignWithSubtractionCounter = assignWithSubtractionCounter;
	}

	public Integer getAssignCounter() {
		return assignCounter;
	}

	public void setAssignCounter(Integer assignCounter) {
		this.assignCounter = assignCounter;
	}

	public Integer getRepeatCounter() {
		return repeatCounter;
	}

	public void setRepeatCounter(Integer repeatCounter) {
		this.repeatCounter = repeatCounter;
	}

	public Integer getDefinedFunctionCounter() {
		return definedFunctionCounter;
	}

	public void setDefinedFunctionCounter(Integer definedFunctionCounter) {
		this.definedFunctionCounter = definedFunctionCounter;
	}

	public Integer getCalledFunctionCounter() {
		return calledFunctionCounter;
	}

	public void setCalledFunctionCounter(Integer calledFunctionCounter) {
		this.calledFunctionCounter = calledFunctionCounter;
	}

	public Integer getIncrementCounter() {
		return incrementCounter;
	}

	public void setIncrementCounter(Integer incrementCounter) {
		this.incrementCounter = incrementCounter;
	}

	public Integer getDecrementCounter() {
		return decrementCounter;
	}

	public void setDecrementCounter(Integer decrementCounter) {
		this.decrementCounter = decrementCounter;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserStatistic [userId=" + userId + ", user=" + user + ", overallScore=" + overallScore + ", moveCounter=" + moveCounter + ", jumpCounter=" + jumpCounter + ", turnLeftCounter=" + turnLeftCounter + ", turnRightCounter="
				+ turnRightCounter + ", assignWithAdditionCounter=" + assignWithAdditionCounter + ", assignWithSubtractionCounter=" + assignWithSubtractionCounter + ", assignCounter=" + assignCounter + ", repeatCounter=" + repeatCounter
				+ ", definedFunctionCounter=" + definedFunctionCounter + ", calledFunctionCounter=" + calledFunctionCounter + ", incrementCounter=" + incrementCounter + ", decrementCounter=" + decrementCounter + "]";
	}

}
