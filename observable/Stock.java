package patterns.observable;

import java.util.ArrayList;

public class Stock {
	
	String ticker;
	double price;
	
	ArrayList<StockListener> stockListenerList = new ArrayList<StockListener>();
	
	
	
	Stock(String ticker, double stock){
		this.ticker = ticker;
		this.price = stock;
	}
	
	void setPrice(double price){
		if (price <= 0){
			throw new IllegalArgumentException();
		}
		double oldPrice = this.price;
		this.price = price;
		for (StockListener listener : stockListenerList){
			listener.stockPriceChanged(this, oldPrice, price);
		}
	}
	
	String getTicker(){
		return ticker;
	}
	
	double getPrice(){
		return price;
	}
	
	void addStockListener(StockListener stockListener){
		stockListenerList.add(stockListener);
	}
	
	void removeStockListener(StockListener stockListener){
		stockListenerList.remove(stockListener);
	}

	@Override
	public String toString() {
		return "Stock [ticker=" + ticker + ", price=" + price
				+ ", length of stockListenerList " +  stockListenerList.size() + "]";
	}
	
	

}