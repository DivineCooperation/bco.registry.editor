/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.struct.node;

import rst.spatial.LocationConfigType.LocationConfig;

/**
 *
 * @author thuxohl
 */
public class LocationConfigContainer extends SendableNode<LocationConfig.Builder> {

    public LocationConfigContainer(LocationConfig.Builder location) {
        super(location.getLabel(), location);
        super.add(location.getLabel(), "label");
        super.add(location.getRoot(), "root");
        super.add(location.getScope().getStringRep(), "Scope");
        super.add(new ChildLocationListContainer(location));
//        if (location.hasParent()) {
//            super.add(location.getParent().getLabel(), "Parent Label");
//        }
        //TODO: change the location type to avoid the infite loop!
    }
}
