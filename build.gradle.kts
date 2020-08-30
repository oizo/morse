buildscript {
    repositories {
        addRepos(this)
    }
    dependencies {
        classpath(Dependencies.android_gradle_plugin)
        classpath(Dependencies.kotlin.gradle_plugin)
    }
}

allprojects {
    repositories {
        addRepos(this)
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}