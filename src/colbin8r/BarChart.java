package colbin8r;

public interface BarChart {
    public void setMaxColLength(int maxColumns);
    public void setMaxRangeValue(double maxRange);
    public int getMaxColLength();
    public double getMaxRangeValue();

    public int getBarLength(double value);
}