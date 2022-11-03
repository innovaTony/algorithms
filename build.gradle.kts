import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
    jacoco
}

group = "com.github.innovatony"
version = "v0.0.0"
val versionPH = version
val artifactIdPH = rootProject.name

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    extensions.configure(JacocoTaskExtension::class) {
        file("$buildDir/jacoco/jacoco.exec")
    }

    finalizedBy("jacocoTestReport")
}
tasks.jacocoTestReport {
    reports {
        html.isEnabled = true
        xml.isEnabled = false
        csv.isEnabled = false

    }

    finalizedBy("jacocoTestCoverageVerification")
}
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }

        rule {
            enabled = true

            element = "CLASS"
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.80".toBigDecimal()
            }
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "200".toBigDecimal()
            }

            excludes = listOf(
                // Insert what you want to exclude here
            )
        }
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage + generates report"

    dependsOn(
        ":test",
        ":jacocoTestReport",
        ":jacocoTestCoverageVerification"
    )

    tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
    tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = artifactIdPH
            version = versionPH.toString()
            from(components["java"])

            pom {
                name.set("Algorithms")
                description.set("A tank of solved & tested algorithms to be reused in softwares")
                url.set("https://github.com/innovaTony/algorithms")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/innovaTony/algorithms/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("innovatony")
                        name.set("innovaTony")
                        email.set("antoinenaoun@gmail.com")
                    }
                }
            }
        }


    }

}