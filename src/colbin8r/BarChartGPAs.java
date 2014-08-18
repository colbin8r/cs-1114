package colbin8r;

public class BarChartGPAs implements BarChart {
	
	private int maxColumns = 80;
	private double maxRange = 4.0;

	public BarChartGPAs() {
		
	}

	@Override
	public void setMaxColLength(int maxColumns) {
		if (maxColumns < 0) {
			this.maxColumns = 80;
		} else {
			this.maxColumns = maxColumns;
		}
	}

	@Override
	public void setMaxRangeValue(double maxRange) {
		if (maxRange == 0) {
			this.maxRange = 1;
		} else {
			this.maxRange = maxRange;
		}
	}

	@Override
	public int getMaxColLength() {
		return this.maxColumns;
	}

	@Override
	public double getMaxRangeValue() {
		return this.maxRange;
	}

	@Override
	public int getBarLength(double value) {
		// (GPA/MaxGPA) * MaxColumnLength
		if (value < 0 ^ this.getMaxRangeValue() < 0) {
			return Integer.MAX_VALUE;
		} else {
			return (int) ((value / this.getMaxRangeValue()) * this.getMaxColLength());
		}
	}

}
