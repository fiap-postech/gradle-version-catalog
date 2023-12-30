plugins {
    `version-catalog`
    `maven-publish`
}

group = "br.com.fiap.tech.challenge"
version = "1.0.5"

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/fiap-postech/gradle-version-catalog")
            credentials {
                username = findProperty("gprUser") as String? ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gprKey") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
