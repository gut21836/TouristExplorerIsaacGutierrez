plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
android {
    compileSdk 33

    defaultConfig {
        applicationId "com.example.touristexplorer"
        minSdk 21
        targetSdk 33
        versionCode 1
        versionName "1.0"
    }

    buildFeatures {
        compose true
    }

    composeOptions {
        kotlinCompilerExtensionVersion '1.5.0'
    }

    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation 'androidx.compose.ui:ui:1.5.0'
    implementation 'androidx.compose.material:material:1.5.0'
    implementation 'androidx.compose.ui:ui-tooling-preview:1.5.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.1'
    implementation 'androidx.activity:activity-compose:1.7.2'
    implementation 'androidx.navigation:navigation-compose:2.6.0'
    implementation 'com.google.firebase:firebase-auth:22.1.0'
    implementation 'com.google.firebase:firebase-firestore-ktx:24.7.1'
    implementation 'io.coil-kt:coil-compose:2.4.0'
}

    apply plugin: 'com.google.gms.google-services'
