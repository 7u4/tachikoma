package com.sourceforgery.tachikoma.database.dao

import com.sourceforgery.tachikoma.DatabaseBinder
import com.sourceforgery.tachikoma.TestBinder
import com.sourceforgery.tachikoma.common.AuthenticationRole
import com.sourceforgery.tachikoma.common.Email
import com.sourceforgery.tachikoma.database.objects.AccountDBO
import com.sourceforgery.tachikoma.database.objects.AuthenticationDBO
import com.sourceforgery.tachikoma.database.objects.IncomingEmailAddressDBO
import com.sourceforgery.tachikoma.database.objects.id
import com.sourceforgery.tachikoma.hk2.get
import com.sourceforgery.tachikoma.identifiers.MailDomain
import io.ebean.EbeanServer
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import org.apache.commons.lang3.RandomStringUtils
import org.glassfish.hk2.api.ServiceLocator
import org.glassfish.hk2.utilities.ServiceLocatorUtilities
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class IncomingEmailAddressDAOSpec : Spek({
    lateinit var serviceLocator: ServiceLocator
    lateinit var incomingEmailAddressDAO: IncomingEmailAddressDAO
    lateinit var ebeanServer: EbeanServer
    beforeEachTest {
        serviceLocator = ServiceLocatorUtilities.bind(RandomStringUtils.randomAlphanumeric(10), TestBinder(), DatabaseBinder())
        incomingEmailAddressDAO = serviceLocator.get()
        ebeanServer = serviceLocator.get()
    }

    afterEachTest {
        serviceLocator.shutdown()
    }

    fun createAuthentication(domain: String): AuthenticationDBO {
        val accountDBO = AccountDBO(MailDomain(domain))
        ebeanServer.save(accountDBO)

        val authenticationDBO = AuthenticationDBO(
            login = domain,
            encryptedPassword = UUID.randomUUID().toString(),
            apiToken = UUID.randomUUID().toString(),
            role = AuthenticationRole.BACKEND,
            account = accountDBO
        )
        ebeanServer.save(authenticationDBO)

        return authenticationDBO
    }

    fun saveIncomingEmailAddress(authenticationDBO: AuthenticationDBO, localPart: String) {
        ebeanServer.save(authenticationDBO)
        val account = ebeanServer.find(AccountDBO::class.java, authenticationDBO.account.id)!!
        val incomingEmailAddressDBO = IncomingEmailAddressDBO(
            account = account,
            localPart = localPart
        )
        incomingEmailAddressDAO.save(incomingEmailAddressDBO)
    }

    describe("IncomingEmailAddressDAO") {

        it("it should be possible to add several incoming email addresses") {

            val authentication1 = createAuthentication("example.org")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "c")
            saveIncomingEmailAddress(authentication1, "")

            val incomingEmailAddresses = incomingEmailAddressDAO.getAll(accountDBO = authentication1.account)

            assertEquals(4, incomingEmailAddresses.size)
        }

        it("it should be possible to add several incoming email addresses on different accounts") {

            val authentication1 = createAuthentication("example.org")
            val authentication2 = createAuthentication("example.net")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "")

            saveIncomingEmailAddress(authentication2, "a")
            saveIncomingEmailAddress(authentication2, "b")
            saveIncomingEmailAddress(authentication2, "c")
            saveIncomingEmailAddress(authentication2, "")

            val incomingEmailAddresses1 = incomingEmailAddressDAO.getAll(accountDBO = authentication1.account)
            val incomingEmailAddresses2 = incomingEmailAddressDAO.getAll(accountDBO = authentication2.account)

            assertEquals(3, incomingEmailAddresses1.size)
            assertEquals(4, incomingEmailAddresses2.size)
        }

        it("it should not be possible to have duplicate local part") {

            val authentication1 = createAuthentication("example.org")
            val authentication2 = createAuthentication("example.net")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "c")

            saveIncomingEmailAddress(authentication2, "c")

            assertFails {
                saveIncomingEmailAddress(authentication1, "c")
            }
            assertFails {
                saveIncomingEmailAddress(authentication2, "c")
            }
        }

        it("it should return a correct account from en E-mail") {

            val authentication1 = createAuthentication("example.org")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "c")
            saveIncomingEmailAddress(authentication1, "")

            val email = Email(MailDomain("example.org"), "a")
            val incomingEmail = incomingEmailAddressDAO.getByEmail(email)

            assertNotNull(incomingEmail)
            assertEquals(authentication1.account.id, incomingEmail!!.account.id)
            assertEquals("a", incomingEmail.localPart)
        }

        it("it should return a correct account from an E-mail") {

            val authentication1 = createAuthentication("example.org")
            val authentication2 = createAuthentication("example.net")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "")

            saveIncomingEmailAddress(authentication2, "a")
            saveIncomingEmailAddress(authentication2, "b")
            saveIncomingEmailAddress(authentication2, "")

            val email1 = Email(MailDomain("example.org"), "ab")
            val email2 = Email(MailDomain("example.net"), "b")
            val incomingEmail1 = incomingEmailAddressDAO.getByEmail(email1)
            val incomingEmail2 = incomingEmailAddressDAO.getByEmail(email2)

            assertNotNull(incomingEmail1)
            assertNotNull(incomingEmail2)
            assertEquals(authentication1.account.id, incomingEmail1!!.account.id)
            assertEquals(authentication2.account.id, incomingEmail2!!.account.id)
            assertEquals("", incomingEmail1.localPart)
            assertEquals("b", incomingEmail2.localPart)
        }

        it("it should be possible to delete an incoming e-mail") {

            val authentication1 = createAuthentication("example.org")

            saveIncomingEmailAddress(authentication1, "a")
            saveIncomingEmailAddress(authentication1, "b")
            saveIncomingEmailAddress(authentication1, "")

            incomingEmailAddressDAO.delete(authentication1.account, "")

            val email1 = Email(MailDomain("example.org"), "ab")
            val incomingEmail1 = incomingEmailAddressDAO.getByEmail(email1)

            assertNull(incomingEmail1)
        }
    }
})