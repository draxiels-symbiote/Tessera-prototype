package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TesseraDao {

    // Wardrobe
    @Query("SELECT * FROM wardrobe_items ORDER BY id DESC")
    fun getAllWardrobeItems(): Flow<List<WardrobeItemEntity>>

    @Query("SELECT * FROM wardrobe_items WHERE id = :id")
    suspend fun getWardrobeItemById(id: Long): WardrobeItemEntity?

    @Query("SELECT * FROM wardrobe_items WHERE category = :category ORDER BY id DESC")
    fun getWardrobeItemsByCategory(category: String): Flow<List<WardrobeItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWardrobeItem(item: WardrobeItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWardrobeItems(items: List<WardrobeItemEntity>)

    @Update
    suspend fun updateWardrobeItem(item: WardrobeItemEntity)

    @Delete
    suspend fun deleteWardrobeItem(item: WardrobeItemEntity)

    @Query("UPDATE wardrobe_items SET availabilityStatus = 'Available'")
    suspend fun markAllAvailable()

    // Outfits
    @Query("SELECT * FROM outfits WHERE vibe = :vibe LIMIT 1")
    fun getOutfitForVibe(vibe: String): Flow<OutfitEntity?>

    @Query("SELECT * FROM outfits")
    fun getAllOutfits(): Flow<List<OutfitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOutfits(outfits: List<OutfitEntity>)

    @Update
    suspend fun updateOutfit(outfit: OutfitEntity)

    // Archive
    @Query("SELECT * FROM archive_entries ORDER BY id ASC")
    fun getAllArchiveEntries(): Flow<List<ArchiveEntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchiveEntries(entries: List<ArchiveEntryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchiveEntry(entry: ArchiveEntryEntity): Long

    @Update
    suspend fun updateArchiveEntry(entry: ArchiveEntryEntity)

    // Hair Styles
    @Query("SELECT * FROM hair_styles")
    fun getAllHairStyles(): Flow<List<HairStyleEntity>>

    @Query("SELECT * FROM hair_styles WHERE headShape = :shape")
    fun getHairStylesForShape(shape: String): Flow<List<HairStyleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHairStyles(styles: List<HairStyleEntity>)

    @Update
    suspend fun updateHairStyle(style: HairStyleEntity)

    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    @Query("UPDATE user_profile SET selectedTheme = :theme WHERE id = 1")
    suspend fun updateTheme(theme: String)

    @Query("UPDATE user_profile SET headShape = :shape WHERE id = 1")
    suspend fun updateHeadShape(shape: String)

    @Query("UPDATE user_profile SET currentHairstyle = :hairstyle WHERE id = 1")
    suspend fun updateHairstyle(hairstyle: String)

    @Query("UPDATE user_profile SET streakDays = streakDays + 1 WHERE id = 1")
    suspend fun incrementStreak()

    @Query("UPDATE user_profile SET isClosetEmptyMode = :empty WHERE id = 1")
    suspend fun setClosetEmptyMode(empty: Boolean)

    @Query("UPDATE user_profile SET isConnected = :connected WHERE id = 1")
    suspend fun setConnectionState(connected: Boolean)
}
