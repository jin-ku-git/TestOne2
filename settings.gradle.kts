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
        maven { url=uri("https://jitpack.io")  }
        maven { url=uri("https://maven.aliyun.com/repository/google)")  }
        maven { url=uri("https://maven.aliyun.com/repository/gradle-plugin")  }
        maven { url=uri("https://maven.aliyun.com/repository/public")  }
        maven { url=uri("https://maven.aliyun.com/repository/jcenter")  }
    }
}

rootProject.name = "TestOne"
include(":app")
 