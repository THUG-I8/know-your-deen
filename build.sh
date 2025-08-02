#!/bin/bash

# Script to build Ammar Islamic App
# تطبيق عَمار الإسلامي

echo "🚀 بدء بناء تطبيق عَمار الإسلامي..."
echo "Starting build process for Ammar Islamic App..."

# Check if we're in the right directory
if [ ! -f "build.gradle" ]; then
    echo "❌ Error: build.gradle not found. Please run this script from the project root directory."
    exit 1
fi

# Check if gradlew exists and is executable
if [ ! -f "gradlew" ]; then
    echo "⚠️  gradlew not found. Attempting to create it..."
    if command -v gradle &> /dev/null; then
        gradle wrapper
    else
        echo "❌ Error: Gradle not found. Please install Gradle first."
        echo "You can install it with: sudo apt install gradle"
        exit 1
    fi
fi

# Make gradlew executable
chmod +x gradlew

# Check Java version
echo "🔍 Checking Java version..."
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$java_version" -lt 11 ]; then
    echo "❌ Error: Java 11 or higher is required. Current version: $java_version"
    echo "Please install Java 11 or higher."
    exit 1
fi
echo "✅ Java version: $(java -version 2>&1 | head -n 1)"

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Build the project
echo "🔨 Building the project..."
./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ Build completed successfully!"
    echo "📱 APK location: app/build/outputs/apk/debug/app-debug.apk"
    
    # Check if APK exists
    if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
        apk_size=$(du -h app/build/outputs/apk/debug/app-debug.apk | cut -f1)
        echo "📦 APK size: $apk_size"
        
        # Show APK info
        echo "📋 APK Information:"
        echo "   - Name: عَمار (Ammar)"
        echo "   - Version: 1.0.0"
        echo "   - Min SDK: 23 (Android 6.0)"
        echo "   - Target SDK: 33 (Android 13)"
        
        echo ""
        echo "🎉 تم بناء التطبيق بنجاح!"
        echo "Successfully built the app!"
        echo ""
        echo "📱 لتثبيت التطبيق على هاتفك:"
        echo "To install the app on your phone:"
        echo "1. انقل ملف APK إلى هاتفك"
        echo "   Transfer the APK file to your phone"
        echo "2. فعّل تثبيت التطبيقات من مصادر غير معروفة"
        echo "   Enable installation from unknown sources"
        echo "3. اضغط على ملف APK للتثبيت"
        echo "   Tap the APK file to install"
    else
        echo "❌ Error: APK file not found after build"
        exit 1
    fi
else
    echo "❌ Build failed. Please check the error messages above."
    exit 1
fi