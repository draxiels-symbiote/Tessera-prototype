package com.example.data.local

object DefaultSeedData {

    val initialProfile = UserProfileEntity(
        id = 1,
        name = "Kester",
        headShape = "Oval",
        currentHairstyle = "Textured crop",
        streakDays = 14,
        weatherLocation = "Abuja",
        weatherTemp = "72°",
        weatherCondition = "Dry",
        selectedTheme = "Obsidian Rose",
        notificationsEnabled = true,
        isClosetEmptyMode = false,
        isConnected = true
    )

    val initialWardrobeItems = listOf(
        WardrobeItemEntity(
            id = 1,
            name = "Charcoal Tailored Blazer",
            category = "Outerwear",
            vibeMatch = "Serious",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuIlSzgCMR-pCDEXPFPqo74iy7TGawD8eQBcUgm3gwm_LJvJTXDMqIgUT2CCaIG1fTv_OqPxGAn1NX8HqiSQfwcemRNlXGbBFlyezOVjoBedCKcNh1ULcLsIJIoWCrqbWmnzOkKz0pdeWr8BdrdIRFT4GdWC-Djj_ET9Yi4rU8_9hdjWRvd0mdVK0yUG7dScaV227JoT2JmWzBWvRdkZ1mU2jwGeQpdm6UBwHGawbViB9eRplsyrdCCtxA",
            wornCount = 12,
            lastWorn = "2 days ago",
            availabilityStatus = "Lent to Amara",
            isFavorite = true
        ),
        WardrobeItemEntity(
            id = 2,
            name = "Relaxed Wide Fit Trousers",
            category = "Bottom",
            vibeMatch = "Drippy",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDAX0WglKRcMqGbe8XYf6yU1oQ9gXf2qRFjLyKZZaNYMDMlDxWnHiTmv6F0IMJjOucyggbsFcjy_1L7lczcqFikwmkJYqzQQQO6RjXS97ETMavY313ww0OOD8mFWEDFg9GBOFUKqP2mS432_5oM_1ZHmk6cQvJpPGuEUXNszbIQvMA3iKhW6MZK6jdRZpoBQCGi6Eeig75uQ8tykCd9XETfUPVD0cTvNAe0nviXIN7W7ODXMIx4NnEa",
            wornCount = 18,
            lastWorn = "Today",
            availabilityStatus = "Available",
            isFavorite = true
        ),
        WardrobeItemEntity(
            id = 3,
            name = "Oversized Boxy Tee (Charcoal)",
            category = "Top",
            vibeMatch = "Drippy",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDpbXH_IGIqwGihYBGVBQKp8SSfdy2zbxJJXPzQIyABaQBy1u9zpWdksWuooqWiUaeVnuYGbVs_vvburapdXq95ETsdtAkAoNuCu9F4lb65ns6JQWhHpyTqPr2oUuB-S8bTt2VD5zY-lxQZPwAzm3edxSvTYYpWlfkRd37PCm618HtUYlXCNB7RvkW9_IYzp8P5D2sZJnVjleUaEVqgOSBbUkNvRFgpCFYI8oNRcH-zRJEAE6sP5mfK",
            wornCount = 24,
            lastWorn = "Today",
            availabilityStatus = "Available",
            isFavorite = true
        ),
        WardrobeItemEntity(
            id = 4,
            name = "Washed Indigo Cargo",
            category = "Bottom",
            vibeMatch = "Drippy",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAJTmpzMn94M46eFnEEDBlwb1arFvpuCvAKKRBzLWzvmJpQHRgZMgxcRoBtm_a9mGu7dtKI_tncVaIQcbxt_9BoFTioozAsAG-cEXWPTDD10CeQl3BFtKw8tYGuupuXeGmOYZAETRc_Fu18Im6_d9a3y0I4aDnCYfisG0tdk9qOz2dksIELVGbGffbhl1JfzflwGfZpD8rKvX4Rq3aHKJxtnb1U5-py_tB7NIwl0CKoPT5R6CRJ2fYg",
            wornCount = 9,
            lastWorn = "4 days ago",
            availabilityStatus = "Available",
            isFavorite = false
        ),
        WardrobeItemEntity(
            id = 5,
            name = "Pleated Slate Trousers",
            category = "Bottom",
            vibeMatch = "Serious",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBDseFksQqNLMxS1wio9JJ3K7sffacTK1aV5ZuoDoNvZLn2nwZK4V3xQab03r1fMo_2uP4lPhWhp1nGi3GmRGKH3xxdn-Ye7VHHVzEEY58EsIWpxSAPWHymd1-5w2qO3Pnny_86au7ZFqUAfedV-LeYiH0xzBnbbgJCDpSEjo20SvmJOilGlMmr_qKuiJ3bhlhAFpoY5Eji4Rypu9YvIqUWDbCX5sRKHSXuFIAuURvGmENLQjRXyeq5",
            wornCount = 4,
            lastWorn = "1 week ago",
            availabilityStatus = "Available",
            isFavorite = false
        ),
        WardrobeItemEntity(
            id = 6,
            name = "Salomon XT-6 (White/Silver)",
            category = "Kicks",
            vibeMatch = "Drippy",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDhAt_n25BYZcZKTdNptTxsi_uLhZfF_B5CSXEC7P5Hv0jhLueO-u3knHKzt7Q_ojA9tNKff1bxqFWvnEQFyUqPnOglQWOhEYsnE0D_aEuIFoFHwQlJP9OsjQvCbvzZiYAMWRdJIyj9UQBGJ6uq1x1wkxLdWwI62UGCvJiqrPB-3ZYiJOsOK9tkzB_urGzlcJgvgZNBDl8R0SBqf8lPV5j4P-CjtyXuPKGFJnEayYRhOhiBtCx70s04",
            wornCount = 31,
            lastWorn = "Today",
            availabilityStatus = "Available",
            isFavorite = true
        )
    )

