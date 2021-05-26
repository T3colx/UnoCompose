package com.example.unocompose.viewmodels

import android.net.nsd.NsdManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unocompose.models.network.NSDClient
import com.example.unocompose.models.network.NSDHost
import com.example.unocompose.models.network.ScanResult

class LobbyScreenViewModel: ViewModel()  {

    private val userList = mutableListOf<ScanResult>()
    val userListState = mutableStateOf<List<String>>(listOf("Amy", "Amy", "Amy", "Amy"))

    private fun updateUserList() {
        for (data in userList) {
            userListState.value += data.name
        }
    }

    suspend fun openOnNetwork(nsdManager: NsdManager) {
        NSDHost(nsdManager).registerService()
    }

    suspend fun findLobby(nsdManager: NsdManager) {
        NSDClient(nsdManager){
            userList.add(it)
            updateUserList()
        }.startDiscovery()

    }
}