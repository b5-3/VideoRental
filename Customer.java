import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;

	private final List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentalVideo(Video video) {
		Rental rental = new Rental(video) ;
		video.setRented(true);
		rentals.add(rental);
	}

	public void clearRentals() {
		rentals.clear();
	}

	public void addRentalVideo(Video video) {
		Rental rental = new Rental(video) ;
		video.setRented(true);
		rentals.add(rental);
	}

	public void returnVideo(String videoTitle) {
		for (Rental rental : rentals) {
			if (rental.returnVideo(videoTitle)) break;
		}
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = each.getDaysRented();

			eachCharge = each.getCharge();
			eachPoint = each.getPoint();

			result += "\t" + each.getVideoTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;
			totalPoint += eachPoint;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	public void printSummary() {
		System.out.println("Name: " + getName() + "\tRentals: " + getRentals().size()) ;
		for ( Rental rental: getRentals() ) {
			rental.printSummary();
		}
	}
}
