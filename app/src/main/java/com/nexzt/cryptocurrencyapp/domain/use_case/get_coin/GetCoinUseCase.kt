package com.nexzt.cryptocurrencyapp.domain.use_case.get_coin

import com.nexzt.cryptocurrencyapp.common.Resource
import com.nexzt.cryptocurrencyapp.data.remote.dto.toCoin
import com.nexzt.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.nexzt.cryptocurrencyapp.domain.model.Coin
import com.nexzt.cryptocurrencyapp.domain.model.CoinDetail
import com.nexzt.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        }catch (e: HttpException){
            emit(Resource.Error(data = null, e.localizedMessage ?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error(data = null, e.localizedMessage ?: "Couldn't reach the server"))
        }
    }


}