package com.nexzt.cryptocurrencyapp.data.repository

import com.nexzt.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.nexzt.cryptocurrencyapp.data.remote.dto.CoinDTO
import com.nexzt.cryptocurrencyapp.data.remote.dto.CoinDetailDTO
import com.nexzt.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDTO> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDTO {
        return api.getCoinById(coinId)
    }
}