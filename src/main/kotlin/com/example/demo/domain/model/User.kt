package com.example.demo.domain.model

import com.example.demo.domain.enum.RoleType

class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)
