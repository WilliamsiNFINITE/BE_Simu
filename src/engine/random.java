package engine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;

public class random {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Logger.load();
        LogicalDateTime start = new LogicalDateTime("04/12/2019 14:00");
        LogicalDateTime end = new LogicalDateTime("04/12/2019 15:00");


        MoreRandom moreRandom = new MoreRandom();
        moreRandom.setSeed(1);
        System.out.println(moreRandom.nextGaussian());

        for (int i = 0; i < 10000; i++) {
            Logger.Information(null, "main", String.valueOf(moreRandom.nextGaussian()));
        }

        Logger.Terminate();

    }
}
