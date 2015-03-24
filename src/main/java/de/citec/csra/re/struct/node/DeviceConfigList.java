/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.struct.node;

import rst.homeautomation.device.DeviceRegistryType;

/**
 *
 * @author thuxohl
 */
public class DeviceConfigList extends NodeContainer<DeviceRegistryType.DeviceRegistry.Builder> {

    public DeviceConfigList(final DeviceRegistryType.DeviceRegistry.Builder deviceRegistry) {
        super("Device Configurations", deviceRegistry);
        deviceRegistry.getDeviceConfigsBuilderList().stream().forEach((deviceConfigBuilder) -> {
            super.add(new DeviceConfigContainer(deviceConfigBuilder));
        });
    }
}
