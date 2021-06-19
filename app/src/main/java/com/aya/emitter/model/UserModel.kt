package com.aya.emitter.model

import androidx.annotation.Keep

@Keep
data class UserModel (

    var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var address: AddressModel = AddressModel(),
    var phone: String = "",
    var website: String = "",
    var company: CompanyModel = CompanyModel()


)