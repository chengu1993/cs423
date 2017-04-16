/**
 * Created by chengu on 2/22/17.
 */
import java.util.*;

public class Priority implements Algorithm {

    private List<Task> queue;

    private Task currentTask;
    private int currentTime, totalResponse, totalTurnaround, totalWait, taskNum;


    public Priority(List<Task> queue) {
        this.queue = queue;
        queue.sort((a, b) -> (b.getPriority() - a.getPriority()));
    }

    @Override
    public void schedule() {

        System.out.println("Priority Scheduling \n");
        int currentTime = 0;

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
