package com.aya.emitter.model

class AddressModel (

    var street: String = "",
    var suite: String = "",
    var city: String = "",
    var zipcode: String = "",
    var geo: GeoModel  = GeoModel()

)