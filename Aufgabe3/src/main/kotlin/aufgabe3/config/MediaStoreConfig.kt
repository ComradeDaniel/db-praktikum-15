package aufgabe3.config

import aufgabe3.api.MediaStoreApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Path
import java.util.Properties

@Configuration
class MediaStoreConfig {
    @Value("\${mediastore.properties-file:db/db.properties}")
    lateinit var propertiesFile: String

    @Bean(destroyMethod = "finish")
    fun mediaStore(): MediaStoreApi {
        val path = Path.of(propertiesFile)
        if (!Files.isRegularFile(path)) {
            throw IllegalStateException(
                "Property-Datei nicht gefunden: ${path.toAbsolutePath()} " +
                    "(working directory sollte die Projekt-Root sein)"
            )
        }

        val properties = Properties()
        Files.newInputStream(path).use { properties.load(it) }

        val implClass = properties.getProperty("db.impl")
            ?: throw IllegalStateException("db.impl fehlt in $path")

        val api = Class.forName(implClass)
            .getDeclaredConstructor()
            .newInstance() as MediaStoreApi
        api.init(properties)
        return api
    }
}
