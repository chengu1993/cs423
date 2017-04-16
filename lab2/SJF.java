import java.util.*;

/**
 * Created by chengu on 2/22/17.
 */
public class SJF implements Algorithm {

    private List<Task> queue;
    private Task currentTask;
    private int totalResponse, totalTurnaround, totalWait, taskNum;


    public SJF(List<Task> queue) {
        this.queue = queue;
        queue.sort((a, b) -> (a.getBurst() - b.getBurst()));
    }

    @Override
    public void schedule() {

        System.out.println("SJF Scheduling \n");

        while (!queue.isEmpty()) {

            currentTask = pickNextTask();

            totalResponse += CPU.getTime();
            totalWait += CPU.getTime();

            CPU.run(currentTask, currentTask.getBurst());

            taskNum++;

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
