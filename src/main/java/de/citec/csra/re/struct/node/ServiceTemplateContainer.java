/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.citec.csra.re.struct.node;

import de.citec.jul.exception.ExceptionPrinter;
import rst.homeautomation.service.ServiceTemplateType.ServiceTemplate;

/**
 *
 * @author thuxohl
 */
public class ServiceTemplateContainer extends VariableNode<ServiceTemplate.Builder> {

    public ServiceTemplateContainer(ServiceTemplate.Builder serviceTemplate) {
        super("service_template", serviceTemplate);
        super.add(serviceTemplate.getServiceType(), "service_type");

        // TODO Tamino: implement global exception handling if gui elements are not able to init.
        try {
            super.add(new MetaConfigContainer(builder.getMetaConfigBuilder()));
        } catch (de.citec.jul.exception.InstantiationException ex) {
            ExceptionPrinter.printHistory(logger, ex);
        }
    }
}
