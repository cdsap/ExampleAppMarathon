
buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }

    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.61")
        classpath("com.malinskiy.marathon:marathon-gradle-plugin:0.2.2-SNAPSHOT")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }

    }
}

