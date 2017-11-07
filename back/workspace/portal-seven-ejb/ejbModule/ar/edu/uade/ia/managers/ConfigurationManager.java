package ar.edu.uade.ia.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.ConfigurationDTO;
import ar.edu.uade.ia.common.enums.ConfigurationType;
import ar.edu.uade.ia.ejbs.ConfigurationEJB;
import ar.edu.uade.ia.entities.Configuration;

/**
 * Session Bean implementation class Configuration
 */
@Stateless
@LocalBean
public class ConfigurationManager {

	@EJB
	private ConfigurationEJB configurationEJB;
	
    /**
     * Default constructor. 
     */
    public ConfigurationManager() {}

    public ConfigurationDTO getConfiguration() throws Exception {
    	List<Configuration> configs = this.configurationEJB.getAll();
    	ConfigurationDTO configurationDTO = new ConfigurationDTO();
    	for (Configuration conf : configs) {
			if (ConfigurationType.BACK_OFFICE_SRC.name().equals(conf.getType())){
				configurationDTO.setBackOffice(conf.getValue());
			}
		}
    	return configurationDTO;
    }
    
	public void saveConfiguration(ConfigurationDTO conf) throws Exception {
    	Configuration config = this.configurationEJB.getByKeyType(ConfigurationType.BACK_OFFICE_SRC);
    	if (config != null) {
    		config.setValue(conf.getBackOffice());
    		this.configurationEJB.update(config);
    	} else {
    		config = new Configuration();
    		config.setType(ConfigurationType.BACK_OFFICE_SRC.name());
    		config.setValue(conf.getBackOffice());
    		this.configurationEJB.save(config);
    	}
	}
}
