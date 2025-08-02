package com.ammarapp.models

// نموذج الآية المحسن
data class VerseEnhanced(
    val id: Int,
    val surahId: Int,
    val verseNumber: Int,
    val arabicText: String,
    val translation: String = "",
    val tafseer: String = "",
    val pageNumber: Int,
    val juzNumber: Int,
    val hizbNumber: Int = 0,
    val rukuNumber: Int = 0,
    val isBookmarked: Boolean = false
)

// نموذج السورة المحسن
data class SurahEnhanced(
    val id: Int,
    val arabicName: String,
    val englishName: String,
    val numberOfVerses: Int,
    val revelationType: String, // مكية أو مدنية
    val orderInQuran: Int,
    val startPage: Int = 1,
    val endPage: Int = 1,
    val juzNumber: Int = 1,
    val isCompleted: Boolean = false,
    val lastReadVerse: Int = 1,
    val readingProgress: Float = 0f
)

// نموذج العلامة المرجعية
data class VerseBookmark(
    val id: Int = 0,
    val surahId: Int,
    val verseId: Int,
    val surahName: String,
    val verseNumber: Int,
    val arabicText: String,
    val pageNumber: Int,
    val title: String,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val tags: List<String> = emptyList()
)

// نموذج إعدادات القراءة
data class QuranReadingSettings(
    val fontSize: Float = 18f,
    val fontFamily: FontFamily = FontFamily.UTHMANIC,
    val lineSpacing: Float = 1.5f,
    val backgroundColor: String = "#FFFFFF",
    val textColor: String = "#000000",
    val nightMode: Boolean = false,
    val showTranslation: Boolean = false,
    val showTafseer: Boolean = false,
    val showVerseNumbers: Boolean = true,
    val showPageNumbers: Boolean = true,
    val autoScroll: Boolean = false,
    val autoScrollSpeed: Int = 3, // 1-5
    val keepScreenOn: Boolean = true,
    val enableVibration: Boolean = true,
    val highlightColor: String = "#FFE4B5"
)

// أنواع الخطوط
enum class FontFamily(val displayName: String, val fontFile: String) {
    UTHMANIC("الخط العثماني", "uthmanic_hafs.ttf"),
    NOOR("خط النور", "noor_font.ttf"),
    AMIRI("خط الأميري", "amiri.ttf"),
    CAIRO("خط القاهرة", "cairo.ttf"),
    SIMPLE("خط بسيط", "simple_arabic.ttf"),
    TRADITIONAL("الخط التقليدي", "traditional.ttf")
}

// أنماط العرض
enum class ReadingMode {
    VERSE_BY_VERSE,     // آية بآية
    PAGE_VIEW,          // عرض الصفحة
    SURAH_VIEW,         // عرض السورة كاملة
    CONTINUOUS_SCROLL   // التمرير المستمر
}

// نموذج تقدم القراءة
data class ReadingProgress(
    val currentSurahId: Int = 1,
    val currentVerseId: Int = 1,
    val currentPageNumber: Int = 1,
    val lastReadingTime: Long = System.currentTimeMillis(),
    val totalVersesRead: Int = 0,
    val totalPagesRead: Int = 0,
    val totalReadingTimeMinutes: Long = 0,
    val readingStreak: Int = 0, // عدد الأيام المتتالية
    val lastStreakDate: String = "",
    val completedSurahs: Set<Int> = emptySet(),
    val dailyGoalVerses: Int = 10,
    val dailyReadVerses: Int = 0,
    val weeklyStats: Map<String, Int> = emptyMap()
)

// نموذج إحصائيات القراءة
data class ReadingStatistics(
    val totalVersesRead: Int,
    val totalPagesRead: Int,
    val totalSurahsCompleted: Int,
    val totalReadingTimeHours: Float,
    val averageReadingPerDay: Float,
    val currentStreak: Int,
    val longestStreak: Int,
    val lastReadingDate: String,
    val completionPercentage: Float,
    val favoriteReadingTime: String, // الوقت المفضل للقراءة
    val monthlyProgress: Map<String, Int> = emptyMap()
)

// نموذج البحث في القرآن
data class QuranSearchResult(
    val surahId: Int,
    val surahName: String,
    val verseId: Int,
    val verseNumber: Int,
    val arabicText: String,
    val translation: String,
    val pageNumber: Int,
    val juzNumber: Int,
    val highlightedText: String, // النص مع التمييز
    val relevanceScore: Float = 0f
)

// نموذج الصفحة القرآنية
data class QuranPage(
    val pageNumber: Int,
    val verses: List<VerseEnhanced>,
    val surahHeaders: List<String> = emptyList(),
    val juzNumber: Int,
    val hizbNumber: Int = 0,
    val hasBasmala: Boolean = false
)

// نموذج الجزء
data class QuranJuz(
    val number: Int,
    val arabicName: String,
    val startSurahId: Int,
    val startVerseNumber: Int,
    val endSurahId: Int,
    val endVerseNumber: Int,
    val totalPages: Int,
    val completionPercentage: Float = 0f
)

// نموذج الحزب
data class QuranHizb(
    val number: Int,
    val juzNumber: Int,
    val startPageNumber: Int,
    val endPageNumber: Int,
    val isCompleted: Boolean = false
)

// نموذج التفسير
data class VerseTafseer(
    val verseId: Int,
    val tafseerText: String,
    val tafseerSource: String = "تفسير الجلالين",
    val shortExplanation: String = "",
    val keywords: List<String> = emptyList()
)

// نموذج الملاحظات الشخصية
data class PersonalNote(
    val id: Int = 0,
    val verseId: Int,
    val noteText: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isPrivate: Boolean = true,
    val tags: List<String> = emptyList()
)

// حالة التحميل
sealed class LoadingState {
    object Loading : LoadingState()
    object Success : LoadingState()
    data class Error(val message: String) : LoadingState()
    object Empty : LoadingState()
}

// نموذج إعدادات التطبيق العامة
data class AppSettings(
    val language: String = "ar",
    val enableNotifications: Boolean = true,
    val enableSounds: Boolean = true,
    val enableVibration: Boolean = true,
    val autoBackup: Boolean = false,
    val syncWithCloud: Boolean = false,
    val lastBackupDate: Long = 0L
)