import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("native.cocoapods") version "1.6.10"
    id("com.android.library")
}

group = "com.vitra"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    ios()
    android()

    cocoapods {
        ios.deploymentTarget = "14"

        framework {
            summary = "Some description for a Kotlin/Native module"
            homepage = "https://vitracash.com"
            baseName = "libphonenumber"
            isStatic = false
            transitiveExport = true
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE

        pod("libPhoneNumber-iOS") {
            version = "~> 0.8"
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.googlecode.libphonenumber:libphonenumber:8.12.41")
            }
        }
    }
}

android {
    compileSdk = 31

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
