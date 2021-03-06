import groovy.transform.BaseScript
import com.electriccloud.commander.dsl.util.BasePlugin

//noinspection GroovyUnusedAssignment
@BaseScript BasePlugin baseScript

// Variables available for use in DSL code
def pluginName = args.pluginName
def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/server/settings/pluginsDirectory").value + "/" + pluginName

project pluginName, {
	property 'ec_icon', value: 'images/icon-plugin.svg'

	description = 'ElectricFlow integration with DaticalDB.'
	ec_visibility = 'pickListOnly'

	property 'postp', {
		property 'deploy', value: new File(pluginDir + "/dsl/properties/postp/deploy.pl").text
		property 'forecast', value: new File(pluginDir + "/dsl/properties/postp/forecast.pl").text
	}
	property 'Datical', {
		property 'installationDir', value: 'C:\\apps\\DaticalDB'
		property 'daticalProjectName', value: 'ECloudIntegrationDemo'
		property 'resource', value: 'daticalWindows'
	}

	loadProcedures(pluginDir, pluginKey, pluginName)
}
