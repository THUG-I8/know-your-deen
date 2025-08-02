package com.ammarapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// نموذج السورة المطور
@Entity(tableName = "surahs_extended")
data class SurahExtended(
    @PrimaryKey val id: Int,
    val arabicName: String,
    val englishName: String,
    val numberOfVerses: Int,
    val revelationType: String, // مكية أو مدنية
    val orderInQuran: Int,
    val startPage: Int,
    val endPage: Int,
    val juzNumber: Int // رقم الجزء
)

// نموذج الآية المطور
@Entity(tableName = "verses_extended")
data class VerseExtended(
    @PrimaryKey val id: Int,
    val surahId: Int,
    val verseNumber: Int,
    val arabicText: String,
    val translation: String = "",
    val tafseer: String = "",
    val pageNumber: Int,
    val juzNumber: Int,
    val hizbNumber: Int,
    val rukuNumber: Int
)

// نموذج العلامة المرجعية
@Entity(tableName = "bookmarks")
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val surahId: Int,
    val verseId: Int,
    val pageNumber: Int,
    val title: String,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

// نموذج التقدم في القراءة
@Entity(tableName = "reading_progress")
data class ReadingProgress(
    @PrimaryKey val id: Int = 1, // سيكون هناك سجل واحد فقط
    val currentSurahId: Int,
    val currentVerseId: Int,
    val currentPageNumber: Int,
    val lastReadingTime: Long = System.currentTimeMillis(),
    val totalVersesRead: Int = 0,
    val totalPagesRead: Int = 0,
    val readingStreak: Int = 0, // عدد الأيام المتتالية
    val lastStreakDate: String = ""
)

// نموذج إعدادات القراءة
@Entity(tableName = "reading_settings")
data class ReadingSettings(
    @PrimaryKey val id: Int = 1,
    val fontSize: Float = 18f,
    val fontFamily: String = "Uthmanic",
    val lineSpacing: Float = 1.5f,
    val backgroundColor: String = "#FFFFFF",
    val textColor: String = "#000000",
    val nightMode: Boolean = false,
    val showTranslation: Boolean = false,
    val showTafseer: Boolean = false,
    val autoScroll: Boolean = false,
    val autoScrollSpeed: Int = 3, // 1-5
    val keepScreenOn: Boolean = true
)

// أنواع العرض
enum class ViewMode {
    PAGE_VIEW,      // عرض الصفحة كاملة
    VERSE_VIEW,     // عرض آية بآية
    SURAH_VIEW      // عرض السورة كاملة
}

// أنواع الخطوط
enum class FontType(val displayName: String, val fontFile: String) {
    UTHMANIC("الخط العثماني", "uthmanic_hafs.ttf"),
    NOOR("خط النور", "noor_font.ttf"),
    AMIRI("خط الأميري", "amiri.ttf"),
    CAIRO("خط القاهرة", "cairo.ttf"),
    SIMPLE("خط بسيط", "simple_arabic.ttf")
}

// نموذج إحصائيات القراءة
data class ReadingStats(
    val totalVersesRead: Int,
    val totalPagesRead: Int,
    val totalSurahsCompleted: Int,
    val totalReadingTime: Long, // بالدقائق
    val averageReadingPerDay: Int,
    val currentStreak: Int,
    val longestStreak: Int,
    val lastReadingDate: String,
    val completionPercentage: Float
)

// نماذج للبحث
data class SearchResult(
    val surahId: Int,
    val surahName: String,
    val verseId: Int,
    val verseNumber: Int,
    val arabicText: String,
    val translation: String,
    val pageNumber: Int
)