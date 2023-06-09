package com.nexzt.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.nexzt.cryptocurrencyapp.domain.model.Coin

data class CoinDTO(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)


fun CoinDTO.toCoin() : Coin {
    return Coin(id, isActive, name, rank, symbol)
}