package io.cucumber.skeleton;

import java.util.ArrayList;

public class Calculator {
    private ArrayList<Integer> args;
    private String operator;

    public Calculator () {
        args = new ArrayList<>();
    }

    public void push(int arg) {
        args.add(arg);
    }

    public void push(String operator) {
        this.operator = operator;
    }

    public int value() {
        int value = 0;
        for (int i=0; i< args.size();i++) {
            if (operator=="+") {
                value += args.get(i);
            } else if (operator=="-") {
                if (i==0) {
                    value = args.get(i);
                }
                else {
                    value -= args.get(i);
                }
            }
        }
        return value;
    }
}
