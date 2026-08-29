import java.util.ArrayList;
import java.util.List;

interface ISubscriber {

    void subscribe(IChannel channel);
    void unsubscribe(IChannel channel);
    void update(String videoTitle, IChannel channel);
}

interface IChannel {

    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notifySubscribers(String videoTitle);
    String getName();
}

class Channel implements IChannel {

    private List<ISubscriber> subscribers = new ArrayList<>();

    private String channelName;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {

        for (ISubscriber subscriber : subscribers) {
            subscriber.update(videoTitle, this);
        }
    }

    @Override
    public String getName() {
        return channelName;
    }

    public void uploadVideo(String videoTitle) {
        System.out.println("\n" + channelName + " uploaded: " + videoTitle);
        notifySubscribers(videoTitle);
    }
}

class Subscriber1 implements ISubscriber {

    private String name;

    public Subscriber1(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(IChannel channel) {

        channel.addSubscriber(this);

        System.out.println(
            name + " subscribed to " + channel.getName()
        );
    }

    @Override
    public void unsubscribe(IChannel channel) {

        channel.removeSubscriber(this);

        System.out.println(
            name + " unsubscribed from " + channel.getName()
        );
    }

    @Override
    public void update(String videoTitle, IChannel channel) {

        System.out.println(
            name +
            " received notification -> New video by: " +
            channel.getName() +
            ", Title: " +
            videoTitle
        );
    }
}

class Subscriber2 implements ISubscriber {

    private String name;

    public Subscriber2(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(IChannel channel) {

        channel.addSubscriber(this);

        System.out.println(
            name + " subscribed to " + channel.getName()
        );
    }

    @Override
    public void unsubscribe(IChannel channel) {

        channel.removeSubscriber(this);

        System.out.println(
            name + " unsubscribed from " + channel.getName()
        );
    }

    @Override
    public void update(String videoTitle, IChannel channel) {

        System.out.println(
            name +
            " received notification -> New video by: " +
            channel.getName() +
            ", Title: " +
            videoTitle
        );
    }
}

class Subscriber3 implements ISubscriber {

    private String name;

    public Subscriber3(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(IChannel channel) {

        channel.addSubscriber(this);

        System.out.println(
            name + " subscribed to " + channel.getName()
        );
    }

    @Override
    public void unsubscribe(IChannel channel) {

        channel.removeSubscriber(this);

        System.out.println(
            name + " unsubscribed from " + channel.getName()
        );
    }

    @Override
    public void update(String videoTitle, IChannel channel) {

        System.out.println(
            name +
            " received notification -> New video by: " +
            channel.getName() +
            ", Title: " +
            videoTitle
        );
    }
}

public class Main {

    public static void main(String[] args) {

        Channel channel1 = new Channel("Navneet Deshta");

        ISubscriber s1 = new Subscriber1("Rahul");
        ISubscriber s2 = new Subscriber2("Aman");
        ISubscriber s3 = new Subscriber3("Rohit");
        
        s1.subscribe(channel1);
        s2.subscribe(channel1);
        s3.subscribe(channel1);

        channel1.uploadVideo("How to Learn LLD from Scratch");
        s2.unsubscribe(channel1);
        channel1.uploadVideo("Observer Design Pattern");
    }
}