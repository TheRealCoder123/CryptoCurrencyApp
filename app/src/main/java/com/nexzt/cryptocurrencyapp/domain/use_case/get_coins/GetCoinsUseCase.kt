package com.nexzt.cryptocurrencyapp.domain.use_case.get_coins

import com.nexzt.cryptocurrencyapp.common.Resource
import com.nexzt.cryptocurrencyapp.data.remote.dto.toCoin
import com.nexzt.cryptocurrencyapp.domain.model.Coin
import com.nexzt.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        }catch (e: HttpException){
            emit(Resource.Error(data = emptyList(),  e.localizedMessage ?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error(data = emptyList(), e.localizedMessage ?: "Couldn't reach the server"))
        }
    }


}