// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Use plugin IDs with explicit versions here (replace versions as needed)
    id("com.android.application") version "8.8.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
