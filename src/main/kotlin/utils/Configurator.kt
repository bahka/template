package utils

import java.io.File
import java.io.FileInputStream
import java.util.*

object EnvConfig {
    lateinit var value: String
    lateinit var token: String
    lateinit var data: String
}

object ConfigurationLoader {

    fun loadProperties() {
        val prop = readProperties("${System.getProperty("ENV_NAME", "example")}.properties")

        EnvConfig.value = prop.getProperty("domain.value")
    }

    private fun readProperties(configName: String): Properties {
        val prop = Properties()
        val file = File("src/main/resources/env_configs/$configName")
        if (!file.exists()) {
            throw RuntimeException("Env configuration doesn't exist for `$configName`")
        }
        FileInputStream(file).use { prop.load(it) }

        // Print all properties from file
        prop.stringPropertyNames()
            .associateWith { prop.getProperty(it) }
            .forEach { println(it) }
        return prop
    }
}
