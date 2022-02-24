import java.io.*;
import java.util.*;
public class Schedule{

	static class Tasks {
		ArrayList<Tasks> tasks;
		String name;
		int time;
		int prior;
		int cpu;

		public Tasks(String taskName, int arrivalTime, int priority, int cpuBurst) {
			name = taskName;
			time = arrivalTime;
			prior = priority;
			cpu = cpuBurst;
		}

		public String getName() {
			return name;
		}

		public Integer getTime() {
			return time;
		}

		public Integer getPrior() {
			return prior;
		}

		public Integer getBurst() {
			return cpu;
		}

		public void setBurst(Integer newBurst) {
			this.cpu=newBurst;
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException{
		ArrayList<Tasks> tasks = new ArrayList<Tasks>();
		File input = new File(args[1]);
		File output = new File("output.txt");  
		Scanner filecontent = new Scanner(input);
		FileWriter myWriter = new FileWriter("output.txt");

		while (filecontent.hasNextLine()) {
			String line = filecontent.nextLine();
			if (line.isEmpty()) continue;
			String[] lines = line.split("\\s*,\\s*");
			tasks.add(new Tasks(lines[0], Integer.parseInt(lines[1]), Integer.parseInt(lines[2]), Integer.parseInt(lines[3])));
		}
		
		if (args[0].equals("fsfc")) {
			myWriter.write("First Come First Serve Scheduling\n\n");
			Collections.sort(tasks, (t1, t2) -> t1.getTime()-t2.getTime());

			for (int i=0; i<tasks.size(); i++) { 
				myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
				myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
				myWriter.write("Burst: " + tasks.get(i).getBurst() + "\n\n");
				myWriter.write("Task " + tasks.get(i).getName() + " Finished\n\n");
			}

			myWriter.write("Tasks are complete.");
			myWriter.close();
		}

		if (args[0].equals("sjf")) {
			myWriter.write("Shortest Job First Scheduling\n\n");
			Collections.sort(tasks, (t1, t2) -> t1.getBurst()-t2.getBurst());

			for (int i=0; i<tasks.size(); i++) { 
				myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
				myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
				myWriter.write("Burst: " + tasks.get(i).getBurst() + "\n\n");
				myWriter.write("Task " + tasks.get(i).getName() + " Finished\n\n");
			} 

			myWriter.write("Tasks are complete.");
			myWriter.close();
		}
		
		if (args[0].equals("pri")) {
			myWriter.write("Priority Scheduling\n\n");
			Collections.sort(tasks, (t1, t2) -> t1.getPrior()-t2.getPrior());
			Collections.reverse(tasks);

			for (int i=0; i<tasks.size(); i++) { 
				myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
				myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
				myWriter.write("Burst: " + tasks.get(i).getBurst() + "\n\n");
				myWriter.write("Task " + tasks.get(i).getName() + " Finished\n\n");
			} 

			myWriter.write("Tasks are complete.");
			myWriter.close();
		}

		if (args[0].equals("rr")) {
			myWriter.write("Round Robin Scheduling\n\n");
			int q = 10;
			int newBurst;
			int count = tasks.size();
			
			while (count > 0) {
				for(int i = 0; i < tasks.size(); i++) {

					if (tasks.get(i).getBurst() < 1) {
						continue;
					}

					myWriter.write("Task " + tasks.get(i).getName() + " started\n\n");
					myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
					myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
					newBurst = tasks.get(i).getBurst()-q;
					tasks.get(i).setBurst(newBurst);

					if (newBurst < 0) {
						newBurst = 0;
					}

					myWriter.write("Remaining Burst: " + newBurst + "\n\n");

					if (tasks.get(i).getBurst() > 1) {
						myWriter.write("Task " + tasks.get(i).getName() + "'s state saved\n\n\n");
					}else {
						myWriter.write("Task " + tasks.get(i).getName() + " finished\n\n\n");
						count--;
					}
				}
				
			}
			myWriter.write("Tasks are complete.");
			myWriter.close();
		}

		if (args[0].equals("pri-rr")) {
			myWriter.write("Priority with Round Robin Scheduling\n\n");
			int q = 10;
			int newBurst;
			int count = tasks.size();
			int first = 0;
			int timer = 1;
			Collections.sort(tasks, (t1, t2) -> t1.getTime()-t2.getTime());
			while (count > 0) {
				for(int i = 0; i < tasks.size(); i++) {
					if (tasks.get(i).getBurst() < 1) {
						continue;
					}
					
					if (first == 0) {
						myWriter.write("Task " + tasks.get(i).getName() + " started\n\n");
						myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
						myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
						newBurst = tasks.get(i).getBurst()-q;
						tasks.get(i).setBurst(newBurst);

						if (newBurst < 0) {
							newBurst = 0;
						}

						myWriter.write("Remaining Burst: " + newBurst + "\n\n");

						if (tasks.get(i).getBurst() > 1) {
							myWriter.write("Task " + tasks.get(i).getName() + "'s state saved\n\n\n");
						}else {
							myWriter.write("Task " + tasks.get(i).getName() + " finished\n\n\n");
							count--;
						}
						first=1;
					}
					Collections.sort(tasks, (t1, t2) -> t1.getPrior()-t2.getPrior());
					Collections.reverse(tasks);
					myWriter.write("Task " + tasks.get(i).getName() + " started\n\n");
					myWriter.write("Will run Name: " + tasks.get(i).getName() +"\n");
					myWriter.write("Priority: " + tasks.get(i).getPrior() + "\n");
					newBurst = tasks.get(i).getBurst()-q;
					tasks.get(i).setBurst(newBurst);

					if (newBurst < 0) {
						newBurst = 0;
					}

					myWriter.write("Remaining Burst: " + newBurst + "\n\n");

					if (tasks.get(i).getBurst() > 1) {
						myWriter.write("Task " + tasks.get(i).getName() + "'s state saved\n\n\n");
					}else {
						myWriter.write("Task " + tasks.get(i).getName() + " finished\n\n\n");
						count--;
					}
				}
			}
		myWriter.write("Tasks are complete.");
		myWriter.close();
		}
	System.out.println("");
	System.out.println("hocam bonuslar yok. nasil yapilacagini cozemedim derste anlatabilirseniz cok iyi olur.\n");
	System.out.println("muhtemelen bazi bilgileri farkli arraylarde tutarak islem yapiliyordur ama yinede sizin yaklasiminizi gormek isterim.");
	}
} 
