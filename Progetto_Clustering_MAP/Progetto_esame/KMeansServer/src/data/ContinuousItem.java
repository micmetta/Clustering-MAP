package data;

public class ContinuousItem extends Item{
	
	
	private static final long serialVersionUID = 1L;

	public ContinuousItem(ContinuousAttribute attribute, Double value){
		super(attribute, value);
	}
	
	double distance(Object a) {
		
		double val_corr = ((ContinuousAttribute)attribute).getScaledValue((Double)this.getValue());
		double val_tup = ((ContinuousAttribute)attribute).getScaledValue((Double)((ContinuousItem)a).getValue());
		
		return	Math.abs(val_corr - val_tup);
		
	}
	
	/*
	double distance(Object a) {		
		
		double dist=0.0;
		
		Attribute objAttribute = ((ContinuousItem)a).getAttribute();
		
		double currVal = ((ContinuousAttribute)this.getAttribute()).getScaledValue((Double)this.getValue());
		
		double tupVal = ((ContinuousAttribute)objAttribute).getScaledValue(   (Double)((ContinuousItem)a).getValue()   );
		
		dist = Math.abs(currVal - tupVal);
		
		return dist;
	}
	*/
}
