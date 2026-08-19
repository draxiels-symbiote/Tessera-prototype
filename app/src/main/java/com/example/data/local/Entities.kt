package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wardrobe_items")
data class WardrobeItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String, // "Top", "Bottom", "Outerwear", "Kicks", "Accessories"
    val vibeMatch: String, // "Drippy", "Attractive", "Serious", "Normal"
    val imageUrl: String,
    val wornCount: Int = 0,
    val lastWorn: String = "Never",
    val availabilityStatus: String = "Available", // "Available", "Lent to Amara", "In Wash", "In Storage"
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "outfits")
data class OutfitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val vibe: String, // "Drippy", "Attractive", "Serious", "Normal"
    val topName: String,
    val bottomName: String,
    val outerwearName: String? = null,
    val shoesName: String,
    val hairStyleName: String,
    val imageUrl: String,
    val pairedWithText: String,
    val isLocked: Boolean = false,
    val isWildcard: Boolean = false
)

@Entity(tableName = "archive_entries")
data class ArchiveEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateLabel: String, // e.g. "Today (Jul 31)", "Yesterday (Jul 30)", "Jul 29", "Jul 28"
    val vibe: String,
    val hairStyle: String,
    val outfitName: String,
    val imageUrl: String,
    val isMissed: Boolean = false,
    val isToday: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "hair_styles")
data class HairStyleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val headShape: String, // "Oval", "Round", "Square", "Heart", "Diamond", "Oblong"
    val tag: String = "", // "CURRENT", "MAY '24", "SAVED"
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val isCurrent: Boolean = false
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val name: String = "Kester",
    val headShape: String = "Oval",
    val currentHairstyle: String = "Textured crop",
    val streakDays: Int = 14,
    val weatherLocation: String = "Abuja",
    val weatherTemp: String = "72°",
    val weatherCondition: String = "Dry",
    val selectedTheme: String = "Obsidian Rose",
    val notificationsEnabled: Boolean = true,
    val isClosetEmptyMode: Boolean = false,
    val isConnected: Boolean = true
)
