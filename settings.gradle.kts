pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Cat Fact"

include(":app")
include(":data")
include(":data:repository")
include(":data:database")
include(":data:network")
include(":data:service")
include(":domain")
