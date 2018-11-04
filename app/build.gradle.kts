import com.malinskiy.marathon.execution.AnnotationFilter
import org.gradle.internal.impldep.org.joda.time.Instant

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("marathon")
    id("kotlin-android-extensions")
}


android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.kaptwithannotationlevel.myapplication"
        minSdkVersion(22)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.61")
    implementation("androidx.appcompat:appcompat:1.0.0-alpha1")
    implementation("androidx.constraintlayout:constraintlayout:1.1.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0-alpha1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0-alpha1")
}

marathon {
    name = "test"
    ignoreFailures = true
    fallbackToScreenshots = false
    analytics {
        influx {
            url = "https://influxdb.default.svc.agoda.mobi"
            user = ""
            password = ""
            dbName = "marathonTest"
        }
    }
    batchingStrategy {
        fixedSize {
            size = 10
        }
    }
    flakinessStrategy {
        probabilityBased {
            minSuccessRate = 0.8
            maxCount = 3
            //  timeLimit = Instant.now().minus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS)
        }
    }

    sortingStrategy {
        executionTime {
            percentile = 90.0
            // timeLimit = Instant.now().minus(30, ChronoUnit.DAYS)
        }
    }

    filteringConfiguration {
        whitelist {
            add(AnnotationFilter("test" .toRegex()))

            //   annotationFilter = includedAnnotationArgument()
        }
        blacklist {
            //     annotationFilter = excludedAnnotationArgument()
        }
    }
}


//    includeSerialRegexes = []
//    excludeSerialRegexes = []
//
//    autoGrantPermission = true
//    testOutputTimeoutMillis = testTimeout()
//    debug = true
//}
//
//ext {
//    listingsGenerator = listingsGeneratorExecution()
//}
//
//enum TestMode {
//    PR_TESTS, E2E, ALL, SCREENSHOTS, CHINA, SMARTLOCK, FLAKY, MONKEY
//}
//
//def testTimeout() {
//    final def timeout
//    switch (parseMode()) {
//        case TestMode.E2E:
//            timeout = 300000
//            break
//        default: timeout = 120000
//    }
//    return timeout
//}
//
//def parseMode() {
//    final def testMode
//    if (project.hasProperty("uiTestConfig")) {
//        switch (project.property("uiTestConfig")) {
//            case "prTests":
//                testMode = TestMode.PR_TESTS
//                break
//            case "e2eTests":
//                testMode = TestMode.E2E
//                break
//            case "screenshots":
//                testMode = TestMode.SCREENSHOTS
//                break
//            case "china":
//                testMode = TestMode.CHINA
//                break
//            case "smartlock":
//                testMode = TestMode.SMARTLOCK
//                break
//            case "flaky":
//                testMode = TestMode.FLAKY
//                break
//            default: testMode = TestMode.ALL
//        }
//    } else {
//        testMode = TestMode.ALL
//    }
//    return testMode
//}
//
//def includedAnnotationArgument() {
//
//    return []
//}
//
//def excludedAnnotationArgument() {
//    final def annotations = []
//
//    return annotations
//}
//
//def listingsGeneratorExecution() {
//    def testExecution = false
//    if (project.hasProperty("listingsGenerator") && project.listingsGenerator) {
//        testExecution = project.listingsGenerator
//    }
//    return testExecution
//}
