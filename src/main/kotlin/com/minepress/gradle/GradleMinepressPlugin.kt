package com.minepress.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleMinepressPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.tasks.register("minepress"){
        it.doLast {
            // Use ProcessBuilder to run the test command
            val processBuilder = ProcessBuilder()
            processBuilder.command("npx", "@minepress/minepress")
            processBuilder.directory(project.rootDir)
            processBuilder.start()
        }
    }
    project.tasks.register("init") {
      it.doLast {
        val minepressDir = project.rootDir.resolve("minepress")
        minepressDir.mkdirs()
        val configFile = minepressDir.resolve("config.json")
        configFile.writeText(
            """
                    {
                      "server": {
                        "host": "localhost",
                        "post": 25565
                      },
                      "username": "MinepressBot"
                    }
                """.trimIndent()
        )
        minepressDir
            .resolve("example.test.js")
            .writeText(
                """
                    describe("Example test", () => {
                      it("/give adds a item to your inventory", () => {
                        mi.sendCommand("give @p minecraft:stone")
                        mi.expect("hand.item.type").toBe(1);
                      });
                      afterEach(() => {
                        mi.sendCommand("clear @p");
                      });
                    })
                """.trimIndent()
            )
        println("Created minepress folder and config.json file in ${minepressDir.absolutePath}")
      }
    }
  }
}
