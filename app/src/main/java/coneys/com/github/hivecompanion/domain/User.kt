package coneys.com.github.hivecompanion.domain

data class User(val email: String, var password: String,
                val username: String, val id:String?=null)