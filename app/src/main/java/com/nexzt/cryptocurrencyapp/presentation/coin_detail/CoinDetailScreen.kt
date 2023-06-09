package com.nexzt.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.nexzt.cryptocurrencyapp.data.remote.dto.TeamMember
import com.nexzt.cryptocurrencyapp.presentation.Screen
import com.nexzt.cryptocurrencyapp.presentation.coin_detail.components.CoinTag
import com.nexzt.cryptocurrencyapp.presentation.coin_detail.components.TeamListItem
import com.nexzt.cryptocurrencyapp.presentation.coin_list.components.CoinListItem

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxHeight()
    ){
        state.coin?.let {coin ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)){
               item {

                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Text(
                           text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                           style = MaterialTheme.typography.headlineMedium,
                           modifier = Modifier.weight(8f),
                           color = Color.White
                       )
                       Text(
                           text = if(coin.isActive) "active" else "inactive",
                           color = if(coin.isActive) Color.Green else Color.Red,
                           fontStyle = FontStyle.Italic,
                           textAlign = TextAlign.End,
                           style = MaterialTheme.typography.bodySmall
                       )
                   }

                   Spacer(modifier = Modifier.height(15.dp))

                   Text(
                       text = coin.description,
                       color = Color.White,
                       style = MaterialTheme.typography.bodyMedium
                   )

                   Spacer(modifier = Modifier.height(15.dp))

                   Text(
                       text = "Tags",
                       color = Color.White,
                       style = MaterialTheme.typography.headlineMedium
                   )

                   Spacer(modifier = Modifier.height(15.dp))

                   FlowRow(
                       mainAxisSpacing = 10.dp,
                       crossAxisSpacing = 10.dp,
                       modifier = Modifier.fillMaxWidth()
                   ) {
                       coin.tags.forEach {
                           CoinTag(tag = it)
                       }
                   }

                   Spacer(modifier = Modifier.height(15.dp))

                   Text(
                       text = "Team Members",
                       color = Color.White,
                       style = MaterialTheme.typography.headlineMedium
                   )

                   if (coin.team.isEmpty()){
                       Spacer(modifier = Modifier.height(25.dp))
                       Text(
                           text = "No Team Members",
                           color = Color.LightGray,
                           style = MaterialTheme.typography.bodyMedium,
                           textAlign = TextAlign.Center,
                           modifier = Modifier.fillMaxWidth()
                       )
                   }

               }
                items(coin.team) {member ->
                    TeamListItem(teamMember = member,
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp))
                    Divider()
                }
            }
        }


        if(state.error.isNotBlank()){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }


    }


}