    val initialOutfits = listOf(
        OutfitEntity(
            id = 1,
            name = "Relaxed Boxy Tee & Wide Fit Trousers",
            vibe = "Drippy",
            topName = "Oversized Boxy Tee",
            bottomName = "Relaxed Wide Fit Trousers",
            outerwearName = null,
            shoesName = "Salomon XT-6 (White/Silver)",
            hairStyleName = "Today's styling: half up, low and loose",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDAX0WglKRcMqGbe8XYf6yU1oQ9gXf2qRFjLyKZZaNYMDMlDxWnHiTmv6F0IMJjOucyggbsFcjy_1L7lczcqFikwmkJYqzQQQO6RjXS97ETMavY313ww0OOD8mFWEDFg9GBOFUKqP2mS432_5oM_1ZHmk6cQvJpPGuEUXNszbIQvMA3iKhW6MZK6jdRZpoBQCGi6Eeig75uQ8tykCd9XETfUPVD0cTvNAe0nviXIN7W7ODXMIx4NnEa",
            pairedWithText = "Paired with Salomon XT-6 (White/Silver)",
            isLocked = false
        ),
        OutfitEntity(
            id = 2,
            name = "Elevated Evening Silk Fit",
            vibe = "Attractive",
            topName = "Silk Knit Polo",
            bottomName = "Dark Wash Denim",
            outerwearName = "Charcoal Tailored Blazer",
            shoesName = "Black Chelsea Boot",
            hairStyleName = "Today's styling: loose waves, side swept",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDyVhWQdOMgwJWfOesf6ZxversFArC1Xm3YpMvZPuS83ui1FNALZ9Y6x1dQ7sg6hb2mMrIeQwPSLQOPbDQdPwcT4jgvw6nO7xaqMvBQxl8Si3NVrSjOemE7744nVb6IbWTrO9ParoSKxmgqIa229j7tOfEWeV9sRPAdnLzx1Y7s_AwTXB7NhB_AbCbjpqfjRZDNCY2Q5j1mteVmqY3RGMxlO-t9Em3JJrATlUnAqmkeYQSicXakRFC-",
            pairedWithText = "Paired with Black Chelsea Boots",
            isLocked = false
        ),
        OutfitEntity(
            id = 3,
            name = "Boardroom Ready Classic",
            vibe = "Serious",
            topName = "Crisp Oxford Shirt",
            bottomName = "Tailored Charcoal Wool Trousers",
            outerwearName = "Navy Single-Breasted Blazer",
            shoesName = "Black Leather Loafer",
            hairStyleName = "Today's styling: neat part, pulled back",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDMfi4qk0x-QSFN-6gje0rvLDIKC6Qrxwak-PdivurOinpvEkCysg7ccAliCay9VEmHYklH5g4vgdtdnVVguea3wKIf-bo9-9tsFaO13UHBSk8akvsTsAvpbFusHBCUnioBvI8NhyYVOLwbaZDzKRIHtmXXJZIcoPXqgeOPfAMVRQ_00_BFwMDH2Ru5UNSea1lQ8nO0nrPbaE1R_r4UNkhI0o4YoZhupfRMgTff74f4UJ3nqxe6B2kU",
            pairedWithText = "Paired with Black Leather Loafer",
            isLocked = false
        ),
        OutfitEntity(
            id = 4,
            name = "Casual Streetwear Bomber",
            vibe = "Normal",
            topName = "White Heavyweight Crewneck",
            bottomName = "Classic Blue Straight-Leg Denim",
            outerwearName = "Olive Green Flight Bomber",
            shoesName = "Retro White Court Sneakers",
            hairStyleName = "Today's styling: tied back, relaxed",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAt5pHvKuIlIVD_8S77IzcB-duZacb2F_-YVgC7cvOKkRKMMvnct-72SLxPlXdnxyxDfSd46G9cmHGxotB1CeLK-86EE3X0Cd90UazDD7j_xdOPpsRIfYKuQXBSVJ1R5DOYzDkIvgSlcJf31GJ7CdZplDSYl40LeYCDx5u6OMm9IL8Hlp-jkm598QfVy3dRE15eGJ-Veqk1_Z_HSY1XZbugqsxMAyoSQLmWYbbbVSAVbwhYm6-2iuW_",
            pairedWithText = "Paired with Retro White Sneakers",
            isLocked = false
        )
    )

