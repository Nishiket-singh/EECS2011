import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double time1, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
//Initialize QueueSimulator global variables. 
 this.arrivalRate = aR;
this.serviceTime = servT;
this.totalSimTime = simT;
  
//This is where the packets that have arrived go.
buffer = new LinkedListQueue<Data>();
//This is where the packets that have departed go.
eventQueue = new LinkedListQueue<Data>();
  }
  
  public double calcAverageWaitingTime(){
          //When the simulation runs for ρ seconds, it exits the while loop
        //and evokes calcAverageWaitingTime to compute the average waiting time of packets that have already
        //departed from the buffer queue.
        //
        //Those packets are now located in the event queue!
        //Basically the waiting time of a single packet is the following:
        //
        //Waiting Time = departureTime - arrivalTime
        //We need to do this: Take the sum of all waiting times of the packets in the eventQueue.
        //Divide that by the number of packets in the event queue.

        //That is the average waiting time!
        //
        //An example of how you can do this is:
        int count = 0;
        double totalwaitingtime = 0;
        while(eventQueue.size() != 0) { 
            Data abc = eventQueue.dequeue();
            totalwaitingtime += abc.getDepartureTime() - abc.getArrivalTime();
            count++;
      }
        double averageWaitTime = totalwaitingtime / count;
        return averageWaitTime;
        //    add waiting time of packet to total waiting time
          //  at the end, divide totalWaitingTime by count
        //  return that ^ 
        //  That is the average waiting time.
      }
  

public double runSimulation(){
currTime = 0;
timeForNextArrival = getRandTime(arrivalRate);
timeForNextDeparture = timeForNextArrival + serviceTime;

while(currTime < totalSimTime){
//We want to have an ARRIVAL if timeForNextArrival < timeForNextDeparture.
//Otherwise we want to have a departure.

//DO an arrival.
if (buffer.isEmpty() || timeForNextArrival < timeForNextDeparture){

//ARRIVAL STUFF happens.
//Make a packet. (Data object)
 Data d = new Data();
//d.setArrivalTime(timeForNextArrival)...
      d.setArrivalTime(timeForNextArrival);
//buffer.enqueue ... d...
      buffer.enqueue(d);

//If the arrival is at 2s and we just arrived, then currTime = 2s;
currTime = timeForNextArrival;
//d.setArrivalTime(currTime);
//buffer.enqueue(d);

//TimeForNextArrival = currTime + getRandTime(arrivalRate)
timeForNextArrival = currTime + getRandTime(arrivalRate);
}
//Do a departure. 
else{

//DEPARTURE STUFF
//Dequeue a packet from buffer.
//set the packet's departure time (timeForNextDeparture).
//packet.setDepartureTime(timeForNextDeparture)
//Enqueue that packet into eventQueue.

//We've just departed, therefore, the time is at the departure time.
currTime = timeForNextDeparture;
Data d = buffer.dequeue();
d.setDepartureTime(currTime); //remove data from buffer and add to eventQueue
eventQueue.enqueue(d);
//If there are more items in the queue, then the next timeForNextDeparture
//is currTime + serviceTime
if(buffer.size() > 0){
timeForNextDeparture = currTime + serviceTime;
}
else{
//timeForNextArrival is the time that the next packet arrives.
//We need to send out that packet after we service it, after it arrives.
timeForNextDeparture = timeForNextArrival + serviceTime;
}
}
}

//Now, call calc average waiting time.
return calcAverageWaitingTime(); 
}
}






