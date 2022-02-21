package com.example.app_convidados.service.constants

class GuestConstants private constructor(){

    companion object {
        const val GUEST_ID = "guest_id"
    }

    object FILTER {
        const val EMPTY = 0
        const val PRESENT = 1
        const val ABSENT = 2
    }

}