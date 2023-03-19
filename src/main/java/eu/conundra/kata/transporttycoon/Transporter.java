package eu.conundra.kata.transporttycoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Transporter {
    private final Queue<String> factoryPackages;
    private final List<String> bPackages = new ArrayList<>();
    private int position1 = 0;
    private String payload1 = "";
    private int position2 = 0;
    private String payload2 = "";


    public Transporter(List<String> factoryPackages) {
        this.factoryPackages = new LinkedList<>(factoryPackages);
    }

    public int solve() {
        var iterations = -1;
        do {
            iterations++;
            if (iterations > 100) return 0;
            dropPackage();
            pickUpPackage();
            move();
        } while (!done());
        return iterations;
    }

    private void dropPackage() {
        if (position1 == 5) {
            bPackages.add(payload1);
            payload1 = "";
        }
        if (position2 == 5) {
            bPackages.add(payload2);
            payload2 = "";
        }
    }

    private void pickUpPackage() {
        if (factoryPackages.isEmpty()) {
            return;
        }
        if (position1 == 0) {
            payload1 = factoryPackages.remove();
        }
        if (factoryPackages.isEmpty()) {
            return;
        }
        if (position2 == 0) {
            payload2 = factoryPackages.remove();
        }
    }

    private void move() {
        if (payload1.equals("")) {
            position1--;
        } else {
            position1++;
        }
        if (payload2.equals("")) {
            position2--;
        } else {
            position2++;
        }
    }

    private boolean done() {
        return factoryPackages.isEmpty()
            && payload1.equals("")
            && payload2.equals("");
    }
}
