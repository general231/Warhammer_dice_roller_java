package StatisticsEngine;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatisticsStorageObject {
    public DescriptiveStatistics numberOfShots;
    public DescriptiveStatistics numberOfHits;
    public DescriptiveStatistics numberOfWounds;
    public DescriptiveStatistics numberOfSaves;
    public DescriptiveStatistics numberOfModelsLost;
    public DescriptiveStatistics numberOfDamageRecieved;

    public StatisticsStorageObject()
    {
        numberOfHits = new DescriptiveStatistics();
        numberOfShots = new DescriptiveStatistics();
        numberOfWounds = new DescriptiveStatistics();
        numberOfSaves = new DescriptiveStatistics();
        numberOfModelsLost = new DescriptiveStatistics();
        numberOfDamageRecieved = new DescriptiveStatistics();
    }
}
