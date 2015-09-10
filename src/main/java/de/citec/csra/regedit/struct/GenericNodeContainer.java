/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.regedit.struct;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage;
import de.citec.csra.regedit.struct.converter.DefaultConverter;
import de.citec.csra.regedit.struct.consistency.Configuration;
import de.citec.csra.regedit.util.FieldDescriptorUtil;
import de.citec.jul.exception.CouldNotPerformException;
import de.citec.jul.exception.InstantiationException;
import de.citec.jul.exception.NotAvailableException;
import java.util.Map.Entry;

/**
 *
 * @author thuxohl
 * @param <MB>
 */
public class GenericNodeContainer<MB extends GeneratedMessage.Builder> extends NodeContainer<MB> {

    public GenericNodeContainer(String descriptor, final MB builder) throws InstantiationException {
        super(descriptor, builder);
        try {

            for (Entry<String, Object> entry : converter.getFields().entrySet()) {
                if (converter instanceof DefaultConverter) {
                    registerElement(FieldDescriptorUtil.getField(entry.getKey(), builder));
                } else {
                    registerElement(entry.getKey(), entry.getValue());
                }
            }
        } catch (InstantiationException ex) {
            throw new InstantiationException(this, ex);
        }
    }

    public GenericNodeContainer(final int fieldNumber, final MB builder) throws InstantiationException {
        this(FieldDescriptorUtil.getField(fieldNumber, builder), builder);
    }

    public GenericNodeContainer(final FieldDescriptor fieldDescriptor, final MB builder) throws InstantiationException {
        super(fieldDescriptor.getName(), builder);
        try {
            if (fieldDescriptor == null) {
                throw new NotAvailableException("fieldDescriptor");
            }

            for (Entry<String, Object> entry : converter.getFields().entrySet()) {
                if (converter instanceof DefaultConverter) {
                    registerElement(FieldDescriptorUtil.getField(entry.getKey(), builder));
                } else {
                    registerElement(entry.getKey(), entry.getValue());
                }
            }
        } catch (CouldNotPerformException ex) {
            throw new InstantiationException(this, ex);
        }
    }

    private void registerElement(FieldDescriptor field) throws InstantiationException {
        if (field.isRepeated()) {
            super.add(new GenericListContainer<>(field, builder));
        } else if (field.getType().equals(FieldDescriptor.Type.MESSAGE)) {
            super.add(new GenericNodeContainer(field, (GeneratedMessage.Builder) builder.getFieldBuilder(field)));
        } else {
            super.add(new LeafContainer(builder.getField(field), field.getName(), this, Configuration.isModifiableField(builder, field.getName())));
        }
    }

    private void registerElement(String fieldName, Object value) throws InstantiationException {
        super.add(new LeafContainer(value, fieldName, this, Configuration.isModifiableField(builder, fieldName)));
    }
}
