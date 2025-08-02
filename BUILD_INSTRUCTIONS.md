# تعليمات بناء تطبيق عَمار

## نظرة عامة على التطبيق

تم تطوير تطبيق عَمار باستخدام Kotlin و Android SDK، ويحتوي على الميزات التالية:

### الميزات الرئيسية:
1. **القرآن الكريم** - عرض السور القرآنية مع البحث والإشارات المرجعية
2. **أذكار الصباح** - أذكار الصباح مع عداد لكل ذكر
3. **أذكار المساء** - أذكار المساء مع عداد لكل ذكر
4. **الحديث الشريف** - مجموعة من الأحاديث النبوية مع البحث والمفضلة
5. **السبحة الإلكترونية** - عداد إلكتروني للذكر مع اهتزاز وصوت
6. **المعرفة الإسلامية** - أقسام شاملة للعقيدة والفقه والسيرة والأخلاق
7. **الإعدادات** - تخصيص التطبيق والإشعارات

### التصميم:
- تصميم مستوحى من الموقع الإسلامي (el-islam.netlify.app)
- بطاقات ملونة بتدرجات جميلة
- واجهة عربية احترافية
- دعم للهواتف الضعيفة والمتوسطة

## طرق البناء

### الطريقة الأولى: باستخدام Android Studio

1. **تحميل Android Studio:**
   - قم بتحميل Android Studio من الموقع الرسمي
   - https://developer.android.com/studio

2. **فتح المشروع:**
   - افتح Android Studio
   - اختر "Open an existing project"
   - اختر مجلد المشروع

3. **بناء APK:**
   - اذهب إلى Build > Build Bundle(s) / APK(s) > Build APK(s)
   - انتظر حتى يكتمل البناء
   - ستجد APK في مجلد `app/build/outputs/apk/debug/`

### الطريقة الثانية: باستخدام Command Line

1. **تثبيت Android SDK:**
   ```bash
   # على Ubuntu/Debian
   sudo apt update
   sudo apt install android-sdk android-sdk-build-tools
   
   # على macOS
   brew install android-sdk
   
   # على Windows
   # قم بتحميل Android Studio أو Android SDK من الموقع الرسمي
   ```

2. **تعيين متغيرات البيئة:**
   ```bash
   export ANDROID_HOME=/path/to/android/sdk
   export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
   ```

3. **بناء المشروع:**
   ```bash
   cd /path/to/AmmarApp
   ./gradlew assembleDebug
   ```

### الطريقة الثالثة: باستخدام Docker

1. **إنشاء Dockerfile:**
   ```dockerfile
   FROM openjdk:11
   
   # تثبيت Android SDK
   RUN apt-get update && apt-get install -y \
       wget \
       unzip \
       && rm -rf /var/lib/apt/lists/*
   
   # تحميل Android SDK
   RUN wget https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip
   RUN unzip commandlinetools-linux-8512546_latest.zip -d /opt/android-sdk
   
   # تعيين متغيرات البيئة
   ENV ANDROID_HOME=/opt/android-sdk
   ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools
   
   # تثبيت build tools
   RUN yes | sdkmanager --licenses
   RUN sdkmanager "build-tools;33.0.0" "platforms;android-33"
   
   WORKDIR /app
   COPY . .
   
   # بناء APK
   CMD ["./gradlew", "assembleDebug"]
   ```

2. **بناء Docker image:**
   ```bash
   docker build -t ammar-app .
   docker run -v $(pwd):/app ammar-app
   ```

## متطلبات النظام

### للبناء:
- Java 11 أو أحدث
- Android SDK Build Tools 33.0.0 أو أحدث
- Android SDK Platform 33 أو أحدث
- Gradle 8.0 أو أحدث

### للتشغيل:
- Android 6.0 (API level 23) أو أحدث
- ذاكرة RAM: 2GB على الأقل
- مساحة تخزين: 100MB متاحة

## هيكل المشروع

```
AmmarApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/ammarapp/
│   │   │   ├── MainActivity.kt
│   │   │   ├── fragments/
│   │   │   ├── adapters/
│   │   │   └── models/
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   └── font/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle/
├── build.gradle
├── settings.gradle
└── gradlew
```

## حل المشاكل الشائعة

### مشكلة: Gradle wrapper not found
**الحل:**
```bash
gradle wrapper
```

### مشكلة: Android SDK not found
**الحل:**
```bash
export ANDROID_HOME=/path/to/android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### مشكلة: Build tools version mismatch
**الحل:**
```bash
sdkmanager "build-tools;33.0.0"
```

### مشكلة: Memory insufficient
**الحل:**
```bash
export GRADLE_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
```

## معلومات إضافية

### الألوان المستخدمة:
- الأزرق: #74C3F5 إلى #7398F5
- البنفسجي: #AB74F5 إلى #737CF5
- البرتقالي: #F59173 إلى #EC73F5
- الأخضر: #038d51 إلى #0091a0
- الرمادي: #6f6767 إلى #4f3c05

### الخطوط المستخدمة:
- Cairo Regular
- Cairo Medium
- Cairo SemiBold
- Cairo Bold

### المكتبات المستخدمة:
- Material Design 3
- AndroidX
- ViewBinding
- RecyclerView
- Navigation Component

## الدعم والمساعدة

إذا واجهت أي مشاكل في البناء، يمكنك:

1. التحقق من إصدارات الأدوات المستخدمة
2. التأكد من تثبيت جميع المتطلبات
3. مراجعة سجلات البناء للحصول على رسائل الخطأ
4. التأكد من صحة هيكل المشروع

## الترخيص

هذا المشروع مفتوح المصدر ومتاح للاستخدام الشخصي والتعليمي.