package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.local.ArchiveEntryEntity
import com.example.data.local.HairStyleEntity
import com.example.data.local.OutfitEntity
import com.example.data.local.TesseraDatabase
import com.example.data.local.UserProfileEntity
import com.example.data.local.WardrobeItemEntity
import com.example.data.repository.TesseraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val profile: UserProfileEntity? = null,
    val selectedVibe: String = "Drippy",
    val currentOutfit: OutfitEntity? = null,
    val isLockedToday: Boolean = false,
    val isAdjustSheetOpen: Boolean = false,
    val isClosetEmpty: Boolean = false,
    val isConnected: Boolean = true,
    val activePage: Int = 0
)

class TesseraViewModel(
    application: Application,
    private val repository: TesseraRepository
) : AndroidViewModel(application) {

    private val _selectedVibe = MutableStateFlow("Drippy")
    val selectedVibe: StateFlow<String> = _selectedVibe.asStateFlow()

    private val _isAdjustSheetOpen = MutableStateFlow(false)
    val isAdjustSheetOpen: StateFlow<Boolean> = _isAdjustSheetOpen.asStateFlow()

    private val _isLockedToday = MutableStateFlow(false)
    val isLockedToday: StateFlow<Boolean> = _isLockedToday.asStateFlow()

    private val _activePage = MutableStateFlow(0)
    val activePage: StateFlow<Int> = _activePage.asStateFlow()

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val allWardrobeItems: StateFlow<List<WardrobeItemEntity>> = repository.allWardrobeItems
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allArchiveEntries: StateFlow<List<ArchiveEntryEntity>> = repository.allArchiveEntries
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allHairStyles: StateFlow<List<HairStyleEntity>> = repository.allHairStyles
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allOutfits: StateFlow<List<OutfitEntity>> = repository.allOutfits
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val homeUiState: StateFlow<HomeUiState> = combine(
        userProfile,
        _selectedVibe,
        allOutfits,
        _isLockedToday,
        _isAdjustSheetOpen
    ) { profile, vibe, outfits, isLocked, isAdjustOpen ->
        val outfit = outfits.find { it.vibe.equals(vibe, ignoreCase = true) }
            ?: outfits.firstOrNull()
        HomeUiState(
            profile = profile,
            selectedVibe = vibe,
            currentOutfit = outfit,
            isLockedToday = isLocked,
            isAdjustSheetOpen = isAdjustOpen,
            isClosetEmpty = profile?.isClosetEmptyMode ?: false,
            isConnected = profile?.isConnected ?: true,
            activePage = _activePage.value
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeUiState()
    )

    init {
        viewModelScope.launch {
            repository.ensureDataInitialized()
        }
    }

    fun setVibe(vibe: String) {
        _selectedVibe.value = vibe
    }

    fun setActivePage(page: Int) {
        _activePage.value = page
    }

    fun setAdjustSheetOpen(open: Boolean) {
        _isAdjustSheetOpen.value = open
    }

    fun lockTodayFit() {
        val currentOutfit = homeUiState.value.currentOutfit
        if (currentOutfit != null && !_isLockedToday.value) {
            _isLockedToday.value = true
            viewModelScope.launch {
                repository.lockOutfit(
                    vibe = _selectedVibe.value,
                    outfitName = currentOutfit.name,
                    hairStyle = currentOutfit.hairStyleName,
                    imageUrl = currentOutfit.imageUrl
                )
            }
        }
    }

    fun applyOccasion(occasion: String, leanVibe: String) {
        _selectedVibe.value = leanVibe
        // If occasion provided, update outfit title or subtext dynamically
        viewModelScope.launch {
            val outfit = allOutfits.value.find { it.vibe.equals(leanVibe, ignoreCase = true) }
            if (outfit != null && occasion.isNotBlank()) {
                val updatedOutfit = outfit.copy(
                    name = "$occasion Look (${outfit.name})",
                    pairedWithText = "Curated for $occasion · ${outfit.shoesName}"
                )
                repository.updateOutfit(updatedOutfit)
            }
        }
    }

    fun swapBottomPiece(bottomItem: WardrobeItemEntity) {
        viewModelScope.launch {
            val currentOutfit = homeUiState.value.currentOutfit ?: return@launch
            val updated = currentOutfit.copy(
                bottomName = bottomItem.name,
                name = "${currentOutfit.topName} & ${bottomItem.name}"
            )
            repository.updateOutfit(updated)
        }
    }

    fun triggerWildcard() {
        viewModelScope.launch {
            val currentOutfit = homeUiState.value.currentOutfit ?: return@launch
            val wildcardOutfit = currentOutfit.copy(
                name = "Experimental Wildcard Mix",
                pairedWithText = "Paired with Minimalist High-Tops & Accent Scarf",
                isWildcard = true
            )
            repository.updateOutfit(wildcardOutfit)
        }
    }

    fun updateTheme(theme: String) {
        viewModelScope.launch {
            repository.updateTheme(theme)
        }
    }

    fun updateHeadShape(shape: String) {
        viewModelScope.launch {
            repository.updateHeadShape(shape)
        }
    }

    fun updateHairstyle(hairstyle: String) {
        viewModelScope.launch {
            repository.updateHairstyle(hairstyle)
        }
    }

    fun markAllAvailable() {
        viewModelScope.launch {
            repository.markAllAvailable()
        }
    }

    fun updateItemAvailability(item: WardrobeItemEntity, status: String) {
        viewModelScope.launch {
            repository.updateWardrobeItem(item.copy(availabilityStatus = status))
        }
    }

    fun deleteWardrobeItem(item: WardrobeItemEntity) {
        viewModelScope.launch {
            repository.deleteWardrobeItem(item)
        }
    }

    fun addNewWardrobeItem(
        name: String,
        category: String,
        vibeMatch: String,
        imageUrl: String
    ) {
        viewModelScope.launch {
            val newItem = WardrobeItemEntity(
                name = name,
                category = category,
                vibeMatch = vibeMatch,
                imageUrl = imageUrl,
                wornCount = 0,
                lastWorn = "Never",
                availabilityStatus = "Available"
            )
            repository.addWardrobeItem(newItem)
        }
    }

    fun toggleEmptyState(empty: Boolean) {
        viewModelScope.launch {
            repository.setClosetEmptyMode(empty)
        }
    }

    fun toggleConnectionState(connected: Boolean) {
        viewModelScope.launch {
            repository.setConnectionState(connected)
        }
    }

    fun resetSampleData() {
        viewModelScope.launch {
            repository.resetSampleData()
            _isLockedToday.value = false
            _selectedVibe.value = "Drippy"
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val database = TesseraDatabase.getDatabase(application)
            val repository = TesseraRepository(database.tesseraDao())
            return TesseraViewModel(application, repository) as T
        }
    }
}
