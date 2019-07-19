package util;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class SystemUtil {
    public static OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static long getSystemTotoleMb() {
       return  mem.getTotalPhysicalMemorySize() / 1024 / 1024;
    }

    public static long getSystemAvailableMb() {
        return mem.getFreePhysicalMemorySize() / 1024 / 1024;
    }

    public static long getUseMemory(Runnable runnable) {
        long start=getSystemAvailableMb();
        runnable.run();
        return start-getSystemAvailableMb();
    }
}
