package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class SimulationEngine {

    private final List<Simulation> simulationList;
    private final List<Thread> threadList = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }
    public SimulationEngine(Simulation simulation){
        this.simulationList = new ArrayList<>();
        this.simulationList.add(simulation);
    }

    public void runSync(){
        for (Simulation simulation : simulationList){
            simulation.run();
        }
    }

    public void runAsync() throws InterruptedException{
        for (Simulation simulation : simulationList){
            Thread simulationThread = new Thread(simulation);
            simulationThread.start();
            threadList.add(simulationThread);
        }
    }

    public void runAsyncInThreadPool() throws InterruptedException{
        for (Simulation simulation : simulationList){
            executorService.submit(simulation);
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException{
        for(Thread thread : threadList){
            thread.join();
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
