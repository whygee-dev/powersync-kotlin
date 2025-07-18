package com.powersync

import com.powersync.connectors.PowerSyncBackendConnector
import com.powersync.connectors.PowerSyncCredentials

class TestConnector : PowerSyncBackendConnector() {
    var fetchCredentialsCallback: suspend () -> PowerSyncCredentials? = {
        testCredentials
    }
    var uploadDataCallback: suspend (PowerSyncDatabase) -> Unit = {
        val tx = it.getNextCrudTransaction()
        tx?.complete(null)
    }

    override suspend fun fetchCredentials(): PowerSyncCredentials? = fetchCredentialsCallback()

    override suspend fun uploadData(database: PowerSyncDatabase) {
        uploadDataCallback(database)
    }

    companion object {
        val testCredentials =
            PowerSyncCredentials(
                token = "test-token",
                endpoint = "https://powersynctest.example.com",
            )
    }
}
