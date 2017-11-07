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
			if (ConfigurationType.LOGGING.name().equals(conf.getType())){
				configurationDTO.setLoggingSource(conf.getValue());
			} else if(ConfigurationType.AUTHORIZE.name().equals(conf.getType())) {
				configurationDTO.setAuthorizeSource(conf.getValue());
			}
		}
    	return configurationDTO;
    }
    
	public void saveConfiguration(ConfigurationDTO conf) throws Exception {
    	Configuration loggingConfig = this.configurationEJB.getByKeyType(ConfigurationType.LOGGING);
    	if (loggingConfig != null) {
    		loggingConfig.setValue(conf.getLoggingSource());
    		this.configurationEJB.update(loggingConfig);
    	} else {
    		loggingConfig = new Configuration();
    		loggingConfig.setType(ConfigurationType.LOGGING.name());
    		loggingConfig.setValue(conf.getLoggingSource());
    		this.configurationEJB.save(loggingConfig);
    	}
    	
    	Configuration authorizeConfig = this.configurationEJB.getByKeyType(ConfigurationType.AUTHORIZE);
    	if (authorizeConfig != null) {
    		authorizeConfig.setValue(conf.getAuthorizeSource());
    		this.configurationEJB.update(authorizeConfig);
    	} else {
    		authorizeConfig = new Configuration();
    		authorizeConfig.setType(ConfigurationType.AUTHORIZE.name());
    		authorizeConfig.setValue(conf.getAuthorizeSource());
    		this.configurationEJB.save(authorizeConfig);
    	}
    	
	}
}
