/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.cellfactory;

import de.citec.csra.dm.remote.DeviceRegistryRemote;
import de.citec.csra.re.struct.node.Node;
import de.citec.csra.re.struct.node.VariableNode;

/**
 *
 * @author thuxohl
 */
public class DescriptionCell extends RowCell {

    public DescriptionCell(DeviceRegistryRemote remote) {
        super(remote);
    }

    @Override
    public void updateItem(Node item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            graphicProperty().setValue(null);
            textProperty().setValue("");
            setContextMenu(null);
        } else if (item instanceof Node) {
            graphicProperty().setValue(null);
            textProperty().setValue(convertDescriptorT(item.getDescriptor()));
            if (!(item instanceof VariableNode)) {
                setContextMenu(null);
            }
        }
    }
    
    private String convertDescriptorT(String descriptor) {
        if(descriptor.equals("")) {
            return descriptor;
        }
        
        String result = "";
        String[] split = descriptor.split("_");
        for(int i = 0; i < split.length; i++) {
            result += Character.toUpperCase(split[i].charAt(0));
            result += split[i].substring(1);
            result += " ";
        }
        return result;
    }
}
