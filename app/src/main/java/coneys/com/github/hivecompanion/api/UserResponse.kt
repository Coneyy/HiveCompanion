package coneys.com.github.hivecompanion.api

import coneys.com.github.hivecompanion.domain.User

sealed class UserResponse(val user: User)
class UserSuccessResponse(user: User, val token: String)
    : UserResponse(user)
class UserUnsuccessResponse(user: User, val errorCode: Int,val errorMessage:String)
    : UserResponse(user)
class UserFailureResponse(user: User)
    : UserResponse(user)
class LoadingUserResponse(user: User)
    : UserResponse(user)