    val initialArchiveEntries = listOf(
        ArchiveEntryEntity(
            id = 1,
            dateLabel = "Today (Jul 31)",
            vibe = "Drippy",
            hairStyle = "Half up, low and loose",
            outfitName = "Relaxed Boxy Tee & Wide Fit Trousers",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuI564AM93k2q5Er9qKOMA7l7ocQryWcIugVOCdKFZkl3Q9EUx9MqPoF671UIt--BGYmI6JlaoEMJzIWiw61DOe1oB_bj4V-pprfvFQ2eeSM3glGt4o2kZjtrOi6p_kw-Jj-tTIL7tDmNcNj2QHZka-wghpEDrh532GYLP6As39NGTmvppQUA_2xX9LTpLwNOUN8P8CmyFMst0-eX7F7wWhF6-UcxtiAxrEKfthsjYxaofgnSfyzbdbfMQ",
            isMissed = false,
            isToday = true
        ),
        ArchiveEntryEntity(
            id = 2,
            dateLabel = "Yesterday (Jul 30)",
            vibe = "Serious",
            hairStyle = "Neat part, pulled back",
            outfitName = "Monochrome Button-Down & Tailored Trousers",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB6cKp4oTVOnv3f0RLnZAVrPdxVdJxX2c6avzvRZkm8jlbIAThls9NvuFyrysaAJqzDd4vparHjqiTVJl1xzNbX-xvo7kUW6aNUtsGgDcYejVuKHMKAlFG9kwGDK3lTsHmij8FLRPW1nYep8w7RayQ8eXTehwTdTuJUiZj67CP_OV0f9DgPuX3glc743Gyo66G9tmXzg0gMorTm3eVvLUMSQUZycfqmI9p3De4dR-Qr1ZLeei0AiKbw",
            isMissed = false,
            isToday = false
        ),
        ArchiveEntryEntity(
            id = 3,
            dateLabel = "Jul 29",
            vibe = "Attractive",
            hairStyle = "Loose waves",
            outfitName = "Pastel Summer Linen & Straw Bag",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDNN-OoKC6Ih89HVsgRRm1WOtRFn7uanYJRfQoZPsE6lltP706c1uZ1jSFVkVESsrmo54aAP_g2ZmY8qatUW6YYszLFpM9A2UtK5eTnLFIKgUezGxj14v1-7JU3BdDCRBohd490HgYg63GY8_-Vejy2v8VQzeJIJ34IqCMlynZRB4szbihZwWVo_Zc17Be4l8p6wCkDb6Y3NidfT16k3IQaJTJZhJnCIMdFyWtJGppRC9WAb3yy5ocx",
            isMissed = false,
            isToday = false
        ),
        ArchiveEntryEntity(
            id = 4,
            dateLabel = "Jul 28",
            vibe = "Missed",
            hairStyle = "Missed — no fit locked",
            outfitName = "No fit locked",
            imageUrl = "",
            isMissed = true,
            isToday = false
        ),
        ArchiveEntryEntity(
            id = 5,
            dateLabel = "Jul 27",
            vibe = "Normal",
            hairStyle = "Tied back",
            outfitName = "Streetwear Bomber & Straight Denim",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAt5pHvKuIlIVD_8S77IzcB-duZacb2F_-YVgC7cvOKkRKMMvnct-72SLxPlXdnxyxDfSd46G9cmHGxotB1CeLK-86EE3X0Cd90UazDD7j_xdOPpsRIfYKuQXBSVJ1R5DOYzDkIvgSlcJf31GJ7CdZplDSYl40LeYCDx5u6OMm9IL8Hlp-jkm598QfVy3dRE15eGJ-Veqk1_Z_HSY1XZbugqsxMAyoSQLmWYbbbVSAVbwhYm6-2iuW_",
            isMissed = false,
            isToday = false
        )
    )

