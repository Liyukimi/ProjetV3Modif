/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gtm.formation.proxibanque.service.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author adminl
 */
@ApplicationPath("/web")
public class WebserviceConfig extends Application 
{

    @Override
    public Map<String, Object> getProperties() 
    {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "fr.gtm.formation.proxibanque.service.webservice");
        return properties;
    }
    
}
