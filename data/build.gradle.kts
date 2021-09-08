plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    api(project(":domain"))

    implementation(Deps.Hilt.core)
    kapt(Deps.Hilt.compiler)
}
