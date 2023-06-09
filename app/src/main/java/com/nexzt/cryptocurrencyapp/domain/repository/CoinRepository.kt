package com.nexzt.cryptocurrencyapp.domain.repository

import com.nexzt.cryptocurrencyapp.data.remote.dto.CoinDTO
import com.nexzt.cryptocurrencyapp.data.remote.dto.CoinDetailDTO
import retrofit2.http.Path

interface CoinRepository {

    suspend fun getCoins(): List<CoinDTO>

    suspend fun getCoinById(coinId: String) : CoinDetailDTO

}