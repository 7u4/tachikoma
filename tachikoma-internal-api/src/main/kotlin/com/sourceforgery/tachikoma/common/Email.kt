package com.sourceforgery.tachikoma.common

import com.sourceforgery.tachikoma.identifiers.MailDomain

class Email {
    constructor(domain: MailDomain, localPart: String) {
        this.domain = domain
        this.localPart = localPart
        this.address = "$localPart@$domain"
    }

    constructor(address: String) {
        this.address = address
        domain = MailDomain(address.substringAfter('@'))
        localPart = address.substringBefore('@')
    }

    val address: String
    val domain: MailDomain
    val localPart: String

    // Don't change this. Used for string templates
    override fun toString() = address

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Email) return false

        return address == other.address
    }

    override fun hashCode() = address.hashCode()
}