package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    List<Simulation> simulations;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }
    ConsoleMapDisplay observer = new ConsoleMapDisplay();

    public void runSync() {
        for(Simulation simulation:simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        List<Thread> threads = new ArrayList<>();

        for(Simulation simulation:simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }

        awaitSimulationsEnd(threads);
    }

    private void awaitSimulationsEnd(List<Thread> threads) {
        for(Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {};
        }
    }

    private void awaitSimulationsEnd(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Unable to finish all tasks within 10 seconds.");
            }
        } catch (InterruptedException e) {
            System.out.println("Executor got interrupted.");
        }
    }

    public void runAsyncInThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for(Simulation simulation:simulations) {
            executor.execute(simulation);
        }

        awaitSimulationsEnd(executor);
    }
}
