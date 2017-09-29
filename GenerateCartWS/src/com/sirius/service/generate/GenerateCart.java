package com.sirius.service.generate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.WeeklyIterator;

import com.sirius.locationws.location.wsdl.LocationBean;
import com.sirius.locationws.location.wsdl.LocationProxy;
import com.sirius.service.generate.database.GenerateDAO;

public class GenerateCart {
    private final Scheduler scheduler = new Scheduler();
//    private final SimpleDateFormat dateFormat =
//        new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
    private final int hourOfDay, minute, second;
    private final Date date;
 
    public GenerateCart(int hourOfDay, int minute, int second) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DATE, 29);
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.HOUR,hourOfDay);
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.SECOND,second);
        date = cal.getTime();
    }
 
    public void start() {
        scheduler.schedule(new SchedulerTask() {
            public void run() {
                generate();
            }
            private void generate() {
            	LocationProxy lpObj = new LocationProxy();
            	List<LocationBean> locations = lpObj.getLocations();
            	
            	for(LocationBean location : locations){
            		
            	}
            }
        }, new WeeklyIterator(hourOfDay, minute, second, date));
    }
}
