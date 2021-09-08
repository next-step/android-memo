const val kotlinVersion = "1.5.21"
const val junit5Version = "5.7.2"

object Deps {
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.3"
    const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:10.0.0"
    const val material = "com.google.android.material:material:1.3.0"
    const val javaxInject = "javax.inject:javax.inject:1"

    const val junit = "junit:junit:4.13.2"
    const val truth = "com.google.truth:truth:1.1.2"
    const val androidxJunit = "androidx.test.ext:junit:1.1.2"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"

    object Kotlin {
        private const val version = "1.4.32"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Coroutines {
        private const val version = "1.4.3"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.3"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$version"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Moshi {
        private const val version = "1.12.0"
        const val core = "com.squareup.moshi:moshi:$version"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Hilt {
        private const val version = "2.38.1"

        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val androidTesting = "com.google.dagger:hilt-android-testing:$version"
        const val core = "com.google.dagger:hilt-core:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Glide {
        private const val version = "4.12.0"
        const val core = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }
}
