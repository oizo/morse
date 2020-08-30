import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

object AndroidBuild {
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val minSdkVersion = 15
}

const val versionMajor = 0
const val versionMinor = 1
const val versionPatch = 0
const val versionBuild = 0

fun versionCode(): Int = (versionMajor * 10000000) + (versionMinor * 100000) + (versionPatch * 1000) + versionBuild
fun versionName(): String = "$versionMajor.$versionMinor.$versionPatch-$versionBuild"

object Versions {
    const val kotlin = "1.4.0"
    const val android_gradle_plugin = "4.0.1"
    const val junit = "4.12"
    const val mockito = "3.4.6"
    const val mockitokotlin2 = "2.2.0"
    const val atsl = "1.3.0-alpha04"
    const val espresso = "3.2.0"
    const val appcompat = "1.2.0"
    const val ktx = "1.2.0"
    const val constraintlayout = "2.0.0"
    const val timber = "4.7.1"
    const val ktlint = "9.3.0"
    const val google_material = "1.3.0-alpha02"
    const val markhor = "1.0.8"

}

object Plugins {
    const val android_application = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "kotlin-android"
    const val kotlin_android_extensions = "kotlin-android-extensions"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    val kotlin = KotlinPlugins
}

object KotlinPlugins {
    const val android = "org.jetbrains.kotlin.android"
}

object Dependencies {
    val kotlin = Kotlin
    val androidx = Androidx
    val google = Google
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val mockito = Mockito
    val markhor = "com.shopgun.android:utils:${Versions.markhor}"
}

object Kotlin {
    val gradle_plugin = kotlin("gradle-plugin")
    val stdlib_jdk8 = kotlin("stdlib-jdk8")
    val junit = kotlin("test-junit")
}

object Mockito {
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitokotlin2 = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitokotlin2}"
}

private fun kotlin(module: String, version: String? = Versions.kotlin): String =
        "org.jetbrains.kotlin:kotlin-$module${version?.let { ":$version" } ?: Versions.kotlin}"

object Espresso {
    const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Test {
    val espresso = Espresso
    const val junit = "androidx.test.ext:junit:1.1.1"
    const val junit_runner = "androidx.test.runner.AndroidJUnitRunner"
    const val runner = "androidx.test:runner:${Versions.atsl}"
    const val rules = "androidx.test:rules:${Versions.atsl}"
}

object Androidx {
    val test = Test
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.google_material}"
}

/**
 * Add repositories to the [RepositoryHandler]
 */
fun addRepos(repoHandler: RepositoryHandler) {
    repoHandler.mavenCentral()
    repoHandler.jcenter()
    repoHandler.google()
    repoHandler.maven("https://jitpack.io")
}
