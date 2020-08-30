plugins {
    id(Plugins.android_library)
    id(Plugins.kotlin_android)
    id(Plugins.kotlin_android_extensions)
}

android {
    compileSdkVersion(AndroidBuild.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidBuild.minSdkVersion)
        targetSdkVersion(AndroidBuild.targetSdkVersion)
        testInstrumentationRunner = Dependencies.androidx.test.junit_runner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.kotlin.stdlib_jdk8)
    implementation(Dependencies.markhor)
    implementation(Dependencies.androidx.ktx)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockito.mockito)
    testImplementation(Dependencies.mockito.mockitoInline)
    testImplementation(Dependencies.mockito.mockitokotlin2)
    testImplementation(Dependencies.kotlin.junit)
    androidTestImplementation(Dependencies.androidx.test.junit)
    androidTestImplementation(Dependencies.androidx.test.runner)
    androidTestImplementation(Dependencies.androidx.test.espresso.core)
}
