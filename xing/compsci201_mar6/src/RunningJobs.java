import java.util.PriorityQueue;
import java.util.Random;

public class RunningJobs {

	public class Job implements Comparable<Job>{
		private int myTime; // time to complete the job
		private int myPriority; //high number means high priority

		public Job(int time, int priority){
			myTime = time;
			myPriority = priority;
		}

		public int compareTo(Job other){
			if(other.myPriority == myPriority) return other.myTime-myTime;
			return other.myPriority - myPriority;
		}

		public String toString(){
			return "Priority: " + myPriority + " Time: " + myTime;
		}
	}

	PriorityQueue<Job> q = new PriorityQueue<Job>();

	public void addJob(int num){
		Random r = new Random(123);
		for(int i = 0; i < num; i++)
			q.add(new Job(r.nextInt(100), r.nextInt(50)));
	}


	public int numJobsComplete(int time){

		int numJobs = 0;
		int timeLeft = time;
		while (!q.isEmpty()){
			numJobs ++;
			timeLeft -= q.remove().myTime;	
			
		}

		return numJobs;
	}

	public static void main(String[] args){
		RunningJobs t = new RunningJobs();
		Random r = new Random(123);
		
		t.addJob(100);
		//how many jobs can you complete in 1000 seconds?
		int x = t.numJobsComplete(1000);
		System.out.println("Completed " + x + " jobs in 1000 seconds");  //20


	}

}
