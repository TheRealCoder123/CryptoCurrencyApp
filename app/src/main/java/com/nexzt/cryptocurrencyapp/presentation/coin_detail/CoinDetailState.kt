package com.nexzt.cryptocurrencyapp.presentation.coin_detail

import com.nexzt.cryptocurrencyapp.domain.model.Coin
import com.nexzt.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)