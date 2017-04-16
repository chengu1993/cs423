/**
 * FCFS scheduling algorithm.
 */
 
import java.util.*;

public class FCFS implements Algorithm
{
    private List<Task> queue;
    private Task currentTask;
    private int totalResponse, totalTurnaround, totalWait, taskNum;



    public FCFS(List<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void schedule() {
        System.out.println("FCFS Scheduling \n");

        while (!queue.isEmpty()) {

            currentTask = pickNextTask();

            totalResponse += CPU.getTime();
            totalWait += CPU.getTime();

            CPU.run(currentTask, currentTask.getBurst());

            taskNum++;
            Math.round(3)

            totalTurnaround += CPU.getTime();
        }
    }

    @Override
    public Task pickNextTask() {
        Task res = queue.get(0);
        queue.remove(0);
        return res;
    }

    @Override
    public double getAverageWaitTime() {
        return (double) totalWait / (double) taskNum;
    }

    @Override
    public double getAverageResponseTime() {
        return (double) totalResponse / (double) taskNum;
    }

    @Override
    public double getAverageTurnaroundTime() {
        return (double) totalTurnaround / (double) taskNum;
    }
}
