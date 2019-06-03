
public class Commission {
	private double salesFigure;
	private double commission_Rate;
	private int commissionRate;
	private double result;
	private double Commission;
	
//	public Commission(double SalesFigure, double CommissionRate, int commissionRate) {
//		this.salesFigure=SalesFigure;
//		this.commission_Rate=CommissionRate;
//		this.commissionRate=commissionRate;
//	}
//	
	public void ComputeCommission(double SalesFigure, double CommissionRate) {
		result= SalesFigure*CommissionRate;
		System.out.println(result);
	}
	public void ComputeCommission(double SalesFigure, int CommissionRate) {
		Commission=(commissionRate/100.0)*SalesFigure;
		System.out.println(Commission);
}
	public void ComputeCommission(double sales) {
		this.commission_Rate=7.5;
		ComputeCommission(this.commission_Rate, sales);
		
		
	}
	public static void main(String[] args) {
		Commission fig=new Commission();
		fig.ComputeCommission(7.0, 6.12);
		fig.ComputeCommission(9, 2);
		fig.ComputeCommission(25);
	}
}