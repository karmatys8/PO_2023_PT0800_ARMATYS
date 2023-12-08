package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private List<Simulation> simulations;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }
    private ExecutorService executor = Executors.newFixedThreadPool(4);
    private List<Thread> threads = new ArrayList<>();

    public void runSync() {
        for(Simulation simulation:simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for(Simulation simulation:simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Unable to finish all tasks within 10 seconds.");
        }

        for(Thread thread:threads) {
            thread.join();
        }
    }

    public void runAsyncInThreadPool() {
        for(Simulation simulation:simulations) {
            executor.execute(simulation);
        }
    }
}
