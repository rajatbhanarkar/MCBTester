package com.mcb.mcbtester;

import java.util.ArrayList;

public class MCBTestDetails {
    class MCBTest{
        String phase, test, status;
        int current;

        public MCBTest(String phase, String test, String status, int current) {
            this.phase = phase;
            this.test = test;
            this.status = status;
            this.current = current;
        }
    }

    ArrayList<MCBTest> allTests;

    public MCBTestDetails(ArrayList<MCBTest> allTests) {
        this.allTests = allTests;
    }
}
