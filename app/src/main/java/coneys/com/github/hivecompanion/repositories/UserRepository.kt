package coneys.com.github.hivecompanion.repositories

import android.content.SharedPreferences
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.domain.User


const val USERNAME = "USERNAME"
const val USEREMAIL = "USEREMAIL"
const val TOKEN = "TOKEN"
const val PASSWORD = "PASSWORD"
const val ID = "ID"


class UserRepository(private val sharedPreferences: SharedPreferences) {


    fun saveUser(validUser: User) {
        sharedPreferences.edit()
                .putString(USERNAME, validUser.username)
                .putString(USEREMAIL, validUser.email)
                .putString(PASSWORD, validUser.password)
                .putString(ID, validUser.id)
                .apply()
    }

    fun getCurrentUser(): User? {
        val userName = sharedPreferences.getString(USERNAME, "")
        val password = sharedPreferences.getString(PASSWORD, "")
        val token = sharedPreferences.getString(TOKEN, "")
        val email = sharedPreferences.getString(USEREMAIL, "")
        val id = sharedPreferences.getString(ID, "")

        return if (userName.isNotEmpty() && password.isNotEmpty()
                && token.isNotEmpty() && email.isNotEmpty()) {
            User(email, password, userName, id)
        } else
            null
    }

    fun saveToken(token: String) {
        sharedPreferences.edit()
                .putString(TOKEN, token).apply()
        App.token = token
    }

    fun getToken() = sharedPreferences.getString(TOKEN, "")

    fun clearUser() {
        sharedPreferences.edit().remove(USERNAME).remove(USEREMAIL).remove(PASSWORD).remove(ID).remove(TOKEN).apply()
    }


}