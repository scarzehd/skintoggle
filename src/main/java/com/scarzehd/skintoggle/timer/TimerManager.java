package com.scarzehd.skintoggle.timer;

import com.scarzehd.skintoggle.SkinToggle;

import java.util.ArrayList;
import java.util.List;

public class TimerManager {
    private static final List<Timer> timers = new ArrayList<>() ;

    static void registerTimer(Timer timer) {
        timers.add(timer);
    }

    public static void tick() {
        ArrayList<Timer> expiredTimers = new ArrayList<>();

        for (Timer timer : timers) {
            if (!timer.ticking) continue;

            timer.ticksLeft--;

            if (timer.ticksLeft <= 0) {
                Boolean persist = timer.callback.apply(timer);
                if (!persist) {
                    expiredTimers.add(timer);
                }
            }
        }

        timers.removeAll(expiredTimers);
    }
}
