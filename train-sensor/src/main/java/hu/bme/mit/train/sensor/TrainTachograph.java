package hu.bme.mit.train.sensor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainTachograph {

    private Table datas = HashBasedTable.create();

    public TrainTachograph(TrainController controller, TrainUser user) {
        datas.put(0,0,System.currentTimeMillis());
        datas.put(0,1,user.getJoystickPosition());
        datas.put(0,2,controller.getReferenceSpeed());
    }

    public Table getDatas() {
        return datas;
    }
}
