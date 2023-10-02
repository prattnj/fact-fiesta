package com.noahpratt.factfiesta

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class ServerProxy {

    fun getFact(): String {
        val url = URL("https://factfiesta.noahpratt.com:443")
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "GET"
            connection.readTimeout = 3000
            connection.connect()

            return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                response.toString()
            } else {
                "Server error"
            }

        } finally {
            connection.disconnect()
        }
    }

}