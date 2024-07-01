plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.awais.e_commereceed"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.awais.e_commereceed"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.navigation.fragment)
    implementation(libs.firebase.firestore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation ("com.google.android.gms:play-services-auth:19.2.0")


    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    // Import the BoM for Firebase


    // Add the dependency for the Firebase Storage library
    implementation ("com.google.firebase:firebase-storage")

    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    implementation ("com.google.android.gms:play-services-location:21.2.0")

    implementation ("com.facebook.android:facebook-android-sdk:latest.release")

    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.jpardogo.googleprogressbar:library:1.2.0")
    // Picasso dependency
    implementation ("com.squareup.picasso:picasso:2.71828")



    // Other dependencies...
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    // other dependencies



    implementation ("com.google.dagger:hilt-android:2.44")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.4.1")
}