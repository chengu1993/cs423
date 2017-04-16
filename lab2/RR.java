/**
 * Created by chengu on 2/22/17.
 */
import java.util.*;

public class RR implements Algorithm{
    private List<Task> queue;
    private static final int QUANTUM = 10;
    private LinkedList<Task> tempQueue;
    private int taskNum;
    private Map<Task, Integer> response, turnaround, lastExecute, wait;


    public RR(List<Task> queue) {
        this.queue = queue;
        this.tempQueue = new LinkedList<>();
        this.response = new HashMap<>();
        this.turnaround = new HashMap<>();
        this.lastExecute = new HashMap<>();
        this.wait = new HashMap<>();
        this.taskNum = 0;
        queue.sort((a, b) -> (b.getPriority() - a.getPriority()));
    }

    @Override
    public void schedule() {

        System.out.println("RR Scheduling \n");
        Task currentTask;

        while (!queue.isEmpty()) {

            currentTask = pickNextTask();
            tempQueue.add(currentTask);
            while(!queue.isEmpty() && peekNextTask().getPriority() == currentTask.getPriority()) {
                tempQueue.add(pickNextTask());
            }

            /*if size of tempQueue is larger than 1, we adopt RR*/
            if(tempQueue.size() > 1){
                while(!tempQueue.isEmpty()){
                    currentTask = tempQueue.poll();
                    //update response time
                    if(!response.containsKey(currentTask)){
                        response.put(currentTask, CPU.getTime());
                    }

                    //calcualte wait time;
                    int waitTime = CPU.getTime() - lastExecute.getOrDefault(currentTask, 0);
                    //update wait
                    wait.put(currentTask, wait.getOrDefault(currentTask, 0) + waitTime);

                    CPU.run(currentTask, Math.min(QUANTUM, currentTask.getBurst()));

                    lastExecute.put(currentTask, CPU.getTime());

                    int remainingBurst = Math.max(0, currentTask.getBurst() - QUANTUM);
                    if(remainingBurst > 0) {
                        currentTask.setBurst(remainingBurst);
                        tempQueue.add(currentTask);
                    } else{ //finish executing
                        turnaround.put(currentTask, CPU.getTime());
                        taskNum++;
                    }

                }
            } else {
                response.put(currentTask, CPU.getTime());
                wait.put(currentTask, CPU.getTime());
                CPU.run(currentTask, currentTask.getBurst());
                turnaround.put(currentTask, CPU.getTime());
                taskNum++;
                System.out.print()
            }

            tempQueue.clear();

        }
    }



    private Task peekNextTask() {
        return queue.get(0);
    }

    @Override
    public Task pickNextTask() {
        Task res = queue.get(0);
        queue.remove(0);
        return res;
    }

    @Override
    public double getAverageWaitTime() {
        double totalWait = wait.values().stream().reduce(0, (a, b) -> (a + b));
        return totalWait / (double) taskNum;
    }

    @Override
    public double getAverageResponseTime() {
        double totalResponse = response.values().stream().reduce(0, (a, b) -> (a + b));
        return totalResponse / (double) taskNum;
    }

    @Override
    public double getAverageTurnaroundTime() {
        double totalTurnaround = turnaround.values().stream().reduce(0, (a, b) -> (a + b));
        return totalTurnaround / (double) taskNum;
    }

}
