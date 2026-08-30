import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

enum VehicleType {
    CAR,
    BIKE
}

enum SlotType {
    CAR,
    BIKE
}

enum TicketStatus {
    ACTIVE,
    PAID
}

class Vehicle {

    private final String vehicleNumber;
    private final VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}


abstract class ParkingSlot {

    private final int slotNumber;
    private final SlotType slotType;

    private boolean occupied;
    private Vehicle parkedVehicle;

    public ParkingSlot(int slotNumber, SlotType slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.occupied = false;
    }

    public void parkVehicle(Vehicle vehicle) {

        if (occupied) {
            throw new IllegalStateException(
                "Slot " + slotNumber + " is already occupied"
            );
        }

        this.parkedVehicle = vehicle;
        this.occupied = true;
    }

    public Vehicle removeVehicle() {

        if (!occupied) {
            throw new IllegalStateException(
                "Slot " + slotNumber + " is already empty"
            );
        }

        Vehicle vehicle = parkedVehicle;

        parkedVehicle = null;
        occupied = false;

        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}

class CarSlot extends ParkingSlot {

    public CarSlot(int slotNumber) {
        super(slotNumber, SlotType.CAR);
    }
}

class BikeSlot extends ParkingSlot {

    public BikeSlot(int slotNumber) {
        super(slotNumber, SlotType.BIKE);
    }
}

class Ticket {

    private final int ticketNumber;
    private final Vehicle vehicle;
    private final ParkingSlot parkingSlot;
    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;
    private TicketStatus status;

    public Ticket(int ticketNumber,Vehicle vehicle,ParkingSlot parkingSlot) {
        this.ticketNumber = ticketNumber;
        this.vehicle = vehicle;
        this.parkingSlot = parkingSlot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
        this.status = TicketStatus.PAID;
    }
}


interface SlotAllocationStrategy {
    ParkingSlot findSlot(List<ParkingSlot> slots,Vehicle vehicle);
}

class FirstAvailableSlotStrategy

    implements SlotAllocationStrategy {

    @Override
    public ParkingSlot findSlot(List<ParkingSlot> slots,Vehicle vehicle) {

        SlotType requiredType =
            vehicle.getVehicleType() == VehicleType.CAR? SlotType.CAR: SlotType.BIKE;

        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied() && slot.getSlotType() == requiredType) {
                return slot;
            }
        }
     return null;
    }
}

interface PaymentMethod {

    void pay(double amount);
}

class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {

        System.out.println(
            "₹" + amount + " paid successfully using UPI."
        );
    }
}

class CreditCardPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {

        System.out.println(
            "₹" + amount +
            " paid successfully using Credit Card."
        );
    }
}

interface PricingStrategy {

    double calculatePrice(Ticket ticket);
}

class HourlyPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(Ticket ticket) {

        LocalDateTime now = LocalDateTime.now();

        long minutes = Duration
            .between(ticket.getEntryTime(), now)
            .toMinutes();

        // minimum 1 hour charge
        long hours = Math.max(
            1,
            (long) Math.ceil(minutes / 60.0)
        );

        if (ticket.getVehicle().getVehicleType()
                == VehicleType.CAR) {

            return hours * 50;
        }

        return hours * 20;
    }
}

class ParkingLot {

    private final List<ParkingSlot> slots;

    private final Map<Integer, Ticket> activeTickets;

    private final SlotAllocationStrategy slotStrategy;
    private final PricingStrategy pricingStrategy;

    private int ticketCounter = 1;

    public ParkingLot(SlotAllocationStrategy slotStrategy,PricingStrategy pricingStrategy) {

        this.slots = new ArrayList<>();
        this.activeTickets = new HashMap<>();

        this.slotStrategy = slotStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) {

        ParkingSlot slot = slotStrategy.findSlot(slots, vehicle);

        if (slot == null) {
            throw new RuntimeException("No parking slot available for "+ vehicle.getVehicleType());
        }
        slot.parkVehicle(vehicle);
        Ticket ticket =new Ticket(ticketCounter++, vehicle, slot);
        activeTickets.put(ticket.getTicketNumber(),ticket);
        System.out.println( vehicle.getVehicleNumber() + " parked at slot " + slot.getSlotNumber());
        return ticket;
    }

    public synchronized void exitVehicle(int ticketNumber, PaymentMethod paymentMethod) {

        Ticket ticket = activeTickets.get(ticketNumber);

        if (ticket == null) {
            throw new RuntimeException("Invalid ticket number.");
        }

        double amount = pricingStrategy.calculatePrice(ticket);

        paymentMethod.pay(amount);

        ticket.getParkingSlot().removeVehicle();

        ticket.closeTicket();

        activeTickets.remove(ticketNumber);

        System.out.println("Vehicle "+ ticket.getVehicle().getVehicleNumber()+ " exited from slot "+ ticket.getParkingSlot().getSlotNumber());
    }

    public void displayAvailableSlots() {

        System.out.println("\nAvailable Slots:");

        for (ParkingSlot slot : slots) {

            if (!slot.isOccupied()) {

                System.out.println(
                    "Slot: "
                    + slot.getSlotNumber()
                    + " | Type: "
                    + slot.getSlotType()
                );
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        SlotAllocationStrategy slotStrategy = new FirstAvailableSlotStrategy();
        PricingStrategy pricingStrategy = new HourlyPricingStrategy();
        ParkingLot parkingLot = new ParkingLot(slotStrategy,pricingStrategy);


        parkingLot.addSlot(new CarSlot(1));
        parkingLot.addSlot(new CarSlot(2));

        parkingLot.addSlot(new BikeSlot(3));
        parkingLot.addSlot(new BikeSlot(4));


        Vehicle car =new Vehicle("HP-06-1234",VehicleType.CAR);
        Vehicle bike =new Vehicle("HP-02-5678",VehicleType.BIKE);


        Ticket carTicket =parkingLot.parkVehicle(car);
        Ticket bikeTicket =parkingLot.parkVehicle(bike);

        parkingLot.displayAvailableSlots();
        PaymentMethod upi =new UPIPayment();
        parkingLot.exitVehicle(carTicket.getTicketNumber(),upi);

        PaymentMethod creditCard = new CreditCardPayment();
        parkingLot.exitVehicle(bikeTicket.getTicketNumber(),creditCard);
        parkingLot.displayAvailableSlots();
    }
}