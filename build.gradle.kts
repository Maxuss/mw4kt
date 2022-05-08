import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    `maven-publish`
}

group = "space.maxus"
version = "1.0-SNAPSHOT"

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

tasks {
    build {

    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}


publishing {
    repositories {
        maven("https://repo.repsy.io/mvn/maxuss/artifacts") {
            this.credentials.username = properties["REPO_USERNAME"] as String?
            this.credentials.password = properties["REPO_PASSWORD"] as String?
        }

        publications {
            create<MavenPublication>(project.name) {
                artifact(tasks.jar)

                this.groupId = project.group.toString()
                this.artifactId = project.name.toLowerCase()
                this.version = project.version.toString()

                pom {
                    name.set(project.name)
                    description.set(project.description)

                    developers {
                        developer {
                            name.set("Maxuss")
                        }
                    }

                    licenses {
                        license {
                            name.set("The MIT License (MIT)")
                            url.set("https://mit-license.org")
                        }
                    }

                    url.set("https://github.com/Maxuss")

                    scm {
                        connection.set("scm:git:git://github.com/Maxuss/mw4kt.git")
                        url.set("https://github.com/Maxuss/mw4kt/tree/master")
                    }
                }
            }
        }
    }
}