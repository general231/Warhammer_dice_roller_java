//Copyright 2020 J Marks
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.

package GUI;

import StatisticsEngine.StatisticsStorageObject;
import javafx.scene.control.TabPane;

public class StatisticsTabPane extends TabPane {
    private GraphTab deadModelsTab;
    private GraphTab damageRecievedTab;
    private StatisticsTab statisticsTab;

    public StatisticsTabPane()
    {
        deadModelsTab = new GraphTab("Dead Models");
        damageRecievedTab = new GraphTab("Damage Dealt");
        statisticsTab = new StatisticsTab("Statistics");
        this.getTabs().add(damageRecievedTab);
        this.getTabs().add(deadModelsTab);
        this.getTabs().add(statisticsTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }


    public void update(StatisticsStorageObject newStats)
    {
        statisticsTab.updateDisplay(newStats);
        deadModelsTab.updateDisplay(newStats.numberOfModelsLost);
        damageRecievedTab.updateDisplay(newStats.numberOfDamageRecieved);
    }
}
