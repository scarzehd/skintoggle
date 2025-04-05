package com.scarzehd.skintoggle.timer;

import java.util.function.Function;
import java.util.function.Supplier;

public class Timer {
    public final Function<Timer, Boolean> callback;

    public int ticksLeft;

    public boolean ticking = true;

    // ticks is the amount of time to run in ticks, after which callback is called.
    public Timer(int ticks, Function<Timer, Boolean> callback) {
        this.callback = callback;
        ticksLeft = ticks;
        TimerManager.registerTimer(this);
    }
}
