package ru.nsu.alowator.timer;

import java.util.Date;

public class RealTimer implements Timer {
    @Override
    public Date getCurrentDateTime() {
        return new Date();
    }
}
