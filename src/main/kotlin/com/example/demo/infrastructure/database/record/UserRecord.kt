/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.infrastructure.database.record

import com.example.demo.domain.enum.RoleType

data class UserRecord(
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var roleType: RoleType? = null
)