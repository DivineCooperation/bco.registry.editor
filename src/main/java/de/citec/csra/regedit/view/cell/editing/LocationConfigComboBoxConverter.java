/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.regedit.view.cell.editing;

import com.google.protobuf.Message;
import de.citec.csra.regedit.util.FieldDescriptorUtil;
import org.dc.jul.extension.rsb.scope.ScopeGenerator;
import rst.rsb.ScopeType.Scope;
import rst.spatial.LocationConfigType.LocationConfig;

/**
 *
 * @author <a href="mailto:thuxohl@techfak.uni-bielefeld.com">Tamino Huxohl</a>
 */
public class LocationConfigComboBoxConverter implements MessageComboBoxConverterInterface {

    @Override
    public String getText(Message msg) {
        return ScopeGenerator.generateStringRep((Scope) msg.getField(FieldDescriptorUtil.getFieldDescriptor(LocationConfig.SCOPE_FIELD_NUMBER, LocationConfig.getDefaultInstance())));
    }

    @Override
    public String getValue(Message msg) {
        return (String) msg.getField(FieldDescriptorUtil.getFieldDescriptor(LocationConfig.ID_FIELD_NUMBER, LocationConfig.getDefaultInstance()));
    }
}
