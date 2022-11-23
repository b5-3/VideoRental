import java.util.Date;
import java.util.List;

public class VRController {
    private final List<Customer> customers;

    private final List<Video> videos;

    public VRController(List<Customer> customers, List<Video> videos) {
        this.customers = customers;
        this.videos = videos;
    }

    public List<Customer> getCustomerList() {
        return customers;
    }

    public List<Video> getVideoList() {
        return videos;
    }

    Customer getCustomer(String customerName) {
        for (Customer customer : getCustomerList()) {
            if (customer.getName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }

    Customer addCustomer(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
        return customer;
    }

    boolean hasCustomer(String name) {
        return getCustomer(name) != null;
    }

     Video addVideo(String title, VideoType videoType, PriceCode priceCode, Date registeredDate) {
        Video video = new Video(title, videoType,priceCode, registeredDate);
        videos.add(video);
        return video;
    }

    Video getVideo(String videoTitle) {
        for (Video video : getVideoList()) {
            if (video.isEqual(videoTitle) && !video.isRented()) {
                return video;
            }
        }
        return null;
    }

    public void rentVideo(String customerName, String videoTitle) {
        Video foundVideo = getVideo(videoTitle);
        if ( foundVideo == null ) return ;

        Customer customer = getCustomer(customerName);
        if (customer != null) {
            customer.setRentalVideo(foundVideo);
        }
    }

    public void returnVideo(String customerName, String videoTitle) {
        Customer customer = getCustomer(customerName);
        if (customer != null) {
            customer.returnVideo(videoTitle);
        }
    }

    public void clearRental(String name) {
        Customer customer = getCustomer(name);
        if (customer != null) {
            customer.printSummary();
            customer.clearRentals();
        }
    }

    public String getCustomerReport(String name) {
        Customer customer = getCustomer(name);
        if (customer != null) {
            return customer.getReport();
        }
        return "no customer found";
    }
}
