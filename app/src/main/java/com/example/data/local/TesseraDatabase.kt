package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        WardrobeItemEntity::class,
        OutfitEntity::class,
        ArchiveEntryEntity::class,
        HairStyleEntity::class,
        UserProfileEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TesseraDatabase : RoomDatabase() {

    abstract fun tesseraDao(): TesseraDao

    companion object {
        @Volatile
        private var INSTANCE: TesseraDatabase? = null

        fun getDatabase(context: Context): TesseraDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TesseraDatabase::class.java,
                    "tessera_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getDatabase(context).tesseraDao()
                                dao.insertOrUpdateProfile(DefaultSeedData.initialProfile)
                                dao.insertWardrobeItems(DefaultSeedData.initialWardrobeItems)
                                dao.insertOutfits(DefaultSeedData.initialOutfits)
                                dao.insertArchiveEntries(DefaultSeedData.initialArchiveEntries)
                                dao.insertHairStyles(DefaultSeedData.initialHairStyles)
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
