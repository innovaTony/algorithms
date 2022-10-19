import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
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