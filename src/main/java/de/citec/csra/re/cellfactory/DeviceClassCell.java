/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.cellfactory;

import de.citec.csra.dm.remote.DeviceRegistryRemote;
import de.citec.csra.lm.remote.LocationRegistryRemote;
import de.citec.csra.re.struct.node.DeviceClassContainer;
import de.citec.csra.re.struct.node.Node;
import de.citec.jul.exception.CouldNotPerformException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import rst.homeautomation.device.DeviceClassType.DeviceClass;

/**
 *
 * @author thuxohl
 */
public class DeviceClassCell extends ValueCell {

    public DeviceClassCell(DeviceRegistryRemote deviceRegistryRemote, LocationRegistryRemote locationRegistryRemote) {
        super(deviceRegistryRemote, locationRegistryRemote);

        applyButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DeviceClassContainer container = (DeviceClassContainer) getItem();
                DeviceClass deviceClass = container.getBuilder().build();
                try {
                    if (deviceRegistryRemote.containsDeviceClass(deviceClass)) {
                        deviceRegistryRemote.updateDeviceClass(deviceClass);
                    } else {
                        deviceRegistryRemote.registerDeviceClass(deviceClass);
                        container.setNewNode(false);
                    }
                    container.setChanged(false);
                } catch (CouldNotPerformException ex) {
                    logger.warn("Could not register or update device class [" + deviceClass + "]", ex);
                }
            }
        });
    }

    @Override
    public void updateItem(Node item, boolean empty) {
        super.updateItem(item, empty);

        if (item instanceof DeviceClassContainer) {
            setGraphic(applyButton);
            if (item.getDescriptor().equals("")) {
                applyButton.setVisible(true);
            }
            ((DeviceClassContainer) item).getChanged().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    System.out.println("CHANGED! From [" + oldValue + "] to [" + newValue + "]");
                    applyButton.setVisible(newValue);
                }
            });
        }
    }
}
