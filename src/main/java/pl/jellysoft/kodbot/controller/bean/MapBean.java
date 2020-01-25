package pl.jellysoft.kodbot.controller.bean;

import java.io.Serializable;
import java.util.List;

public class MapBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer botPositionRow;
	private Integer botPositionCol;
	private Integer botRotation;
	private Integer batteryLevel;
	private List<String> mapSlides;
	
	private List<DataRow> data;
	
	public Integer getBotPositionRow() {
		return botPositionRow;
	}
	public void setBotPositionRow(Integer botPositionRow) {
		this.botPositionRow = botPositionRow;
	}
	public Integer getBotPositionCol() {
		return botPositionCol;
	}
	public void setBotPositionCol(Integer botPositionCol) {
		this.botPositionCol = botPositionCol;
	}
	public Integer getBotRotation() {
		return botRotation;
	}
	public void setBotRotation(Integer botRotation) {
		this.botRotation = botRotation;
	}
	public List<DataRow> getData() {
		return data;
	}
	public void setData(List<DataRow> data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class DataRow{
		private Integer type;
		private Integer row;
		private Integer col;
		public DataRow(Integer type, Integer row, Integer col){
			this.type=type;
			this.row = row;
			this.col = col;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getRow() {
			return row;
		}
		public void setRow(Integer row) {
			this.row = row;
		}
		public Integer getCol() {
			return col;
		}
		public void setCol(Integer col) {
			this.col = col;
		}
		
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	public List<String> getMapSlides() {
		return mapSlides;
	}
	public void setMapSlides(List<String> mapSlides) {
		this.mapSlides = mapSlides;
	}
	@Override
	public String toString() {
		return "MapBean [botPositionRow=" + botPositionRow + ", botPositionCol=" + botPositionCol + ", botRotation=" + botRotation + ", batteryLevel=" + batteryLevel + ", mapSlides=" + mapSlides + ", data=" + data
				+ ", getBotPositionRow()=" + getBotPositionRow() + ", getBotPositionCol()=" + getBotPositionCol() + ", getBotRotation()=" + getBotRotation() + ", getData()=" + getData() + ", getBatteryLevel()=" + getBatteryLevel()
				+ ", getMapSlides()=" + getMapSlides() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
