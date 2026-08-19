package com.example.data.repository

import com.example.data.local.ArchiveEntryEntity
import com.example.data.local.DefaultSeedData
import com.example.data.local.HairStyleEntity
import com.example.data.local.OutfitEntity
import com.example.data.local.TesseraDao
import com.example.data.local.UserProfileEntity
import com.example.data.local.WardrobeItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class TesseraRepository(private val dao: TesseraDao) {

    // Wardrobe Items
    val allWardrobeItems: Flow<List<WardrobeItemEntity>> = dao.getAllWardrobeItems()

    suspend fun getWardrobeItemById(id: Long): WardrobeItemEntity? = dao.getWardrobeItemById(id)

    fun getWardrobeItemsByCategory(category: String): Flow<List<WardrobeItemEntity>> =
        dao.getWardrobeItemsByCategory(category)

    suspend fun addWardrobeItem(item: WardrobeItemEntity): Long = dao.insertWardrobeItem(item)

    suspend fun updateWardrobeItem(item: WardrobeItemEntity) = dao.updateWardrobeItem(item)

    suspend fun deleteWardrobeItem(item: WardrobeItemEntity) = dao.deleteWardrobeItem(item)

    suspend fun markAllAvailable() = dao.markAllAvailable()

    // Outfits
    fun getOutfitForVibe(vibe: String): Flow<OutfitEntity?> = dao.getOutfitForVibe(vibe)

    val allOutfits: Flow<List<OutfitEntity>> = dao.getAllOutfits()

    suspend fun updateOutfit(outfit: OutfitEntity) = dao.updateOutfit(outfit)

    suspend fun lockOutfit(vibe: String, outfitName: String, hairStyle: String, imageUrl: String) {
        dao.incrementStreak()
        val todayEntry = ArchiveEntryEntity(
            dateLabel = "Today (Jul 31)",
            vibe = vibe,
            hairStyle = hairStyle,
            outfitName = outfitName,
            imageUrl = imageUrl,
            isMissed = false,
            isToday = true
        )
        dao.insertArchiveEntry(todayEntry)
    }

    // Archive
    val allArchiveEntries: Flow<List<ArchiveEntryEntity>> = dao.getAllArchiveEntries()

    // Hair Styles
    val allHairStyles: Flow<List<HairStyleEntity>> = dao.getAllHairStyles()

    fun getHairStylesForShape(shape: String): Flow<List<HairStyleEntity>> =
        dao.getHairStylesForShape(shape)

    suspend fun updateHairStyle(style: HairStyleEntity) = dao.updateHairStyle(style)

    // User Profile & Settings
    val userProfile: Flow<UserProfileEntity?> = dao.getUserProfile()

    suspend fun updateTheme(theme: String) = dao.updateTheme(theme)

    suspend fun updateHeadShape(shape: String) = dao.updateHeadShape(shape)

    suspend fun updateHairstyle(hairstyle: String) = dao.updateHairstyle(hairstyle)

    suspend fun setClosetEmptyMode(empty: Boolean) = dao.setClosetEmptyMode(empty)

    suspend fun setConnectionState(connected: Boolean) = dao.setConnectionState(connected)

    suspend fun resetSampleData() {
        dao.insertOrUpdateProfile(DefaultSeedData.initialProfile)
        dao.insertWardrobeItems(DefaultSeedData.initialWardrobeItems)
        dao.insertOutfits(DefaultSeedData.initialOutfits)
        dao.insertArchiveEntries(DefaultSeedData.initialArchiveEntries)
        dao.insertHairStyles(DefaultSeedData.initialHairStyles)
    }

    suspend fun ensureDataInitialized() {
        val currentProfile = dao.getUserProfile().first()
        if (currentProfile == null) {
            dao.insertOrUpdateProfile(DefaultSeedData.initialProfile)
            dao.insertWardrobeItems(DefaultSeedData.initialWardrobeItems)
            dao.insertOutfits(DefaultSeedData.initialOutfits)
            dao.insertArchiveEntries(DefaultSeedData.initialArchiveEntries)
            dao.insertHairStyles(DefaultSeedData.initialHairStyles)
        }
    }
}
