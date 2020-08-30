plugins {
    id(Plugins.android_application)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_android_extensions)
}

android {
    compileSdkVersion(AndroidBuild.compileSdkVersion)
    defaultConfig {
        applicationId = "io.hvam.android.morsedetectorsample"
        minSdkVersion(AndroidBuild.minSdkVersion)
        targetSdkVersion(AndroidBuild.targetSdkVersion)
        versionCode = versionCode()
        versionName = versionName()
        testInstrumentationRunner = Dependencies.androidx.test.junit_runner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":library"))
    implementation(Dependencies.kotlin.stdlib_jdk8)
    implementation(Dependencies.androidx.ktx)
    implementation(Dependencies.androidx.appcompat)
    implementation(Dependencies.androidx.constraintlayout)
    implementation(Dependencies.google.material)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlin.junit)
    androidTestImplementation(Dependencies.androidx.test.junit)
    androidTestImplementation(Dependencies.androidx.test.runner)
    androidTestImplementation(Dependencies.androidx.test.espresso.core)
}
