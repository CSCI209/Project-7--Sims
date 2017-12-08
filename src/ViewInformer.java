/**
   * @author Shep Sims, Peter Blair, Andrew Baca, Trip Calihan
   * File: ViewInformer.java
   * 
   * A public interface class that possesses the abstract panelPressed method
   */
public abstract interface ViewInformer {
	/**
	   * Creates the abstract method panel pressed to be implemented
	   * @param CellPanel AbstractCellPanel
	   */
	public abstract void panelPressed(AbstractCellPanel CellPanel);
}
