package com.sourceforgery.tachikoma.incoming

import com.sourceforgery.tachikoma.logging.logger
import com.sourceforgery.tachikoma.mta.MailAcceptanceResult
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer
import java.net.Socket
import java.nio.charset.StandardCharsets

class LMTPServer(
        private val socket: Socket,
        private val param: (String, String, String) -> MailAcceptanceResult
) : Runnable {

    override fun run() {
        try {
            socket.use { lmtpSocket ->
                val output = OutputStreamWriter(lmtpSocket.getOutputStream(), StandardCharsets.US_ASCII)
                val input = BufferedReader(InputStreamReader(lmtpSocket.getInputStream(), StandardCharsets.US_ASCII))

                output.sendLine("220 localhost LMTP Tachikoma")

                input.assertRegex("LHLO (.*)")
                output.sendLine("250-localhost")
                output.sendLine("250-SIZE 10240000")
                output.sendLine("250 8BITMIME")

                val from = input
                        .assertRegex("MAIL FROM: *(.*)")
                        .groupValues[1]
                        .substringBeforeLast(' ')
                output.sendLine("250 OK")

                val to = input
                        .assertRegex("RCPT TO: *(.*)")
                        .groupValues[1]
                output.sendLine("250 OK")

                input.assertRegex("DATA")
                output.sendLine("354 End data with <CR><LF>.<CR><LF>")
                val data = input
                        .lineSequence()
                        .takeWhile {
                            it != "."
                        }
                        .joinToString(separator = "\r\n")
                val acceptanceResult = param(from, data, to)
                if (acceptanceResult.acceptanceStatus == MailAcceptanceResult.AcceptanceStatus.REJECTED) {
                    output.sendLine("550 nobody here with that email")
                } else {
                    output.sendLine("250 email queued")
                }

                input.assertRegex("QUIT")
                output.sendLine("221 Closing connection")
            }
        } catch (e: Exception) {
            LOGGER.error("", e)
        }
    }

    companion object {
        private val LOGGER = logger()
    }
}

private fun BufferedReader.assertRegex(regex: String): MatchResult {
    val line = readLine()
    return Regex(regex).matchEntire(line)
            ?: throw RuntimeException("$line didn't match $regex")
}

private fun Writer.sendLine(line: String) {
    write(line)
    write("\r\n")
    flush()
}