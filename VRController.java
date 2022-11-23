import java.util.Date;
import java.util.List;

public class VRController {
    private List<Customer> customers;

    private List<Video> videos;

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

    void addVideo(Video addedVideo) {
        videos.add(addedVideo);
    }

    Video addVideo(String title, VideoType videoType, PriceCode priceCode, Date registeredDate) {
        Video video = new Video(title, videoType,priceCode, registeredDate);
        videos.add(video);
        return video;
    }

    Video getVideo(String videoTitle) {
        for (Video video : getVideoList()) {
            if (video.getTitle().equals(videoTitle) && !video.isRented()) {
                return video;
            }
        }
        return null;
    }

    public void rentVideo(Customer customer, String videoTitle) {
        Video foundVideo = getVideo(videoTitle);
        if ( foundVideo == null ) return ;

        customer.setRentalVideo(foundVideo);
    }

    public void returnVideo(Customer customer, String videoTitle) {
        customer.returnVideo(videoTitle);
    }
}
