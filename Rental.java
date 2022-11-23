import java.util.Date;

public class Rental {
	private final int MS_TO_DAYS = 1000 * 60 * 60 * 24;

	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public String getVideoTitle() {
		return video.getTitle();
	}

	public int getStatus() {
		return status;
	}

	public boolean returnVideo(String videoTitle) {
		if (getVideo().isReturnable(videoTitle)) {
			if (status == 1) {
				this.status = 1;
				returnDate = new Date();
			}
			getVideo().setRented(false);
			return true;
		}
		return false;
	}

	public int getDaysRented() {
		long diff;
		if (getStatus() == 1) { // returned Video
			diff = returnDate.getTime() - rentDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime() - rentDate.getTime();
		}
		return (int) (diff / MS_TO_DAYS) + 1;
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		if ( getDaysRented() <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case VHS: limit = 5 ; break ;
			case CD: limit = 3 ; break ;
			case DVD: limit = 2 ; break ;
		}
		return limit ;
	}
	public void println() {
		getVideo().println();
	}

	public double getCharge() {
		double charge = 0;
		switch (video.getPriceCode()) {
			case REGULAR:
				charge += 2;
				if (getDaysRented() > 2)
					charge += (getDaysRented() - 2) * 1.5;
				break;
			case NEW_RELEASE:
				charge = getDaysRented() * 3;
				break;
		}

		return charge;
	}

	public int getPoint() {
		int point = video.getPriceCode() == PriceCode.NEW_RELEASE ? 2 : 1;

		if ( getDaysRented() > getDaysRentedLimit() )
			point -= Math.min(point, video.getLateReturnPointPenalty()) ;

		return point;
	}
}
