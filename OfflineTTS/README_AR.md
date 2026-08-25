# Offline TTS Project - دليل الاستخدام

## نظرة عامة على المشروع
هذا المشروع هو تطبيق Android بسيط للـ Text-to-Speech (TTS) يعمل **بدون إنترنت** ويدعم ثلاث لغات:
- الإنجليزية (English)
- العربية (Arabic)
- الفرنسية (French)

## هيكلية المشروع
```
OfflineTTS/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/offlinetts/
│   │   │   └── MainActivity.kt          # كود كوتلين الرئيسي
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml    # تصميم الواجهة
│   │   │   └── values/
│   │   │       └── strings.xml          # النصوص واللغات
│   │   └── AndroidManifest.xml          # ملف الإعدادات
│   ├── build.gradle.kts                 # إعدادات التطبيق
│   └── proguard-rules.pro
├── build.gradle.kts                     # إعدادات المشروع الرئيسية
├── settings.gradle.kts                  # إعدادات المشروع
└── gradle.properties                    # خصائص Gradle
```

## كيفية بناء وتشغيل المشروع على VSCode

### الخطوة 1: تثبيت المتطلبات الأساسية
قبل البدء، تأكد من تثبيت:
1. **Java Development Kit (JDK) 17 أو أحدث**
   - حمل من: https://adoptium.net/
   
2. **Android SDK**
   - حمل Android Studio للحصول على SDK أو حمّله منفصلاً
   
3. **VSCode مع الإضافات التالية:**
   - Extension Pack for Java (من Microsoft)
   - Kotlin Language (من JetBrains)
   - Android IDE (اختياري)

### الخطوة 2: إعداد متغيرات البيئة
أضف المتغيرات التالية لنظامك:

**على Windows:**
```cmd
set ANDROID_HOME=C:\Users\YourName\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
```

**على Linux/Mac:**
```bash
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

### الخطوة 3: فتح المشروع في VSCode
1. افتح VSCode
2. اختر `File > Open Folder`
3. اختر مجلد `OfflineTTS`

### الخطوة 4: بناء المشروع
افتح Terminal في VSCode (`Ctrl+`` `) واكتب:

```bash
cd /workspace/OfflineTTS
./gradlew assembleDebug
```

**على Windows:**
```cmd
gradlew.bat assembleDebug
```

### الخطوة 5: تشغيل المشروع على جهاز حقيقي
1. فعّل **Developer Options** و **USB Debugging** على هاتفك
2. وصّل الهاتف بالكمبيوتر عبر USB
3. في Terminal اكتب:
```bash
adb devices
```
للتأكد من اتصال الجهاز

4. لتثبيت التطبيق:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### الخطوة 6: إنشاء APK للإصدار النهائي
لإنشاء نسخة Release جاهزة للنشر:

```bash
./gradlew assembleRelease
```

ستجد ملف APK في:
```
app/build/outputs/apk/release/app-release.apk
```

## ملاحظات هامة

### 1. أصوات اللغات
التطبيق يستخدم أصوات TTS المثبتة على جهاز المستخدم. لضمان عمل جميع اللغات:
- **الإنجليزية**: مثبتة افتراضياً على معظم الأجهزة
- **العربية**: قد يحتاج المستخدم لتحميل صوت العربية من إعدادات الهاتف
- **الفرنسية**: قد يحتاج المستخدم لتحميل صوت الفرنسية

### 2. كيفية تحميل الأصوات على Android
أخبر المستخدمين بالتالي:
1. الذهاب إلى `Settings > Accessibility > Text-to-speech output`
2. الضغط على أيقونة الترس ⚙️ بجانب المحرك
3. تحميل اللغات المطلوبة (عربي، فرنسي)

### 3. استكشاف الأخطاء
إذا لم يعمل الصوت:
- تأكد من أن صوت الجهاز ليس مكتوماً
- تحقق من أن اللغة مدعومة على الجهاز
- أعد تشغيل التطبيق

## تعديل المشروع

### إضافة لغة جديدة
1. افتح `app/src/main/res/values/strings.xml`
2. أضف اللغة الجديدة في `languages_array`
3. عدّل `MainActivity.kt` وأضف الحالة الجديدة في دالة `speak()`

مثال لإضافة الإسبانية:
```kotlin
"Spanish" -> Locale.SPAIN
```

## التراخيص
هذا المشروع مفتوح المصدر للاستخدام الشخصي والتعليمي.

---
**تم الإنشاء بواسطة مساعد الذكاء الاصطناعي**
