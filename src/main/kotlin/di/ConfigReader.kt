package di

import kotlinx.serialization.json.Json
import java.io.File

class ConfigReader(path: String) {
    private val configFile = File(path)
    private val json = Json { prettyPrint = true }

    val config: Config = json.decodeFromString(configFile.readText())
}
