package patterns.observable;

import java.util.ArrayList;

public class StockIndex implements StockListener{
	String name;
	double index;
	ArrayList<Stock> stockObjectsUsedInIndex = new ArrayList<Stock>();
	StockIndex(String name, Stock...stocks){
		this.name = name;
		if (stocks == null) {
			this.index = 0;
		}
		for (Stock stock : stocks) {
			addStock(stock);
			stock.addStockListener(this);
		}
	}
	void addStock(Stock stock){
		if (!stockObjectsUsedInIndex.contains(stock)){
			this.index += stock.getPrice();
			stockObjectsUsedInIndex.add(stock);
		}
	}
	
	void removeStock(Stock stock){
		if (stockObjectsUsedInIndex.contains(stock)){
			stockObjectsUsedInIndex.remove(stock);
			this.index -= stock.getPrice();
		}
	}
	
	double getIndex(){
		return index;
	}

	@Override
	public void stockPriceChanged(Stock stock, double oldPrice, double newPrice) {
		if (oldPrice != newPrice && stockObjectsUsedInIndex.contains(stock)){
			this.index += newPrice - oldPrice;
		}
	}

	@Override
	public String toString() {
		return "StockIndex [name=" + name + ", index=" + index
				+ ", stockObjectsUsedInIndex=" + stockObjectsUsedInIndex + "]";
	}
	
	

}