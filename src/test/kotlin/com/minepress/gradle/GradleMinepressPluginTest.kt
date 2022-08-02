package com.minepress.gradle

import kotlin.test.Test
import kotlin.test.assertNotNull
import org.gradle.testfixtures.ProjectBuilder

class GradleMinepressPluginTest {
  fun check_task(name: String) {
    val project = ProjectBuilder.builder().build()
    project.plugins.apply("com.minepress")
    val task = project.tasks.findByName(name)
    assertNotNull(task)
  }
  @Test
  fun `plugin registers init task`() {
    check_task("init")
  }
  @Test
  fun `plugin registers minepress task`() {
      check_task("minepress")
  }

}