    val initialHairStyles = listOf(
        HairStyleEntity(
            id = 1,
            name = "Textured Crop Fade",
            headShape = "Oval",
            tag = "CURRENT",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLu5NCH1UjamYgYYpIuMZiGHiqqqxYk__L4NzoILUYk7thQELyQy_1ys3xEW1G3xjSRXrcKxv9dr7CJ28uzMRwRlrM0RCz_U-dvMlSH629HcxluyR4tMtIAMMWpgjlsQEUNkvln5d8DR533qda25eA8edyCOZXnPJPxTUJJXzFSRw6r1MCyjUFwrulv-KmUjI_tSXqC3CJXr3EoQ4UnT9Z7zJJblkz_VIORu01Fs6wGA1vyZmUhrzb-ZOA",
            isFavorite = true,
            isCurrent = true
        ),
        HairStyleEntity(
            id = 2,
            name = "Classic Pompadour",
            headShape = "Oval",
            tag = "MAY '24",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuJYQIueRv5p0cRnHtwkFd4SLFOnAqwGs8InrZXo-nSkgYIRNkpyHXi9wf2bL6SvrhLhJxTPgcqXFW6B9fsPzeNcYZSdoj7bKw1hjILw2BlzweQ17D3wcDCLV1h65UaLoqieW2jWucyqMjj5FV7pRmAUrrQsMfKeLegEVeYhJe0c-NClRSjMIip0kCqeuQtYOVX_dyjQb4AupJy7sAq8UKmAREI-iUGTKRMd517MiHBN9QHveeX4diqLw",
            isFavorite = false,
            isCurrent = false
        ),
        HairStyleEntity(
            id = 3,
            name = "Messy Fringe Crop",
            headShape = "Round",
            tag = "",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLvcJL3cLnsZBauHWhqJEcRAlbeSt7lyYt2LgXvagZvfMr9y49dcfA4Dgw5BqTgzFEmlznxE_-a7PdzNN2pZJusMPIOoHRMJyqw46bGtUMiqDEB_y78UYtJ3hVcwleNVaf_FYznE-5GBDMPD0DpZJWmHoocFeDXVa-zxLuOO24kj29sg8lbTKEvmNEZGnW6lc76UWfnjQfoyr3XvYK9H-iKGTC-XJ76fV6nAA557aDGAJo77mEiJunFEnII",
            isFavorite = false,
            isCurrent = false
        ),
        HairStyleEntity(
            id = 4,
            name = "Geometric Line Buzz",
            headShape = "Square",
            tag = "SAVED",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLt_caR34DjfLwwdO4HkIPJxqN2zeXEbRk4iytotLPhKvypgbUAwdSX2H-NZ1QRM2m6UG50VDihH5990MBqLfTvJHbrAoV56OkAxAmS1m2Ka6AyfS6wLwOLQpnZkmilF05vdUDcO8Q6UCivLbBFwNwv38pTza-9rWgmnBo_9rwaL24dGZ26dnk8FCKhrzUGWIptgFeSljRhEwHY9mHYF6pjTSshgDz6AcrWAx06Y7d0NWnsj50hCx_17ZD4",
            isFavorite = true,
            isCurrent = false
        ),
        HairStyleEntity(
            id = 5,
            name = "Curly High-Top Fade",
            headShape = "Heart",
            tag = "",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLstub1GnwRYweIrwpof7j5QZcCohpYLFQaaStAC73k12ILhulTYKTCKEU2BnugvY5yziw37ZyoArxc0rJOmi2JebyLxdA4y3Pvntby29JyyZxT61IhBLFRo5yFWTfOWgirtA7frv2UMswY9ZLr_MD-V9Ry7CzGJhiWIczfrxJXtLRETg8-9TiyhKqW-qL9-VhzUxNniC1dE9tbvzS5WHZCl3n3SZjWM-oqIrS_Als-TnX1I_PNUYTQti-w",
            isFavorite = false,
            isCurrent = false
        ),
        HairStyleEntity(
            id = 6,
            name = "Medium Length Flow",
            headShape = "Diamond",
            tag = "",
            imageUrl = "https://lh3.googleusercontent.com/aida/AP1WRLuY5ktm08511DMiomUhIIieubpQN1peawabgHcLlx-OlG6XdZ1QegojYPRIsC_ujYqNSKV1t38U6_LUZAPrk9fY4PaTkwNADIpNQD54L75HvluAmZdu6he0Zg8dSi89Rct0mZPeVo5bOVOFxwwVjiOsoNvgPtivfJCVwOZzeCOXkbfPhrnExJQPZXuy884hy0k51i_sR_sh3kk7BFWu-TJVpbwxu9KGufAMQwirJz-hx6gGATDj2S4HL-s",
            isFavorite = false,
            isCurrent = false
        )
    )
}
