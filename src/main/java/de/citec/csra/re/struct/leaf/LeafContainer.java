/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.struct.leaf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.ProtocolMessageEnum;
import de.citec.csra.re.struct.node.Node;
import de.citec.csra.re.struct.node.NodeContainer;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import rst.homeautomation.unit.UnitTypeHolderType;
import rst.homeautomation.unit.UnitTypeHolderType.UnitTypeHolder.UnitType;

/**
 *
 * @author thuxohl
 * @param <T>
 */
public class LeafContainer<T> implements Leaf<T> {

    private final Property<T> value;
    private final Property<String> descriptor;
    private final NodeContainer parent;
    private final int index;

    public LeafContainer(T value, String descriptor, NodeContainer parent) {
        this.value = new SimpleObjectProperty<>(value);
        this.descriptor = new ReadOnlyObjectWrapper<>(descriptor);
        this.parent = parent;
        this.index = -1;
    }

    public LeafContainer(T value, String descriptor, NodeContainer parent, int index) {
        this.value = new SimpleObjectProperty<>(value);
        this.descriptor = new ReadOnlyObjectWrapper<>(descriptor);
        this.parent = parent;
        this.index = index;
    }

    @Override
    public T getValue() {
        return value.getValue();
    }

    @Override
    public String getDescriptor() {
        return descriptor.getValue();
    }

    @Override
    public void setValue(T value) {
        this.value.setValue(value);

        Descriptors.FieldDescriptor field = parent.getBuilder().getDescriptorForType().findFieldByName(descriptor.getValue());
//        System.out.println("field:fullname" + field.getFullName());
//        System.out.println("field:name" + field.getName());
//        System.out.println("field:type" + field.getType());
//        System.out.println("field:Jvatype" + field.getJavaType());
//        System.out.println("getValue():" + getValue());
//        System.out.println("getValue().Class:" + getValue().getClass());

        if (value instanceof ProtocolMessageEnum) {
            if (index == -1) {
                parent.getBuilder().setField(field, ((ProtocolMessageEnum) getValue()).getValueDescriptor());
            } else if(value instanceof UnitType) {
                parent.getBuilder().setRepeatedField(field, index, UnitTypeHolderType.UnitTypeHolder.newBuilder().setUnitType(((UnitType) value)).build());
            }
        } else if (value instanceof String) {
            parent.getBuilder().setField(field, value);
        } else if (value instanceof Float) {
            parent.getBuilder().setField(field, value);
        } else if (value instanceof GeneratedMessage) {
            parent.getBuilder().setField(field, value);
        }

//        activateApplyButton();
    }

//    @Override
    public Node getThis() {
        return this;
    }

    private void activateApplyButton() {
//        int i = 0;
//        if( parent instanceof SendableNode ) {
//            System.out.println("Parent is sendable");
//            ((SendableNode) parent).getApplyButton().setVisible(true);
//        } else {
//            NodeContainer node = (NodeContainer) parent.getParent().getValue();
//            while( !(node instanceof SendableNode)  ) {
//                System.out.println("Iterarot ["+i+"] is no sendable");
//                node = (NodeContainer) parent.getParent().getValue();
//                i++;
//                if( i > 10 ) {
//                    break;
//                }
//                Thread.yield();
//            }
//            ((SendableNode) node).getApplyButton().setVisible(true);
//        }
    }
}
